package cn.edu.swu.mapper;

import cn.edu.swu.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    List<Question> getAllQuestions();

    Question getQuestionById(@Param("id") Long id);

    Long getMaxId();

    void saveQuestion(@Param("question") Question question);

    void updateAll(@Param("id") Long id,
                   @Param("problem") String problem,
                   @Param("keywords") String keywords,
                   @Param("type") String type,
                   @Param("media_type") String mediaType,
                   @Param("answer") String answer);

    void updateQuestionKeywords(@Param("id") Long id, @Param("keywords") String keywords);

    void updateProblem(@Param("id") Long id, @Param("problem") String problem);

    void updateFlag(@Param("id") Long id, @Param("flag") int flag);

    void updateType(@Param("id") Long id, @Param("type") String type);

    void updateMediaType(@Param("id") Long id, @Param("media_type") String mediaType);

    void updateAnswer(@Param("id") Long id, @Param("answer") String answer);
}
