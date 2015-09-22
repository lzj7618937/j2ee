package com.sishuok.es.tool;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

public class GenEntityMysql {

    private String packageOutPath = "com.sishuok.es.tool.entity";//指定实体生成所在包的路径
    private String authorName = "jaylee";//作者名字
    private String tablename = "exam_answer";//表名
    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*
    private String path = "/media/jaylee/App/workspaces/git/es-master/web/src/main/java/com/sishuok/es/tool/";

    //数据库连接
    private static final String URL = "jdbc:mysql://localhost:3306/es";
    private static final String NAME = "root";
    private static final String PASS = "1qaz2wsx";
    private static final String DRIVER = "com.mysql.jdbc.Driver";


    /*
     * 构造函数
     */
    public GenEntityMysql() {
        //创建连接
        Connection con;
        //查要生成实体类的表
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
            int size = rsmd.getColumnCount();   //统计列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = parse(colnames, colTypes, colSizes);

            try {
                String outputPath = path + "entity/" + initcap(tablename) + ".java";
                File f = new File(outputPath);
                if (f.exists())
                    f.delete();
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
    private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
        StringBuffer sb = new StringBuffer();

        //包说明部分
        sb.append("package " + this.packageOutPath + ";\r\n");
        sb.append("\r\n");

        //判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        }
        sb.append("import com.sishuok.es.common.entity.BaseEntity;\r\n\r\n");
        sb.append("import javax.persistence.*;\r\n\r\n");

        //注释部分
        sb.append("/**\r\n");
        sb.append("* " + tablename + " 实体类 \r\n");
        sb.append("* <p>User: " + authorName + " \r\n");
        sb.append("* <p>Date: " + new Date() + " \r\n");
        sb.append("* <p>Version: 1.0 \r\n");
        sb.append("*/ \r\n");

        //注解部分
        sb.append("\r\n@Entity \r\n");
        sb.append("@Table(name = \"" + initcap(tablename) + "\") \r\n");

        //实体部分
        sb.append("public class " + initcap(tablename) + " extends BaseEntity<Long>{\r\n");

        processAllAttrs(sb);//属性
        processAllMethod(sb);//get set方法
        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private void processAllAttrs(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            if (!colnames[i].equals("id")) {
                sb.append("\r\n\t@Column(name = \"" + colnames[i] + "\")\r\n");
                sb.append("\tprivate " + sqlType2JavaType(colTypes[i], colSizes[i]) + " " + init_Cap(colnames[i]) + ";\r\n");
            }
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colnames.length; i++) {
            if (!colnames[i].equals("id")) {

                //set方法
                sb.append("\r\n");
                sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i], colSizes[i]) + " " +
                        init_Cap(colnames[i]) + "){\r\n");
                sb.append("\t\tthis." + init_Cap(colnames[i]) + " = " + init_Cap(colnames[i]) + ";\r\n");
                sb.append("\t}\r\n");

                //get方法
                sb.append("\r\n");
                sb.append("\tpublic " + sqlType2JavaType(colTypes[i], colSizes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
                sb.append("\t\treturn " + init_Cap(colnames[i]) + ";\r\n");
                sb.append("\t}\r\n");
            }
        }

    }

    /**
     * 功能：将输入字符串的首字母,下划线后的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initcap(String str) {
        return init_Cap(initFCap(str));
    }

    /**
     * 功能：将输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private String initFCap(String str) {

        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }

        return new String(ch);
    }

    /**
     * 功能：将输入字符串下划线后的首字母改成大写
     *
     * @param str
     * @return
     */
    private String init_Cap(String str) {
        String result = str;

        char[] ch = str.toCharArray();

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == '_') {
                ch[i + 1] = (char) (ch[i + 1] - 32);
            }
        }

        result = new String(ch);

        result = result.replaceAll("\\_", "");

        return result;
    }

    /**
     * 功能：获得列的数据类型
     *
     * @param sqlType
     * @return
     */
    private String sqlType2JavaType(String sqlType, int sqlSize) {

        if (sqlType.equalsIgnoreCase("bit")) {
            return "boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            if (sqlSize == 1)
                return "boolean";
            else
                return "byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "short";
        } else if (sqlType.equalsIgnoreCase("int")) {
            return "int";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }

        return null;
    }

    /**
     * 出口
     * TODO
     *
     * @param args
     */
    public static void main(String[] args) {

        new GenEntityMysql();

    }

}  