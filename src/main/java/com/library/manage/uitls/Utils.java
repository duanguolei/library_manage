package com.library.manage.uitls;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public String formatToYearMonthDay(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {

            String formattedDateStr = sdf.format(date);

            return formattedDateStr;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Utils().formatToYearMonthDay(new Date()));
    }

}
