package com.example.springbootminio.controller;

import com.example.springbootminio.entity.MinIoUploadVo;
import com.example.springbootminio.entity.R;
import com.example.springbootminio.service.MinioService;
import io.minio.messages.DeleteObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName UploadController.java
 * @Description 文件上传处理
 * @createTime 2022年03月12日 14:03:00
 */
@RestController
@Slf4j
public class UploadController {

    @Autowired
    private MinioService minioService;

    /**
     * 文件下载
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public R upload(MultipartFile file, HttpServletRequest request) throws IOException {
        String strDir = request.getParameter("bucketName") == null ? "car" : request.getParameter("bucketName");

        try {
            MinIoUploadVo uploadVo = minioService.upload(strDir, file);
            return R.ok().message("文件上传成功").data(uploadVo);
        } catch (Exception e) {
            log.error("上传文件失败，msg={}", e.getMessage());
            e.printStackTrace();
            return R.error();
        }
    }

    /**
     * 文件下载公共组件
     *
     * @param minIoUploadVo
     * @param response
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(MinIoUploadVo minIoUploadVo, HttpServletResponse response) {

        try {
            minioService.download(response, minIoUploadVo.getBucketName(), minIoUploadVo.getMinFileName());
        } catch (Exception e) {
            log.error("下载文件失败，msg={}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 文件服务器文件删除（支持多个）
     *
     * @param minIoUploadVo
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public R delete(MinIoUploadVo minIoUploadVo) {

        try {
            List<DeleteObject> objectList = new ArrayList<>();

            String fileDir = minIoUploadVo.getBucketName().concat("/");
            Arrays.stream(minIoUploadVo.getMinFileName().split(",")).forEach(name -> objectList.add(new DeleteObject(fileDir.concat(name))));

            minioService.removeObject("car", objectList);

            return R.ok().message("删除成功");
        } catch (Exception e) {
            log.error("删除文件失败，msg={}", e.getMessage());
            e.printStackTrace();
            return R.error();
        }
    }

}