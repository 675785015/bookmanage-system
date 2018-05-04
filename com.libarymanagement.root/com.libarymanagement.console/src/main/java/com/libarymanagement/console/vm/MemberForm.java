package com.libarymanagement.console.vm;

import com.libarymanagement.core.pojo.TbMember;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Lee on 2018/4/25.
 */
public class MemberForm {


    public TbMember toEntity(MemberForm form){
        TbMember member = new TbMember();
        member.setCardNumber(form.getCardNumber().trim());
        member.setCollegeName(form.getCollegeName().trim());
        member.setMobile(form.getMobile().trim());
        return member;
    }
    @NotBlank(message = "学号不能为空")
    @Size(min = 8, max = 15,message = "学号应为8-15字符")
    private String cardNumber;
    @NotBlank(message = "学院名不能为空")
    private String collegeName;
    @NotBlank(message = "姓名不能为空")
    @Size(min = 2,max = 20,message = "姓名应为2-20个字符")
    private String trueName;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$",message = "手机号码格式不正确")
    private String mobile;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
