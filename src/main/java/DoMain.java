/**
 * Created by lch on 2018/2/22.
 */
//import utils.ExcelBaseUtil;

import com.google.gson.Gson;
import utils.ExcelCellEntity;
import utils.ExcelSheetEntity;
import utils.ExcelUtil;
import config.RunConfig;

import java.util.ArrayList;
import java.util.List;

public class DoMain {
    public static void main(String[] args) {
//        File file = new File("test.xls");
        RunConfig rc = new RunConfig();
        List<ExcelSheetEntity> sheetList = new ArrayList<ExcelSheetEntity>();
        List<ExcelCellEntity> cellEntityList = new ArrayList<ExcelCellEntity>();
        ExcelCellEntity ex1 = new ExcelCellEntity(3, 1);
        ExcelCellEntity ex2 = new ExcelCellEntity(4, 1);
        ExcelCellEntity ex3 = new ExcelCellEntity(5, 1);
        ExcelCellEntity ex4 = new ExcelCellEntity(6, 1);
        ExcelCellEntity ex5 = new ExcelCellEntity(5, 8);
        ExcelCellEntity ex6 = new ExcelCellEntity(5, 10);
        ExcelCellEntity ex7 = new ExcelCellEntity(6, 8);
        ExcelCellEntity ex8 = new ExcelCellEntity(6, 10);
        ExcelCellEntity ex9 = new ExcelCellEntity(8, 8);
        ExcelCellEntity ex10 = new ExcelCellEntity(8, 10);

        cellEntityList.add(ex1);
        cellEntityList.add(ex2);
        cellEntityList.add(ex3);
        cellEntityList.add(ex4);
        cellEntityList.add(ex5);
        cellEntityList.add(ex6);
        cellEntityList.add(ex7);
        cellEntityList.add(ex8);
        cellEntityList.add(ex9);
        cellEntityList.add(ex10);
        ExcelSheetEntity excelSheetEntity = new ExcelSheetEntity();
        excelSheetEntity.setSheetName("lisi");
        excelSheetEntity.setCellList(cellEntityList);
        sheetList.add(excelSheetEntity);
        String file = "test.xlsx";
        try {
            List<ExcelSheetEntity> result = ExcelUtil.readExcelWithCelllist(file, sheetList);
            for (ExcelSheetEntity sheetEntity : result) {
                System.out.println(sheetEntity.getSheetName());
                for (ExcelCellEntity cellEntity : sheetEntity.getCellList()) {
                    System.out.println(cellEntity.getRow() + "-" + cellEntity.getCol() + "=" + cellEntity.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ExcelBaseUtil.readExcel(file);
        Gson gson = new Gson();
        System.out.println(gson.toJson(sheetList));


    }
}
