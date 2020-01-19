package com.example;

import org.apache.commons.lang.ArrayUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/10/22 20:24
 * @Description:MySQL表自动生成Java实体类(全部表)
 * https://blog.csdn.net/scyxm0426/article/details/80805029
 */
public class GenEntity {
    private static String[] colnames; // 列名数组
    private static String[] colTypes; // 列名类型数组
    private static int[] colSizes; // 列名大小数组
    private static String[] comments;  //列名注释
    private static boolean f_util = false; // 是否需要导入包java.util.*
    private static boolean f_sql = false; // 是否需要导入包java.sql.*

    /*** 数据库连接常量,需优化.每次运行main方法创建连接CPU损耗率高; **/
    private static final String URL = "jdbc:mysql://localhost:3306/activities?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false";
    private static final String NAME = "root";
    private static final String PASS = "159357";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, NAME, PASS);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return conn;
    }

    public static String nowDateString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * 获取数据中所有表名
     * @param packageName
     * @return
     */
    public static List<String> getMysqlTableName(String packageName) {
        List<String> tableNames = new ArrayList<>() ;
        ResultSet rs = null;
        try {
            //获取数据库的元数据
            DatabaseMetaData db = getConnection().getMetaData();
            //从元数据中获取到所有的表名
            rs = db.getTables(null, null, null, new String[] { "TABLE" });
            while(rs.next()) {
                tableNames.add(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tableNames;
    }

    /**
     * 自动生成java类
     */
    public static void autoCreateClass() {
        System.out.println("===========autoCreateClass  start==============");
        String packageName = GenEntity.class.getPackage().getName();
        List<String> tableName= getMysqlTableName(packageName);
        tableName.stream().forEach(str  ->{
            genEntity(packageName , str);
        });
        System.out.println("===========autoCreateClass  end==============");
    }

    /*
     * 数据库表反转自动生成实体类
     * packageName :生成实体类放入指定包中
     * tableName :数据库表名,需要自动生成的表;
     */
    public static void genEntity(String packageName,String tableName) {

        // 查要生成实体类的表
        String sql = "select * from " + tableName;
        try {
            Connection conn = getConnection();
            Statement stmt = (Statement) conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData() ;
            int size = rsmd.getColumnCount(); // 统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            comments = new String[size];
            //数据库表结构sql
            String  showTableSql = "show full columns from " + tableName ;
            //获取表中字段注释
            getFieldComments(stmt , showTableSql);

            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i+1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                if (colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("timestamp")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image")
                        || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            colnames  =  formatColNames(colnames);
            String tempArray[] =  formatColNames(tableName);
            String javaClassName = tempArray[0];
            String content = parse(packageName,javaClassName);

            try {
                File directory = new File("");
                String path = GenEntity.class.getResource("").getPath();

                FileWriter fw = new FileWriter(directory.getAbsolutePath()
                        + "/src/main/java/com/activities/"
//                        + path.substring(path.lastIndexOf("/main/java/com/activities/", path
//                        .length()), path.length())
                        + initcap(javaClassName)
                        + ".java");
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 获取列名字段注释
     */
    private static void getFieldComments(Statement stat , String sql) {
        try {
            ResultSet rs = stat.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                comments[i]  = rs.getString("Comment");
                i ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将数据库字段变为java中驼峰格式(user_name变为userName)
     * @param dbKeys
     * @return
     */
    private static String[] formatColNames(String... dbKeys) {
        String[] newArray = new String[dbKeys.length];
        if(ArrayUtils.isNotEmpty(dbKeys)){
            int i = 0;
            for(String key : dbKeys){
                String[] words = key.split("_");
                String result = toUppercase4FirstLetter(words);
                newArray[i] = result;
                i ++;
            }
        }
        return newArray;
    }

    private static String toUppercase4FirstLetter(String... words){
        StringBuilder buffer = new StringBuilder();
        if(words != null && words.length > 0){
            for(int i=0;i<words.length;i++){
                String word = words[i];
                String firstLetter = word.substring(0, 1);
                String others = word.substring(1);
                String upperLetter = null;
                if(i != 0){
                    upperLetter = firstLetter.toUpperCase();
                } else {
                    upperLetter = firstLetter;
                }
                buffer.append(upperLetter).append(others);
            }
            return buffer.toString();
        }
        return "";
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @param packageName
     *            实体类生成后放入指定包中;
     * @param tablename
     *            需要生成实体类的表名;
     * @param colnames
     *            字段名称
     * @param colTypes
     *            字段类型
     * @param colSizes
     *            字段大小
     * @return
     */
    private static String parse(String packageName, String tableName) {
        StringBuilder sb = new StringBuilder();
        //包
        sb.append("package " + packageName + ";\r\n");
        sb.append("\r\n");
        // 判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }

        // 注释部分
        sb.append("   /**\r\n");
        sb.append("    * " + tableName + " 实体类\r\n");
        sb.append("    * " + nowDateString() + "  zijixiangba \r\n");
        sb.append("    */ \r\n");
        // 实体部分
        sb.append("\r\n\r\npublic class " + initcap(tableName) + "{\r\n");
        processAllAttrs(sb);// 属性
        processAllMethod(sb);// get set方法
        sb.append("}\r\n");
        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private static void processAllAttrs(StringBuilder sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
                    + colnames[i] + ";" + "\t//" + comments[i]  + "\r\n");
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private static void processAllMethod(StringBuilder sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "("
                    + sqlType2JavaType(colTypes[i]) + " " + colnames[i]
                    + "){\r\n");
            sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
                    + initcap(colnames[i]) + "(){\r\n");
            sb.append("\t\treturn " + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
        }

    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型,转换成java实体类中类型;
     * @param sqlType
     * @return
     */
    private static String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")
                || sqlType.equalsIgnoreCase("integer")
                || sqlType.equalsIgnoreCase("integer unsigned")
        ) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal")
                || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real")
                || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar")
                || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar")
                || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")
                || sqlType.equalsIgnoreCase("timestamp")
                || sqlType.equalsIgnoreCase("time")
                || sqlType.equalsIgnoreCase("date")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    /**
     * 出口 TODO
     *
     * @param args
     */
    public static void main(String[] args) {
        autoCreateClass();
    }
}