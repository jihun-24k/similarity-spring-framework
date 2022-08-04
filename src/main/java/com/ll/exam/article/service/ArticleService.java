package com.ll.exam.article.service;

import com.ll.exam.annotation.AutoWired;
import com.ll.exam.annotation.Service;
import com.ll.exam.article.dto.ArticleDto;
import com.ll.exam.article.repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {
    @AutoWired
    ArticleRepository articleRepository;

    public List<ArticleDto> getArticles() {
        return articleRepository.getArticles();
    }
}
