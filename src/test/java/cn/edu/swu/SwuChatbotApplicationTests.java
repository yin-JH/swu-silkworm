package cn.edu.swu;

import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.utils.DataSetReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SwuChatbotApplicationTests {

    @Autowired
    QuestionMapper questionMap;

    @Test
    void contextLoads() throws IOException {
        DataSetReader dataSetReader = new DataSetReader();
        List<Question> questions = dataSetReader.read("excel/silkworm-faq.xlsx");

        for (Question question : questions) {
            List<String> keywords = question.getKeywords();
            String originalKeywords = "";

            for (String keyword : keywords) {
                originalKeywords += keyword + "|";
            }

            question.setOriginalKeywords(originalKeywords);
        }

        for (Question question : questions) {
            questionMap.saveQuestion(question);
        }

        System.out.println(questions);
    }

}
