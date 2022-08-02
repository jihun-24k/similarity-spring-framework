package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;

public class Con {
    public static ArticleController getArticleController() {
        return new ArticleController();
    }
}
