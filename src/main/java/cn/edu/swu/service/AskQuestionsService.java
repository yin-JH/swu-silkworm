package cn.edu.swu.service;

import cn.edu.swu.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @description: 该方法通过mysql作为数据库进行问题的查找匹配
     */
    public String askOneQ(String question){
        question = "蚕能不能蜕皮？";

        return null;
    }
}
