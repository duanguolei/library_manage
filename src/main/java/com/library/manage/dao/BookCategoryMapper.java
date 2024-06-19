package com.library.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.manage.control.Category;
import com.library.manage.entity.BookCategory;

import java.util.List;


public interface BookCategoryMapper extends BaseMapper<BookCategory> {


    public void insertChirdCategory(BookCategory bookCategory);
    public Long getParentId(BookCategory bookCategory);

    public List<Long> getchirdIds(long parentId);

    public  String getCategoryName(long bookId);

    public BookCategory getCategotyBYBookid(long bookId);





}
