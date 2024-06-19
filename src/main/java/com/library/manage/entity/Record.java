package com.library.manage.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import com.library.manage.entity.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
//import java.sql.Date;

@Entity
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=false)
    @TableField(value = "book_id")
    private Book book;


    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=false)
    @TableField(value= "reader_id")
    private Reader reader;


    //约定归还日期
    @TableField(value = "return_date")
    private String returnDate;

    //实际归还日期
    @TableField(value = "actual_return_date")
    private Date actualReturnDate;

    //是否逾期
    @TableField(value = "is_overdue")
    private boolean isOverdue;

  
    //是否归还
    @TableField(value = "is_return")
    private  boolean isReturn;


    @TableField(value = "create_time")
    private Date createTime;


//    @TableField(exist = false)
//    private long count;
//
//
//    public void setCount(long count) {
//        this.count = count;
//    }
//
//    public long getCount() {
//        return count;
//    }

    public void setId(long id) {
        this.id = id;
    }



    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setActualReturnDate(Date actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Date getActualReturnDate() {
        return actualReturnDate;
    }

    public String getReturnDate() {
        return returnDate;
    }



    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public boolean isReturn() {
        return isReturn;
    }

    public void setReturn(boolean aReturn) {
        isReturn = aReturn;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }
}
