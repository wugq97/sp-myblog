package com.wugq.blog.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageInfo<T> implements Serializable {
    private int perPage = 20;

    private int currentPage = 1;

    private int count = 0;

    private int maxPage = 1;

    private List<T> items = new ArrayList<>();

    public PageInfo(){}

    public PageInfo(int currentPage, int perPage) {
        this.currentPage = currentPage;
        this.perPage = perPage;
    }

    public int getStartIndex() {
        return (this.currentPage - 1) * this.perPage;
    }

    public void setCount(int count) {
        this.count = count;
        if (this.count % this.perPage == 0) {
            this.maxPage = this.count / this.perPage;
        } else {
            this.maxPage = this.count / this.perPage + 1;
        }
        if (this.maxPage < 1)
            this.maxPage = 1;
        if (this.currentPage > this.maxPage || this.currentPage < 1)
            this.currentPage = this.maxPage;
    }

    public int getPerPage() {
        return this.perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCount() {
        return this.count;
    }

    public int getMaxPage() {
        return this.maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
