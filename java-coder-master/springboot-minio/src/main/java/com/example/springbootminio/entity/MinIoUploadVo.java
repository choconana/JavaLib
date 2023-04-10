package com.example.springbootminio.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MinIoUploadVo.java
 * @Description TODO
 * @createTime 2022年03月12日 14:32:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinIoUploadVo implements Serializable {

    //对象桶名
    private String bucketName;

    //文件真实名称
    private String fileRealName;

    //文件名称
    private String minFileName;

    //文件路径
    private String minFileUrl;
}
