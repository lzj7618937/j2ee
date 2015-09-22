package com.sishuok.es.exam.subject.task;

import com.sishuok.es.test.BaseIT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-18 上午11:08
 * <p>Version: 1.0
 */
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ExamSubjectTaskIT extends BaseIT {

    @Autowired
    private ExamSubjectTask examSubjectTask;

    @Test
    public void testExamSubjectTask() {
        for (int i = 0; i < 23; i++) {
            String url = "http://localhost/test/" + (i + 1) + ".html";
            examSubjectTask.saveData(url, i);
        }
    }
}
