package config;

import excelUtils.ExcelSheetEntity;

import java.util.List;

/**
 * Created by lch on 2018/3/1.
 */
public class RunConfig {
    private String scanPath; //扫描路径
    private String dbName;
    private String tableName;
    private boolean isDeepScan; //是否深度扫描
    private boolean cleanData;//是否清空数据
    private List<ExcelSheetEntity> sheetEntities;

    public boolean isCleanData() {
        return cleanData;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public boolean isDeepScan() {
        return isDeepScan;
    }

    public void setDeepScan(boolean deepScan) {
        isDeepScan = deepScan;
    }

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
