package ioUtils;

import config.RunConfig;
import dbUtils.SqlManger;
import excelUtils.ExcelCellEntity;
import excelUtils.ExcelSheetEntity;
import excelUtils.ExcelUtil;
import jsonUtils.ConfigJsonTrans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lch on 2018/3/1.
 */
public class PathScanWithExcelFile {

    private static RunConfig runConfig = new ConfigJsonTrans(TxtFileReader.readTxtFile("config.txt")).getRunConfigObj();
    private List<List<String>> allData = new ArrayList<List<String>>();
    public void start() throws Exception {
        System.out.println(new ConfigJsonTrans(runConfig).getConfigJsonString());
        scanPath(runConfig.getScanPath());
        System.out.println("文件扫描结束......");
        System.out.println("开始将数据插入到数据库中....");
        SqlManger sm = new SqlManger();
        sm.runInsertDb(allData);
        System.out.println("插入数据结束....");
    }

    private void scanPath(String filePath) throws Exception {
        File file = new File(filePath);
        if (file.isFile()) {
            throw new Exception("这里需要的是一个目录，不是一个文件.....");
        }
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File item : files) {
                    String fileFullName = item.getAbsolutePath();
                    if (item.isDirectory() && runConfig.isDeepScan()) {
                        System.out.println("深度遍历文件夹:" + fileFullName);
                        scanPath(fileFullName);
                    } else {
                        if (FileAndPathUtils.isExcelFile(fileFullName)) {
                            System.out.println("正在处理文件:" + fileFullName);
                            allData.add(readExcelWithConfig(item));
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    private List<String> readExcelWithConfig(File file) throws Exception {
        List<ExcelSheetEntity> oneRowData = ExcelUtil.readExcelWithCelllist(file.getAbsolutePath(), runConfig.getSheetEntities());
        System.out.println("正在插入数据库操作....");
        //List<List<String>> allData = new ArrayList<List<String>>();
        List<String> rowData = new ArrayList<String>();
        for (ExcelSheetEntity sheetEntity : oneRowData) {
            List<ExcelCellEntity> cellEntities = sheetEntity.getCellList();
            for (ExcelCellEntity cell : cellEntities) {
                rowData.add(cell.getValue());
            }
        }
        return rowData;
    }
}
