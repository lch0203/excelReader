package utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
/**
 * Created by lch on 2018/2/22.
 */
public class ExcelUtil {
    public static String getExtenName(String fileFullname) {
        return fileFullname.substring(fileFullname.lastIndexOf(".") + 1, fileFullname.length());
    }

    public static int excelColStrToNum(String colStr) {
        int length = colStr.length();
        int num = 0;
        int result = 0;
        for (int i = 0; i < length; i++) {
            char ch = colStr.charAt(length - i - 1);
            num = (int) (ch - 'A' + 1);
            num *= Math.pow(26, i);
            result += num;
        }
        return result;
    }

    /**
     * Excel column index begin 1
     *
     * @param columnIndex
     * @return
     */
    public static String excelColIndexToStr(int columnIndex) {
        if (columnIndex <= 0) {
            return null;
        }
        String columnStr = "";
        columnIndex--;
        do {
            if (columnStr.length() > 0) {
                columnIndex--;
            }
            columnStr = ((char) (columnIndex % 26 + (int) 'A')) + columnStr;
            columnIndex = (int) ((columnIndex - columnIndex % 26) / 26);
        } while (columnIndex > 0);
        return columnStr;
    }


    /***
     *
     * @param file 文件
     * @param cellList 多个sheet及对应的cell
     * @throws Exception
     */
    public static List<ExcelSheetEntity> readExcelWithCelllist(String file, List<ExcelSheetEntity> cellList) throws Exception {
        String fileType = getExtenName(file);
        InputStream is = null;
        Workbook wb = null;
        try {
            is = new FileInputStream(file);
            if (fileType.equals("xls")) {
                wb = new HSSFWorkbook(is);
            } else if (fileType.equals("xlsx")) {
                wb = new XSSFWorkbook(is);
            } else {
                throw new Exception("读取的不是excel文件");
            }
            for (ExcelSheetEntity entity : cellList) {
                int sheetSize = wb.getNumberOfSheets();
                Sheet sheet = wb.getSheet(entity.getSheetName());
                List<ExcelCellEntity> cellEntityList = entity.getCellList();
                for (ExcelCellEntity cellEntity : cellEntityList) {
                    Row row = sheet.getRow(cellEntity.getRow());
                    Cell cell = row.getCell(cellEntity.getCol());
                    if (cell != null) {
                        cellEntity.setValue(cell.toString());
                        System.out.println("正在读取文件"+file+"中的" + sheet.getSheetName()+"sheet"+(cellEntity.getRow()+1)+"行"+excelColIndexToStr(cellEntity.getCol()+1)+"列的数据");
                    }
                }
            }
            return cellList;

        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

//    public static List<List<List<String>>> readExcelWithoutTitle(String filepath) throws Exception {
//        String fileType = getExtenName(filepath);
//        InputStream is = null;
//        Workbook wb = null;
//        try {
//            is = new FileInputStream(filepath);
//
//            if (fileType.equals("xls")) {
//                wb = new HSSFWorkbook(is);
//            } else if (fileType.equals("xlsx")) {
//                wb = new XSSFWorkbook(is);
//            } else {
//                throw new Exception("读取的不是excel文件");
//            }
//            List<List<List<String>>> result = new ArrayList<List<List<String>>>();//对应excel文件
//
//            int sheetSize = wb.getNumberOfSheets();
//            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
//                Sheet sheet = wb.getSheetAt(i);
//                List<List<String>> sheetList = new ArrayList<List<String>>();//对应sheet页
//
//                int rowSize = sheet.getLastRowNum() + 1;
//                for (int j = 0; j < rowSize; j++) {//遍历行
//                    Row row = sheet.getRow(j);
//                    if (row == null) {//略过空行
//                        continue;
//                    }
//                    int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
//                    List<String> rowList = new ArrayList<String>();//对应一个数据行
//                    for (int k = 0; k < cellSize; k++) {
//                        Cell cell = row.getCell(k);
//                        String value = null;
//                        if (cell != null) {
//                            value = cell.toString();
//                        }
//                        System.out.println(value);
//                        rowList.add(value);
//                    }
//                    sheetList.add(rowList);
//                }
//                result.add(sheetList);
//            }
//
//            return result;
//        } catch (FileNotFoundException e) {
//            throw e;
//        } finally {
////            if (wb != null) {
////
////            }
//            if (is != null) {
//                is.close();
//                System.out.println("is.close()");
//            }
//        }
//    }

}
