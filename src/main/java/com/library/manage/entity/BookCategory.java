package com.library.manage.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BookCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name; // 大类

    @OneToOne(cascade = CascadeType.ALL)
    @TableField(value = "parent_category_id")
    private BookCategory parentCategory;



//
//    public void setparentCategoryId(long id){
//        this.parentCategory.setId(id);
//    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BookCategory getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(BookCategory parentCategory) {
        this.parentCategory = parentCategory;
    }

    public void setCategory(String category) {


    }
}