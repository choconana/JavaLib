package com.example.springbootminio.service;

import com.example.springbootminio.entity.MinIoUploadVo;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MinioService.java
 * @Description TODO
 * @createTime 2022年03月12日 14:26:00
 */
@Service
@Slf4j
public class MinioService {

    @Resource
    private MinioClient instance;

    @Value("${minio.endpoint}")
    private String endpoint;

    private static final String SEPARATOR_DOT = ".";

    private static final String SEPARATOR_ACROSS = "-";

    private static final String SEPARATOR_STR = "";

    /**
     * @Description 判断 bucket是否存在
     */
    public boolean bucketExists(String bucketName) {
        try {
            return instance.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 创建 bucket
     *
     * @param bucketName
     */
    public void makeBucket(String bucketName) {
        try {
            boolean isExist = instance.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                instance.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param bucketName
     */
    public boolean removeBucket(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException, ServerException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                // 有对象文件，则删除失败
                if (item.size() > 0) {
                    return false;
                }
            }
            // 删除存储桶，注意，只有存储桶为空时才能删除成功。
            instance.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
            flag = bucketExists(bucketName);
            if (!flag) {
                return true;
            }
        }
        return false;
    }

    public List<String> listBucketNames() throws IOException, InvalidResponseException, InvalidKeyException, NoSuchAlgorithmException, ServerException, ErrorResponseException, XmlParserException, InsufficientDataException, InternalException {
        List<Bucket> bucketList = listBuckets();
        List<String> bucketListName = new ArrayList<>();
        for (Bucket bucket : bucketList) {
            bucketListName.add(bucket.name());
        }
        return bucketListName;
    }

    /**
     * 列出所有存储桶
     */
    public List<Bucket> listBuckets() throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        return instance.listBuckets();
    }

    /**
     * 列出存储桶中的所有对象名称
     */
    public List<String> listObjectNames(String bucketName) throws InvalidKeyException, ErrorResponseException,
            IllegalArgumentException, InsufficientDataException, InternalException,
            InvalidResponseException, NoSuchAlgorithmException, XmlParserException, IOException, ServerException {
        List<String> listObjectNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<Item>> myObjects = listObjects(bucketName);
            for (Result<Item> result : myObjects) {
                Item item = result.get();
                listObjectNames.add(item.objectName());
            }
        }
        return listObjectNames;
    }

    /**
     * 列出存储桶中的所有对象
     */
    public Iterable<Result<Item>> listObjects(String bucketName) {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            return instance.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        }
        return null;
    }


    /**
     * 通过文件地址，保留原始文件名 文件上传
     */
    public MinIoUploadVo upload(String bucketName, String objectName, InputStream stream) throws Exception {
        if (!this.bucketExists(bucketName)) {
            this.makeBucket(bucketName);
        }
        instance.putObject(
                PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                        stream, stream.available(), -1) // PutObjectOptions，上传配置(文件大小，内存中文件分片大小)
                        .build());
        String url = instance.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket(bucketName)
                        .object(objectName)
                        .expiry(60 * 60 * 24)
                        .build());
        // 返回生成文件名、访问路径
        return new MinIoUploadVo(bucketName, objectName, "", url);
    }

    /**
     * 文件上传 （自定义文件名称）
     */
    public MinIoUploadVo upload(String strDir, MultipartFile multipartFile) throws Exception {

        // bucket 不存在，创建
        if (!this.bucketExists(strDir)) {
            this.makeBucket(strDir);
        }
        InputStream inputStream = multipartFile.getInputStream();
        // 创建一个 headers
        Map<String, String> headers = new HashMap<>();
        // 添加请求头 文件的ContentType 动态配置 multipartFile.getContentType()
        headers.put("Content-Type", "application/octet-stream");

        String fileName = multipartFile.getOriginalFilename();

        String minFileName = minFileName(fileName);
        instance.putObject(
                PutObjectArgs.builder().bucket(strDir).object(minFileName).stream(
                        inputStream, inputStream.available(), -1) // PutObjectOptions，上传配置(文件大小，内存中文件分片大小)
                        .headers(headers)
                        .build());
        String url = endpoint.concat("/").concat(strDir).concat("/").concat(minFileName);
        // 返回生成文件名、访问路径
        return new MinIoUploadVo(strDir, fileName, minFileName, url);
    }

    /**
     * 下载文件
     */
    public void download(HttpServletResponse response, String bucketName, String minFileName) throws Exception {

        InputStream fileInputStream =
                instance.getObject(
                        GetObjectArgs.builder().object(bucketName.concat("/").concat(minFileName)).build());
        response.setHeader("Content-Disposition", "attachment;filename=" + minFileName);
        response.setContentType("application/force-download");
        response.setCharacterEncoding("UTF-8");
        IOUtils.copy(fileInputStream, response.getOutputStream());
    }

    /**
     * 删除一个文件
     */
    public boolean removeObject(String bucketName, String objectName) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        boolean flag = bucketExists(bucketName);
        if (flag) {
            instance.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
            return true;
        }
        return false;
    }

    /**
     * 删除指定桶的多个文件对象, 返回删除错误的对象列表，全部删除成功，返回空列
     */
    public List<String> removeObject(String bucketName, List<DeleteObject> objects) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {
        List<String> deleteErrorNames = new ArrayList<>();
        boolean flag = bucketExists(bucketName);
        if (flag) {
            Iterable<Result<DeleteError>> results = instance.removeObjects(
                    RemoveObjectsArgs.builder().bucket(bucketName).objects(objects).build());
            for (Result<DeleteError> result : results) {
                DeleteError error = result.get();
                deleteErrorNames.add(error.objectName());
            }
        }
        return deleteErrorNames;
    }


    /**
     * 生成上传文件名
     */
    private String minFileName(String originalFileName) {
        String suffix = originalFileName;
        if (originalFileName.contains(SEPARATOR_DOT)) {
            suffix = originalFileName.substring(originalFileName.lastIndexOf(SEPARATOR_DOT));
        }
        return UUID.randomUUID().toString().replace(SEPARATOR_ACROSS, SEPARATOR_STR).toUpperCase() + suffix;
    }
}
