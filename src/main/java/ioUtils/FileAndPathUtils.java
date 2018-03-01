package ioUtils;

/**
 * Created by lch on 2018/3/1.
 */
public class FileAndPathUtils {
    public static String getExtenName(String fileFullname) {
        return fileFullname.substring(fileFullname.lastIndexOf(".") + 1, fileFullname.length());
    }

    public static boolean excelFileNameStartWithErrorFormat(String fileFullname){
        return !fileFullname.contains("~$");
    }

    public static  boolean isExcelFile(String fileFullname){
        return (FileAndPathUtils.getExtenName(fileFullname).equals("xls") || FileAndPathUtils.getExtenName(fileFullname).equals("xlsx")) && excelFileNameStartWithErrorFormat(fileFullname);
    }
}
