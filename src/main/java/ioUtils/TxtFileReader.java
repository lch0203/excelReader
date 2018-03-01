package ioUtils;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/**
 * Created by lch on 2018/3/1.
 */
public class TxtFileReader {
    public static String readTxtFile(String filePath) {
        String result = "";
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    result += lineTxt;
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (FileNotFoundException ffe) {
            System.out.println("找不到指定的文件");
        } catch (IOException ioe) {
            System.out.println("文件读写错误ffff");
        } catch (Exception e) {
            System.out.println("错误");
        }
        return result;
    }
}
