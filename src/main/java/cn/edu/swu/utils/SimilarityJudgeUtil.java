package cn.edu.swu.utils;

import cn.edu.swu.entity.Question;

import java.util.List;

/**
 * @className: SimilarityJudgeUtil
 * @author： yin
 * @date： 2021/3/6 22:03
 * @description：该类用于判断一个用户提的问题和数据库中某一条问题的匹配程度进行判断
 */
public class SimilarityJudgeUtil {

    /**
     * @methodName：judge
     * @author: yin
     * @date: 2021/3/6  22:05
     * @param：Question userQ, Question dbQ
     * @return：boolean
     * @throws:
     * @description: 该方法将用户提出的question（userQ）和传入的另一个question（数据库中的某个问题dbQ）进行
     * 比较，如果匹配度超过阈值（这里的阈值暂定为关键词最多仅仅有一个无法匹配上）就返回true代表匹配成功；如果
     * 没有超过阈值就返回false，代表匹配失败
     *
     * 这里的匹配算法暂时为暴力匹配法
     */
    public static boolean judge(Question userQ, Question dbQ){
        List<String> userQKeywords = userQ.getKeywords();
        List<String> dbQKeywords = dbQ.getKeywords();

        int count = 0;

        //System.out.println(userQKeywords);
        //System.out.println(dbQ);

        for (String userQKeyword : userQKeywords) {
            for (String dbQKeyword : dbQKeywords) {
                if(userQKeyword.equals(dbQKeyword)){
                    count++;
                    break;
                }
            }
        }

        if(count > 0 && count >= ((userQKeywords.size() + dbQKeywords.size()) / 2))
            return true;
        return false;
    }
}
