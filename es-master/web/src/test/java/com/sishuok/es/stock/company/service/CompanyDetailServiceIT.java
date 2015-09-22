package com.sishuok.es.stock.company.service;

import com.sishuok.es.stock.company.entity.CompanyDetail;
import com.sishuok.es.stock.company.task.CompanyDetailTask;
import com.sishuok.es.tool.util.SheetContent;
import com.sishuok.es.tool.util.WorkBookReader;
import com.sishuok.es.test.BaseIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;

/**
 * <p>User: jaylee
 * <p>Date: 2015-04-30 下午11:21
 * <p>Version: 1.0
 */

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class CompanyDetailServiceIT extends BaseIT {

    @Autowired
    private CompanyDetailService companyDetailService;

    private CompanyDetail companyDetail;

    @Test
    public void testSave() {
        companyDetailService.deleteAll();
        ArrayList<SheetContent> sheetContents = new ArrayList<SheetContent>();
        String configFile = "/home/jaylee/config.xls";
        try {
            sheetContents = WorkBookReader.ReadExcelFile(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String dataDate = "20150430";

        String stockCodes[][] = sheetContents.get(0).getSheetContent();
        for (int i = 0; i < stockCodes.length; i++) {
            String stockCode = stockCodes[i][0];
            String url = "http://stockpage.10jqka.com.cn/" + stockCode + "/";
            CompanyDetail companyDetail = CompanyDetailTask.getCompanyDetail(stockCode, dataDate, url);
            if (companyDetail != null) {
                System.out.println("Save:" + stockCode + "-" + companyDetail.getGsmc());
                companyDetailService.save(companyDetail);
            }
        }
    }
}
