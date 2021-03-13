package cn.edu.swu.service;

import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.utils.NLPUtil;
import cn.edu.swu.utils.QuestionsHandler;
import cn.edu.swu.utils.SearchEngine;
import cn.edu.swu.utils.TermFilter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.*;

/**
 * @Author: Mou
 * @Date: 2021/3/7 20:44
 * @Description: 管理后台的相关操作
 * @Version: 1.0
 */

@Service
public class AdminService {

    @Autowired
    QuestionMapper questionMapper;

    /**
     * 查询功能 返回所有的有效条目
     */
    public PageInfo itemsRetrieve(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<Question> allQuestions = questionMapper.getAllQuestions();

        PageInfo pageInfo = new PageInfo(allQuestions,10);

        return pageInfo;

        /*String allJson = "";
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
        return res;*/
    }

    /**
     * 删除功能，将相应的条目flag置为0
     * 需要重新返回所有条目
     */
    public void itemsDelete(Long id) {
        //List<Question> allQuestions = questionMap.getAllQuestions();
        //删除该条目
        synchronized (Object.class) {
            List<Question> loadQuestions = QuestionsHandler.getQuestions();
            //修改内存中的数据
            Iterator<Question> it = loadQuestions.iterator();
            while (it.hasNext()){
                Question q = it.next();
                if (q.getId().equals(id)) {
                    //将检索空间中的一并删除
                    SearchEngine searchEngine = SearchEngine.getInstance();

                    try {
                        searchEngine.deleteDoc(q.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    it.remove();
                }
            }

            //修改查询工具SearchEngine中的数据


            //修改数据库中的数据
            questionMapper.updateFlag(id, 0);
        }

    }

    /**
     * 增加新条目
     */
    public void itemAdd(String problem,
                          String type,
                          String media_type,
                          String answer) {

        synchronized (Object.class) {
            //获取内存中的数据
            List<Question> loadQuestions = QuestionsHandler.getQuestions();

            Long maxId = questionMapper.getMaxId();
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

            //添加到索引空间
            SearchEngine searchEngine = SearchEngine.getInstance();
            try {
                searchEngine.addDoc(q);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //添加到数据库中
            questionMapper.saveQuestion(q);
        }

    }
    /**
     * 修改功能，修改所有的条目
     */
    public void itemUpdateAll(Long id,
                           String problem,
                           String type,
                           String media_type,
                           String answer){

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

                    //更新索引空间中的数据
                    SearchEngine searchEngine = SearchEngine.getInstance();
                    try {
                        searchEngine.updateOneDoc(q);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }


            //获取数据库中的数据
            List<Question> allQuestions = questionMapper.getAllQuestions();
            for (Question q : allQuestions) {
                if(q.getId().equals(id)){
                    questionMapper.updateAll(id,problem,keywords,type,media_type,answer);
                }
            }
        }
    }

    /**
     * 修改功能，修改相关的条目
     */
    public void itemUpdate(Long id, String parameter, String content) {
        List<Question> loadQuestions = QuestionsHandler.getQuestions();
        switch (parameter) {
            case "problem":
                synchronized (Object.class) {
                    //修改数据库中的数据
                    questionMapper.updateProblem(id, content);
                    //修改内存中的数据
                    for (Question q : loadQuestions) {
                        if (q.getId().equals(id)) {
                            q.setQuestion(content);
                        }
                    }
                }
                break;
            case "keywords":
                synchronized (Object.class) {
                    //修改数据库中的数据
                    questionMapper.updateQuestionKeywords(id, content);
                    //修改内存中的数据
                    for (Question q : loadQuestions) {
                        if (q.getId().equals(id)) {
                            q.setOriginalKeywords(content);
                        }
                    }
                }
                break;
            case "type":
                synchronized (Object.class) {
                    //修改数据库中的数据
                    questionMapper.updateType(id, content);
                    //修改内存中的数据
                    for (Question q : loadQuestions) {
                        if (q.getId().equals(id)) {
                            q.setType(content);
                        }
                    }
                }
                break;
            case "media_type":
                synchronized (Object.class) {
                    //修改数据库中的数据
                    questionMapper.updateMediaType(id, content);
                    //修改内存中的数据
                    for (Question q : loadQuestions) {
                        if (q.getId().equals(id)) {
                            q.setMediaType(content);
                        }
                    }
                }
                break;
            case "answer":
                synchronized (Object.class) {
                    //修改数据库中的数据
                    questionMapper.updateAnswer(id, content);
                    //修改内存中的数据
                    for (Question q : loadQuestions) {
                        if (q.getId().equals(id)) {
                            q.setAnswer(content);
                        }
                    }
                }
                break;
            default:
                System.err.println("数据修改无效!");
        }

    }



}
