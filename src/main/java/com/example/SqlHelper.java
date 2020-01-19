package com.example;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: gaoxi
 * @CreateDate: 2019/10/22 20:40
 * @Description:mysql 自动生产java 实体类脚本
 * https://blog.csdn.net/fst438060684/article/details/84339898
 */
@Slf4j
public class SqlHelper {
    //基本数据配置
    private String packageOutPath = "com.activities";// 指定实体生成所在包的路径
    private String authorName = "gxy";// 作者名字
    private String tablename = "act_period_ambry";// 表名
    private String[] colnames; // 列名数组
    private static String[] comments;  //列名注释,gxy自定义添加
    private static String[] tableComments;  //表名注释,gxy自定义添加
    private String[] colTypes; // 列名类型数组
    private String version = "V0.01"; // 版本
    private int[] colSizes; // 列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*
    private boolean f_lang = false; // 是否需要导入包java.lang.*
    private boolean f_io = false; // 是否需要导入包java.io.Serializable
    private String defaultPath = "/src/main/java/";
    // 数据库连接
    private static final String URL = "jdbc:mysql://101.254.136.114:13306/zdd_api?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&useSSL=false";
    private static final String NAME = "root";
    private static final String PASS = "Xlttidb@2019!";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    /*
     * 构造函数
     * tablename需要生成的数据库表名
     */
    public SqlHelper(String tablename) {
        this.tablename = tablename;
        // 创建连接
        Connection con;
        // 查要生成实体类的表
        String sql = "select * from " + tablename;
        PreparedStatement pStemt = null;
        try {
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(URL, NAME, PASS);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount(); // 统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            comments = new String[size];//自定义添加
            tableComments = new String[1];//自定义添加
            for (int i = 0; i < size; i++) {

                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);
                //自动生成包配置


                 if (colTypes[i].equalsIgnoreCase("datetime")) {
                 f_util = true;
                 }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
//                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")
//                        || colTypes[i].equalsIgnoreCase("datetime") || colTypes[i].equalsIgnoreCase("time")
//                        || colTypes[i].equalsIgnoreCase("date") || colTypes[i].equalsIgnoreCase("datetime2")) {
//                    f_sql = true;
//                }
                // if (colTypes[i].equalsIgnoreCase("int")) {
                // f_lang = true;
                // }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
            //gxy自定义添加 start
            //数据库表结构sql
            String  showTableSql = "show full columns from " + tablename ;
            Statement stmt = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            //获取表中字段注释
            getFieldComments(stmt , showTableSql);

            //数据库表上的注释 方案一 ： https://blog.csdn.net/sinat_25712187/article/details/78971933
//            String showTableAnnotation = "SHOW CREATE TABLE " + tablename ;
            //数据库表上的注释 方案二
            String showTableAnnotation = "SELECT TABLE_NAME,TABLE_COMMENT FROM information_schema.TABLES WHERE table_name = '" + tablename +"'";
            //数据库表上的注释 方案三
//            String showTableAnnotation = "show table status WHERE Name = '" + tablename +"'";
            getFieldTableComments(stmt , showTableAnnotation);

            //gxy自定义添加 end
            String content = parse(tableComments,colnames, colTypes, colSizes);

            try {
                File directory = new File("");

                String path = this.getClass().getResource("").getPath();

                System.out.println(path);

                String outputPath = directory.getAbsolutePath() + this.defaultPath
                        + this.packageOutPath.replace(".", "/") + "/" + initcap(underlineToHump(tablename)) + ".java";
                System.out.println("执行完毕，生成路径为："+outputPath);
                FileWriter fw = new FileWriter(outputPath);
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
     * 功能：生成实体类主体代码
     *
     * @param colnames
     * @param colTypes
     * @param colSizes
     * @return
     */
    private String parse(String[] tableComments,String[] colnames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();
        // 生成package包路径
        sb.append("package " + this.packageOutPath + ";\r\n");
        // 判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        if (f_lang) {
            sb.append("import java.lang.*;\r\n");
        }
        if (f_io) {
            sb.append("import java.io.Serializable;\r\n");
        }
        sb.append("import lombok.Data;\r\n");

        sb.append("\r\n");
        // 注释部分
        sb.append("/**\r\n");
        sb.append(" * @文件名称：" + initcap(underlineToHump(this.tablename)) + ".java\r\n");
        sb.append(" * @创建时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r\n");
        sb.append(" * @创  建  人：" + this.authorName + " \r\n");
        if (tableComments != null && tableComments.length > 0){
            sb.append(" * @文件描述：" + tableComments[0] +"实体类\r\n");
        }else {
            sb.append(" * @文件描述：" + tablename +" 实体类\r\n");
        }
        sb.append(" * @文件版本：" + this.version + " \r\n");
        sb.append(" */");
        sb.append("\r@Data");
        // 实体部分
        sb.append("\npublic class " + initcap(underlineToHump(tablename)));
        if (f_io) {
            sb.append(" implements Serializable ");
        }
        sb.append( "{\r\n");
        processAllAttrs(sb);// 属性
//        processAllMethod(sb);// get set方法
        sb.append("}\r\n");

        return sb.toString();
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
     * 获取表上的注释
     */
    private static void getFieldTableComments(Statement stat , String sql) {
        try {
            ResultSet rs = stat.executeQuery(sql);
            int i = 0;
            //方案一
//            while (rs.next()) {
//                String createDDL = rs.getString(2);
//                String comment = parse(createDDL);
//                tableComments[i]  = comment;
//                i ++;
//            }

            if (rs != null && rs.next()) {
                //方案二
                tableComments[i]  = rs.getString("TABLE_COMMENT");
                //方案三
//                tableComments[i]  = rs.getString("Comment");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            if (StringUtils.isNotBlank(comments[i])){
                sb.append("\t/**\r\n");
                sb.append("\t * " + comments[i] + "\r\n");
                sb.append("\t */\r\n");
            }
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + this.underlineToHump(colnames[i]) + ";" + "\r\n");//gxy自定义添加
//            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + this.underlineToHump(colnames[i]) + ";"+ "\t//" + comments[i]  + "\r\n");//gxy自定义添加
        }

    }

    /**
     * 功能：生成所有方法
     * 如果使用lombok注解方式，则不需要生成get set方法
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " "
                    + colnames[i] + "){\r\n");
            sb.append("\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
            sb.append("\t}\r\n");
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
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
    private String initcap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("int identity")) {
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("image") || sqlType.equalsIgnoreCase("varbinary(max)")
                || sqlType.equalsIgnoreCase("varbinary") || sqlType.equalsIgnoreCase("udt")
                || sqlType.equalsIgnoreCase("binary")) {
            return "Byte[]";
        } else if (sqlType.equalsIgnoreCase("nchar") || sqlType.equalsIgnoreCase("nvarchar(max)")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nvarchar(ntext)")
                || sqlType.equalsIgnoreCase("uniqueidentifier") || sqlType.equalsIgnoreCase("xml")
                || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("varchar(max)")
                || sqlType.equalsIgnoreCase("text") || sqlType.equalsIgnoreCase("varchar")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("real")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("smallint") || sqlType.equalsIgnoreCase("tinyint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("date") || sqlType.equalsIgnoreCase("datetime")
                || sqlType.equalsIgnoreCase("time") || sqlType.equalsIgnoreCase("datetime2")
                || sqlType.equalsIgnoreCase("timestamp") )  {
            return "Date";
        } else {
            System.out.println("数据类型异常，类型为：" + sqlType);
        }

        return null;
    }
    /**
     * 下划线转驼峰
     *
     * @String para
     */
    private  String underlineToHump(String para){
        StringBuilder result=new StringBuilder();
        String a[]=para.split("_");
        for(String s:a){
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else{
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /**
     * 返回注释信息
     * @param all
     * @return
     */
    public static String parse(String all) {

        String comment = null;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return "";
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }

    /**
     * 出口 TODO
     *
     * @param args
     */
    public static void main(String[] args) {
        log.debug("------------" + System.currentTimeMillis());
        new SqlHelper("system_setting");
        System.out.println(System.currentTimeMillis());
        log.debug("------------" + System.currentTimeMillis());

    }
}