package com.library.manage.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.library.manage.dao.BookMapper;
import com.library.manage.dao.PunishMapper;
import com.library.manage.dao.ReaderMapper;
import com.library.manage.dao.RecordMapper;
import com.library.manage.entity.Book;
import com.library.manage.entity.Punish;
import com.library.manage.entity.Reader;
import com.library.manage.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PunishManage {

    @Autowired
    private PunishMapper punishMapper;

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private BookMapper bookMapper;


    @GetMapping("/punish")
    public String InitPunish(Model model,
                         @RequestParam(value = "record_id",required = false) String record_id){


        if(record_id!=null){
            long record_id1=Long.parseLong(record_id);
            QueryWrapper<Punish> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("record_id",record_id1);
            Punish punish=punishMapper.selectOne(queryWrapper);

            Record record=recordMapper.selectById(record_id1);

            long book_id=recordMapper.findallbookidbyrecordid(record_id1);
            long reader_id=recordMapper.findallreaderidbyrecordid(record_id1);

            Book book=bookMapper.selectById(book_id);
            Reader reader=readerMapper.selectById(reader_id);
            record.setBook(book);
            record.setReader(reader);
            punish.setRecord(record);
            System.out.println(punish);
            model.addAttribute("punish_data",punish);

        }
        return "punish";
    }

    @ResponseBody
    @PostMapping("/punish_search")
    public Map<String, Object> punish_search( @RequestParam(value = "record_id",required = false) String record_id) {
        Map<String, Object> response = new HashMap<>();
        if(record_id != null){
            long record_id1=Long.parseLong(record_id);
            QueryWrapper<Punish> queryWrapper=new QueryWrapper<>();
//            System.out.println(record_id1);
            queryWrapper.eq("record_id",record_id1);

            List<Punish> punishList=punishMapper.selectList(queryWrapper);

            if(punishList.size()>0){
                response.put("status",true);
            }
            else {
                response.put("status",false);
            }

        }

        return response;

    }


    @ResponseBody
    @PostMapping("/punish_submit")
    public Map<String, Object> punish_submit(
            @RequestParam(value = "money",required = false)  String money,
            @RequestParam(value = "reason",required = false)  String reason,
            @RequestParam(value = "record_id",required = false)  String record_id
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            double money1 = Double.parseDouble(money);
            long record_id1 = Long.parseLong(record_id);

            Punish punish = new Punish();
            punish.setMoney(money1);
            punish.setReason(reason);
            Record record = new Record();
            record.setId(record_id1);
            punish.setRecord(record);
            punish.setCreateTime(new Date());
            punishMapper.insertPuninsh(punish);

            response.put("status", true);
        }
        catch (Exception e) {
            e.printStackTrace();
            response.put("status", false);
        }
        return response;

    }
}
