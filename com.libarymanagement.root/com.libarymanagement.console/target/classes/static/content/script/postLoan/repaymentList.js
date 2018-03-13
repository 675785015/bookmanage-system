/**
 * Created by Lee on 2016/11/17.
 */

$(function () {

    $(".select2").select2();

    /*时间选择*/
    $('.chooseDate').datepicker({
        format: 'yyyy-mm-dd',
        weekStart: 1,
        autoclose: true,
        todayBtn: 'linked',
        language: 'cn'
    });
});
//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //标的
    plan: {},
    //台账列表
    incomeDetailList : [],
    fundType : ['','本金','利息'],
    repayStatus : ['还款已冻结', '未还款', '已还款']


};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: ".content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
        model.searchObj = $("#defaultForm").serializeObject(); //初始化 model.search 对象
        model.searchObj.bpStatusSear = '';
        model.searchObj.pageIndex=0;
        model.searchObj.pageSize=15;
    },
    //初始化远程数据
    created:function(){
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){
            if((model.searchObj.searchDateSt!=null && model.searchObj.searchDateSt!= "")
                ||(model.searchObj.searchDateEnd!=null && model.searchObj.searchDateEnd!="")){
                if(model.searchObj.searchDateType==null || model.searchObj.searchDateType ==''){
                    layer.alert("请选择时间查询类型");
                    return;
                }
            }
            if (typeof event !== "undefined"){ //点击查询按钮的话，是查询第一页数据
                model.searchObj.pageIndex=0;
            }

            $("#searchBtn").addClass("disabled");//禁用按钮
            $.get("/api/incomeDetail/getRepaymentList",model.searchObj,function(response){
                if (response.success_is_ok){
                    vm.incomeDetailList=response.data;
                    if (response.count>0){
                        var pageCount = Math.ceil(response.count / model.searchObj.pageSize);
                        //调用分页
                        laypage({
                            cont: $('#pageNum'), //容器。值支持id名、原生dom对象，jquery对象,
                            pages : pageCount, //总页数
                            curr : model.searchObj.pageIndex+1,
                            groups: 7, //连续显示分页数
                            jump: function(obj, first){ //触发分页后的回调
                                if(!first){ //点击跳页触发函数自身，并传递当前页：obj.curr
                                    console.log(obj.curr);
                                    vm.searchObj.pageIndex=obj.curr -1;
                                    vm.search();
                                }
                            },
                            skin: '#3c8dbc'
                        });
                    }else{
                        $("#pageNum").html("");
                    }
                }
                $("#searchBtn").removeClass("disabled");//解除禁用
            });
        },
        openView : function(bpId, period){
            window.location.href = "/postLoan/incomeDetail/"+bpId+"/"+period;
        },
        repay : function(bpId, period){
            layer.open({
                title:'现在确认还款吗',
                btn: ['确定', '取消'],
                area:['300px'],
                btn1: function(){
                    layer.load(1)
                    $.post("/api/bidPlan/repay", {indBpId:bpId, indPeriods:period}, function(response){
                        layer.closeAll();
                        if (response.success_is_ok){
                            layer.msg(response.msg);
                            setTimeout(function(){window.location.href = "/postLoan/index"}, 3000);
                        }else{
                            layer.alert(response.error);
                        }
                    })
                },
                btn2: function(){
                    layer.closeAll();
                }
            });
        },
        download : function(){
            if((model.searchObj.searchDateSt!=null && model.searchObj.searchDateSt!= "")
                ||(model.searchObj.searchDateEnd!=null && model.searchObj.searchDateEnd!="")){
                if(model.searchObj.searchDateType==null || model.searchObj.searchDateType ==''){
                    layer.alert("请选择时间查询类型");
                    return;
                }
            }
            window.open("/api/incomeDetail/download?searchDateType="+model.searchObj.searchDateType+"&searchDateSt="+model.searchObj.searchDateSt+"&searchDateEnd="+model.searchObj.searchDateEnd);
        },
        createcard : function(){
            if((model.searchObj.searchDateSt!=null && model.searchObj.searchDateSt!= "")
                ||(model.searchObj.searchDateEnd!=null && model.searchObj.searchDateEnd!="")){
                if(model.searchObj.searchDateType==null || model.searchObj.searchDateType ==''){
                    layer.alert("请选择时间查询类型");
                    return;
                }
            }
            window.open("/api/incomeDetail/createcard?searchDateType="+model.searchObj.searchDateType+"&searchDateSt="+model.searchObj.searchDateSt+"&searchDateEnd="+model.searchObj.searchDateEnd);
        },
        IncomeCard : function(){
            if((model.searchObj.searchDateSt!=null && model.searchObj.searchDateSt!= "")
                ||(model.searchObj.searchDateEnd!=null && model.searchObj.searchDateEnd!="")){
                if(model.searchObj.searchDateType==null || model.searchObj.searchDateType ==''){
                    layer.alert("请选择时间查询类型");
                    return;
                }
            }
            window.open("/api/incomeDetail/incomecard?searchDateType="+model.searchObj.searchDateType+"&searchDateSt="+model.searchObj.searchDateSt+"&searchDateEnd="+model.searchObj.searchDateEnd);
        },
        monthCount : function(){
            if((model.searchObj.searchDateSt==null || model.searchObj.searchDateSt== "")){
                layer.alert("请选择要查询的起始时间")
            }
                var searchDate = model.searchObj.searchDateSt;
                var reg = new RegExp("(\\d{4})-(\\d{2})-(\\d{2})")
                var datess = searchDate.replace(reg,'$1-$2-01')

                layer.msg('当前下载为'+datess+"起,当月汇总", {
                    time: 0 //不自动关闭
                    ,btn: ['下载', '取消']
                    ,yes: function(index){
                        layer.close(index);
                        window.open("/api/incomeDetail/monthlyDownload?dateStr="+datess);
                    }
                });
        }
    }
});


$("#searchDateSt").change(function(){
    vm.searchObj.searchDateSt=$(this).val();
});
//
$("#searchDateEnd").change(function(){
    vm.searchObj.searchDateEnd=$(this).val();
});

