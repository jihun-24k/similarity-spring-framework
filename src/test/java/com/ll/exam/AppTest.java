package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.article.repository.ArticleRepository;
import com.ll.exam.article.service.ArticleService;
import com.ll.exam.home.article.HomeController;
import com.ll.exam.util.Util;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void assertThatTest() {
        int rs = 10;
        assertThat(rs).isEqualTo(10);
    }

    @Test
    public void ioc__articleController() {
        ArticleController articleController = Container.getObj(ArticleController.class);

        assertThat(articleController).isNotNull();
    }

    @Test
    public void ioc__homeController() {
        HomeController homeController = Container.getObj(HomeController.class);

        assertThat(homeController).isNotNull();
    }

    @Test
    public void ioc__articleController__withSigleton() {
        ArticleController articleController1 = Container.getObj(ArticleController.class);
        ArticleController articleController2 = Container.getObj(ArticleController.class);

        assertThat(articleController1).isEqualTo(articleController2);
    }

    @Test
    public void ioc__homeController__withSigleton() {
        HomeController homeController1 = Container.getObj(HomeController.class);
        HomeController homeController2 = Container.getObj(HomeController.class);

        assertThat(homeController2).isEqualTo(homeController1);
    }

    @Test
    public void ioc__searchAllControllerAnnotationClass() {
        List<String> controllerNames = Container.getAllControllerNames();

        controllerNames.contains("home");
        controllerNames.contains("article");
    }

    @Test
    public void ioc__articleService() {
        ArticleService articleService = Container.getObj(ArticleService.class);
        assertThat(articleService).isNotNull();
    }

    @Test
    public void ioc__articleService__withSingleton() {
        ArticleService articleService1 = Container.getObj(ArticleService.class);
        ArticleService articleService2 = Container.getObj(ArticleService.class);

        assertThat(articleService1).isEqualTo(articleService2);
    }

    @Test
    public void ioc__articleRepository() {
        ArticleRepository articleRepository = Container.getObj(ArticleRepository.class);
        assertThat(articleRepository).isNotNull();
    }

    @Test
    public void ioc__articleRepository__withSingleton() {
        ArticleRepository articleRepository1 = Container.getObj(ArticleRepository.class);
        ArticleRepository articleRepository2 = Container.getObj(ArticleRepository.class);

        assertThat(articleRepository1).isEqualTo(articleRepository2);
    }

    @Test
    public void ioc__resolveComponents__ContoSer() {
        ArticleController articleController = Container.getObj(ArticleController.class);
        ArticleService articleService = Util.reflection.getFieldValue(articleController, "articleService", null);

        assertThat(articleService).isNotNull();
    }

    @Test
    public void ioc__resolveComponents__SertoRep() {
        ArticleService articleService = Container.getObj(ArticleService.class);

        ArticleRepository articleRepository = Util.reflection.getFieldValue(articleService, "articleRepository", null);

        assertThat(articleRepository).isNotNull();
    }

    @Test
    public void ControllerManager__scanMappings() {
        ControllerManager.init(); // 클래스를 강제로 로딩되게 하려는 목적
    }
    @Test
    public void ControllerManager__routeInfoTest() {
        Map<String, RouteInfo> routeInfos = ControllerManager.getRouteInfosForTest();
        System.out.println(routeInfos);

        assertThat(routeInfos.size()).isEqualTo(3);
    }
}
