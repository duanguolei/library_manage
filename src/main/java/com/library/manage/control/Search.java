package com.library.manage.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.library.manage.dao.BookMapper;
import com.library.manage.dao.ReaderMapper;
import com.library.manage.dao.RecordMapper;
import com.library.manage.entity.Book;
import com.library.manage.entity.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Search {
    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private BookMapper bookMapper;


    @ResponseBody
    @PostMapping("/search")
    public Map<String ,Object> searchBYkeyword(@RequestParam(value = "keyword",required = false) String keyword){


        Map<String, Object> resp=new HashMap<>();
        if(keyword==null||keyword.equals("")){
            return resp;
        }
        QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("isbn",keyword);
        queryWrapper.or().like("author",keyword);
        queryWrapper.or().like("publisher",keyword);
        queryWrapper.or().like("title",keyword);
        queryWrapper.or().like("description",keyword);
        long count=bookMapper.selectCount(queryWrapper);
        if(count>0){
            resp.put("status",true);
            String bookurl="/book?keyword="+keyword;
            resp.put("data",bookurl);
            return resp;
        }

        QueryWrapper<Reader> queryWrapper1=new QueryWrapper<>();
        queryWrapper.like("name",keyword);
        queryWrapper.or().like("student_id",keyword);
        queryWrapper.or().like("phone",keyword);
        queryWrapper.or().like("card_id",keyword);
        long count1=readerMapper.selectCount(queryWrapper1);
        if(count1>0){
            resp.put("status",true);
            String readerurl="/reader?keyword="+keyword;
            resp.put("data",readerurl);
            return resp;
        }
        resp.put("staus",false);
        return resp;
    }
}
