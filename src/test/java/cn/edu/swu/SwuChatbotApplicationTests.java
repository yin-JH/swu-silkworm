package cn.edu.swu;

import java.io.*;


import cn.edu.swu.entity.Question;
import cn.edu.swu.entity.UserQuestion;
import cn.edu.swu.mapper.QuestionMapper;

import cn.edu.swu.mapper.UserQuestionMapper;
import cn.edu.swu.service.AdminService;
import cn.edu.swu.service.AskQuestionsService;
import cn.edu.swu.utils.NLPUtil;
import cn.edu.swu.utils.QuestionsHandler;
import cn.edu.swu.utils.SearchEngine;
import cn.edu.swu.utils.TermFilter;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.lucene.HanLPAnalyzer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.util.ResourceUtils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@SpringBootTest
class SwuChatbotApplicationTests {

    @Autowired
    QuestionMapper questionMap;

    @Autowired
    AskQuestionsService askQuestionsService;

    @Autowired
    AdminService adminService;

    @Autowired
    UserQuestionMapper userQuestionMapper;

    /*@Test
    void contextLoads() throws IOException {
        StringReader reader = new StringReader("5龄蚕吃什么样的桑叶？");
        IKSegmenter ikSegmenter = new IKSegmenter(reader, true);

        Lexeme lexeme = null;

        while ((lexeme = ikSegmenter.next()) != null) {
            System.out.println(lexeme.getLexemeText());
            System.out.println(lexeme.getLexemeTypeString());
        }
    }*/

    @Test
    void NLPTest() {
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        String[] testCase = new String[]{
                "今天，刘志军案的关键人物,山西女商人丁书苗在市二中院出庭受审。",
                "刘喜杰石国祥会见吴亚琴先进事迹报告团成员",
        };
        for (String sentence : testCase) {
            System.out.println("N-最短分词：" + nShortSegment.seg(sentence) + "\n最短路分词：" + shortestSegment.seg(sentence));
        }
    }

    @Test
    void TestSentence() {
        Segment nShortSegment = new NShortSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);
        Segment shortestSegment = new DijkstraSegment().enableCustomDictionary(false).enablePlaceRecognize(true).enableOrganizationRecognize(true);

        List<Term> seg = shortestSegment.seg("3龄蚕有多大");

        System.out.println(seg);

