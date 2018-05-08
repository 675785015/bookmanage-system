//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //列表
    orderlist: [],
    status: ["否", "是"]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 10;
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {

            $.get("/api/order/orderList", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.orderlist = response.data;
                    if (response.count > 0) {
                        var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages: pageCount, //总页数
                            curr: model.searchObj.pageIndex + 1,
                            groups: 7, //连续显示分页数
                            jump: function (obj, first) { //触发分页后的回调
                                if (!first) { //点击跳页触发函数自身，并传递当前页：obj.curr
                                    vm.searchObj.pageIndex = obj.curr - 1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    } else {
                        $("#pageNum").html("");
                    }
                }
            });
        },
        getlist: function (state) {
            model.searchObj.onShelfState = state;
            vm.search();
        },
        backBook: function (id) {
            layer.confirm('确认归还此书？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("/api/order/backBook", {id: id}, function (response) {
                    if (response.success_is_ok) {
                        layer.msg(response.msg);
                        vm.search();
                    } else {
                        layer.msg(response.error)
                    }
                })
            });
        },
        toOrderDetail:function(id){
            window.location.href="/toOrderDetail/"+id;
        }

    }
});