package cn.edu.swu.controller;

import cn.edu.swu.entity.Question;
import cn.edu.swu.mapper.QuestionMapper;
import cn.edu.swu.service.AskQuestionsService;
import cn.edu.swu.service.TestService;
import cn.edu.swu.utils.NLPUtil;
import cn.edu.swu.utils.TermFilter;
import com.hankcs.hanlp.seg.common.Term;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    AskQuestionsService askQuestionsService;

    /*@RequestMapping("/AddQuestionFromExcelToMySql")
    @ResponseBody
    public String AddQuestionFromExcelToMySql(){
        testService.AddQuestionFromExcelToMySql();
        return "ok";
    }*/

    @RequestMapping("01")
    @ResponseBody
    public String test01(){
        String q = "蚕的天敌";
        askQuestionsService.askOneQ(q);
        System.err.println("问题 ： " + q);
        return "ok";
    }

    @RequestMapping("02")
    @ResponseBody
    public String test02(){
        return testService.test02();
    }
}
