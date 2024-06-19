package com.library.manage.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.manage.dao.BookCategoryMapper;
import com.library.manage.dao.BookMapper;
import com.library.manage.dao.PunishMapper;
import com.library.manage.dao.RecordMapper;
import com.library.manage.entity.Book;
import com.library.manage.entity.BookCategory;
import com.library.manage.entity.Punish;
import com.library.manage.entity.Record;
import com.library.manage.uitls.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToMany;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.time.LocalDate;
import java.util.*;

@Controller
public class BookManage {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookCategoryMapper bookCategoryMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Autowired
    private PunishMapper punishMapper;

    private Utils toolutils;

    //

    @PostMapping("/book_cover")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadBookCover(
            @RequestParam("image") MultipartFile image,
            @RequestParam("bookid") Long bookId
    ) {
        Map<String, Object> response = new HashMap<>();

        try {

            if (bookId == null || bookId <= 0) {
                response.put("status", false);
                response.put("message", "无效的书籍ID");
                return ResponseEntity.badRequest().body(response);
            }

            // 检查文件是否为空
            if (image == null) {
                response.put("status", false);
                response.put("message", "上传的图片为空");
                return ResponseEntity.badRequest().body(response);
            }
            String filename=bookId+".jpg";

            String directory = "D://javaproject/manage/src/main/resources/static/images/"+filename;

            image.transferTo(new File(directory));
            response.put("status", true);
            response.put("message", "上传书籍封面成功");
//            Book book=new Book();
            Book book=bookMapper.selectById(bookId);
//            Book boo1=bookMapper.selectById(bookId);
//            book.setId(bookId);
            book.setCover("images/"+filename);
            bookMapper.updateById(book);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", false);
            response.put("message", "上传书籍封面失败：" + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/book")
    public String managebook(Model model){

        List<BookCategory> bookCategoryList=bookCategoryMapper.selectList(null);



        model.addAttribute("categories",bookCategoryList);


        return "managebook";
    }


    @PostMapping("/book_delete")
    @ResponseBody
    public Map<String, Object> deleteBook(@RequestParam(value="id",required = false) String bookid) {
        Map<String, Object> response = new HashMap<>();
        try {

        if (bookid != null) {



            QueryWrapper<Record> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("book_id",bookid);
            List<Record> recordList = recordMapper.selectList(queryWrapper);


            recordList.forEach(record -> {
                QueryWrapper<Punish>  queryWrapper1=new QueryWrapper<>();
                queryWrapper1.eq("record_id",record.getId());
                punishMapper.delete(queryWrapper1);

            });

            recordMapper.delete(queryWrapper);

            long bookid1 = Long.parseLong(bookid);
            bookMapper.deleteById(bookid1);
        }

        response.put("status",true);}
        catch (Exception e){
            response.put("status",false);
        }
        return  response;

    }


    //插入数据
    @ResponseBody
    @PostMapping("/book_add")
    public   Map<String, Object>  addBook(
    HttpServletRequest request
    ) {
        Map<String, Object> response = new HashMap<>();
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            Book book=new Book();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String fieldName = entry.getKey();
                String[] fieldValues = entry.getValue();

                if (fieldValues != null && fieldValues.length > 0) {
                    String fieldValue = fieldValues[0];

                    switch (fieldName) {
                        case "category":
                            long categoryId = Long.parseLong(fieldValue);
                            BookCategory category = new BookCategory();
                            category.setId(categoryId);
                            book.setCategory(category);
                            break;
                        case "isbn":
                            book.setIsbn(fieldValue);
                            break;
                        case "cover":
                            book.setCover(fieldValue);
                            break;
                        case "title":
                            book.setTitle(fieldValue);
                            break;
                        case "author":
                            book.setAuthor(fieldValue);
                            break;
                        case "description":
                            book.setDescription(fieldValue);
                            break;
                        case "publisher":
                            book.setPublisher(fieldValue);
                            break;
                        case "price":
                            double price = Double.parseDouble(fieldValue);
                            book.setPrice(price);
                            break;
                        case "publishdate":
//                            Date publishDate = dateFormat.parse(fieldValue);
                            book.setPublishDate(fieldValue);
                            break;

                        default:

                            break;
                    }
                }
            }
            // 处理 Book 对象，将其插入数据库等操作
            book.setStatus(true);
            book.setDelete(false);
            book.setCreateTime(new Date());
            book.setBorrowCount(0);
            bookMapper.inertBook(book);
            response.put("status", true);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", false);
            return response;
        }
    }


    //图书是否销毁
    @ResponseBody
    @PostMapping("/book_active")
    public Map<String, Object> activeBook(@RequestParam(value="id",required = false) String bookid){
        Map<String, Object> response=new HashMap<>();

        if(bookid!=null) {
            long bookid1 = Long.parseLong(bookid);

            Book book2=bookMapper.selectById(bookid1);

            if (book2.isDelete()) {
                book2.setDelete(false);

                bookMapper.updateById(book2);
            }
            else {
                book2.setDelete(true);

                bookMapper.updateById(book2);
            }
        }
        response.put("status",true);
        return response;
    }


    //图书列表
    @ResponseBody
    @PostMapping("/book")
    public Map<String, Object> getBooks(@RequestParam(value="category",required = false) String category,
                                        @RequestParam(value = "keyword",required = false) String keyword){
        Map<String, Object> response=new HashMap<>();

        List<Book> bookList=new ArrayList<>();
        if(category!=null){
            Integer categoryId=Integer.parseInt(category);
            long categoryId1=(long) categoryId;

            QueryWrapper<Book> qw=new QueryWrapper<>();
            qw.eq("category_id",categoryId1);
            bookMapper.selectList(qw).forEach(book -> {
                BookCategory category1=bookCategoryMapper.getCategotyBYBookid(book.getId());
                book.setCategory(category1);
                bookList.add(book);
            });

            try {
                getBookListByCategory(bookList,categoryId1);
            Collections.reverse(bookList);
            response.put("bookList",bookList);
            }
            catch (Exception e){
            System.out.println(e);
            }
        }
        else if(keyword!=null){
            QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
            queryWrapper.like("isbn",keyword);
            queryWrapper.or().like("author",keyword);
            queryWrapper.or().like("publisher",keyword);
            queryWrapper.or().like("title",keyword);
            queryWrapper.or().like("description",keyword);
            List<Book> bookList1=bookMapper.selectList(queryWrapper);
            bookList1.forEach(book -> {
                BookCategory category1=bookCategoryMapper.getCategotyBYBookid(book.getId());
                book.setCategory(category1);
                bookList.add(book);
            });
            Collections.reverse(bookList);
            response.put("bookList",bookList);

        }
        else {
            List<Book> bookAllList=bookMapper.selectList(null);

            bookAllList.forEach(book -> {

                BookCategory category1=bookCategoryMapper.getCategotyBYBookid(book.getId());
                book.setCategory(category1);

            });
            Collections.reverse(bookAllList);
            response.put("bookList",bookAllList);

        }
        return response;
    }

//    递归获得图书列表，通过分类
    public void getBookListByCategory(List<Book> bookList,long categoryId){
        List<Long> categoryIds=bookCategoryMapper.getchirdIds(categoryId);

        for(Long id:categoryIds){
            QueryWrapper<Book> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("category_id",id);
            BookCategory category=bookCategoryMapper.selectById(id);
            List<Book> bookList1=bookMapper.selectList(queryWrapper);
            bookList1.forEach(book -> {
                book.setCategory(category);
                bookList.add(book);
            });
           getBookListByCategory(bookList,id);
        }
    }


//更新图书
    @ResponseBody
    @PostMapping("/book_update")
    public Map<String, Object>  updateBook(@RequestParam(value="id",required = false) String bookid,HttpServletRequest request){
        Map<String, Object>  response=new HashMap<>();
        if(bookid!=null){
            long bookid1=Long.parseLong(bookid);
            Book book=bookMapper.selectById(bookid1);
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String fieldName = entry.getKey();
                String[] fieldValues = entry.getValue();

                if (fieldValues != null && fieldValues.length > 0) {
                    String fieldValue = fieldValues[0];

                    switch (fieldName) {
                        case "category":
                            if(fieldValue!="") {
                                long categoryId = Long.parseLong(fieldValue);
                                BookCategory category = new BookCategory();
                                category.setId(categoryId);
                                book.setCategory(category);
                            }
                            else {
                                BookCategory category=bookCategoryMapper.getCategotyBYBookid(bookid1);
                                book.setCategory(category);
                            }

                            break;
                        case "isbn":
                            book.setIsbn(fieldValue);
                            break;
                        case "cover":
                            book.setCover(fieldValue);
                            break;
                        case "title":
                            book.setTitle(fieldValue);
                            break;
                        case "author":
                            book.setAuthor(fieldValue);
                            break;
                        case "description":
                            book.setDescription(fieldValue);
                            break;
                        case "publisher":
                            book.setPublisher(fieldValue);
                            break;
                        case "price":
                            double price = Double.parseDouble(fieldValue);
                            book.setPrice(price);
                            break;
                        case "publishdate":

                            if(fieldValue!="") {
                                book.setPublishDate(fieldValue);
                            }
                            break;

                        default:

                            break;
                    }
                }
            }

            bookMapper.updatebook(book);
            response.put("status",true);

        }


        return response;

    }

}
