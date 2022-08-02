package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.article.HomeController;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void assertThatTest(){
        int rs = 10;
        assertThat(rs).isEqualTo(10);
    }

    @Test
    public void ioc__articleController() {
        ArticleController articleController = Container.getArticleController();

        assertThat(articleController).isNotNull();
    }
    @Test
    public void ioc__homeController() {
        HomeController homeController = Container.getHomeController();

        assertThat(homeController).isNotNull();
    }

    @Test
    public void ioc__articleController__withSigleton() {
        ArticleController articleController1 = Container.getArticleController();
        ArticleController articleController2 = Container.getArticleController();

        assertThat(articleController1).isEqualTo(articleController2);
    }

    @Test
    public void ioc__homeController__withSigleton() {
        HomeController homeController1 = Container.getHomeController();
        HomeController homeController2 = Container.getHomeController();

        assertThat(homeController2).isEqualTo(homeController1);
    }

    @Test
    public void ioc__searchAllControllerAnnotationClass() {
        List<String> controllerNames = Container.getAllControllerNames();

        controllerNames.contains("home");
        controllerNames.contains("article");
    }
}
