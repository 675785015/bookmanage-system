/**
 * Created by Jack on 2016/11/25.
 */
var model = {
    form:"#contract_form",
    submitObj:$("#contract_form").serializeObject(),
    error:{hide:true,message:""}
};

var vm = new Vue({
    el:".content",
    data:model,
    mounted:function(){
        this.bindFormValidate($(this.form));
    },
    methods:{
        submit:function(){
            var bootstrapValidator = $(vm.form).data('bootstrapValidator').validate();
            if(!bootstrapValidator.isValid()){
                return false;
            }
            layer.load(2);

            var option = {
                //url:"${path}/item/validBrandName.do",
                //type:"post",
                //dataType:"json",
                async:false,//把ajax变成同步，代码会按着顺序执行
                data:{
                    fileName:"contract"
                },
                success:function(response){
                    if (response.success_is_ok){
                        layer.msg(response.msg);
                    }else{
                        layer.msg(response.error);
                    }
                    layer.closeAll();
                },
                error:function(){
                    alert("系统错误");
                }
            };
            $("#contract_form").ajaxSubmit(option);
        },
        toFdd:function(){
            $.post("/filePlugin/uploadTempToFdd",function(response){
                if(response.success_is_ok){
                    layer.msg(response.msg)
                }else{
                    layer.alert(response.error);
                }
            })
        },
        //绑定表单验证
        bindFormValidate:function($form){
            $form.bootstrapValidator({
                fields: {
                    contract: {
                        validators: {
                            notEmpty: {
                                message: '请选择文件'
                            },
                            regexp: {/* 只需加此键值对，包含正则表达式，和提示 */
                                regexp: /(\.pdf)$/,
                                message: '文件类型不正确'
                            }
                        }
                    }
                }
            });
        }
    }
});