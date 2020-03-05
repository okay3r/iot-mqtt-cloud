<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/home/base.jsp"%>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">

    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            查看设备
            <small>步进电机</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-travellog-manage-list.html">查看类别</a></li>
            <li class="active">步进电机</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">

                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="新建" onclick='location.href="device-edit.jsp"'><i class="fa fa-file-o"></i> 新建</button>
                                <button type="button" class="btn btn-default" title="删除" onclick='confirm("你确认要删除吗？")'><i class="fa fa-trash-o"></i> 删除</button>
                                <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>
                    <!--工具栏/-->

                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                            <tr>
                                <th class="" style="padding-right:0px;">
                                </th>
                                <th class="">设备名</th>
                                <th class="">状态</th>
                                <th class="">创建时间</th>
                                <th class="">更新时间</th>


                                <th class="text-center">操作</th>
                            </tr>
                        </thead>
                        <tbody>



                            <tr>
                                <td><input name="ids" type="checkbox"></td>

                                <td>1号步进电机</td>
                                <td>启用</td>
                                <td>2020-01-01</td>
                                <td>2020-01-01</td>

                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="device-edit.jsp"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="../log/log-list.jsp"'>历史记录</button>
                                </td>
                            </tr>
                            <tr>
                                <td><input name="ids" type="checkbox"></td>

                                <td>1号步进电机</td>
                                <td>启用</td>
                                <td>2020-01-01</td>
                                <td>2020-01-01</td>

                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="device-edit.jsp"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="../log/log-list.jsp"'>历史记录</button>
                                </td>
                            </tr>
                            <tr>
                                <td><input name="ids" type="checkbox"></td>

                                <td>1号步进电机</td>
                                <td>启用</td>
                                <td>2020-01-01</td>
                                <td>2020-01-01</td>

                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="device-edit.jsp"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="../log/log-list.jsp"'>历史记录</button>
                                </td>
                            </tr>
                            <tr>
                                <td><input name="ids" type="checkbox"></td>

                                <td>1号步进电机</td>
                                <td>启用</td>
                                <td>2020-01-01</td>
                                <td>2020-01-01</td>

                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="device-edit.jsp"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="../log/log-list.jsp"'>历史记录</button>
                                </td>
                            </tr>
                            <tr>
                                <td><input name="ids" type="checkbox"></td>

                                <td>1号步进电机</td>
                                <td>启用</td>
                                <td>2020-01-01</td>
                                <td>2020-01-01</td>

                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="device-edit.jsp"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="../log/log-list.jsp"'>历史记录</button>
                                </td>
                            </tr>
                            <tr>
                                <td><input name="ids" type="checkbox"></td>

                                <td>1号步进电机</td>
                                <td>启用</td>
                                <td>2020-01-01</td>
                                <td>2020-01-01</td>

                                <td class="text-center">
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="device-edit.jsp"'>编辑</button>
                                    <button type="button" class="btn bg-olive btn-xs" onclick='location.href="../log/log-list.jsp"'>历史记录</button>
                                </td>
                            </tr>




                        </tbody>
                        <!--
                            <tfoot>
                            <tr>
                            <th>Rendering engine</th>
                            <th>Browser</th>
                            <th>Platform(s)</th>
                            <th>Engine version</th>
                            <th>CSS grade</th>
                            </tr>
                            </tfoot>-->
                    </table>
                    <!--数据列表/-->

                </div>
                <!-- 数据表格 /-->


            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        总共2 页，共14 条数据。 每页
                        <select class="form-control">
                            <option>10</option>
                            <option>15</option>
                            <option>20</option>
                            <option>50</option>
                            <option>80</option>
                        </select> 条
                    </div>
                </div>

                <div class="box-tools pull-right">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">首页</a>
                        </li>
                        <li><a href="#">上一页</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">下一页</a></li>
                        <li>
                            <a href="#" aria-label="Next">尾页</a>
                        </li>
                    </ul>
                </div>

            </div>
            <!-- /.box-footer-->


        </div>

    </section>
    <!-- 正文区域 /-->

</div>

<script>
    $(document).ready(function() {

        // 激活导航位置
        setSidebarActive("travellog-manage");

        // 列表按钮 
        $("#dataList td input[type='checkbox']").iCheck({
            checkboxClass: 'icheckbox_square-blue',
            increaseArea: '20%'
        });
        // 全选操作 
        $("#selall").click(function() {
            var clicks = $(this).is(':checked');
            if (!clicks) {
                $("#dataList td input[type='checkbox']").iCheck("uncheck");
            } else {
                $("#dataList td input[type='checkbox']").iCheck("check");
            }
            $(this).data("clicks", !clicks);
        });
    });
</script>