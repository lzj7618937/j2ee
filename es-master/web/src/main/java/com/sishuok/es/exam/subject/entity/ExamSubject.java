package com.sishuok.es.exam.subject.entity;

import com.sishuok.es.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * <p>User: jaylee
 * <p>Date: 2015-05-16 下午10:06
 * <p>Version: 1.0
 */
@Entity
@Table(name = "exam_subject")
public class ExamSubject extends BaseEntity<Long> {

    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    @Column(name = "remark")
    private String remark;

    @Column(name = "answer")
    private String answer;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
