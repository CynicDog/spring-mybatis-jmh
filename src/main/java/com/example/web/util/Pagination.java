package com.example.web.util;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pagination {

    private int rows;
    private int pages = 5;
    private int page;
    private int totalRows;
    private int totalPages;
    private int totalBlocks;
    private int currentBlock;
    private int beginPage;
    private int endPage;
    private boolean isFirst;
    private boolean isLast;
    private int prePage;
    private int nextPage;
    private int begin;
    private int end;

    public Pagination(int rows, int page, int totalRows) {
        this.rows = rows;
        this.page = page;
        this.totalRows = totalRows;

        init();
    }

    private void init() {
        this.totalPages = (int) Math.ceil((double) totalRows / rows);
        this.totalBlocks = (int) Math.ceil((double) totalPages / pages);
        this.currentBlock = (int) Math.ceil((double) page / pages);
        this.beginPage = (currentBlock - 1) * pages + 1;
        this.endPage = currentBlock * pages;

        if (currentBlock == totalBlocks) {
            this.endPage = totalPages;
        }

        isFirst = (page == 1);
        isLast = (page == totalPages);

        prePage = page - 1;
        nextPage = page + 1;

        begin = (page - 1) * rows + 1;
        end = page * rows;
    }

}
