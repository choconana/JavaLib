package com.example.springdemo.filter;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 陳樂
 * @version 1.0.0
 * @ClassName MyImportSelector.java
 * @Description 批量导入组件
 * @createTime 2022年05月01日 17:56:00
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.example.springdemo.entity.Teacher",
                "com.example.springdemo.entity.Student"
        };
    }
}