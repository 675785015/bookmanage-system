package com.libarymanagement.console.vm;

import com.libarymanagement.core.pojo.TbOrder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Lee on 2018/4/26.
 */
public class OrderForm {

    @NotNull
    private Long memberId;
    @NotBlank
    private String isbn;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planDate;




    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }
}
