package com.sishuok.es.exam.subject.entity;

import com.sishuok.es.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-16 下午10:06
 * <p>Version: 1.0
 */
@Entity
@Table(name = "exam_answer")
public class ExamAnswer extends BaseEntity<Long> {

    @Column(name = "serial_no")
    private String serialNo;

    @Column(name = "content")
    private String content;

    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "flag")
    private boolean flag;

    @Column(name = "remark")
    private String remark;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
