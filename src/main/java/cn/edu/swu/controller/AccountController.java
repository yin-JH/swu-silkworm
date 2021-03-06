package cn.edu.swu.controller;

import cn.edu.swu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v0.01/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @ResponseBody
    @RequestMapping("/login")
    public String login(@RequestParam(name = "accountName") String accountName, @RequestParam("accountPassword") String accountPassword, HttpServletRequest request){
        return accountService.login(accountName, accountPassword, request);
    }
}
