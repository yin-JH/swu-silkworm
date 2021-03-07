package cn.edu.swu.service;

import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.utils.NLPUtil;
import cn.edu.swu.utils.TermFilter;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TestService {

    @Autowired
    QuestionMapper questionMapper;

    /*public void AddQuestionFromExcelToMySql(){
        DataSetReader dataSetReader = new DataSetReader();
        List<Question> questions = null;
        try {
            questions = dataSetReader.read("excel/silkworm-faq.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Question question : questions) {
            List<String> keywords = question.getKeywords();
            String originalKeywords = "";

            for (String keyword : keywords) {
                originalKeywords += keyword + "|";
            }

            question.setOriginalKeywords(originalKeywords);
        }

        for (Question question : questions) {
            questionMapper.saveQuestion(question);
        }

        System.out.println(questions);
    }*/

    public String test02(){
        List<Question> allQuestions = questionMapper.getAllQuestions();

        for (Question question : allQuestions) {
            String q = question.getQuestion();

            List<Term> terms = NLPUtil.getInstance().segOneQuestion(q);

            List<Term> filteredTerms = TermFilter.getInstance().filter(terms);

            String keywords = "";

            for (Term filteredTerm : filteredTerms) {
                keywords += filteredTerm.word + "|";
            }

            questionMapper.updateQuestionKeywords(question.getId(), keywords);
        }
        return "ok | update";
    }
}
