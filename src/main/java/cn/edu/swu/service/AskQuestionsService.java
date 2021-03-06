package cn.edu.swu.service;

import cn.edu.swu.entity.Question;
import cn.edu.swu.utils.SearchEngine;
import cn.edu.swu.utils.SearchEngineFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AskQuestionsService {

    public String askOneQ(String question){
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
    }
}
