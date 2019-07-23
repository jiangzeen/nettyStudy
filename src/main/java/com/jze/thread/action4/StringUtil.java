package com.jze.thread.action4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: 蒋泽恩
 * @date: 2019/5/25 13:01
 * @description:
 */
public class StringUtil {

    public static int hasContain(String content, String keyword){
        int count = 0;
        Pattern pattern = Pattern.compile(keyword);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            count++;
        }
        return count;
    }

    public static int hasContainCount(String content, String keyword,
                                      int count){
        int index = content.indexOf(keyword);
        if (index != -1){
            count++;
            if (index < content.length() - 1){
                content =  content.substring(index + 1);
                return hasContainCount(content, keyword ,count);
            }

        }
        return count;
    }
}
