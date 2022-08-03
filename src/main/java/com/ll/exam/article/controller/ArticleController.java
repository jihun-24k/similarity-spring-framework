package com.ll.exam.article.controller;

import com.ll.exam.Rq;
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
    public void showList(Rq rq) {
        rq.println("게시물 리스트");
    }

    @GetMapping("/usr/article/detail/{boardCode}/{id}")
    public void showDetail(Rq rq) {
        rq.println("게시물 상세페이지");
    }

    @GetMapping("/usr/article/modify/{boardCode}/{id}")
    public void showModify(Rq rq) {
        rq.println("게시물 수정페이지");

        long id = rq.getLongPathValueByIndex(1, -1);
        // long id = rq.getLongParam("id"); // 곧 기능 구현
    }
}
