<#--获取静态资源路径：方案一 -->
<#assign path="${springMacroRequestContext.getContextPath()}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
   <#--https://blog.csdn.net/weixin_33958585/article/details/92057867-->
    <title>layui在线调试</title>
    <#--<link rel="stylesheet" href="http://blog.aigouzhushou.com/layui-v2.4.3/layui-v2.4.5/css/layui.css" media="all">-->
    <link rel="stylesheet" href="${path}/static/layui/css/layui.css" media="all">
    <script type="application/javascript" src="${path}/static/js/jquery-3.3.1.js" ></script>
    <style>
        body{margin: 10px;}
        .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
    </style>
</head>
<body>
<div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="form-review">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">学校筛选：</label>
            <div class="layui-input-block">
                <input type="text" name="schoolName" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <button class="layui-btn" lay-submit lay-filter="form-search" id="form-search">
                <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
        </div>
    </div>
</div>
<table class="layui-hide" id="demo" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<div class="layui-tab layui-tab-brief" lay-filter="demo">
    <ul class="layui-tab-title">
        <li class="layui-this">演示说明</li>
        <li>日期</li>
        <li>分页</li>
        <li>上传</li>
        <li>滑块</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">

            <div class="layui-carousel" id="test1">
                <div carousel-item>
                    <div><p class="layui-bg-green demo-carousel">在这里，你将以最直观的形式体验 layui！</p></div>
                    <div><p class="layui-bg-red demo-carousel">在编辑器中可以执行 layui 相关的一切代码</p></div>
                    <div><p class="layui-bg-blue demo-carousel">你也可以点击左侧导航针对性地试验我们提供的示例</p></div>
                    <div><p class="layui-bg-orange demo-carousel">如果最左侧的导航的高度超出了你的屏幕</p></div>
                    <div><p class="layui-bg-cyan demo-carousel">你可以将鼠标移入导航区域，然后滑动鼠标滚轮即可</p></div>
                </div>
            </div>
        </div>
        <div class="layui-tab-item">
            <div id="laydateDemo"></div>
        </div>
        <div class="layui-tab-item">
            <div id="pageDemo"></div>
        </div>
        <div class="layui-tab-item">
            <div class="layui-upload-drag" id="uploadDemo">
                <i class="layui-icon"></i>
                <p>点击上传，或将文件拖拽到此处</p>
            </div>
        </div>
        <div class="layui-tab-item">
            <div id="sliderDemo" style="margin: 50px 20px;"></div>
        </div>
    </div>
</div>

<blockquote class="layui-elem-quote layui-quote-nm layui-hide" id="footer">layui {{ layui.v }} 提供强力驱动</blockquote>

