//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
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
            formSubmit:function(){
                vm.isDisabled = true;
                var obj = $("#fileForm").serializeObject();
                $.post("/api/order/addOrder",obj,function(response){
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


/*开始时间选择*/
laydate({
    elem:'#planDate',
    istime: true,
    istoday : false,
    format: 'YYYY-MM-DD',
    choose : function(datas) {
        $("#fileForm").data('bootstrapValidator').updateStatus("planDate","VALID","notEmpty");
    }
});