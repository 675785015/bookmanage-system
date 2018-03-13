/**
 * Created by Jack on 2016/11/18.
 */
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    borrowers:[],
    borrType:["","个人","企业"],
    type:["","房产抵押","车辆抵押","信用贷"]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj=$("#search_form").serializeObject();
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=15;
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
        search:function(event){
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }
            $("#btnSearch").addClass("disabled");//禁用按钮
            var loadIndex = layer.load(2);
            $.get("/api/borrower/borrowerList",model.searchObj,function(response){
                if (response.success_is_ok){
                    vm.borrowers=response.data;
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
                layer.close(loadIndex);
            });
        },
        findParentDepartmentFunc:function(teamId){
        },
        //绑定表单验证
        bindFormValidate:function($form){
            $form.bootstrapValidator({
                fields: {
                    name: {
                        validators: {
                            notEmpty: {
                                message: '姓名不能为空'
                            },
                            stringLength: {
                                min: 2,
                                max: 5,
                                message: '姓名长度必须在2到5位之间'
                            }
                        }
                    },
                    mobile: {
                        validators: {
                            notEmpty: {
                                message: '手机号不能为空'
                            },
                            regexp: {
                                regexp: /^1[3-8]\d{9}$/,
                                message: '请输入正确的手机号码'
                            }
                        }
                    }
                }
            })
        },
        openView:function(empId){
            window.location.href = "/borrower/detail/"+empId;
        }
    }
});
