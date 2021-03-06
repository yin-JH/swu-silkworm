package cn.edu.swu.service;

import cn.edu.swu.entity.Account;
import cn.edu.swu.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public String login(String accountName, String accountPassword, HttpServletRequest request) {

        System.out.println(accountName + "    " + accountPassword);

        Account oneAccount = accountMapper.getOneAccount(accountName, accountPassword);

        System.out.println(oneAccount);

        //找不到该用户，返回失败
        if (oneAccount == null)
            return "fail";

        //验证成功，将用户信息保存到session中
        request.getSession().setAttribute("account",oneAccount);
        return "success";
    }
}
