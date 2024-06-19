package com.library.manage.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.manage.entity.Record;

import java.util.List;

public interface RecordMapper extends BaseMapper<Record> {

    public void insertrecord(Record record);
    public List<Record> findallrecord();
    public List<Record> findallrecordBybookid(long bookid);
    public Long findallreaderidbyrecordid(long recordid);

    public Long findallbookidbyrecordid(long recordid);

    public List<Record> findlastmonthrecords();




}
