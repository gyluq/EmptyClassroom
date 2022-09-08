package com.musen.EmptyClassRoom.service;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author GuYeLuo
 * @create 2022/9/2 16:50
 */
public class ParseHtml {

    /**
     * 返回课表信息，ArrayList中为Map,一个Map包含了一个教室的所有课表信息（以星期为表头，内容为周信息）
     *
     * @return
     */
    public ArrayList<Map> getClassroomInf() {
        ArrayList<Map> arrayList = new ArrayList<>();
        String[] week = {"7", "1", "2", "3", "4", "5", "6"};
        String[] time = {"12", "34", "56", "78", "90"};
        ParseHtml parseHtml = new ParseHtml();

        try {
            Document document = Jsoup.parse(new File("C:\\Users\\asd\\Desktop\\dd.html"), "utf-8");
            //获取tbody中的tr,即行,size为93
            Elements trsFromTable = document.select("tbody tr");
            //遍历行
            for (Element tr : trsFromTable) {
                int j = 0;
                HashMap<String, String> hashMap = new HashMap<>();

                //获取行中的单元格,每个tr有43个td
                Elements tds = tr.select("td");
                //遍历单元格中的文本，将有意义的部分插入数据库
                for (Element td : tds) {
                    String classInf = td.text();
                    if (j != 0) {
                        //第11、12节课跳过
                        if (j % 6 == 0) {
                            j++;
                            continue;
                        }
                        //课表信息添加到map
                        else if (!classInf.equals("")) {
                            hashMap.put("w" + week[j / 6] + "_" + time[j % 6 - 1], parseHtml.extractWeek(classInf));
                        }
                        // 第一个单元格中为教室信息
                    } else {
                        hashMap.put("classroom", td.text());
                    }
                    j++;
                }
                arrayList.add(hashMap);
            }
            //arrayList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * 用来content中的括号中的周信息
     *
     * @param content td中的信息
     * @return 括号中的周信息
     */
    public String extractWeek(String content) {
        StringBuilder sb = new StringBuilder();
        //使用懒惰匹配
        Pattern pat_brackets = Pattern.compile("\\(.*?\\)");
        Pattern pat_weeks = Pattern.compile("\\d+-\\d+|\\d+");
        Matcher mat1 = pat_brackets.matcher(content);
        //获取括号中的周信息
        while (mat1.find()) {
            String bracketsContent = mat1.group();
            Matcher mat2 = pat_weeks.matcher(bracketsContent);
            //提取周信息
            while (mat2.find()) {
                String weekContent = mat2.group();
                if (weekContent.contains("-")) {
                    String[] split = weekContent.split("-");
                    int beg = Integer.parseInt(split[0]);
                    int end = Integer.parseInt(split[1]);
                    for (int i = beg; i <= end; i++) {
                        sb.append(i).append(",");
                    }
                } else {
                    sb.append(weekContent).append(",");
                }
            }
        }
        return sb.toString();
    }
}
