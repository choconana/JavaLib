package generalpractice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @User: Dell
 * @Author: hezhidong
 * @Date: 2020/8/19 10:37
 */

public class ReadCsv {
    /*
     * author:命运的信徒 date:2019-1-15 arm:java读取.csv文件里面的所有内容
     */
    public static void main(String[] args) {
        // 1. .csv文件的路径。注意只有一个\的要改成\\
        File csv = new File(
                "http://120.92.86.192:5060/extract/getEnzymeCSV"); // CSV文件路径
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null) // 读取到的内容给line变量
            {
                everyLine = line;
                System.out.println(everyLine);
                allString.add(everyLine);
            }
            System.out.println("csv表格中所有行数：" + allString.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}