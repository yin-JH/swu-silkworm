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

    }

}
