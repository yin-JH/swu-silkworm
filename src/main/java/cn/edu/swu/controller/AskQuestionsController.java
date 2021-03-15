package cn.edu.swu.controller;

import cn.edu.swu.service.AskQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0.01/askOnWeb")
public class AskQuestionsController {

    @Autowired
    AskQuestionsService askQuestionsService;

    /**
     * @methodName：askOneQ
     * @author: yin
     * @date: 2021/3/4  14:21
     * @param：String question
     * @return：String
     * @throws:
     * @description: 这个接口是提供给网页的问答接口，通过该接口可以输入问题，然后后端处理再返回回答的答案
     */

    @RequestMapping("askOneQ")
    public String askOneQ(@RequestParam(name = "question") String question){

        System.out.println(question);

        return askQuestionsService.askOneQUseSearchEngine(question);
    }

}
