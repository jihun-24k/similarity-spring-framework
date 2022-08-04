package com.ll.exam.article.controller;

import com.ll.exam.Rq;
import com.ll.exam.annotation.AutoWired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.service.ArticleService;

import java.util.List;

@Controller // ArticleController는 컨트롤러라고 명시
public class ArticleController {

    @AutoWired
    ArticleService articleService;
    @GetMapping("/usr/article/list/{boardCode}") // /usr/article/list/free 와 같이 관련된 요청을 처리하는 함수이다.
    // 아래 showList 는 Get /usr/article/list 으로 요청이 왔을 때 실행 되어야 하는 함수이다.
    public void showList(Rq rq) {
        List<ArticleDto> articleDtos = articleService.getArticles();

        rq.setAttr("articles", articleDtos);
        rq.view("usr/article/list");
    }

    @GetMapping("/usr/article/detail/{boardCode}/{id}")
    public void showDetail(Rq rq) {
        rq.println("게시물 상세페이지<br>");

        long id = rq.getLongPathValueByIndex(1, -1);
        // long id = rq.getLongParam("id"); // 곧 기능 구현

        rq.println("%d번 게시물".formatted(id));
    }

    @GetMapping("/usr/article/modify/{boardCode}/{id}")
    public void showModify(Rq rq) {
        rq.println("게시물 수정페이지<br>");

        long id = rq.getLongPathValueByIndex(1, -1);
        // long id = rq.getLongParam("id"); // 곧 기능 구현

        rq.println("%d번 게시물".formatted(id));
    }
}
