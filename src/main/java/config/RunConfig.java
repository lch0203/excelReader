package config;

import utils.ExcelSheetEntity;

import java.util.List;

/**
 * Created by lch on 2018/3/1.
 */
public class RunConfig {
    private String scanPath;
    private List<ExcelSheetEntity> sheetEntities;

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public List<ExcelSheetEntity> getSheetEntities() {
        return sheetEntities;
    }

    public void setSheetEntities(List<ExcelSheetEntity> sheetEntities) {
        this.sheetEntities = sheetEntities;
    }
}
