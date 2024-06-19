package com.library.manage.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.manage.dao.BookMapper;
import com.library.manage.dao.PunishMapper;
import com.library.manage.dao.ReaderMapper;
import com.library.manage.dao.RecordMapper;
import com.library.manage.entity.Book;
import com.library.manage.entity.Reader;
import com.library.manage.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class Dashbord {

    @Autowired
    private ReaderMapper readerMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private PunishMapper punishMapper;

    private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private LocalDate localDate=LocalDate.now();


    public List<String> generateDateRange() {
        // 计算前一个月的日期
        LocalDate startDate = localDate.minusMonths(1);


        List<String> dateList = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(localDate); date = date.plusDays(1)) {

            dateList.add(date.format(formatter));
        }

        return dateList;
    }


    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("readerCount", readerMapper.selectCount(null));
        model.addAttribute("bookCount", bookMapper.selectCount(null));
        model.addAttribute("recordCount",recordMapper.selectCount(null));
        model.addAttribute("puninshCount",punishMapper.selectCount(null));
        return "dashbord";
    }
        //借阅次数排名
    @ResponseBody
    @PostMapping("/book_ranking")
    public Map<String, Object> bookRanking(){
        Map<String, Object> resp = new HashMap<>();

        List<String> categories=new ArrayList<>();
        List<Long>  values=new ArrayList<>();
        long top=10;
        List<Book> booklsit=bookMapper.getTopBooklist(top);
        for(Book book:booklsit) {
            categories.add(book.getTitle());
            values.add(book.getBorrowCount());
        }
        resp.put("categories",categories);
        resp.put("values",values);
        resp.put("status",true);

        return resp;
    }

    //借阅违规比例
    @ResponseBody
    @PostMapping("/record_overdue_rate")
    public Map<String, Object> record_overdue_rate(){
        Map<String, Object> resp = new HashMap<>();

        QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("is_overdue",1);

        long overdu_count=recordMapper.selectCount(queryWrapper);

        QueryWrapper<Record> queryWrapper1=new QueryWrapper<>();
        queryWrapper.eq("is_overdue",0);
        queryWrapper.eq("is_return",1);
        long ontime_count=recordMapper.selectCount(queryWrapper1);
         List<Map<String, Object>> data=new ArrayList<>();
         Map<String, Object> map=new HashMap<>();

         map.put("name","超时违规");
         map.put("y",overdu_count);
         data.add(map);

         map=new HashMap<>();
         map.put("name","按时归还");
         map.put("y",ontime_count);
         data.add(map);
         resp.put("data",data);
        resp.put("status",true);

        return resp;
    }

    @ResponseBody
    @PostMapping("/reader_rank")
    public Map<String, Object>  reader_rank(){
        Map<String, Object> resp=new HashMap<>();
        QueryWrapper<Reader> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("borrow_count");
        queryWrapper.last("limit 20");    //数量限制在20
        List<Reader> readerList=readerMapper.selectList(queryWrapper);
        resp.put("data",readerList);
        resp.put("status",true);
        return resp;
    }

    @ResponseBody
    @PostMapping("/borrow_trend")
    public Map<String, Object>  borrow_trend(){
        Map<String, Object> resp=new HashMap<>();

        List<String> categories=generateDateRange();
//        System.out.println(categories);

        List<Long> values=new ArrayList<>();

//        List<Record> records=recordMapper.selectList(null);
        List<Record> records=recordMapper.findlastmonthrecords();

        for(String date:categories) {
            long count=0;
            for(Record record:records) {
                String cratetime=dateFormat.format(record.getCreateTime());
                if(date.equals(cratetime)){
                    count++;
                }
            }
            values.add(count);
        }

//        System.out.print(values);
        resp.put("categories",categories);
        resp.put("values",values);
        resp.put("status",true);

        return resp;
    }



}
