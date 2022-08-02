package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void assertThatTest(){
        int rs = 10;
        assertThat(rs).isEqualTo(10);
    }

    @Test
    public void ioc__articleController() {
        ArticleController articleController = Con.getArticleController();

        assertThat(articleController).isNotNull();
    }
}
