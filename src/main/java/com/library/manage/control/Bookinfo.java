package com.library.manage.control;


import com.library.manage.dao.BookMapper;
import com.library.manage.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Bookinfo {

    @Autowired
    private BookMapper bookMapper;

    @GetMapping(path = {"/book_detail/{id}"})
    public String bookinfo(@PathVariable(value = "id",required = false) String id, Model model){
        if(id!=null){
            Integer bookid = Integer.parseInt(id);
            Book book=bookMapper.selectById((long) bookid);

            String desc=book.getDescription();
            String descWithParagraphs = "<p>"+desc.replaceAll("\\n", "</p><p>")+"</p>";
            book.setDescription(descWithParagraphs);

            model.addAttribute("book",book);
        }
        return "bookinfo";
    }

}
