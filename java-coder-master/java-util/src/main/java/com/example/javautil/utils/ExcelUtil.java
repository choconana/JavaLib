package com.example.javautil.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

public class ExcelUtil {

    public static <T> byte[] exportOrder(List<T> tList, Class<T> tClass, String fileName) {
        //获取workBook对象
//        String fileName = "订单明细";
        return exportCommon(null, tList, tClass, fileName);
    }

    private static <T> byte[] exportCommon(ExportParams exportParams, List<T> tList, Class<T> tClass, String fileName) {
        if (null == exportParams) {
            //不设置主题
            exportParams = new ExportParams();
        }
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, tClass, tList);
        //写流
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            workbook.write(os);
            return os.toByteArray();
//            FileUtil.byte2File(os.toByteArray(), ,fileName);
        } catch (Exception e) {
            LoggerClient.error("exportCommon exception:", e);
            return null;
        }
    }

    public static <T> List<T> importExcel(InputStream fileInputStream, Integer titleRows, Integer headerRows, Class<T> pojoClass) {
        if (fileInputStream == null) {
            return null;
        } else {
            ImportParams params = new ImportParams();
            params.setTitleRows(titleRows);
            params.setHeadRows(headerRows);
            List list = null;

            try {
                list = ExcelImportUtil.importExcel(fileInputStream, pojoClass, params);
                return list;
            } catch (NoSuchElementException var7) {
                throw new RuntimeException("excel文件不能为空");
            } catch (Exception var8) {
                throw new RuntimeException(var8.getMessage());
            }
        }
    }
}
