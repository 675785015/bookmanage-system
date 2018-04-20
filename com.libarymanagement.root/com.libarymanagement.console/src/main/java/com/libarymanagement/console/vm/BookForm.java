package com.libarymanagement.console.vm;

import com.libarymanagement.core.pojo.Book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Lee on 2018/4/20.
 */
public class BookForm {

    public Book toEntity(BookForm form) {
        Book book = new Book();
        book.setName(form.getName());
        book.setAuthor(form.getAuthor());
        book.setPress(form.getPress());
        book.setPreview(form.getPreview());
        book.setIsbn(form.getIsbn());
        book.setCategoryId(form.getCategoryId());
        book.setCategoryField(form.getCategoryField());
        book.setCoverPath(form.getCoverPath());
        return book;
    }

    @NotBlank(message = "书名不能为空")
    private String name;
    @NotBlank(message = "简介不能为空")
    @Size(min = 1, max = 500)
    private String preview;
    @NotBlank(message = "作者不能为空")
    @Size(min = 1, max = 50)
    private String author;
    @NotBlank(message = "ISBN不能为空")
    @Size(min = 10, max = 20)
    private String isbn;
    @NotBlank(message = "出版社不能为空")
    @Size(min = 1, max = 50)
    private String press;
    @NotNull(message = "分类ID不能为空")
    private Long categoryId;
    @NotBlank(message = "分类信息不能为空")
    private String categoryField;
    @NotBlank(message = "图片路径不能为空")
    private String coverPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryField() {
        return categoryField;
    }

    public void setCategoryField(String categoryField) {
        this.categoryField = categoryField;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }
}
