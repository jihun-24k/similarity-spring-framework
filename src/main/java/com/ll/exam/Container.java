package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.article.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Container {
    private static final ArticleController articleController;
    private static final HomeController homeController;

    static {
        articleController = Util.cls.newObj(ArticleController.class, null);
        homeController = Util.cls.newObj(HomeController.class, null);
    }

    public static ArticleController getArticleController() {
        return articleController;
    }

    public static List<String> getAllControllerNames() {
        List<String> names = new ArrayList<>();

        Reflections ref = new Reflections("com.ll.exam");
        for (Class<?> cls: ref.getTypesAnnotatedWith(Controller.class)){
            String name = cls.getSimpleName();
            name = name.replace("Controller","");
            name = Util.str.decapitalize(name);

            names.add(name);
        }
        return names;
    }

    public static HomeController getHomeController() {
        return homeController;
    }
}
