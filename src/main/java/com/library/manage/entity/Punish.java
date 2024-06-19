package com.library.manage.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import com.library.manage.entity.*;

@Entity
@Data
public class Punish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double money;

    //罚款原因
    private  String reason;

    @TableField("create_time")
    private Date createTime;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=false)
    @TableField(value = "record_id")
    private Record record;


    public void setRecord(Record record) {
        this.record = record;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setMoney(double money) {
        this.money = money;
    }

}
