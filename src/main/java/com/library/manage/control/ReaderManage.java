package com.library.manage.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.library.manage.dao.PunishMapper;
import com.library.manage.dao.ReaderMapper;
import com.library.manage.dao.RecordMapper;
import com.library.manage.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class ReaderManage {


    @Autowired
    private PunishMapper punishMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private ReaderMapper readerMapper;

    @GetMapping("/reader")
    public String Readerinit(){

        return "managreader";
    }

    @ResponseBody
    @PostMapping("/reader")
    public Map<String,Object> ReaderList(@RequestParam(value="keyword",required = false) String keyword){
        Map<String, Object> response =new HashMap<>();
        System.out.println(keyword);
        if(keyword!=null){
            QueryWrapper<Reader> queryWrapper=new QueryWrapper<>();
            queryWrapper.like("name",keyword);
            queryWrapper.or().like("student_id",keyword);
            queryWrapper.or().like("phone",keyword);
            queryWrapper.or().like("card_id",keyword);
            List<Reader> readerList = readerMapper.selectList(queryWrapper);
            if(readerList.size()==0){
                response.put("status",false);

            }else {
            response.put("status", true);}
            Collections.reverse(readerList);
            response.put("data", readerList);
        }
        else {
            List<Reader> readerList = readerMapper.selectList(null);
            Collections.reverse(readerList);
            response.put("status", true);
            response.put("data", readerList);
        }

        return response;
    }

    @ResponseBody
    @PostMapping("/reader_add")
    public Map<String,Object> Readeradd(HttpServletRequest request){
        Map<String, Object> response = new HashMap<>();
        try {
            String studentId = request.getParameter("studentId");
            String cardId = request.getParameter("cardId");
            String name = request.getParameter("name");
            String college = request.getParameter("college");
            String major = request.getParameter("major");
            String grade = request.getParameter("grade");
            String phone = request.getParameter("phone");
            boolean graduation = request.getParameter("graduation") != null;

            Reader reader=readerMapper.selectOne(Wrappers.<Reader>query().eq("student_id",studentId));

            if(reader!=null) {
                response.put("status",false);
                response.put("msg","该学号已存在");
                return response;
            }

            Reader reader2=readerMapper.selectOne(Wrappers.<Reader>query().eq("card_id",cardId));

            if(reader2!=null) {
                response.put("status",false);
                response.put("msg","身份ID已存在");
                return response;
            }
            Reader reader3=readerMapper.selectOne(Wrappers.<Reader>query().eq("phone",phone));

            if(reader3!=null) {
                response.put("status",false);
                response.put("msg","电话号码已存在");
                return response;
            }

            Reader reader_add=new Reader();
            reader_add.setCardId(cardId);
            reader_add.setStudentId(studentId);
            reader_add.setName(name);
            reader_add.setCollege(college);
            reader_add.setMajor(major);
            reader_add.setGrade(grade);
            reader_add.setPhone(phone);
            reader_add.setGraduation(graduation);
            reader_add.setValid(true);
            reader_add.setBorrowCount(0);
            reader_add.setCreateTime(new Date());
//            System.out.print(reader_add);
            readerMapper.insert(reader_add);
            response.put("status",true);


        }
        catch (Exception e){
            e.printStackTrace();
            response.put("status",false);
        }
        return response;

    }


    @ResponseBody
    @PostMapping("/reader_active")
    public Map<String, Object> activeBook(@RequestParam(value="id",required = false) String readerid){
        Map<String, Object> response=new HashMap<>();

        if(readerid!=null) {
            long readerid1 = Long.parseLong(readerid);


            Reader reader2 = readerMapper.selectById(readerid1);

            if (reader2.isValid()) {
                reader2.setValid(false);
                readerMapper.updateById(reader2);
            }
            else {
                reader2.setValid(true);
                readerMapper.updateById(reader2);
            }
        }
        response.put("status",true);
        return response;
    }


    @PostMapping("/reader_delete")
    @ResponseBody
    public Map<String, Object> deleteBook(@RequestParam(value="id",required = false) String readerid) {
        Map<String, Object> response = new HashMap<>();
        try {


            if (readerid != null) {


                QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
                queryWrapper.eq("reader_id",readerid);
                List<Record> recordList = recordMapper.selectList(queryWrapper);

                recordList.forEach(record -> {
                    QueryWrapper<Punish>  queryWrapper1=new QueryWrapper<>();
                    queryWrapper1.eq("record_id",record.getId());
                    punishMapper.delete(queryWrapper1);

                });

                recordMapper.delete(queryWrapper);
                long readerid1 = Long.parseLong(readerid);
                readerMapper.deleteById(readerid1);
            }
            response.put("status",true);}
        catch (Exception e){
            response.put("status",false);
        }
        return  response;

    }


    @PostMapping("/reader_update")
    @ResponseBody
    public Map<String, Object> updateBook(@RequestParam(value="id",required = false) String readerid,HttpServletRequest request){
        Map<String, Object> response = new HashMap<>();
        if(readerid!=null){
            long readerid1 = Long.parseLong(readerid);
            Reader reader=readerMapper.selectById(readerid1);

        try {
            String studentId = request.getParameter("studentId");
            String cardId = request.getParameter("cardId");
            String name = request.getParameter("name");
            String college = request.getParameter("college");
            String major = request.getParameter("major");
            String grade = request.getParameter("grade");
            String phone = request.getParameter("phone");
            boolean graduation = request.getParameter("graduation") != null;

            Reader reader1=readerMapper.selectOne(Wrappers.<Reader>query().eq("student_id",studentId));
            if(reader1!=null&&reader1.getId()!=readerid1 ) {
                response.put("status",false);
                response.put("msg","该学号已存在");
                return response;
            }

            Reader reader2=readerMapper.selectOne(Wrappers.<Reader>query().eq("card_id",cardId));

            if(reader2!=null&&reader1.getId()!=readerid1) {
                response.put("status",false);
                response.put("msg","身份ID已存在");
                return response;
            }
            Reader reader3=readerMapper.selectOne(Wrappers.<Reader>query().eq("phone",phone));

            if(reader3!=null&&reader1.getId()!=readerid1) {
                response.put("status",false);
                response.put("msg","电话号码已存在");
                return response;
            }
            reader.setStudentId(studentId);
            reader.setName(name);
            if(college!=""){
            reader.setCollege(college);}
            reader.setMajor(major);
            reader.setGrade(grade);
            reader.setPhone(phone);



            reader.setGraduation(graduation);
//            System.out.println(reader);
            readerMapper.updateById(reader);

//            System.out.print(reader_add);
//            readerMapper.insert(reader);
            response.put("status",true);


        }
        catch (Exception e){
            e.printStackTrace();
            response.put("status",false);
        }}

        return response;

    }

}
