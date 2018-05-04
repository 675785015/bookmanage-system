//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    filepath:'',
    realpath:'http://localhost:8081/image/timg.jpg',
    rootPath:'http://localhost:8081',
    isDisabled:false
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
        el: ".content",
        data: model,
        beforeCreate: function () {
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
            //使用js来提交表单，表单不能跳转
            submitUpload: function () {
                var option = {
                    url:"/fileUpload/uploadPic",//如果当前ajax指定url就使用该url，如果没有指定就使用表单中的url
                    data:{
                        fileName:"imgsFile"
                    },
                    dataType:"json",
                    success:function(response){
                        if (response.success_is_ok) {
                            vm.filepath = response.data;
                            vm.realpath = vm.rootPath+response.data;
                        }else{
                            layer.msg(response.error,{icon:5});
                        }

                    },
                    error:function(){
                        alert("系统错误");
                    }
                };
                //使用ajax方式提交表单
                $("#fileForm").ajaxSubmit(option);
            },
            formSubmit:function(){
                vm.isDisabled = true;
                var obj = $("#fileForm").serializeObject();
                $.post("/api/book/addBook",obj,function(response){
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


