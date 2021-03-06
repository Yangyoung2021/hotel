<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css"
          media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
    <style>
        .thumbBox{ height:200px; overflow:hidden; border:1px solid #e6e6e6; border-radius:2px; cursor:pointer; position:relative; text-align:center; line-height:200px;width: 210px;}
        .thumbImg{ max-width:100%; max-height:100%; border:none;}
        .thumbBox:after{ position:absolute; width:100%; height:100%;line-height:200px; z-index:-1; text-align:center; font-size:20px; content:"缩略图"; left:0; top:0; color:#9F9F9F;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <%-- 搜索条件 --%>
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">房间编号</label>
                            <div class="layui-input-inline">
                                <label>
                                    <input type="text" name="roomnum" autocomplete="off" class="layui-input">
                                </label>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间类型</label>
                            <div class="layui-input-inline">
                                <select name="roomtypeid" id="s_roomTypeId" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属楼层</label>
                            <div class="layui-input-inline">
                                <select name="floorid" id="s_floorId" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间状态</label>
                            <div class="layui-input-inline">
                                <select name="status" id="s_status" autocomplete="off" class="layui-input">
                                    <option value="">全部</option>
                                    <option value="1">可预订</option>
                                    <option value="2">已预定</option>
                                    <option value="3">已入住</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
                            <button type="submit" class="layui-btn" lay-submit lay-filter="data-search-btn"><i
                                    class="layui-icon layui-icon-search"></i>搜索
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i
                                    class="layui-icon layui-icon-refresh-1"></i>重置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <%-- 表格工具栏 --%>
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i
                        class="layui-icon layui-icon-add-1"></i>添加
                </button>
            </div>
        </script>

        <%-- 数据表格 --%>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <%-- 行工具栏 --%>
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i
                    class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i
                    class="layui-icon layui-icon-close"></i>删除</a>
        </script>

        <%-- 添加和修改窗口 --%>
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存房型id -->
                <input type="hidden" name="id">
                <div class="layui-col-md12 layui-col-xs12">
                    <div class="layui-row layui-col-space10">
                        <div class="layui-col-md9 layui-col-xs7">
                            <div class="layui-form-item magt3" style="margin-top: 8px;">
                                <label class="layui-form-label">房间编号</label>
                                <div class="layui-input-block">
                                    <input type="text" class="layui-input" name="roomnum" lay-verify="required"  placeholder="请输入房间编号">
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">房间类型</label>
                                <div class="layui-input-block">
                                    <select name="roomtypeid" id="roomtypeid" lay-verify="required">
                                        <option value="">请选择房型</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-form-item">
                                <label class="layui-form-label">房间备注</label>
                                <div class="layui-input-block">
                                    <textarea class="layui-textarea" name="remark" id="remark"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="layui-col-md3 layui-col-xs5">
                            <div class="layui-upload-list thumbBox mag0 magt3">
                                <input type="hidden" name="photo" id="photo" value="/images/defaultimg.jpg">
                                <img class="layui-upload-img thumbImg" src="/hotel/show/images/defaultimg.jpg">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item magb0">
                        <div class="layui-inline">
                            <label class="layui-form-label">所属楼层</label>
                            <div class="layui-input-inline">
                                <select name="floorid" id="floorid" lay-verify="required">
                                    <option value="">请选择楼层</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">房间状态</label>
                            <div class="layui-input-inline">
                                <select name="status" id="status" lay-verify="required">
                                    <option value="">请选择房间状态</option>
                                    <option value="1">可预订</option>
                                    <option value="2">已预定</option>
                                    <option value="3">已入住</option>
                                </select>
                            </div>
                        </div>

                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">房间要求</label>
                        <div class="layui-input-block" >
                            <textarea id="roomrequirement" name="roomrequirement" class="layui-textarea"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">房间详情</label>
                        <div class="layui-input-block" >
                            <textarea id="roomdesc" name="roomdesc" style="display: none;"></textarea>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center;">
                            <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit" id="doSubmit"><span
                                    class="layui-icon layui-icon-add-1"></span>提交
                            </button>
                            <button type="reset" class="layui-btn layui-btn-warm"><span
                                    class="layui-icon layui-icon-refresh-1"></span>重置
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table', 'laydate', 'jquery', 'layer','upload','layedit'], function () {
        var $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            upload = layui.upload,
            layedit = layui.layedit,
            layer = layui.layer,
            table = layui.table;

        //渲染表格组件
        var tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/room/getRoomListByPage',
            toolbar: '#toolbarDemo',
            cols: [[
                {field: 'id', width: 60, title: '编号', align: "center"},
                {field: 'roomnum', minWidth: 120, title: '房间编号', align: "center"},
                {field: 'typename', minWidth: 100, title: '房间类型', align: "center"},
                {field: 'floorname', minWidth: 100, title: '所属楼层', align: "center"},
                {field: 'statusStr', minWidth: 100, title: '房间状态', align: "center"},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1并且当前页的数据量为0
                if (curr > 1 && res.data.length === 0) {
                    var pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });

        //查询房型类型下拉列表
        $.post("/admin/roomType/findAll",{},function (data) {
            //拼接字符串
            let html = "";
            //循环遍历数据
            for (let i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].typeName+"</option>"
            }
            $("[name='roomtypeid']").append(html);
            //重新渲染下拉列表组件
            form.render("select");
        },"json");

        //查询楼层类型下拉列表
        $.post("/admin/floor/findAll",{},function (data) {
            //拼接字符串
            let html = "";
            //循环遍历数据
            for (let i = 0; i < data.length; i++) {
                html += "<option value='"+data[i].id+"'>"+data[i].name+"</option>"
            }
            $("[name='floorid']").append(html);
            //重新渲染下拉列表组件
            form.render("select");
        },"json");

        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            //执行搜索重载
            tableIns.reload({
                //将表格中的数据包装成对象发送
                where : data.field ,
                //当前页码
                page: {
                    curr: 1
                }
            });
            return false;
        });

        //监听头部工具栏事件
        //toolbar是头部工具栏事件
        //currentTableFilter是表格lay-filter过滤器的值
        table.on(
            "toolbar(currentTableFilter)",function (obj) {
                switch (obj.event){
                    case  "add": //添加按钮
                        openAddWindow();//打开添加窗口
                        break;
                }
            }
        );

        let url;//提交地址
        let mainIndex;//打开窗口的索引
        let detail;//富文本域对象
        /**
         * 打开添加窗口
         */
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加房间",//窗口标题
                area: ["800px", "600px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                maxmin: true,
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加的提交请求
                    url = "/admin/room/addRoom";
                    //重置默认图片,注意：显示图片必须在图片名称前加上/hotel/show
                    $(".thumbImg").attr("src","/hotel/show/images/defaultimg.jpg");
                    //重置图片隐藏域的值
                    $("#photo").val("images/defaultimg.jpg");
                }
            });
            //设置默认窗口最大化
            layer.full(mainIndex);
            //初始化富文本域
            detail = layedit.build("roomdesc",{
                uploadImage:{
                    url:"/admin/file/uploadFile",//文件上传地址
                    type:"post"//提交方式
                }
            });
        }

        //监听表单提交事件
        form.on("submit(doSubmit)",function (data) {
            //将富文本编辑器同步到文本编辑器中
            layedit.sync(detail);
            $.post(url,$("#dataFrm").serialize(),function(result){
                if(result.success){
                    //刷新数据表格
                    tableIns.reload();
                    //关闭窗口
                    layer.close(mainIndex);
                }
                //提示信息
                layer.msg(result.message);
            },"json");
            //禁止页面刷新
            return false;
        });

        //渲染文件上传区域
        upload.render({
            elem:".thumbImg",//绑定元素
            url: '/admin/file/uploadFile',//文件上传地址
            acceptMime: 'image/*',//规定打开文件选择框时，筛选出的文件类型
            field: 'file',//文件上传的字段值，等同于input标签的name属性值，该值必须与控制器中的方法参数名一致
            method: "post",//提交方式
            //文件上传成功后的回调函数
            done: function (res, index, upload) {
                //设置图片回显路径
                $(".thumbImg").attr("src",res.data.src);
                $('.thumbBox').css("background", "#fff");
                //给图片隐藏域赋值
                $("#photo").val(res.imagePath);
            }
        });

        //监听行工具栏事件
        table.on("tool(currentTableFilter)",function (obj) {
                console.log(obj);
                switch (obj.event){
                    case  "edit": //编辑按钮
                        openUpdateWindow(obj.data);//打开编辑窗口
                        break;
                    case  "delete": //删除按钮
                        deleteById(obj.data);
                        break;
                }
            }
        );

        /**
         * 打开修改窗口
         */
        function openUpdateWindow(data) {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加角色",//窗口标题
                area: ["800px", "600px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //数据回显
                    form.val("dataFrm",data)
                    //添加的提交请求
                    url = "/admin/room/updateRoom";
                    //图片回显
                    $(".thumbImg").attr("src","/hotel/show/"+data.photo);
                    //隐藏值回显
                    $("#photo").val(data.photo)
                }
            });
            //设置默认窗口最大化
            // layer.full(mainIndex);
            //初始化富文本域
            detail = layedit.build("roomdesc",{
                uploadImage:{
                    url:"/admin/file/uploadFile",//文件上传地址
                    type:"post"//提交方式
                }
            });
        }

        /**
         * 删除房间数据
         * @param data 当前行的数据
         */
        function deleteById(data){
            //判断该房间状态
            $.get("/admin/room/checkStatus",{"id":data.id},function (result){
                if(result.exist){
                    //房间在使用中，提示无法删除
                    layer.msg(result.message);
                }else{
                    //提示用户是否确定删除房间
                    layer.confirm("确定要删<span style='color: red'>"+data.roomnum+"</span>房间吗",function (index){
                        //用户确定删除
                        $.get("/admin/room/deleteRoom",{"id":data.id},function (result){
                            if (result.success){
                                //删除成功
                                tableIns.reload();
                            }
                            //提示信息
                            layer.msg(result.message);
                        },"json");
                        layer.close(index);
                    });
                }
            },"json");

        }
    });
</script>

</body>
</html>
