package com.example.springbootminio.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MinIoConfig.java
 * @Description TODO
 * @createTime 2022年03月12日 14:01:00
 */
@Configuration
public class MinIoConfig {

    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 注入minio 客户端
     *
     * @return
     */
    @Bean
    public MinioClient minioClient() {

        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
