package com.libarymanagement.console.category;

import com.libarymanagement.console.vm.CategoryForm;
import com.libarymanagement.core.pojo.Category;
import com.libarymanagement.core.responseModel.base.JsonResult;
import com.libarymanagement.core.responseModel.base.JsonResultError;
import com.libarymanagement.core.responseModel.base.JsonResultList;
import com.libarymanagement.core.responseModel.base.JsonResultOk;
import com.libarymanagement.core.service.CategoryService;
import com.libarymanagement.core.utils.ValidationUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by Lee on 2018/2/8.
 */
@Controller
@RequestMapping("api/category")
public class CategoryController {


    @Autowired
    public CategoryService categoryService;

    //categorylist列表
    @RequestMapping("list")
    @ResponseBody
    public JsonResult categoryList(Long categoryId) {
        List<Category> list = categoryService.getCategorys(categoryId);

        return new JsonResultList<>(list);
    }


    //添加category
    @RequestMapping("add")
    @ResponseBody
    public JsonResult addCategory(@ModelAttribute("form") CategoryForm form) {
        //校验对象参数
        String s = ValidationUtil.validateModel(form);
        if (!StringUtils.isEmpty(s)) {
            return new JsonResultError(s);
        }

        Category entity = form.getEntity(form);
        int i = categoryService.addUpdateDelCategory(entity);
        if (i > 0) {
            return categoryList(form.getParentId());
        } else {
            return new JsonResultError();
        }
    }

    //删除category
    @RequestMapping("remove")
    @ResponseBody
    public JsonResult remove(@Param("category")Category category) {
        category.setIsDel(1);
        int i = categoryService.addUpdateDelCategory(category);
        if (i > 0) {
            JsonResult jsonResult = categoryList(category.getParentId());
            return jsonResult;
        } else {
            return new JsonResultError("删除失败");
        }
    }
}
