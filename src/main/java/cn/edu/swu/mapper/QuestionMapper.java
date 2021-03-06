package cn.edu.swu.mapper;

import cn.edu.swu.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    public List<Question> getAllQuestions();

    public void saveQuestion(@Param("question") Question question);

    public void updateQuestionKeywords(@Param("id") Long id, @Param("keywords") String keywords);

    void updateProblem(@Param("id") Long id, @Param("problem") String problem);
}
