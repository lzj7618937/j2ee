package com.sishuok.es.stock.company.task;

import com.sishuok.es.stock.company.entity.CompanyDetail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * <p>User: jaylee
 * <p>Date: 2015-04-30 下午11:21
 * <p>Version: 1.0
 */
public class CompanyDetailTask {
    public static CompanyDetail getCompanyDetail(String stockCode, String dataDate, String url) {
        CompanyDetail companyDetail = new CompanyDetail();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0").ignoreContentType(true).timeout(30000).get();
            // 公司简介
            String companyDetails = "dl.company_details dd";
            String companyDetails2 = "table.gsjj-table td";
            if (doc != null) {
                Elements elem = doc.select(companyDetails);
                Elements elem2 = doc.select(companyDetails2);
                if (elem.size() > 14 && elem2.size() > 7) {
                    String ssrq = elem.get(0).text();//上市日期
                    String zyyw = elem.get(1).text();//主营业务
                    String ywxx = elem.get(2).text().replaceAll("　　", "");//业务详细
                    String zgb = elem.get(3).text();//总股本
                    String ltg = elem.get(4).text();//流通股
                    String yysrzzl = elem.get(5).text();//营业收入增长率
                    String yysr = elem.get(6).text();//营业收入
                    String jlr = elem.get(7).text();//净利润
                    String jlrzzl = elem.get(8).text();//净利润增长率
                    String mgsy = elem.get(9).text();//每股收益
                    String mgjzc = elem.get(10).text();//每股净资产
                    String jzcsyl = elem.get(11).text();//净资产收益率
                    String mgxjl = elem.get(12).text();//每股现金流
                    String mggjj = elem.get(13).text();//每股公积金
                    String mgwfplr = elem.get(14).text();//每股未分配利润
                    String gsmc = elem2.get(1).text();//公司名称
                    String ssdy = elem2.get(3).text();//所属地域
                    String sjgn = elem2.get(5).text();//涉及概念
                    String sshy = elem2.get(7).text();//所属行业

                    companyDetail.setStockCode(stockCode);
                    companyDetail.setSsrq(ssrq);
                    companyDetail.setZyyw(zyyw);
                    companyDetail.setYwxx(ywxx);
                    companyDetail.setZgb(zgb);
                    companyDetail.setLtg(ltg);
                    companyDetail.setYysrzzl(yysrzzl);
                    companyDetail.setYysr(yysr);
                    companyDetail.setJlr(jlr);
                    companyDetail.setJlrzzl(jlrzzl);
                    companyDetail.setMgsy(mgsy);
                    companyDetail.setMgjzc(mgjzc);
                    companyDetail.setJzcsyl(jzcsyl);
                    companyDetail.setMgxjl(mgxjl);
                    companyDetail.setMggjj(mggjj);
                    companyDetail.setMgwfplr(mgwfplr);
                    companyDetail.setGsmc(gsmc);
                    companyDetail.setSsdy(ssdy);
                    companyDetail.setSjgn(sjgn);
                    companyDetail.setSshy(sshy);
                    companyDetail.setDataDate(dataDate);
                } else {
                    return null;
                }
            }
        } catch (
                Exception e
                )

        {
            System.out.println(stockCode + " is null!");
            //e.printStackTrace();kkkk
            //testVIM O
            //testVIM test
            return null;
        }

        return companyDetail;
    }
}
