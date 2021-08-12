package com.young.vo;

import com.young.domain.Orders;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 传参工具类
 */
public class OrdersVo extends Orders {
    //当前页码
    private Integer page;
    //每页显示数量
    private Integer limit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    //离职时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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
}
