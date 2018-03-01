package dbUtils;

import config.RunConfig;
import excelUtils.ExcelCellEntity;
import excelUtils.ExcelSheetEntity;
import ioUtils.TxtFileReader;
import jsonUtils.ConfigJsonTrans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lch on 2018/3/1.
 */
public class SqlManger {
    //得到配置文件
    private static RunConfig runConfig = new ConfigJsonTrans(TxtFileReader.readTxtFile("config.txt")).getRunConfigObj();
    private List<String> tableColTitles;


    public SqlManger() {
        List<ExcelSheetEntity> sheetEntities = runConfig.getSheetEntities();
        tableColTitles = new ArrayList<String>();
        for (ExcelSheetEntity sheetEntity : sheetEntities) {
            List<ExcelCellEntity> cellEntities = sheetEntity.getCellList();
            for (ExcelCellEntity cellEntity : cellEntities) {
                tableColTitles.add(cellEntity.getFieldName());
            }
        }
        //System.out.println(new ConfigJsonTrans(runConfig).getConfigJsonString());
    }

    private String getCreateTableSql() {
        String table_name = runConfig.getTableName();
        String table_cols = "";
        for (String item : tableColTitles) {
            table_cols += "\t" + item;
            table_cols += " VARCHAR(255),\n";
        }
        table_cols = table_cols.substring(0, table_cols.length() - 2) + "\n";
        String sql = "CREATE TABLE " + table_name + "(\n" +
                "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                table_cols +
                ");";
        return sql;
    }

    private String getInsertSql(List<String> values){
        String table_name = runConfig.getTableName();
        String table_cols = "";
        String value_express = "";
        for (String item : tableColTitles) {
            table_cols +=  item + ",";
        }
        table_cols = table_cols.substring(0, table_cols.length() - 1);
        for(String value:values){
            value_express += "'" + value +"'" + ",";
        }
        value_express = value_express.substring(0,value_express.length()-1);
        String sql = "INSERT INTO tablename(" +
                table_cols +
                ")\n" +
                "VALUES(" +
                value_express +
                ");";
        return sql;
    }

    private List<String> getinsertSqls(List<List<String>> insertSqls){
        List<String> reslut = new ArrayList<String>();
        for (List<String> lists:insertSqls) {
            reslut.add(getInsertSql(lists));
        }
        return reslut;
    }

    public void runInsertDb(List<List<String>> allData){
        DatabaseUtil.getDatabase(runConfig.getDbName(),runConfig.getTableName(),getCreateTableSql(),runConfig.isCleanData(),getinsertSqls(allData));
    }

    public List<String> getTableColTitles() {
        return tableColTitles;
    }
}
