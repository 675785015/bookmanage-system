
//表单登录验证封装
function initValidateForm() {
    $('#fileForm').bootstrapValidator({
        fields: {
            mobile: {
                message: '手机号校验失败',
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: /^[1]{1}[3,4,5,8]{1}[0-9]{9}$/,
                        message: '手机号格式不正确'
                    }
                }
            },
            cardNumber: {
                validators: {
                    notEmpty: {
                        message: '学生证号不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 10,
                        message: '学生证号长度6-10'
                    },
                    regexp: {
                        regexp: /^[0-9]*$/,
                        message: '学生证号格式不正确'
                    }
                }
            },
            collegeName: {
                validators: {
                    notEmpty: {
                        message: '学院不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 15,
                        message: '学院长度3-15'
                    }
                }
            },
            trueName:{
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 20,
                        message: '姓名长度2-20'
                    }
                }
            }
        }
    });
}

$(function(){
    initValidateForm();
})

//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    filepath:'',
    isDisabled:false,
    member:{}
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
        el: ".content",
        data: model,
        beforeCreate: function () {
            initValidateForm();
        },
        //初始化远程数据
        created: function () {

            //this.search();
        },
        //方法，可用于绑定事件或直接调用
        methods: {
            search: function (event) {

                $.get("/api/category/list", model.searchObj, function (response) {
                    if (response.success_is_ok) {
                        vm.categorys = response.data;
                    }
                });
            },
            formSubmit:function(){
                var bootstrapValidator = $("#fileForm").data('bootstrapValidator').validate();
                if (!bootstrapValidator.isValid()) {
                    return;
                }
                vm.isDisabled = true;
                //for(var e in member){
                //    alert(e+":"+member[e]);
                //}
                var obj = $("#fileForm").serializeObject();
                $.post("/api/member/addMember",obj,function(response){
                    if(response.success_is_ok){
                        layer.msg(response.msg,{icon: 1,time:3000},function(){
                            window.location.reload();
                        });
                    }else{
                        layer.msg(response.error,{icon: 5});
                    }
                    vm.isDisabled = false;
                })
            }
        }
    });

