package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.article.HomeController;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private static Map<Class, Object> objects;

    static {
        objects = new HashMap<>();

        objects.put(ArticleController.class, new ArticleController());
        objects.put(HomeController.class, new HomeController());
    }

    public static <T> T getObj(Class cls){
        return (T)objects.get(cls);
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
}
