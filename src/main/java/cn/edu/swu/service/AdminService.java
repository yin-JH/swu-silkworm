package cn.edu.swu.service;

import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Mou
 * @Date: 2021/3/7 20:44
 * @Description: 管理后台的相关操作
 * @Version: 1.0
 */

@Service
public class AdminService {

    @Autowired
    QuestionMapper questionMap;

    /**
     * 查询功能 返回所有的有效条目
     */
    public String itemsRetrieve() {
        List<Question> allQuestions = questionMap.getAllQuestions();

        String allJson = "";
        String res;

        //返回json格式
        for (Question q : allQuestions) {
            allJson += "{\"id\":\"" + q.getId() +
                    "\",\"problem\":\"" + q.getQuestion() +
                    "\",\"keywords\":\"" + q.getOriginalKeywords() +
                    "\",\"type\":\"" + q.getType() +
                    "\",\"media_type\":\"" + q.getMediaType() +
                    "\",\"answer\":\"" + q.getAnswer() +
                    "\"}" + ",";
        }
        int len = allJson.length();
        res = allJson.substring(0, len - 1);
        return res;
    }

    /**
     * 删除功能，将相应的条目flag置为0
     * 需要重新返回所有条目
     */
    public String itemsDelete(Long id) {
        List<Question> allQuestions = questionMap.getAllQuestions();
        //删除该条目
        questionMap.updateFlag(id, 0);

        //返回json格式
        String res = "";
        res = itemsRetrieve();
        return res;
    }

    /**
     * 修改功能，修改相关的条目
     */
    public String itemsUpdate(Long id, String parameter, String content) {
        switch (parameter) {
            case "problem":
                questionMap.updateProblem(id, content);
                break;
            case "keywords":
                questionMap.updateQuestionKeywords(id, content);
                break;
            case "type":
                questionMap.updateType(id, content);
                break;
            case "media_type":
                questionMap.updateMediaType(id, content);
                break;
            case "answer":
                questionMap.updateAnswer(id, content);
                break;
            default:
                System.err.println("数据修改无效!");
        }

        //返回json格式
        String res = "";
        res = itemsRetrieve();
        return res;
    }

}
