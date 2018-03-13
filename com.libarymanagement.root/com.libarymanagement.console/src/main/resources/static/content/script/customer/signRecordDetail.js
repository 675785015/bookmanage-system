/**
 * Created by lee on 2016/11/18.
 */
$(function () {

    $(".select2").select2();

});
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {
        goStatus:''
    },
    //列表
    orders:[],
    status:["未投资","已投资"],
    goId:{}
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj = $("#defaultForm").serializeObject(); //初始化 model.search 对象
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=15;
    },
    //初始化远程数据
    created:function(){
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        //表单登录验证封装
        myInitValidateForm: function (obj) {
            obj.bootstrapValidator({
                fields: {
                    phone: {
                        validators: {
                            regexp: {
                                regexp: /^1[3|4|5|7|8][0-9]\d{4,8}$/,
                                message: '手机号码不正确'
                            },
                            notEmpty: {
                                message:"手机号码不能为空"
                            }
                        }
                    }
                }
            });
        },
        search:function(event){
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            $("#btnSearch").addClass("disabled");//禁用按钮
            $.get("/api/customer/signRecordDetails",model.searchObj,function(response){
                if (response.success_is_ok){
                    vm.orders=response.data;

                    var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                    if (pageCount>0){
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            curr:model.searchObj.pageIndex+1,//设置当前页
                            groups: 7, //连续显示分页数
                            jump: function(obj, first){ //触发分页后的回调
                                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex=obj.curr -1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }
                }
                $("#btnSearch").removeClass("disabled");//解除禁用
            });
        },
        openEditForm : function(index){

            vm.goId = model.orders[index].goodsId;
            $('#myModal').modal()
            //var content = $("#editForm").show();
            //
            //layer.open({
            //    title: '修改用户信息',
            //    type: 1,
            //    skin: 'layui-layer-demo', //样式类名
            //    closeBtn: 1, //不显示关闭按钮
            //    anim: 2,
            //    shadeClose: true, //开启遮罩关闭
            //    content: content.show(),
            //    cancel:function(){
            //        window.parent.document.getElementById("editForm").style="display:none"
            //    }
            //});
        },
        excel:function(loginId){
            window.open("/api/customer/download/"+loginId)
        }
    }
});