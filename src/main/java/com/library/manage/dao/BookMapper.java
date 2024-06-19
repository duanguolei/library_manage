package com.library.manage.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.manage.entity.Book;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {

    public void inertBook(Book book);

    public List<Book> getTopBooklist(long n);

    public void  updatebook(Book book);
}
