package com.ll.exam.article.controller;

import com.ll.exam.annotation.AutoWired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.service.ArticleService;

@Controller // ArticleController는 컨트롤러라고 명시
public class ArticleController {

    @AutoWired
    ArticleService articleService;
    @GetMapping("/usr/article/list/{boardCode}") // /usr/article/list/free 와 같이 관련된 요청을 처리하는 함수이다.
    // 아래 showList 는 Get /usr/article/list 으로 요청이 왔을 때 실행 되어야 하는 함수이다.
    public void showList(){
    }
    @GetMapping("/usr/article/detail/{boardCode}")
    public void showDetail(){

    }
}