<#--获取静态资源路径：方案二 -->
<script src="${basePath}/layui/layui.js"></script>
<#--<script src="${request.contextPath}/static/layui/layui.js"></script>-->
<#--<script src="http://blog.aigouzhushou.com/layui-v2.4.3/layui-v2.4.5/layui.js"></script>-->
<script>
    var table = null;
    var editData;
    var mylimit = 5;
    layui.config({
        version: '1575889601627' //为了更新 js 缓存，可忽略
    });

    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
        table = layui.table //表格
        var laydate = layui.laydate //日期
            ,laypage = layui.laypage //分页
            ,layer = layui.layer //弹层
            // ,table = layui.table //表格
            ,carousel = layui.carousel //轮播
            ,upload = layui.upload //上传
            ,element = layui.element //元素操作
            ,slider = layui.slider //滑块
            ,form = layui.form //表单
            ,common = layui.common

        //向世界问个好
        layer.msg('Hello World');
        //监听Tab切换
        element.on('tab(demo)', function(data){
            layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
                tips: 1
            });
        });

        //执行一个 table 实例
        var ins1 = table.render({
            id: 'idTest'
            ,elem: '#demo'
            ,height: 420
            ,url: '/free/fm/list' //数据接口
            ,title: '用户表' //导出的文件名
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,defaultToolbar: [] // 数组为空 右边不显示
            ,totalRow: false //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
                ,{field: 'name', title: '用户名', width:80}
                ,{field: 'cityName', title: '城市', width: 90, sort: true, totalRow: true}
                ,{field: 'age', title: '年龄', width:80, sort: true}
                ,{field: 'address', title: '地址', width: 80, sort: true, totalRow: true}
                ,{field: 'schoolId', title: '学校id', width:150}
                ,{field: 'school', title: '学校', width: 200,templet:function (row) {
                        if (row.school.schoolName != null && row.school.schoolName != "") {
                            return row.school.schoolName;
                        } else {
                            return "";
                        }
                    }}
                ,{field: 'time', title: '创建时间', width: 200,templet: "<div>{{layui.util.toDateString(d.time, 'yyyy-MM-dd HH:mm:ss')}}</div>"}
                ,{field: 'wealth', title: '财富', width: 135, sort: true, totalRow: true}
                ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
            ]]
            , skin: 'row'
            , even: true
            , limits: [3, 5, 10]
            , limit: mylimit //每页默认显示的数量
            ,request: {
                pageName: 'curr' //页码的参数名称，默认：page
                ,limitName: 'nums' //每页数据量的参数名，默认：limit
            }
            , done: function (res) {
                console.log(mylimit)
                data = res.data;
                count = res.count;
                mylimit = res.count
                console.log(mylimit)
                //将上述表格示例导出为 csv 文件
                // table.exportFile(ins1.config.id, data); //data 为该实例中的任意数量的数据
            }

        });

        //监听checkbox选中事件
        table.on('checkbox(test)', function(obj){
            var checkStatus = table.checkStatus('idTest')// table.render里指定的id: 'idTest'
            console.log(checkStatus.data) //获取选中行的数据
            console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
            console.log(checkStatus.isAll ) //表格是否全选
            console.log(obj)
            console.log(getRowFexId("id"));
            // table.exportFile(ins1.config.id, checkStatus.data); //data 为该实例中的任意数量的数据
        });

        //监听行单击事件（双击事件为：rowDouble，单击事件：row）
        table.on('rowDouble(test)', function(obj){
            var data = obj.data;
            layer.alert(JSON.stringify(data), {
                title: '当前行数据：'
            });

            //标注选中样式
            obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
        });

        // 监听搜索 -- 表单搜索
        form.on('submit(form-search)', function(data){
            var field = data.field;
            //执行重载
            table.reload('idTest', {
                url: '/free/fm/list', //设置异步接口
                method: 'get',
                where: field ,// 请求参数
                cellMinWidth: 80,
                page: {
                    curr: 1 //重新从第 1 页开始
                }
            });
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data //获取选中的数据
            ,editList=[];
            for(var i=0;i<data.length;i++){ //因为这块获取的是数组，所以当前行数据应该为数组中的第一条，所以要遍历数组
                $.each(data[i],function(name,value) {
                    editList.push(value);
                }); }
            switch(obj.event){
                case 'add':
                    // layer.msg('添加');
                    // break;
                    //脚本编辑弹出层
                    var name = encodeURIComponent(data.toolName);
                    layer.open({
                        type: 2,
                        title: '添加用户信息',
                        shadeClose: true,
                        shade: 0.8,
                        maxmin: true,
                        area: ['70%', '70%'],
                        content: 'addUI',
                        success: function(layero, index){
                        }
                    });
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        // layer.alert('编辑行：<br>'+ JSON.stringify(data));
                        // layer.alert('编辑 [id]：'+ checkStatus.data[0].id);

                        //https://www.jianshu.com/p/be7dee1b69a0
                        //脚本编辑弹出层
                        var name = encodeURIComponent(data.toolName);
                        layer.open({
                            type: 2,
                            title: '编辑学员信息',
                            shadeClose: true,
                            shade: 0.8,
                            maxmin: true,
                            area: ['70%', '70%'],
                            content: 'editUI2?id='+data[0].id,
                            success: function(layero, index){
                            }
                        });
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            layer.close(index);
                            //向服务端发送删除指令，获取后端
                            $.ajax({
                                type: 'post',
                                url: "/free/fm/delete",
                                data: {
                                    id: data.id,  //此处是根据id查询此id是否被删除，故将id传向后端
                                },
                                // contentType: 'application/json',
                                success: function (data) {
                                    //https://www.cnblogs.com/zhenzi0322/p/12097851.html layer.msg的使用
                                    layer.msg('删除成功啦', {icon: 1,time: 2000 //2秒关闭（如果不配置，默认是3秒）
                                    }, function(){
                                        //2秒后自动执行这里面的内容
                                        table.reload('idTest'); //数据刷新
                                        layer.close(index);
                                    });
                                },
                                error: function (data) {
                                    layer.msg('连接网络失败，请检查网络设置或联系管理员', { icon: 2 }, { time: 2000 });
                                }
                            });
                        });
                        // layer.msg('删除');
                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event //获得 lay-event 对应的值
                ,editList=[]; //存放获取到的那条json数据中的value值（不放key）
            $.each(data,function(name,value) {//循环遍历json数据
                editList.push(value);//将json数据中的value放入数组中（下面的子窗口显示的时候要用到）
            });
            if(layEvent === 'detail'){
                layer.alert(JSON.stringify(data), {
                    title: '当前行数据：'
                });
                // layer.msg('查看操作');
                //标注选中样式
                obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令，获取后端
                    $.ajax({
                        type: 'post',
                        url: "/free/fm/delete",
                        data: {
                            id: data.id,  //此处是根据id查询此id是否被删除，故将id传向后端
                        },
                        // contentType: 'application/json',
                        success: function (data) {
                            //https://www.cnblogs.com/zhenzi0322/p/12097851.html layer.msg的使用
                            layer.msg('删除成功啦', {icon: 1,time: 2000 //2秒关闭（如果不配置，默认是3秒）
                            }, function(){
                                //2秒后自动执行这里面的内容
                                table.reload('idTest'); //数据刷新
                                layer.close(index);
                            });
                        },
                        error: function (data) {
                            layer.msg('连接网络失败，请检查网络设置或联系管理员', { icon: 2 }, { time: 2000 });
                        }
                    });
                });
            } else if(layEvent === 'edit'){
                //脚本编辑弹出层
                var name = encodeURIComponent(data.toolName);
                //获取编辑行的值，与子页面form.val配合使用，填充到子页面对应的input中
                editData = {
                    'name' : data.name
                    ,'age' : data.age
                    ,'schoolId' : data.schoolId
                    ,'cityName' : data.cityName
                    ,'address' : data.address
                    ,'time' : data.time
                };
                layer.open({
                    type: 2,
                    title: '编辑学员信息',
                    shadeClose: true,
                    shade: 0.8,
                    maxmin: true,
                    area: ['70%', '70%'],
                    content: 'editUI?id='+ data.id,//设置你要弹出的jsp页面(这里id无用，因为是直接通过editData传递要修改的值，)
                    success: function(data){
                        // var body = layer.getChildFrame('body', index);
                        // var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
                        // var inputList = body.find("input");//获取到子窗口的所有的input标签
                        // for (var i = 0; i < inputList.length; i++ ) {
                        //     console.log(inputList[i].name)
                        //     for (var j = 0; j < editData)
                        //     if (inputList[i].name.equals(editData)) {
                        //
                        //     }
                            // $(inputList[i]).val(editList[i]); //遍历子窗口的input标签，将之前数组中的值一次放入显示
                        // }
                    }
                });

                // editData = {
                //     'name' : data.name
                //     ,'age' : data.age
                //     ,'schoolId' : data.schoolId
                //     ,'cityName' : data.cityName
                //     ,'address' : data.address
                //     ,'time' : data.time
                // };
                // layer.open('编辑角色','editUI',['650px', '550px'],function (data,index) {
                //     if (data.status == null || data.status == '') {
                //         data.status = 2;
                //     }
                //     common.ajax("/role/edit", "post", data, function (rel) {
                //         if (rel.code == 100) {
                //             layer.msg(rel.message, {icon: 6, time:1000}, function () {
                //                 //笑脸
                //                 table.reload('table-manage'); //数据刷新
                //                 layer.close(index);
                //             });
                //         } else {
                //             layer.msg(rel.message, {icon: 5});
                //         }
                //     });
                // });
                // layer.msg('编辑操作');
            }
        });

        //执行一个轮播实例
        carousel.render({
            elem: '#test1'
            ,width: '100%' //设置容器宽度
            ,height: 200
            ,arrow: 'none' //不显示箭头
            ,anim: 'fade' //切换动画方式
        });

        //将日期直接嵌套在指定容器中
        var dateIns = laydate.render({
            elem: '#laydateDemo'
            ,position: 'static'
            ,calendar: true //是否开启公历重要节日
            ,mark: { //标记重要日子
                '0-10-14': '生日'
                ,'2018-08-28': '新版'
                ,'2018-10-08': '神秘'
            }
            ,done: function(value, date, endDate){
                if(date.year == 2017 && date.month == 11 && date.date == 30){
                    dateIns.hint('一不小心就月底了呢');
                }
            }
            ,change: function(value, date, endDate){
                layer.msg(value)
            }
        });

        //分页
        laypage.render({
            elem: 'pageDemo' //分页容器的id
            ,count: 100 //总页数
            ,skin: '#1E9FFF' //自定义选中色值
            ,limit: 5
            ,skip: true //开启跳页
            ,jump: function(obj, first){
                //obj包含了当前分页的所有参数，比如：
                // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                // console.log(obj.limit); //得到每页显示的条数

                //首次不执行
                if(!first){
                    // current_page = obj.curr
                    layer.msg('第'+ obj.curr +'页', {offset: 'b'});
                }
            }
        });

        //上传
        upload.render({
            elem: '#uploadDemo'
            ,url: '' //上传接口
            ,done: function(res){
                console.log(res)
            }
        });

        //滑块
        var sliderInst = slider.render({
            elem: '#sliderDemo'
            ,input: true //输入框
        });

        //底部信息
        var footerTpl = lay('#footer')[0].innerHTML;
        lay('#footer').html(layui.laytpl(footerTpl).render({}))
            .removeClass('layui-hide');
    });

    //获取选中行FexId属性值公共方法
    //value 为传入的  {field: 'id',style:'display:none;'} field值
    //返回结果 是这一列被选中的 用,隔开的字符串
    function getRowFexId(value) {
        var checkStatus = table.checkStatus('idTest')
            , data = checkStatus.data, arr = new Array();
        for (var i = 0; i < data.length; i++) {
            arr.push(data[i][value]);
        }
        return arr.join(",");
    }
</script>
</body>
</html>
