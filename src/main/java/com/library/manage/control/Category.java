package com.library.manage.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.library.manage.dao.BookCategoryMapper;
import com.library.manage.entity.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class Category {

    @Autowired
    private BookCategoryMapper bookCategoryMapper;


    @ResponseBody
    @PostMapping("/category_all")
    public Map<String, Object> category_all() {
        Map<String, Object> response = new HashMap<>();
        List category_list = new ArrayList<>();
        QueryWrapper<BookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("parent_category_id");
        List<BookCategory> bookCategoryList = bookCategoryMapper.selectList(queryWrapper);
        for (BookCategory bookCategory : bookCategoryList) {
            Map<String, Object> category_result = new HashMap<>();
            category_result.put("value", bookCategory.getId());
            category_result.put("label", bookCategory.getName());
            List chird_category_list=new ArrayList<>();
            get_chird_category(bookCategory.getId(),chird_category_list);
            category_result.put("children",chird_category_list);
            category_list.add(
                    category_result
            );
        }
        System.out.println(
                category_list
        );
        response.put("data", category_list);
        return response;

    }

    public void get_chird_category(long categoryId,List category_list) {

        QueryWrapper<BookCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_category_id", categoryId);
        List<BookCategory> bookCategoryList = bookCategoryMapper.selectList(queryWrapper);
        if (bookCategoryList.size() == 0) {
            return;
        }
        bookCategoryList.forEach(bookCategory -> {
            Map<String, Object> category_result = new HashMap<>();
            category_result.put("value", bookCategory.getId());
            category_result.put("label", bookCategory.getName());
            List chird_category_list=new ArrayList<>();
            get_chird_category(bookCategory.getId(),chird_category_list);
            category_result.put("children",chird_category_list);
            category_list.add(category_result);
        });

    }





    @ResponseBody
    @PostMapping(path = {"/category_add/{categoryId}","/category_add"},produces = "application/json")
    public Map<String, Object> category_add(@PathVariable(value="categoryId",required =false) String categoryId, Model model,
                               HttpServletRequest request
                               ) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> response = new HashMap<>();
        String name = parameterMap.get("name")[0];
        System.out.println(name);


        BookCategory addcatefory = new BookCategory();
        addcatefory.setName(name);

        if(categoryId!=null){
            Integer cateforyid = Integer.parseInt(categoryId);
            BookCategory pacategory = bookCategoryMapper.selectById((long) cateforyid);

            if(pacategory!=null){
                addcatefory.setParentCategory(pacategory);
                bookCategoryMapper.insertChirdCategory(addcatefory);
            }
        }
        else {
            bookCategoryMapper.insert(addcatefory);
        }
        response.put("status", true);
        return response;


    }




    private void buildParentCategories(Integer categoryId, List<BookCategory> parentCategories) {
        BookCategory category = bookCategoryMapper.selectById((long) categoryId);
        if (category != null) {
            parentCategories.add(category);
            try {


                long paid = bookCategoryMapper.getParentId(category);
                System.out.println(paid);
                while (paid != 0) {
                    BookCategory pacategory = bookCategoryMapper.selectById(paid);
                    parentCategories.add(pacategory);
                    try {
                        System.out.println(paid);
                        System.out.println(category);
                        paid = bookCategoryMapper.getParentId(pacategory);
                    }
                    catch (Exception e) {
                        paid = 0;
                    }

                }
            }
            catch (Exception e){
                long paid = 0;
            }

        }
    }

    @GetMapping(path = {"/category/{categoryId}","/category"})
    public String category(@PathVariable(value="categoryId",required =false) String categoryId, Model model) {

//        System.out.println(categoryId);
        List<BookCategory> parentCategories = new ArrayList<>();

        if(categoryId!=null) {
            Integer cateforyid = Integer.parseInt(categoryId);
            BookCategory category = bookCategoryMapper.selectById((long) cateforyid);

            if (category != null) {

                QueryWrapper<BookCategory> queryWrapper=new QueryWrapper<>();
               ;queryWrapper.eq("parent_category_id",category.getId());
                List<BookCategory> bookCategoryList = bookCategoryMapper.selectList(queryWrapper);
                model.addAttribute("bookCategoryList", bookCategoryList);


                Integer categoryIdInt = Integer.parseInt(categoryId);
                buildParentCategories(categoryIdInt, parentCategories);
//                parentCategories.add(category);

            }

        }
        else {

            QueryWrapper<BookCategory> queryWrapper=new QueryWrapper<>();
            ;queryWrapper.isNull("parent_category_id");
//            ;queryWrapper.eq("id",1);
            List<BookCategory> bookCategoryList = bookCategoryMapper.selectList(queryWrapper);

//            System.out.println(bookCategoryList);
            model.addAttribute("bookCategoryList", bookCategoryList);
        }

        System.out.println(parentCategories);
        Collections.reverse(parentCategories);
        model.addAttribute("parentCategories", parentCategories);

        return "category";
    }
}
