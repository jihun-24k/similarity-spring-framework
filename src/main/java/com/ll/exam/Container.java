package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.Service;
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
        scanService();
        scanController();
    }

    public static void scanService(){
        Reflections ref = new Reflections("com.ll.exam");
        for(Class<?> cls : ref.getTypesAnnotatedWith(Service.class)){
            objects.put(cls, Util.cls.newObj(cls,null));
        }
    }

    public static void scanController(){
        Reflections ref = new Reflections("com.ll.exam");
        for(Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)){
            objects.put(cls, Util.cls.newObj(cls,null));
        }
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