        //System.out.println(TermFilter.getInstance().filter(seg));

    }

    @Test
    void updateAllKeywords() {
        List<Question> allQuestions = questionMap.getAllQuestions();

        for (Question question : allQuestions) {
            String q = question.getQuestion();

            List<Term> terms = NLPUtil.getInstance().segOneQuestion(q);

            List<Term> filteredTerms = TermFilter.getInstance().filter(terms);

            String keywords = "";

            for (Term filteredTerm : filteredTerms) {
                keywords += filteredTerm.word + "|";
            }

            questionMap.updateQuestionKeywords(question.getId(), keywords);

        }
    }

    @Test
    void removeBiaoDina() {
        List<Question> allQuestions = questionMap.getAllQuestions();
        for (Question question : allQuestions) {
            String q = question.getQuestion();
            if (q.endsWith("?") || q.endsWith("？")) {
                q = q.substring(0, q.length() - 1);
            }
            questionMap.updateProblem(question.getId(), q);
        }
    }

    @Test
    void aQServiceTest() {
        String q = "蚕能不能蜕皮？";

        //存储问题的关键词
        List<Term> qWords = TermFilter.getInstance().filter(NLPUtil.getInstance().segOneQuestion(q));
        System.out.println(qWords);

        List<Question> allQuestions = questionMap.getAllQuestions();//获取数据库中所有条目
        for (Question question : allQuestions) {

        }

    }

    @Test
    void TestQuestionsHandler() {

        System.err.println(QuestionsHandler.getQuestions().size());

        System.out.println(QuestionsHandler.getQuestions());
    }

    @Test
    void TestAskOneQuestionInterface() {
        askQuestionsService.askOneQ("蚕宝宝感染病毒病有哪些");
    }

    @Test
    void testFilePath() {
        BufferedReader br = null;
        try {
            File customDirectoryFile = ResourceUtils.getFile("classpath:cd/cd.txt");
            br = new BufferedReader(new FileReader(customDirectoryFile));

            String word = null;
            while ((word = br.readLine()) != null) {
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

    @Test
    void testItemsRetrieve() {
        //返回内存中的内容
        List<Question> allQuestions = QuestionsHandler.getQuestions();

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
        System.out.println(res);
    }

    public String itemsRetrieve() {
        //返回数据库中的内容
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
        System.out.println(res);
        return res;
    }
    @Test
    void testItemsDelete() throws InterruptedException {
        synchronized (Object.class) {
            Long id = 11L;
            List<Question> loadQuestions = QuestionsHandler.getQuestions();
            //修改内存中的数据
            Iterator<Question> it = loadQuestions.iterator();
            while (it.hasNext()){
                Question q = it.next();
                if (q.getId().equals(id)) {
                   it.remove();
                }
            }

            //修改数据库中的数据
            questionMap.updateFlag(id, 0);
        }

        //Thread.sleep(100);
        System.out.println("返回数据库中的内容======");
        itemsRetrieve();
        System.out.println("返回内存中的内容======" );
        testItemsRetrieve();
    }
    @Test
    void testItemsUpdate() {
        Long id = 7L;
        String parameter = "problem";
        String content = "为什么蚕要蜕皮";
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
        System.out.println(res);
    }

    @Test
    void testRetrieve(){

        String res = adminService.itemsRetrieve();
        System.out.println(res);
    }

    @Test
    void testMaxId(){
        Long id = questionMap.getMaxId();
        List<Question> allQuestions = questionMap.getAllQuestions();
        System.out.println(id);
        System.out.println(allQuestions);
    }
    @Test
    void testItemAdd() {
        String problem = "测试条目增加";
        String type = "测试类型";
        String media_type = "text";
        String answer = "测试内容";

        //获取内存中的数据
        List<Question> loadQuestions = QuestionsHandler.getQuestions();
        synchronized (Object.class) {
            Long maxId = questionMap.getMaxId();
            Long newId = maxId + 1L;
            //为内存中添加新条目
            Question q = new Question();
            q.setId(newId);
            q.setFlag(1);
            q.setQuestion(problem);
            q.setType(type);
            q.setMediaType(media_type);
            q.setAnswer(answer);

            //切词
            List<Term> terms = NLPUtil.getInstance().segOneQuestion(problem);
            List<Term> filteredTerms = TermFilter.getInstance().filter(terms);
            String keywords = "";

            for (Term filteredTerm : filteredTerms) {
                keywords += filteredTerm.word + "|";
            }
            q.setOriginalKeywords(keywords);

            //添加到内存中
            loadQuestions.add(q);
            //添加到数据库中
            questionMap.saveQuestion(q);
        }
        List<Question> allQuestions = QuestionsHandler.getQuestions();
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

        System.out.println("返回数据库中的内容======");
        itemsRetrieve();
        System.out.println("返回内存中的内容======" );
        testItemsRetrieve();
    }
    @Test
    void testItemUpdate(){
        Long id = 183L;
        String problem = "测试条目修改2";
        String type = "测试类型修改2";
        String media_type = "text修改2";
        String answer = "测试内容修改2";

        synchronized (Object.class){
            //切词
            List<Term> terms = NLPUtil.getInstance().segOneQuestion(problem);
            List<Term> filteredTerms = TermFilter.getInstance().filter(terms);
            String keywords = "";

            for (Term filteredTerm : filteredTerms) {
                keywords += filteredTerm.word + "|";
            }

            //获取内存中的数据
            List<Question> loadQuestions = QuestionsHandler.getQuestions();
            for (Question q : loadQuestions) {
                if(q.getId().equals(id)){
                    q.setQuestion(problem);
                    q.setOriginalKeywords(keywords);
                    q.setType(type);
                    q.setMediaType(media_type);
                    q.setAnswer(answer);
                }
            }
            //获取数据库中的数据
            List<Question> allQuestions = questionMap.getAllQuestions();
            for (Question q : allQuestions) {
                if(q.getId().equals(id)){
                    questionMap.updateAll(id,problem,keywords,type,media_type,answer);
                }
            }
        }

        System.out.println("返回数据库中的内容======");
        itemsRetrieve();
        System.out.println("返回内存中的内容======" );
        testItemsRetrieve();
    }

    /*@Test
    void testHanlpWithLuncen() throws Exception{
        String text = "中华人民共和国很辽阔";
        for (int i = 0; i < text.length(); ++i)
        {
            System.out.print(text.charAt(i) + "" + i + " ");
        }
        System.out.println();
        Analyzer analyzer = new HanLPAnalyzer();

        TokenStream tokenStream = analyzer.tokenStream("field", new StringReader(text));
        tokenStream.reset();
        while (tokenStream.incrementToken())
        {
            //CharTermAttribute attribute = tokenStream.getAttribute(CharTermAttribute.class);
            // 偏移量
            OffsetAttribute offsetAtt = tokenStream.getAttribute(OffsetAttribute.class);
            // 距离
            PositionIncrementAttribute positionAttr = tokenStream.getAttribute(PositionIncrementAttribute.class);
            // 词性
            TypeAttribute typeAttr = tokenStream.getAttribute(TypeAttribute.class);
            System.out.printf("[%d:%d %d] %s/%s\n", offsetAtt.startOffset(), offsetAtt.endOffset(), positionAttr.getPositionIncrement(), typeAttr.type());
        }
    }*/

    @Test
    void testLucene(){
        SearchEngine searchEngine = SearchEngine.getInstance();

        try {
            searchEngine.deleteDoc(151L);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSearchEngineUpdateOneDoc() throws Exception {

        SearchEngine searchEngine = SearchEngine.getInstance();

        List<Question> results = searchEngine.search("蚕宝宝有多大");

        System.out.println(results);

        for (Question result : results) {
            result.setAnswer("wobuzhidaoya");
            searchEngine.updateOneDoc(result);
        }

        System.err.println("==========================");

        System.out.println(searchEngine.search("蚕宝宝有多大"));
    }


    @Test
    void testAddDoc() throws Exception {

        SearchEngine searchEngine = SearchEngine.getInstance();

        System.out.println(searchEngine.search("蚕宝宝有多大"));

        System.err.println("========================================================");

        List<Question> questions = QuestionsHandler.getQuestions();
        Question question = questions.get(100);

        question.setQuestion("我是伞兵");

        searchEngine.addDoc(question);
        System.out.println(searchEngine.search("我是伞兵"));

        System.err.println("=====================================================");


    }

    @Test
    void testUserQuestionMapper(){
        UserQuestion userQuestion = new UserQuestion();
        userQuestion.setUserProblem("东百往事");
        userQuestion.setSystemAnswer(2L);
        userQuestion.setFlag(1);


        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        String format = formatter.format(date);

        userQuestion.setAskDate(format);

        userQuestionMapper.saveOneUserQuestion(userQuestion);
    }

    @Test
    void testUserQuestionMapperSelectAll(){
        List<UserQuestion> userQuestions = userQuestionMapper.loadAllUserQuestions();

        System.out.println(userQuestions);
    }
    @Test
    void testAskOneQReturn(){
        System.out.println(askQuestionsService.askOneQUseSearchEngine("怎样除沙"));
    }
}
