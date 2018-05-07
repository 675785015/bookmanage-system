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
        },
        //初始化远程数据
        created: function () {
            this.search();
        },
        //方法，可用于绑定事件或直接调用
        methods: {
            search: function (event) {
                var memberId = $("#memberId").val();
                $.get("/api/member/getMemberDetail", {id:memberId}, function (response) {
                    if (response.success_is_ok) {
                        vm.member = response.data;
                    }else{
                        layer.msg("查询失败");
                    }
                });
            },
            formSubmit:function(){
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


