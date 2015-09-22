package com.sishuok.es.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-18 上午10:05
 * <p>Version: 1.0
 */
public class TestOthers {

    @Test
    public void testOther() {
        String afterStr = "a.美国和英国";
        String[] answers = afterStr.split("\\.");
        System.out.println(answers[0]);
    }

    @Test
    public void testJsoup() {
        String url = "http://localhost/test/1.html";
        int index = 0;
        Document doc = null;

        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0").ignoreContentType(true).timeout(30000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 公司简介
        String companyDetails = "tr.STYLE03 div";
        String companyDetails2 = "tr.STYLE8 B";
        if (doc != null) {
            Elements elem = doc.select(companyDetails);
            Elements elem2 = doc.select(companyDetails2);
            for (int i = 0, j = 0; i < elem.size(); i = i + 2, j++) {
                long id = index * 100 + j;
                Elements prevs = elem.get(i).select("br");
                Elements afters = elem.get(i).select("input");
                Elements contentP = elem.get(i).select("p");
                String typeHtml = elem2.get(j).html();
                System.out.println("=======================================" + typeHtml + "============================================");
                try {
                    String content = "";
                    //如果div中包含p标签，则取p的内容，否则取<br>前面的文字内容
                    if (contentP.size() > 0) {
                        for (Element p : contentP) {
                            content += p.html().trim() + "\r\n";
                        }
                        int lastIndex = content.indexOf("br");
                        if(lastIndex>1) {
                            content = content.substring(0, lastIndex-1);
                        }
                    } else {
                        Node nodePre = prevs.get(0).previousSibling();
                        content = nodePre.toString().trim();
                    }

                    System.out.println("题目内容:" + content);//题目内容
                    String remark = elem.get(i).select("font").text();
                    System.out.println("备注:" + remark);//备注
                    String type = elem2.get(j).html();
                    if (!typeHtml.contains("判断题")) {
                        for (Element a : afters) {
                            String afterStr = a.nextSibling().toString().trim();
                            if (afterStr.contains(".")) {
                                String[] answers = afterStr.split("\\.");
                                String serial_no = answers[0];
                                String answerContent = answers[1];
                            } else {
                                //判断题无选项
                            }
                            System.out.println("选项:" + afterStr);//选项
                        }
                    }
                    String key = prevs.last().nextSibling().toString().trim();
                    System.out.println("正确答案:" + key);//正确答案


                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("====================================================================================\r\n");

            }
        }
    }
}
