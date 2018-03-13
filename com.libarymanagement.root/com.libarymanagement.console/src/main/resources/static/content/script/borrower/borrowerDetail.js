/**
 * Created by Jack on 2016/11/18.
 */
//Vue实例
//Model
var model = {
    detail:{},
    files:{}
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
    },
    //初始化远程数据
    created:function(){
        this.search();
    },
    mounted:function(){
        //在 el 被替换后，做页面元素变动的操作
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search: function (event) {
            $.post("/api/borrower/borrowerDetail", {bId: $("#bId").val()}, function (response) {
                if (response.success_is_ok) {
                    vm.detail = response.data;
                    $.post("/api/borrowerFiles/getBorrowerFile", {loginId: vm.detail.loginId}, function (response) {
                        if (response.success_is_ok) {
                            vm.files = response.data;
                        }
                    })
                }
            });
        },
        openViewForm: function (path) {
            //console.log(id);
            var tempObj = $('#viewMaterialModal').clone();
            tempObj.find('img').attr('src', path);
            var tempHtml = tempObj.html();
            layer.open({
                title: '预览材料',
                content: tempHtml,
                btn: ['确定'],
                area: ['600px', '600px'],
                btn1: function () {
                    layer.closeAll();
                }
            });
        }
    }
});
