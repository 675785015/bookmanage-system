package com.libarymanagement.console.vm;

import com.libarymanagement.core.pojo.TbOrder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Lee on 2018/4/26.
 */
public class OrderForm {

    @NotNull
    private Long memberId;
    @NotNull
    private Long bookId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planDate;


    public TbOrder toEntity(OrderForm form) {
        TbOrder order = new TbOrder();
        order.setOrdMemberId(form.getMemberId());
        order.setOrdBookId(form.getBookId());
        order.setOrdPlanTime(form.getPlanDate());
        return order;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }
}
