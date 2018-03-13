//Vue实例
//Model
var model = {
    //查询条件
    searchObj: {},
    //分类列表
    categorys:[],
    categorys2:[],
    categorys3:[],
    parentId:'',
    pName:'',
    num : 1
};

// 创建一个 Vue 实例 (ViewModel),它连接 View 与 Model
var vm = new Vue({
    el: "#content",
    data: model,
    beforeCreate:function(){
        //初始化本地数据
    },
    //初始化远程数据
    created:function(){
        this.search();
    },
    //方法，可用于绑定事件或直接调用
    methods: {
        search:function(event){

            $.get("/api/category/list",model.searchObj,function(response){
                if (response.success_is_ok){
                    vm.categorys=response.data;
                }
            });
        },
        addChild : function(id,name,num){
            vm.parentId = id;
            vm.pName = name;
            vm.num = num;
            $('#myModal').modal();
        },
        addChildCategory:function(){
            var editInfo = $("#editForm").serializeObject();
            $.post("/api/category/add",editInfo,function(response){
                if (response.success_is_ok){
                    parent.layer.closeAll();
                    if(vm.num == 1){
                        vm.categorys=response.data;
                    }else if(vm.num == 2){
                        vm.categorys2 = response.data;
                    }else if(vm.num == 3){
                        vm.categorys3 = response.data;
                    }

                }else{
                    layer.msg("添加失败",{icon: 5});
                }
            });
        },
        getChildCategorys2:function(parentId){
            vm.categorys2 = [];
            vm.categorys3 = [];
            $.get("/api/category/list",{categoryId:parentId},function(response){
                if (response.success_is_ok){
                    vm.categorys2=response.data;
                }
            });
        },
        getChildCategorys3:function(parentId){
            vm.categorys3 = [];
            $.get("/api/category/list",{categoryId:parentId},function(response){
                if (response.success_is_ok){
                    vm.categorys3=response.data;
                }
            });
        },
        removeCategory:function(categoryId, num){
            $.post("/api/category/remove",{id:categoryId},function(response){
                if (response.success_is_ok) {
                    layer.msg("删除成功");
                    if(num == 1){
                        vm.categorys=response.data;
                    }else if(num == 2){
                        vm.categorys2 = response.data;
                    }else if(num == 3){
                        vm.categorys3 = response.data;
                    }
                }else{
                    layer.msg(response.error);
                }
            })
        }
    }
});