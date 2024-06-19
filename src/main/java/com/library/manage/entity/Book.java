package com.library.manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import javax.swing.*;
import java.util.Date;


@Entity
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval=false)
    @TableField(value = "category_id")
    private BookCategory category;

    private String isbn;

    //封面地址
    private String cover;

    private String title;

    private String author;

    private String description;

    private String publisher;

    private double price;

    @TableField(value ="publish_date")
    private String publishDate;

    private boolean status; // 状态（借出、在馆）

    //是某注销
    private boolean  isDelete;


    //借阅次数
    @TableField("borrow_count")
    private long borrowCount;

    @TableField("create_time")
    private Date createTime;

    public void setBorrowCount(long borrowCount) {
        this.borrowCount = borrowCount;
    }

    public long getBorrowCount() {
        return borrowCount;
    }

    public boolean isStatus() {
        return status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
