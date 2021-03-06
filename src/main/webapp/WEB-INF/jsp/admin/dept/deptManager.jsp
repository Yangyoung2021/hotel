<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/layui/css/public.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <!-- 搜索栏条件编辑 -->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">部门名称</label>
                            <div class="layui-input-inline">
                                <label>
                                    <input type="text" name="deptName" autocomplete="off" class="layui-input">
                                </label>
                            </div>
                        </div>
                        <div class="layui-inline">
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

        <!-- 头部工具栏区域 -->
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-1"></i> 添加 </button>
            </div>
        </script>

        <!-- 表格区域 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <!-- 行工具栏 -->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i class="layui-icon layui-icon-delete"></i>删除</a>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <div class="layui-form-item">
                    <label class="layui-form-label">部门名称</label>
                    <div class="layui-input-block">
                        <!--隐藏域-->
                        <input type="hidden" name="id">
                        <label>
                            <input type="text" name="deptName" lay-verify="required" autocomplete="off"
                                   placeholder="请输入部门名称" class="layui-input">
                        </label>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门地址</label>
                    <div class="layui-input-block">
                        <label>
                            <input type="text" name="address" lay-verify="required" autocomplete="off" placeholder="请输入部门地址"
                                   class="layui-input">
                        </label>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">部门备注</label>
                    <div class="layui-input-block">
                        <label for="content"></label><textarea class="layui-textarea" name="remark" id="content"></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span
                                class="layui-icon layui-icon-add-1"></span>提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-warm"><span
                                class="layui-icon layui-icon-refresh-1"></span>重置
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table','layer'], function () {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            table = layui.table;

        <!-- 引入部门信息 -->
        let tableIns = table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/admin/dept/list',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {field: 'id', width: 120, title: '部门编号', sort: true,align: 'center'},
                {field: 'deptName', minWidth: 120, title: '部门名称', sort: true,align: 'center'},
                {field: 'address', minWidth: 150, title: '部门地址', sort: true,align: 'center'},
                {field: 'createDate', minWidth: 120, title: '创建时间', sort: true,align: 'center'},
                {field: 'remark', minWidth: 120, title: '备注', sort: true,align: 'center'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            //加入此段代码，当最后一页的数据被删完后，自动返回到上一页
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

        //监听工具栏事件
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

        let url;//提交地址
        let mainIndex;//打开窗口的索引
        /**
         * 打开添加窗口
         */
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加部门",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    //添加的提交请求
                    url = "/admin/dept/addDept";
                }
            });
        }


        /**
         * 打开修改窗口
         * @param data  当前行的数据
         */
        function openUpdateWindow(data) {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "修改部门",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //表单数据回显
                    form.val("dataFrm",data);//参数1：lay-filter值  参数2：回显的数据
                    //修改的提交请求
                    url = "/admin/dept/updateDept";
                }
            });
        }

        /**
         * 删除部门数据
         * @param data 当前行的数据
         */
        function deleteById(data){
            //判断该部门是否存在员工
            $.get("/admin/dept/checkDeptEmployee",{"deptId":data.id},function (result){
                if(result.exist){
                    //存在员工，提示用户无法删除
                    layer.msg(result.message);
                }else{
                    //不存在员工，提示用户是否确定删除员工
                    layer.confirm("确定要删<span style='color: red'>"+data.deptName+"</span>吗",function (index){
                        //用户确定删除
                        $.get("/admin/dept/deleteDept",{"deptId":data.id},function (result){
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

        //监听表单提交事件
        form.on("submit(doSubmit)",function (data) {
            $.post(url,data.field,function(result){
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








    });
</script>

</body>
</html>
