package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;

import java.util.ArrayList;
import java.util.List;

public class Container {
    private static final ArticleController articleController;

    static {
        articleController = new ArticleController();
    }

    public static ArticleController getArticleController() {
        return articleController;
    }

    public static List<String> getAllControllerNames() {
        return new ArrayList<>();
    }
}
