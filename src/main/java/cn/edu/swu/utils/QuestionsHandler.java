package cn.edu.swu.utils;

import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.lang.reflect.Executable;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @className: QuestionsHandler
 * @author： yin
 * @date： 2021/3/6 20:28
 * @description：该类是一个数据持有类，将数据库中所有的question数据持有并且load到内存中，
 * 因为数据库中的数据偏少可以将数据全部存在内存中，未来当数据量提升时，该类可以考虑废弃
 */
public class QuestionsHandler {
    private static SqlSessionTemplate sqlSessionTemplate;
    private static SqlSession sqlSessionForQuestionMapper;

    private static QuestionMapper questionMapper;

    private static volatile List<Question> questions;

    static {
        sqlSessionTemplate = SpringUtil.getBean(SqlSessionTemplate.class);
        sqlSessionForQuestionMapper = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

        questionMapper = sqlSessionForQuestionMapper.getMapper(QuestionMapper.class);
    }

    private static void loadQuestions(){
        questions = questionMapper.getAllQuestions();
    }

    public static List<Question> getQuestions() {

        //双重检查 double check
        if(questions == null){
            synchronized (QuestionsHandler.class){
                if(questions == null){
                    loadQuestions();
                }
            }
        }

        return questions;
    }
}
