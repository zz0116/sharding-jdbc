package com.zyz.demo.ssm.controller;

import com.zyz.demo.ssm.model.Article;
import com.zyz.demo.ssm.model.User;
import com.zyz.demo.ssm.service.ArticleService;
import com.zyz.demo.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    private UserService userService;
    private ArticleService articleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @RequestMapping("login.action")
    public String login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        Model model) {
        User user = userService.selectUserByName(username);
        String pwd = user.getPassword();
        if (pwd.equals(password)) {
            model.addAttribute("user", user);

            List<Article> articles = articleService.selectArticleByUid(user.getUid());
            model.addAttribute("articles", articles);
            return "userinfo";
        }
        return "failure";
    }

    @RequestMapping("register.action")
    public String register(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           Model model) {
        User user = new User(username, password);
        boolean success = userService.saveUser(user);
        if (success) {
            return "success";
        }
        return "failure";
    }
}
