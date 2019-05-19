package com.wugq.blog.service;

public interface ViewService {
    int getView(int articleId,int userId);

    void addView(int articleId,int userId);
}
