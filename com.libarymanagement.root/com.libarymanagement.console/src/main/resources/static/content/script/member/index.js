//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //列表
    memberlist: [],
    status: ["销户", "正常"]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
        model.searchObj.pageIndex = 0;
        model.searchObj.pageSize = 10;
        model.searchObj.status = '';
    },
    //初始化远程数据
    created: function () {
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {

            $.get("/api/member/getMemberList", model.searchObj, function (response) {
                if (response.success_is_ok) {
                    vm.memberlist = response.data;
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
                                    console.log(obj.curr);
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
        onshelf: function (id, status) {
            layer.confirm('确认此书' + (status == 1 ? '上架' : '下架') + '？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("/api/book/onshelf", {bookId: id, status: status}, function (response) {
                    if (response.success_is_ok) {
                        layer.msg(response.msg, {icon: 1});
                        vm.search();
                    } else {
                        layer.msg(response.error)
                    }
                })
            });
        },
        todetail: function (id) {
            window.location.href = "/toMemberDetail/" + id;
        },
        myorders: function (id) {
            window.location.href = "";
        },
        //注销
        logout: function (id) {

            var formValue = {};
            formValue.id = id;
            layer.prompt({
                formType: 2,
                value: '',
                title: "请写明注销原因",
                area: ['300px', '170px'] //自定义文本域宽高
            }, function (value, index, elem) {
                formValue.reason = value;
                layer.close(index);
                $.post("/api/member/logoutMember", formValue, function (response) {
                    if (response.success_is_ok) {
                        layer.msg("已注销",{icon:1},function(){vm.search();});
                    } else {
                        layer.alert(response.error);
                    }
                });
            });
        }
    }
});