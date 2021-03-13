package cn.edu.swu.utils;
import	java.util.ArrayList;

import cn.edu.swu.entity.Question;

import java.util.Arrays;
import java.util.List;

/**
 * @className: MatchUtil
 * @author： yin
 * @date： 2021/3/6 22:03
 * @description：该类用于判断一个用户提的问题和数据库中某一条问题的匹配程度进行判断
 */
public class MatchUtil {

    /**
     * @methodName：judge
     * @author: yin
     * @date: 2021/3/6  22:05
     * @param：Question userQ, Question dbQ
     * @return：boolean
     * @throws:
     * @description: 该方法将用户提出的question（userQ）和传入的另一个questions（数据库中的所有问题集合）进行
     * 比较，将匹配度最高的三个Question作为 List 返回
     *
     * 这里的匹配算法暂时为窗口算法
     */
    public static List<Question> match(Question userQ, List<Question> questions){
        List<String> userQKeywords = userQ.getKeywords();

        Question[] window = new Question[3];
        int[] windowCount = new int[]{-1, -1, -1};

        for (Question question : questions) {
            List<String> dbKeywords = question.getKeywords();
            int count = 0;

            for (String dbKeyword : dbKeywords) {
                for (String userQKeyword : userQKeywords) {
                    if(userQKeyword.equals(dbKeyword))
                        count++;
                }//第三层循环，循环用户提出问题的所有关键词
            }//第二层循环，循环数据库中某一问题的所有关键词

            //System.err.println(((count * 1.00) / dbKeywords.size()));

            if(count > windowCount[2] && (window[2] == null || (((count * 1.00) / dbKeywords.size()) > 0.5))){
                if(count > windowCount[1] && (window[1] == null || (((count * 1.00) / dbKeywords.size()) > 0.5))){
                    if(count > windowCount[0] && (window[0] == null || (((count * 1.00) / dbKeywords.size()) > 0.5))){
                        //当count位于第一时，所有的后移
                        windowCount[2] = windowCount[1];
                        window[2] = window[1];

                        windowCount[1] = windowCount[0];
                        window[1] = window[0];

                        windowCount[0] = count;
                        window[0] = question;
                    }
                    else{
                        windowCount[2] = windowCount[1];
                        window[2] = window[1];

                        windowCount[1] = count;
                        window[1] = question;
                    }
                }
                else {
                    windowCount[2] = count;
                    window[2] = question;
                }
            }

        }//外层循环，循环内存中所有的问题

        List<Question> result = new ArrayList<> ();

        for(int i = 0; i < 3; i++){
            if(window[i] != null && (windowCount[i] > (userQKeywords.size()) / 2)) {
                /*System.out.println(windowCount[i]);
                System.out.println(userQKeywords.size());
                System.err.println(window[i]);*/

                result.add(window[i]);
            }
        }

        return result;
    }
}
