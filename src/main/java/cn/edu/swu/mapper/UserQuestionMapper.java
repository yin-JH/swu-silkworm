package cn.edu.swu.mapper;

import cn.edu.swu.entity.UserQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserQuestionMapper {
    void saveOneUserQuestion(@Param("userQuestion") UserQuestion userQuestion);

    List<UserQuestion> loadAllUserQuestions();
}
