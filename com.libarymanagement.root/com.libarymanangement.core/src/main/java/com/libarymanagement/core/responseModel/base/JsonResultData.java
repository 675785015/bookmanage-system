package com.libarymanagement.core.responseModel.base;

/**
 * Created by Lee on 2018/2/20.
 */
public class JsonResultData<T> extends JsonResult {
    public JsonResultData() {}
    public JsonResultData(T data)
    {
        this.setSuccess_is_ok(true);
        this.setState(1);
        this.setData(data);
    }
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
