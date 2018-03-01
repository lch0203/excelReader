package dbUtils;

/**
 * Created by lch on 2018/2/22.
 */

import java.sql.*;
import java.util.List;

public class DatabaseUtil {
    public static boolean getDatabase(String dbName,
                                      String tableName,
                                      String createTableSql,
                                      boolean isCleanData,
                                      List<String> insertSqls) {
        boolean flag = true;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println("no sqlite connector");
            return false;
        }
        // 得到连接 会在你所填写的目录建一个你命名的文件数据库
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db", null, null);
            // 设置自动提交为false
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();

            //判断表是否存在
            ResultSet rsTables = conn.getMetaData().getTables(null, null, tableName, null);
            if (rsTables.next()) {
                if (isCleanData) {
                    System.out.println("表存在,正在清除之前的数据...");
                    stmt.executeUpdate("DELETE FROM " + tableName);
                    System.out.println("数据清除成功...");
                } else {
                    System.out.println("不清除之前数据，继续添加新的数据...");
                }
            } else {
                stmt.executeUpdate(createTableSql);
               // System.out.println("数据添加成功....");
            }
            System.out.println("开始添加新数据....");
            for (String sql : insertSqls) {
                stmt.executeUpdate(sql);
            }
            // 提交
            conn.commit();
//            // 得到结果集
//            ResultSet rs = stmt.executeQuery("select * from student;");
//            while (rs.next()) {
//                System.out.println("id = " + rs.getString("id"));
//                System.out.println("name = " + rs.getString("name"));
//            }
//            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL异常!");
            return false;
        }
        return true;
    }
}
