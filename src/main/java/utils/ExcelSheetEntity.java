package utils;

import java.util.List;

/**
 * Created by lch on 2018/2/28.
 */
public class ExcelSheetEntity {
    private String sheetName;
    private List<ExcelCellEntity> cellList;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<ExcelCellEntity> getCellList() {
        return cellList;
    }

    public void setCellList(List<ExcelCellEntity> cellList) {
        this.cellList = cellList;
    }
}
