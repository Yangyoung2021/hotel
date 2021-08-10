package com.young.vo;

import com.young.domain.Role;

/**
 * 传参工具类
 */
public class RoleVo extends Role {
    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "RoleVo{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
