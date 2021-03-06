<!DOCTYPE html>
<html>
<head>
    <title>编辑角色</title>
    <#--<#include "../meta.ftl">-->
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <![endif]-->
    <link rel="stylesheet" href="${request.contextPath}/static/layui/css/layui.css" media="all">

    <style>
        .star-red {
            color: red;
        }
    </style>
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-form-role" id="layuiadmin-form-role" style="padding: 40px 30% 0 30%;">
    <div class="layui-inline" style="display: none;">
        <label class="layui-form-label">id</label>
        <div class="layui-input-inline">
            <input name="id" type  value="${person.id}" readonly="" autocomplete="off" class="layui-input" type="text">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"><span class="star-red"> * </span>用户名</label>
        <div class="layui-input-block">
            <input type="text" name="name" value="${person.name}" lay-verify="name|required" autocomplete="off" class="layui-input" maxlength="20">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"><span class="star-red"> * </span>年龄</label>
        <div class="layui-input-block">
            <input type="text" name="age" value="${person.age}"  lay-verify="age|required" autocomplete="off" class="layui-input" maxlength="20">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"><span class="star-red"> * </span>学校id</label>
        <div class="layui-input-block">
            <input type="text" name="schoolId" value="${person.schoolId}"  lay-verify="schoolId|required" autocomplete="off" class="layui-input" maxlength="20">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"><span class="star-red"> * </span>城市</label>
        <div class="layui-input-block">
            <input type="text" name="cityName" value="${person.cityName}"  lay-verify="cityName|required" autocomplete="off" class="layui-input" maxlength="20">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-block">
            <textarea type="text" name="address"   autocomplete="off" class="layui-textarea">${person.address}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">日期</label>
        <div class="layui-input-inline">
            <input type="text" name="time" value="${person.time}"  id="date1" lay-verify="datetime" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit="" lay-filter="editStudent">立即提交</button>
        </div>
    </div>

</div>
<script src="${request.contextPath}/static/layui/layui.js"></script>
<#--<script type="text/javascript" src="${request.contextPath}/static/script/demo/add.js"></script>-->
<script>
    layui.use(['form','layer','laydate'] ,function(){
        var $ = layui.jquery
            ,form = layui.form
            ,layer = layui.layer
            ,laydate = layui.laydate;
        var index = parent.layer.getFrameIndex(window.name); //修改成功的时候点击 确定 会关闭子窗口，这里获取一下子窗口
        //将日期直接嵌套在指定容器中
        var dateIns = laydate.render({
            elem: '#date1'
            ,type: 'datetime'
            ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
            ,calendar: true //是否开启公历重要节日
        });
        form.render();
        //监听提交
        form.on('submit(editStudent)', function(data){ //这块要跟表单中的lay-filter="editStudent"对应
            $.ajax({
                url: "/free/fm/add",
                type: "post",
                async: false,   //不要让它异步提交
                data: data.field,
                dataType: "json",
                success: function (data) {
                    if(data.success=true){
                        layer.msg("修改成功",{icon: 1,time:1500},function () {
                            parent.location.reload();    //重新加载父页面，进行数据刷新
                            layer.close(layer.index);
                        });
                    } else{
                        layer.alert("修改失败",{icon: 2,time:2000});
                    }
                }
            });
            return false;
        });
    });
</script>

</body>
</html>