package com.library.manage.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Reader {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String studentId;

    private String cardId;

    private String name;

    private String college;

    //专业
    private String major;

    //年级
    private String grade;

    //电话

    private String phone;

    //毕业
    private boolean graduation;

    //有效
    private boolean  valid;



    @TableField("borrow_count")
    private long borrowCount;

    public long getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(long borrowCount) {
        this.borrowCount = borrowCount;
    }

    @TableField("create_time")
    private Date createTime;

    public String getStudentId() {
        return studentId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setGraduation(boolean graduation) {
        this.graduation = graduation;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isGraduation() {
        return graduation;
    }

    public boolean isValid() {
        return valid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
