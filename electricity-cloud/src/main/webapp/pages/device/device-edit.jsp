<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/home/base.jsp"%>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">

    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            查看设备
            <small>设备管理表单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-travellog-manage-list.html">查看设备</a></li>
            <li class="active">设备管理表单</li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <div class="box-body">

            <!--tab页-->
            <div class="nav-tabs-custom">

                <!--tab头-->
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#tab-form" data-toggle="tab">表单</a>
                    </li>
                </ul>
                <!--tab头/-->

                <!--tab内容-->
                <div class="tab-content">

                    <!--表单内容-->
                    <div class="tab-pane active" id="tab-form">
                        <form id="editForm" action="#" method="post">
                            <input type="hidden" id="id" name="id" value="${id}">
                            <input type="hidden" id="deviceId" name="deviceId" value="${deviceId}">
                            <div class="row data-type" style="margin: 0px">
                                <div class="col-md-2 title">设备名</div>
                                <div class="col-md-10 data">
                                    <input type="text" class="form-control" placeholder="设备名" name="deviceName" value="${deviceName}">
                                </div>

                                <div class="col-md-2 title">状态</div>
                                <div class="col-md-10 data line-height36">
                                    <div class="form-group form-inline">
                                        <div class="radio"><label><input type="radio" name="using" value="0"> 开启</label></div>
                                        <div class="radio"><label><input type="radio" name="using" value="1"> 关闭</label></div>
                                    </div>
                                </div>


                            </div>
                        </form>

                        <!--工具栏-->
                        <div class="box-tools text-center">
                            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
                            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                        </div>
                        <!--工具栏/-->
                    </div>
                    <!--表单内容/-->

                </div>
                <!--tab内容/-->

            </div>
            <!--tab页/-->


            <!-- .box-footer
        <div class="box-footer"></div>
        -->
            <!-- /.box-footer-->

        </div>

    </section>
    <!-- 正文区域 /-->

</div>

<script>
    $(document).ready(function() {
        // 激活导航位置
        setSidebarActive("travellog-manage");
    });
</script>