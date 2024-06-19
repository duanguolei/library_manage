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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
public class RecordManage {

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private PunishMapper punishMapper;

    private DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    private LocalDate curentDate = LocalDate.now();

    //图书借阅记录
    @GetMapping("/record")
    public String record(){
        return "record";
    }



    //借阅记录数据
    @ResponseBody
    @PostMapping("/record")
    public Map<String,Object> getRecordList(@RequestParam(value = "book_id",required = false) String book_id,
                                      @RequestParam(value = "reader_id",required = false) String reader_id){

        Map<String,Object> response = new HashMap<>();
        if(book_id != null){
            long id1 = Long.parseLong(book_id);
            QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("book_id",id1);
            List<Record> record_list=recordMapper.selectList(queryWrapper);
            processAllRecords(record_list);

            response.put("status",true);
            response.put("data",record_list);

        }
        else if(reader_id != null){
            System.out.println(reader_id);
            long id1 = Long.parseLong(reader_id);
            QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("reader_id",id1);
            List<Record> record_list=recordMapper.selectList(queryWrapper);
            processAllRecords(record_list);
            response.put("status",true);
            response.put("data",record_list);

        }
        else {
            List<Record> record_list=recordMapper.findallrecord();
            processAllRecords(record_list);
            response.put("data",record_list);
        }
        return response;
    }

    public void processAllRecords(List<Record> recordList){
        recordList.forEach(record -> {

            if(!record.isOverdue() && !record.isReturn()){
                LocalDate retrunDate=LocalDate.parse(record.getReturnDate(),formatter);
                long diffdays= ChronoUnit.DAYS.between(curentDate,retrunDate);
                if(diffdays<0){
                    System.out.print(diffdays);
                    record.setOverdue(true);
                    recordMapper.updateById(record);
                }


            }

            long readerid=recordMapper.findallreaderidbyrecordid(record.getId());
            Reader reader=readerMapper.selectById(readerid);
            record.setReader(reader);
            long bookid =recordMapper.findallbookidbyrecordid(record.getId());
            Book book=bookMapper.selectById(bookid);
            record.setBook(book);




        });
        Collections.reverse(recordList);
    }

    //借用图书
    @ResponseBody
    @PostMapping("/book_borrow")
    public Map<String,Object> recordBorrow(@RequestParam(value = "bookid",required = false) String bookid,
                                     @RequestParam(value = "readerid",required = false) String readerid,
                                     @RequestParam(value = "return_date",required = false) String return_date){

        Map<String,Object> response = new HashMap<>();
        if(bookid !=null && readerid != null && return_date != null){
            long bookid1 = Long.parseLong(bookid);
            long readerid1 = Long.parseLong(readerid);
            response.put("status",false);
            Book book=bookMapper.selectById(bookid1);
            if(book.isStatus()){
                if(book.isDelete()){
                    response.put("msg","该书已被删除");
                }
                else {

                    Reader reader=readerMapper.selectById(readerid1);
                    if(reader.isValid()){
                        QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
                        queryWrapper.eq("book_id",bookid1);
                        queryWrapper.eq("reader_id",readerid1);
                        queryWrapper.eq("is_return",false);
                        Integer count=recordMapper.selectList(queryWrapper).size();
                        if(count>5){
                            response.put("msg","该读者已借阅未还超过5本图书，禁止借阅");
                        }
                        else {
                            response.put("status",true);
                            reader.setBorrowCount(reader.getBorrowCount()+1);
                            readerMapper.updateById(reader);
                            Record record=new Record();
                            record.setBook(book);
                            record.setReader(reader);
                            record.setReturn(false);
                            record.setOverdue(false);
                            record.setReturnDate(return_date);
                            record.setCreateTime(new Date()
                            );
                            recordMapper.insertrecord(record);

                            book.setStatus(false);
                            book.setBorrowCount(book.getBorrowCount()+1);
                            bookMapper.updateById(book);

                            response.put("msg","借用成功！！！");
                            return  response;
                        }

                    }
                    else {
                        response.put("msg","该读者无权限");
                    }

                }
            }
            else {
                response.put("msg","该书已被借走");
            }

            response.put("status",true);


        }
        else {
            response.put("status",false);
        }
        return response;
    }

    //还书
    @ResponseBody
    @PostMapping("/book_return")
    public Map<String,Object> recordRetrun(@RequestParam(value = "recordid",required = false) String recordid){
        Map<String, Object> response=new HashMap<>();
        if(recordid != null){
            long recordid1 = Long.parseLong(recordid);
            Record record=recordMapper.selectById(recordid1);
//            Record record=new Record();
//            record.setId(recordid1);
            record.setReturn(true);
            record.setActualReturnDate(new Date());
            recordMapper.updateById(record);
            long bookid=recordMapper.findallbookidbyrecordid(recordid1);
            Book book=bookMapper.selectById(bookid);
            book.setStatus(true);
            bookMapper.updateById(book);
            response.put("status",true);
        }

        return response;
    }


    //删除借阅记录
    @ResponseBody
    @PostMapping("/record_delete")
    public Map<String, Object> reocrdDelete(@RequestParam(value = "record_id",required = false) String recordid){
        Map<String, Object> resp=new HashMap<>();
        resp.put("status",false);
        if(recordid != null){
            long recordid1 = Long.parseLong(recordid);


            QueryWrapper<Punish> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("record_id",recordid1);
            punishMapper.delete(queryWrapper);

//            punishMapper.deletebyrecordId(recordid1);//先删除外键

            recordMapper.deleteById(recordid1);

            resp.put("status",true);


        }

        return resp;
    }

}
