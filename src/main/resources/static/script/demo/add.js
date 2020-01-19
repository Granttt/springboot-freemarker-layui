var menusData = null;
layui.use(['index' , 'table', 'verify'], function () {
    var $ = layui.$,
        form = layui.form,
        common = layui.common
        ,verify = layui.verify;

    $("#addMenus").on('click',function () {
        common.openOpt('选择菜单','/role/menusUI',['650px', '550px'],function (data,index) {
            var strMenuIds = '';
            for(var i in data) {
                strMenuIds = strMenuIds + data[i] + ",";
            }
            $("#menuIds").val(strMenuIds.substring(0,strMenuIds.length-1));
            if ($("#menuIds").val() != null && $("#menuIds").val() != "") {
                $("#addMenus").val("修改菜单");
            }
            menusData = data;
            layer.close(index);
        });
    })

    form.verify({
        name: function(value){
            var msg = "";
            if (value != null || value != "") {
                //验证角色名是否重名
                $.ajax({
                    url: '/role/getRoleByName?name=' + value,
                    type: "get",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    async: false,//这得注意是同步
                    success: function (pageRel) {
                        if (pageRel.code == 300) {
                            msg =  pageRel.message;
                            return msg;
                        }
                    }
                });
            }
            return msg;
        }
        ,menuIds: function (value) {
            var menuIds = $("#menuIds").val();
            if (value == null || value == "") {
                return "请选择菜单";
            }
        }
    });
// input 去除空格
    verify.trimSpace('input');
});