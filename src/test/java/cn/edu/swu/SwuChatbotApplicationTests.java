package cn.edu.swu;

import java.io.*;


import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;

import cn.edu.swu.service.AskQuestionsService;
import cn.edu.swu.utils.NLPUtil;
import cn.edu.swu.utils.QuestionsHandler;
import cn.edu.swu.utils.TermFilter;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.util.ResourceUtils;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.util.List;


@SpringBootTest
class SwuChatbotApplicationTests {

    @Autowired
    QuestionMapper questionMap;

    @Autowired
    AskQuestionsService askQuestionsService;

    @Test
    void contextLoads() throws IOException {
        StringReader reader = new StringReader("5龄蚕吃什么样的桑叶？");
        IKSegmenter ikSegmenter = new IKSegmenter(reader, true);

        Lexeme lexeme = null;

        while ((lexeme = ikSegmenter.next()) != null){
            System.out.println(lexeme.getLexemeText());
            System.out.println(lexeme.getLexemeTypeString());
        }
    }

    @Test
    void NLPTest(){
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String[] testCase = new String[]{
                "今天，刘志军案的关键人物,山西女商人丁书苗在市二中院出庭受审。",
                "刘喜杰石国祥会见吴亚琴先进事迹报告团成员",
        };
        for (String sentence : testCase)
        {
            System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
        }
    }

    @Test
    void TestSentence(){
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);

        List<Term> seg = shortestSegment.seg("3龄蚕有多大");

        System.out.println(seg);

        //System.out.println(TermFilter.getInstance().filter(seg));

    }

    @Test
    void updateAllKeywords(){
        List<Question> allQuestions = questionMap.getAllQuestions();

        for (Question question : allQuestions) {
            String q = question.getQuestion();

            List<Term> terms = NLPUtil.getInstance().segOneQuestion(q);

            List<Term> filteredTerms = TermFilter.getInstance().filter(terms);

            String keywords = "";

            for (Term filteredTerm : filteredTerms) {
                keywords += filteredTerm.word + "|";
            }

            questionMap.updateQuestionKeywords(question.getId(),keywords);

        }
    }

    @Test
    void removeBiaoDina(){
        List<Question> allQuestions = questionMap.getAllQuestions();
        for(Question question : allQuestions){
            String q = question.getQuestion();
            if(q.endsWith("?")|| q.endsWith("？")){
                q = q.substring(0,q.length() - 1);
            }
            questionMap.updateProblem(question.getId(),q);
        }
    }

    @Test
    void aQServiceTest(){
        String q = "蚕能不能蜕皮？";

        //存储问题的关键词
        List<Term> qWords = TermFilter.getInstance().filter(NLPUtil.getInstance().segOneQuestion(q));
        System.out.println(qWords);

        List<Question> allQuestions = questionMap.getAllQuestions();//获取数据库中所有条目
        for (Question question : allQuestions) {

        }

    }

    @Test
    void TestQuestionsHandler(){

        System.err.println(QuestionsHandler.getQuestions().size());

        System.out.println(QuestionsHandler.getQuestions());
    }

    @Test
    void TestAskOneQuestionInterface(){
        askQuestionsService.askOneQ("蚕宝宝感染病毒病有哪些");
    }

    @Test
    void testFilePath(){
        BufferedReader br = null;
        try {
            File customDirectoryFile =  ResourceUtils.getFile("classpath:cd/cd.txt");
            br = new BufferedReader(new FileReader(customDirectoryFile));

            String word = null;
            while((word = br.readLine()) != null){
                System.out.println(word);
                //CustomDictionary.add(word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
