package com.sishuok.es.tool;

import com.sishuok.es.tool.util.ConfigHelp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-20 上午9:08
 * <p>Version: 1.0
 */
public class GenCode {
    private String packageOutPath;//指定实体生成所在包的路径
    private String authorName;//作者名字
    private String jdbcDriver;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String outPath;

    private String[] colnames; // 列名数组
    private String[] colTypes; //列名类型数组
    private int[] colSizes; //列名大小数组

    public GenCode() {
        this.jdbcDriver = ConfigHelp.getValue("jdbcDriver");
        this.dbUrl = ConfigHelp.getValue("dbUrl");
        this.dbUsername = ConfigHelp.getValue("dbUsername");
        this.dbPassword = ConfigHelp.getValue("dbPassword");
        this.outPath = ConfigHelp.getValue("outPath");
        this.authorName = ConfigHelp.getValue("authorName");
        this.packageOutPath = ConfigHelp.getValue("packageOutPath");
    }

    public void genAllCode(String tableName) {
        genEntity(tableName);
        genRepository(tableName);
        genService(tableName);
    }

    public void genEntity(String tableName) {
        GenEntity genEntity = new GenEntity(packageOutPath, authorName, jdbcDriver, dbUrl, dbUsername, dbPassword, tableName);
        String content = genEntity.parse();
        String fileName = outPath + "entity/" + GenUtil.initcap(tableName) + ".java ";
        writeFile(fileName, content);
    }

    public void genService(String tableName) {
        String fileName = outPath + "service/" + GenUtil.initcap(tableName) + "Service.java ";
        String content = getServiceCode(tableName, this.packageOutPath, this.authorName);
        writeFile(fileName, content);
    }

    public void genRepository(String tableName) {
        String fileName = outPath + "repository/" + GenUtil.initcap(tableName) + "Repository.java ";
        String content = getRepositoryCode(tableName, this.packageOutPath, this.authorName);
        writeFile(fileName, content);
    }

    /**
     * 把content内容写入文件path中
     *
     * @param filePath
     * @param content
     */
    private void writeFile(String filePath, String content) {
        try {
            File f = new File(filePath);
            if (f.exists())
                f.delete();
            FileWriter fw = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getServiceCode(String tableName, String packageOutPath, String authorName) {
        StringBuffer sb = new StringBuffer();

        //包说明部分
        sb.append("package " + packageOutPath + ".service;\r\n");
        sb.append("\r\n");

        //引入包说明部分
        sb.append("import com.sishuok.es.common.service.BaseService;\r\n\r\n");
        sb.append("import " + packageOutPath + ".entity." + GenUtil.initcap(tableName) + ";\r\n\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n\r\n");

        //注释部分
        sb.append("/**\r\n");
        sb.append("* " + tableName + " 服务类 \r\n");
        sb.append("* <p>User: " + authorName + " \r\n");
        sb.append("* <p>Date: " + new Date() + " \r\n");
        sb.append("* <p>Version: 1.0 \r\n");
        sb.append("*/ \r\n");

        //代码部分
        sb.append("@Service\r\n");
        sb.append("public class " + GenUtil.initcap(tableName) + "Service extends BaseService<" + GenUtil.initcap(tableName) + ", Long> {\r\n");
        sb.append("}\r\n");

        return sb.toString();
    }

    private String getRepositoryCode(String tableName, String packageOutPath, String authorName) {
        StringBuffer sb = new StringBuffer();

        //包说明部分
        sb.append("package " + packageOutPath + ".repository;\r\n");
        sb.append("\r\n");

        //引入包说明部分
        sb.append("import com.sishuok.es.common.repository.BaseRepository;\r\n\r\n");
        sb.append("import " + packageOutPath + ".entity." + GenUtil.initcap(tableName) + ";\r\n\r\n");

        //注释部分
        sb.append("/**\r\n");
        sb.append("* " + tableName + " 数据操作类 \r\n");
        sb.append("* <p>User: " + authorName + " \r\n");
        sb.append("* <p>Date: " + new Date() + " \r\n");
        sb.append("* <p>Version: 1.0 \r\n");
        sb.append("*/ \r\n");

        //代码部分
        sb.append("public interface " + GenUtil.initcap(tableName) + "Repository extends BaseRepository<" + GenUtil.initcap(tableName) + ", Long>{\r\n");
        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * 出口
     * TODO
     *
     * @param args
     */
    public static void main(String[] args) {
        String tableName =  ConfigHelp.getValue("tablename");
        GenCode genCode = new GenCode();
        genCode.genAllCode(tableName);
    }

}
