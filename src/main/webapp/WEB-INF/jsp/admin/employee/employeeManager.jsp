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

        <!-- 搜索条件 -->
        <fieldset class="table-search-fieldset">
            <legend>搜索信息</legend>
            <div style="margin: 10px 10px 10px 10px">
                <form class="layui-form layui-form-pane" action="">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">登陆名称</label>
                            <div class="layui-input-inline">
                                <label>
                                    <input type="text" name="loginName" autocomplete="off" class="layui-input">
                                </label>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">真实姓名</label>
                            <div class="layui-input-inline">
                                <label>
                                    <input type="text" name="name" autocomplete="off" class="layui-input">
                                </label>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">员工性别</label>
                            <div class="layui-input-inline">
                                <label>
                                    <select name="sex" autocomplete="off" class="layui-input">
                                        <option value="">请选择性别</option>
                                        <option value="1">男</option>
                                        <option value="2">女</option>
                                    </select>
                                </label>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">所属部门</label>
                            <div class="layui-input-inline">
                                <label for="s_deptId"></label><select name="deptId" autocomplete="off" id="s_deptId" class="layui-input">
                                    <option value="">请选择部门</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">开始日期</label>
                            <div class="layui-input-inline">
                                <label for="startTime"></label><input type="text" name="startTime" id="startTime" autocomplete="off" class="layui-input" readonly>
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">结束日期</label>
                            <div class="layui-input-inline">
                                <label for="endTime"></label><input type="text" name="endTime" id="endTime" autocomplete="off" class="layui-input" readonly>
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block" style="text-align: center">
                            <button type="submit" class="layui-btn"  lay-submit lay-filter="data-search-btn"><i class="layui-icon layui-icon-search"></i>搜索</button>
                            <button type="reset" class="layui-btn layui-btn-warm"><i class="layui-icon layui-icon-refresh-1"></i>重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

        <!-- 表格工具栏 -->
        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"><i class="layui-icon layui-icon-add-1"></i>添加 </button>
            </div>
        </script>

        <!-- 数据表格 -->
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <!-- 行工具栏 -->
        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-xs data-count-edit" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete"><i class="layui-icon layui-icon-close"></i>删除</a>
            <button class="layui-btn layui-btn-xs layui-btn-warm" lay-event="resetPwd"><i class="layui-icon layui-icon-refresh"></i>重置密码 </button>
            <button class="layui-btn layui-btn-xs" lay-event="grantRole"><i class="layui-icon layui-icon-edit"></i>分配角色 </button>
        </script>

        <!-- 添加和修改窗口 -->
        <div style="display: none;padding: 5px" id="addOrUpdateWindow">
            <form class="layui-form" style="width:90%;" id="dataFrm" lay-filter="dataFrm">
                <!-- 隐藏域，保存员工id -->
                <input type="hidden" name="id" id="id">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">登陆名称</label>
                        <div class="layui-input-block">
                            <label for="loginName"></label><input type="text" name="loginName" id="loginName" lay-verify="required" autocomplete="off" placeholder="请输入登陆名称" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">员工姓名</label>
                        <div class="layui-input-block">
                            <label for="name"></label><input type="text" name="name" id="name" lay-verify="required" autocomplete="off" placeholder="请输入员工姓名" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">入职日期</label>
                        <div class="layui-input-block">
                            <label for="hiredate"></label><input type="text" name="hireDate" id="hiredate" readonly lay-verify="required" autocomplete="off" placeholder="请选择入职日期" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">所属部门</label>
                        <div class="layui-input-block">
                            <label for="deptId"></label><select name="deptId" id="deptId" class="layui-input">
                                <option value="">请选择部门</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">员工性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="sex" value="1" title="男" checked>
                            <input type="radio" name="sex" value="2" title="女" >
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">员工备注</label>
                    <div class="layui-input-block">
                        <label for="remark"></label><textarea class="layui-textarea" name="remark" id="remark"></textarea>
                    </div>
                </div>


                <div class="layui-form-item layui-row layui-col-xs12">
                    <div class="layui-input-block" style="text-align: center;">
                        <button type="button" class="layui-btn" lay-submit lay-filter="doSubmit"><span class="layui-icon layui-icon-add-1"></span>提交</button>
                        <button type="button" class="layui-btn layui-btn-warm" ><span class="layui-icon layui-icon-refresh-1"></span>重置</button>
                    </div>
                </div>
            </form>
        </div>

        <!-- 员工分配角色弹出层 -->
        <div style="display: none;padding: 5px" id="selectUserRoleDiv">
            <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
        </div>

    </div>
