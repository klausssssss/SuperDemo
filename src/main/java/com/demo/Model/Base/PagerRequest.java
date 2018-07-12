package com.demo.Model.Base;

import javax.validation.constraints.NotNull;

public class PagerRequest {
    @NotNull(message = "分页信息不可为空")
    private Integer pageSize;
    @NotNull(message = "分页信息不可为空")
    private Integer pageIndex;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
