package com.example.springbootfluentmybatisdemo.config;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import org.junit.jupiter.api.Test;

public class EntityGeneratorDemo {
    // 数据源 url
    static final String url = "jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf8";
    // 数据库用户名
    static final String username = "root";
    // 数据库密码
    static final String password = "123456";

    public static void main(String[] args) {
        FileGenerator.build(Empty.class);
    }

    @Test
    public void generate() throws Exception {
        FileGenerator.build(Empty.class);
    }

    @Tables(
            /** 数据库连接信息 **/
            url = url, username = "root", password = "123456",
            /** Entity类parent package路径 **/
            basePack = "com.example.springbootfluentmybatisdemo",
            /** Entity代码源目录 **/
            srcDir = "src/main/java",
            /** Dao代码源目录 **/
            daoDir = "src/main/java",
            /** 如果表定义记录创建，记录修改，逻辑删除字段 **/
            gmtCreated = "gmt_create", gmtModified = "gmt_modified", logicDeleted = "is_deleted",
            /** 需要生成文件的表 **/
            tables = @Table(value = {"user_info"})
    )
    static class Empty { //类名随便取, 只是配置定义的一个载体

    }
}