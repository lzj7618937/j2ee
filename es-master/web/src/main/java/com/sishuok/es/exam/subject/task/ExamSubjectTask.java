package com.sishuok.es.exam.subject.task;

import com.sishuok.es.exam.subject.entity.ExamAnswer;
import com.sishuok.es.exam.subject.entity.ExamSubject;
import com.sishuok.es.exam.subject.service.ExamAnswerService;
import com.sishuok.es.exam.subject.service.ExamSubjectService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-17 上午12:17
 * <p>Version: 1.0
 */
@Service()
public class ExamSubjectTask {

    @Autowired
    private ExamSubjectService examSubjectService;

    @Autowired
    private ExamAnswerService examAnswerService;

    public void saveData(String url, int index) {
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
                int id = index * 100 + j;
                Elements prevs = elem.get(i).select("br");
                Elements afters = elem.get(i).select("input");
                Elements contentP = elem.get(i).select("p");
                String typeHtml = elem2.get(j).html();
                System.out.println("=======================================" + typeHtml + "============================================");
                try {
                    ExamSubject examSubject1 = new ExamSubject();
                    String content = "";
                    //如果div中包含p标签，则取p的内容，否则取<br>前面的文字内容
                    if (contentP.size() > 0) {
                        for (Element p : contentP) {
                            content += p.html().trim() + "\r\n";
                        }
                        int lastIndex = content.indexOf("br");
                        if (lastIndex > 1) {
                            content = content.substring(0, lastIndex - 1);
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
                            ExamAnswer examAnswer2 = new ExamAnswer();
                            String afterStr = a.nextSibling().toString().trim();
                            if (afterStr.contains(".")) {
                                String[] answers = afterStr.split("\\.");
                                String serial_no = answers[0];
                                String answerContent = answers[1];
                                examAnswer2.setSubjectId((int) id);
                                examAnswer2.setSerialNo(serial_no);
                                examAnswer2.setContent(answerContent);
                                examAnswer2.setFlag(false);
                                examAnswer2.setRemark("xxxxxxx");
                                examAnswerService.save(examAnswer2);
                            } else {
                                //判断题无选项
                            }
                            System.out.println("选项:" + afterStr);//选项
                        }
                    }
                    String key = prevs.last().nextSibling().toString().trim();
                    System.out.println("正确答案:" + key);//正确答案

                    examSubject1.setSubjectId(id);
                    examSubject1.setContent(content);
                    examSubject1.setAnswer(key);
                    examSubject1.setRemark(remark);
                    examSubject1.setType(elem2.get(j).html());
                    examSubject1.setCategory("公共基础");
                    examSubjectService.save(examSubject1);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("====================================================================================\r\n");

            }
        }
    }
}
