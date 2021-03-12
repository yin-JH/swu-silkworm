package cn.edu.swu.service;

import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.utils.*;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AskQuestionsService {

    @Autowired
    QuestionMapper questionMapper;

    /**
     * @methodName：askOneQ
     * @author: yin
     * @date: 2021/3/6  14:55
     * @param：String question
     * @return：String
     * @throws:
     * @description: 该方法使用excel来作为数据库，该方法已废弃
     */
    /*public String askOneQ(String question){
        SearchEngine searchEngine = SearchEngineFactory.getInstance();
        List<Question> questions = null;

        try {
            questions = searchEngine.search(question);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String res = "";

        System.out.println("Search : " + question);
        for (Question q : questions) {
            System.out.println(q.getMediaType());
            System.out.println("Result : " + q.toString());
            res += q.toString();
            res += "\n";
        }

        return res;
    }*/

    /**
     * @methodName：askOneQ
     * @author: yin
     * @date: 2021/3/6  14:55
     * @param：String question
     * @return：String
     * @throws:
     * @description: 该方法通过mysql作为数据库进行问题的查找匹配,检索算法由自己提供
     */
    public String askOneQ(String question){

        Question userQuestion = new Question();
        List<Question> questions = QuestionsHandler.getQuestions();

        //暴力匹配
        List<Term> termList = NLPUtil.getInstance().segOneQuestion(question);
        List<String> filteredTermList = TermFilter.getInstance().filterAndReturnStr(termList);

        //将用户提的问题和已经处理好切分完毕的关键词加入到 Question 中
        userQuestion.setKeywords(filteredTermList);

        //将用户提的问题和数据库中的问题进行匹配
        List<Question> result = MatchUtil.match(userQuestion, questions);

        System.out.println(result);

        //通过UserQuestionUtil来记录


        return result.toString();
    }

    /**
     * @methodName：askOneQUseSearchEngine
     * @author: yin
     * @date: 2021/3/12  11:08
     * @param：String question
     * @return：String
     * @throws: throws IOException
     * @description: 这个方法是通过Lucene提供的检索工具进行检索
     */
    public String askOneQUseSearchEngine(String question) {
        SearchEngine searchEngine = SearchEngine.getInstance();

        List<Question> results = new ArrayList<>();
        try {
            results = searchEngine.search(question);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return results.toString();
    }
}
