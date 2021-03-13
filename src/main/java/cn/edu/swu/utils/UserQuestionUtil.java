package cn.edu.swu.utils;

import cn.edu.swu.entity.UserQuestion;
import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.mapper.UserQuestionMapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: UserQuestion
 * @author： yin
 * @date： 2021/3/11 22:17
 * @description：该工具类的作用与用户提问有关，拥有记录用户提问，读取数据库中保存的用户提问的功能
 */
public class UserQuestionUtil {

    private static final UserQuestionUtil INSTANCE = new UserQuestionUtil();

    private static SqlSessionTemplate sqlSessionTemplate;
    private static SqlSession sqlSessionForQuestionMapper;

    private static UserQuestionMapper userQuestionMapper;

    static {
        sqlSessionTemplate = SpringUtil.getBean(SqlSessionTemplate.class);
        sqlSessionForQuestionMapper = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

        userQuestionMapper = sqlSessionForQuestionMapper.getMapper(UserQuestionMapper.class);
    }

    private UserQuestionUtil(){

    }

    public static UserQuestionUtil getInstance(){
        return INSTANCE;
    }

    public void saveUserQuestion(String userProblem, Long answerId){
        UserQuestion userQuestion = new UserQuestion();

        userQuestion.setUserProblem(userProblem);
        userQuestion.setSystemAnswer(answerId);
        userQuestion.setFlag(1);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        String format = formatter.format(date);

        userQuestion.setAskDate(format);

        userQuestionMapper.saveOneUserQuestion(userQuestion);
        sqlSessionForQuestionMapper.commit();
    }
}
