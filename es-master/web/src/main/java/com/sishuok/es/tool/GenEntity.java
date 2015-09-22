package com.sishuok.es.tool;

import java.sql.*;
import java.util.Date;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-20 上午9:39
 * <p>Version: 1.0
 */
public class GenEntity {
    private String packageOutPath;//指定实体生成所在包的路径
    private String authorName;//作者名字
    private String tableName;
    private String[] colNames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组
    private boolean f_util = false; // 是否需要导入包java.util.*
    private boolean f_sql = false; // 是否需要导入包java.sql.*


    public GenEntity(String packageOutPath, String authorName, String jdbcDriver, String dbUrl, String dbUsername, String dbPassword, String tablename) {
        this.packageOutPath = packageOutPath;
        this.authorName = authorName;
        this.tableName = tablename;

        //创建连接
        Connection con;
        //查要生成实体类的表
        String sql = "select * from " + tablename;
        PreparedStatement pStemt = null;
        try {
            try {
                Class.forName(jdbcDriver);
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();
            int size = rsmd.getColumnCount();   //统计列
            colNames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colNames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("datetime")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
                    f_sql = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @return
     */
    public String parse() {
        StringBuffer sb = new StringBuffer();

        //包说明部分
        sb.append("package " + this.packageOutPath + ".entity;\r\n");
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
        sb.append("* " + tableName + " 实体类 \r\n");
        sb.append("* <p>User: " + authorName + " \r\n");
        sb.append("* <p>Date: " + new Date() + " \r\n");
        sb.append("* <p>Version: 1.0 \r\n");
        sb.append("*/ \r\n");

        //注解部分
        sb.append("\r\n@Entity \r\n");
        sb.append("@Table(name = \"" + GenUtil.initcap(tableName) + "\") \r\n");

        //实体部分
        sb.append("public class " + GenUtil.initcap(tableName) + " extends BaseEntity<Long>{\r\n");

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

        for (int i = 0; i < colNames.length; i++) {
            if (!colNames[i].equals("id")) {
                sb.append("\r\n\t@Column(name = \"" + colNames[i] + "\")\r\n");
                sb.append("\tprivate " + sqlType2JavaType(colTypes[i], colSizes[i]) + " " + GenUtil.init_Cap(colNames[i]) + ";\r\n");
            }
        }

    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private void processAllMethod(StringBuffer sb) {

        for (int i = 0; i < colNames.length; i++) {
            if (!colNames[i].equals("id")) {

                //set方法
                sb.append("\r\n");
                sb.append("\tpublic void set" + GenUtil.initcap(colNames[i]) + "(" + sqlType2JavaType(colTypes[i], colSizes[i]) + " " +
                        GenUtil.init_Cap(colNames[i]) + "){\r\n");
                sb.append("\t\tthis." + GenUtil.init_Cap(colNames[i]) + " = " + GenUtil.init_Cap(colNames[i]) + ";\r\n");
                sb.append("\t}\r\n");

                //get方法
                sb.append("\r\n");
                sb.append("\tpublic " + sqlType2JavaType(colTypes[i], colSizes[i]) + " get" + GenUtil.initcap(colNames[i]) + "(){\r\n");
                sb.append("\t\treturn " + GenUtil.init_Cap(colNames[i]) + ";\r\n");
                sb.append("\t}\r\n");
            }
        }

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
}
