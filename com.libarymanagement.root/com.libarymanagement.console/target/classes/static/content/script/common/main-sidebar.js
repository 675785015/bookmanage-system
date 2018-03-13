/**
 * Created by wenyq on 2017/6/28.
 */

//Vue实例
//Model
/*$(window).load(function(){
    activeMenu();
});*/
var model = {
    dataside:[]
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vms = new Vue({
    el: ".sidebar-menu",
    data: model,
    beforeCreate: function () {
        //初始化本地数据
    },
    //初始化远程数据
    created: function () {
        this.searchside();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        searchside: function (event) {
            //$.post("/api/jurisdiction/selectall", null, function (response) {
            //    if (response.success_is_ok) {
            //        vms.dataside = response.data;
            //    }
            //})
        },
        showhide: function (id) {

            $("ul[class='treeview-menu']").hide(500);
            $("#t_show_"+id).show(500);
        }
    },
  updated: function() {
          activeMenu();
  }
});
//根据url地址来判断左侧菜单选中
function activeMenu() {
    var hrefVal=window.location.pathname;
    if($('#hidden_active_menu').length > 0)
    {
        hrefVal=$('#hidden_active_menu').val();
    }
    var menuObj=$('#ulList').find("a[href$='"+hrefVal+"']");
    if(typeof menuObj=="undefined"){
        return;
    }
    menuObj.parent().addClass('active');
    var menuParent={};
    var idVal="";
    var __i=0;
    while(idVal!="ulList")
    {
        menuParent=  menuObj.parent();
        idVal=menuParent.attr('id');
        if(menuParent.hasClass('treeview')&&menuParent.hasClass('active')==false)
        {
            menuParent.addClass('active');
        }
        menuObj=menuParent;
        __i=__i+1;
        if(__i>4)break;
    }
}