</div>
<script src="${pageContext.request.contextPath}/statics/layui/lib/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table','laydate','jquery'], function () {
        let $ = layui.jquery,
            form = layui.form,
            laydate = layui.laydate,
            table = layui.table;

        //渲染日期组件
        laydate.render({
            elem: '#startTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#endTime', //指定元素
            type: 'datetime'
        });
        laydate.render({
            elem: '#hiredate' //指定元素
        });

        //渲染表格组件
        let tableIns = table.render({
            elem: '#currentTableId',
            url: '/admin/employee/findAll',
            toolbar: '#toolbarDemo',
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 100, title: '员工编号', align: "center"},
                {field: 'loginName', width: 120, title: '登录名', align: "center"},
                {field: 'name', width: 120, title: '真实姓名', align: "center"},
                {field: 'sex', width: 80, title: '性别', align: "center"},
                {field: 'deptName', width: 120, title: '所属部门', align: "center"},
                {field: 'hireDate', width: 150, title: '入职日期', align: "center"},
                {field: 'createDate', width: 150, title: '创建时间', align: "center"},
                {title: '操作', minWidth: 120, toolbar: '#currentTableBar', align: "center"}
            ]],
            page: true,
            done: function (res, curr, count) {
                //判断当前页码是否是大于1并且当前页的数据量为0
                if (curr > 1 && res.data.length === 0) {
                    let pageValue = curr - 1;
                    //刷新数据表格的数据
                    tableIns.reload({
                        page: {curr: pageValue}
                    });
                }
            }
        });
        // 监听搜索操作
        form.on('submit(data-search-btn)', function (data) {
            tableIns.reload({
                where: data.field,
                page: {
                    curr: 1
                }
            });
            return false;
        });

        //发送ajax请求查询部门下拉列表
        $.get("/admin/dept/deptList",function(result){
            let html = "";
            //循环遍历
            for (let i = 0; i < result.length; i++) {
                html +="<option value='"+result[i].id+"'>"+result[i].deptName+"</option>"
            }
            //将html追加到下拉列表中
            $("[name='deptId']").append(html);
            //重新渲染下拉列表组件
            form.render("select");
        },"json");

        //监听头部工具栏事件
        table.on("toolbar(currentTableFilter)",function (obj) {
            switch (obj.event) {
                case "add"://添加按钮
                    openAddWindow();//打开添加窗口
                    break;
            }
        });

        let url;//提交地址
        let mainIndex;//打开窗口的索引
        /**
         * 打开添加窗口
         */
        function openAddWindow() {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "添加员工",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                //清空表单数据
                    $("#dataFrm")[0].reset();
                //添加的提交请求
                    url = "/admin/employee/addEmployee";
                }
            });
        }

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
                    case  "resetPwd": //重置密码按钮
                        resetPwd(obj.data);
                        break;
                    case  "grantRole": //分配菜单
                        grantRole(obj.data);
                        break;
                }
            }
        );

        /**
         * 打开修改窗口
         * @param data  当前行的数据
         */
        function openUpdateWindow(data) {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "修改员工信息",//窗口标题
                area: ["800px", "400px"],//窗口宽高
                content: $("#addOrUpdateWindow"),//引用的内容窗口
                success: function () {
                    //表单数据回显
                    form.val("dataFrm",data);//参数1：lay-filter值  参数2：回显的数据
                    //修改的提交请求
                    url = "/admin/employee/updateEmployee";
                }
            });
        }

        /**
         * 删除员工数据
         * @param data 当前行的数据
         */
        function deleteById(data){
            //删除员工
            layer.confirm("确定要删<span style='color: red'>" + data.loginName + "</span>吗", function (index) {
                //用户确定删除
                $.get("/admin/employee/deleteEmployee", {"eid": data.id}, function (result) {
                    if (result.success) {
                        //删除成功
                        tableIns.reload();
                    }
                    //提示信息
                    layer.msg(result.message);
                }, "json");
                layer.close(index);
            });
        }

        /**
         * 重置密码
         * @param data 当前行的数据
         */
        function resetPwd(data){
            //重置密码
            layer.confirm("确定要重置<span style='color: red'>" + data.loginName + "</span>的密码吗", function (index) {
                //用户确定重置
                $.get("/admin/employee/resetPwd", {"eid": data.id}, function (result) {
                    if (result.success) {
                        //重置成功
                        tableIns.reload();
                    }
                    //提示信息
                    layer.msg(result.message);
                }, "json");
                layer.close(index);
            });
        }

        //分配菜单方法
        function grantRole(data) {
            mainIndex = layer.open({
                type: 1,//打开类型
                title: "分配<span style='color: red'>"+data.loginName+"</span>的菜单",//窗口标题
                area: ["800px", "550px"],//窗口宽高
                content: $("#selectUserRoleDiv"),//引用的内容窗口
                btn: ['<i class="layui-icon layui-icon-ok"></i>确定', '<i class="layui-icon layui-icon-close"></i>取消']
                ,yes: function(index, layero){
                    //获取选中行
                    let checkStatus = table.checkStatus('roleTable');
                    if(checkStatus.data.length > 0){
                        //定义数组,保存选中角色的id值
                        let ids = [];
                        //获取选中角色的id
                        for (let i = 0; i < checkStatus.data.length; i++) {
                            ids.push(checkStatus.data[i].id);
                        }
                        //将数组转换成字符串格式
                        let joins = ids.join(",");
                        //发送ajax请求
                        $.get("/admin/employee/saveEmployee",{"roleIds":joins,"empId":data.id},function (result){
                            if (result.success){
                                //保存成功
                                tableIns.reload();
                            }
                            //提示信息
                            layer.msg(result.message);
                            layer.close(mainIndex);
                        },"json");
                    }else{
                        layer.msg("请选择要分配到角色")
                    }
                }
                ,btn2: function(index, layero){
                    return true;
                },
                success: function () {
                    //调用方法初始化
                    initRoleTable(data);
                }
            });
        }

        /**
         * 初始化角色菜单
         * @param data
         */
        function initRoleTable(data) {
            table.render({
                elem: '#roleTable',
                url: '${pageContext.request.contextPath}/admin/employee/initRoleTable?id='+data.id,
                cols: [[
                    {type: "checkbox", width: 50},
                    {field: 'id', MinWidth: 100, title: '角色编号', align: "center"},
                    {field: 'rolename', MinWidth: 120, title: '角色姓名', align: "center"},
                    {field: 'roledesc', MinWidth: 120, title: '角色描述', align: "center"},
                ]]
            });
        }

    });

</script>

</body>
</html>
