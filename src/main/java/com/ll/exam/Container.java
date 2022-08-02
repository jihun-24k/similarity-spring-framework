package com.ll.exam;

import com.ll.exam.annotation.AutoWired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.Repository;
import com.ll.exam.annotation.Service;
import org.reflections.Reflections;

import java.util.*;

public class Container {
    private static Map<Class, Object> objects;

    static {
        objects = new HashMap<>();
        scanComponents();
    }

    public static void scanComponents(){
        scanRepositories();
        scanServices();
        scanControllers();

        // 모든 컴포넌트들을 의존성을 해결해주는 메소드
        resolveDependenciesAllComponents();
    }

    // 모든 컴포넌트들의 의존성 검사
    public static void resolveDependenciesAllComponents(){
        for (Class<?> cls : objects.keySet()){
            Object o = objects.get(cls);

            resolveDependencies(o);
        }
    }

    // 컴포넌트들의 의존성 해결
    public static void resolveDependencies(Object o){
        // 오브젝트를 리스트로 받아와
        Arrays.asList(o.getClass().getDeclaredFields())
                // 문자열로 반환 시켜
                .stream()
                // 필터링을 통해 AutoWired인 필드만 들고오고
                .filter(f->f.isAnnotationPresent(AutoWired.class))
                // map 요소에 접근하게 만들어주고
                .map(field -> {field.setAccessible(true); return field;
                })
                // 향상된 반복문 == forEach
                .forEach(field -> {
                    Class cls = field.getType();
                    Object dependency = objects.get(cls);
                    try {
                        field.set(o,dependency);
                    } catch (IllegalAccessException e) {
                    }
                });

    }

    public static void scanRepositories(){
        Reflections ref = new Reflections("com.ll.exam");
        for(Class<?> cls : ref.getTypesAnnotatedWith(Repository.class)){
            objects.put(cls, Util.cls.newObj(cls,null));
        }
    }

    public static void scanServices(){
        Reflections ref = new Reflections("com.ll.exam");
        for(Class<?> cls : ref.getTypesAnnotatedWith(Service.class)){
            objects.put(cls, Util.cls.newObj(cls,null));
        }
    }

    public static void scanControllers(){
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
