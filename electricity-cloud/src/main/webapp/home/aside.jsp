<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="../img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p> ${sessionScope.loginUser.userName}</p>
                <a href="#">${sessionScope.loginUser.companyName}</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>

            <%--
                        <c:forEach items="${sessionScope.modules}" var="item">
                            <c:if test="${item.ctype==0}">
                                <li class="treeview">
                                    <a href="#">
                                        <i class="fa fa-cube"></i> <span>${item.name}</span>
                                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                                    </a>
                                    <ul class="treeview-menu">
                                        <c:forEach items="${sessionScope.modules}" var="item2">
                                            <c:if test="${item2.ctype==1 && item2.parentId == item.id}">
                                                <li id="${item2.id}">
                                                    <a onclick="setSidebarActive(this)" href="${item2.curl}" target="iframe">
                                                        <i class="fa fa-circle-o"></i>${item2.name}
                                                    </a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:if>
                        </c:forEach>
                         --%>

           <li class="treeview">
               <a href="../pages/category/category-list.jsp" onclick="setSidebarActive(this)" target="iframe">
                   <i class="fa fa-cube"></i> <span>类别管理</span>
               </a>
           </li>

         <li class="treeview">
             <a href="#">
                 <i class="fa fa-cube"></i> <span>查看设备</span>
                 <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                 </span>
             </a>
             <ul class="treeview-menu">
                 <li>
                     <a href="../pages/device/device-list.jsp" onclick="setSidebarActive(this)" target="iframe">
                         <i class="fa fa-circle-o"></i>步进电机
                     </a>
                 </li>
                 <li>
                     <a href="#" onclick="setSidebarActive(this)"  target="iframe">
                         <i class="fa fa-circle-o"></i>传感器
                     </a>
                 </li>
                 <li>
                     <a href="#" onclick="setSidebarActive(this)"  target="iframe">
                         <i class="fa fa-circle-o"></i>舵机
                     </a>
                 </li>
             </ul>
         </li>

         <%--<li class="treeview">
             <a href="#">
                 <i class="fa fa-cube"></i> <span>管理设备</span>
                 <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                 </span>
             </a>
             <ul class="treeview-menu">
                 <li>
                     <a href="../pages/category/category-list.jsp" onclick="setSidebarActive(this)" target="iframe">
                         <i class="fa fa-circle-o"></i>步进电机
                     </a>
                 </li>
                 <li>
                     <a href="#" onclick="setSidebarActive(this)"  target="iframe">
                         <i class="fa fa-circle-o"></i>传感器
                     </a>
                 </li>
                 <li>
                     <a href="#" onclick="setSidebarActive(this)"  target="iframe">
                         <i class="fa fa-circle-o"></i>舵机
                     </a>
                 </li>
             </ul>
         </li>--%>

                  <li class="treeview">
             <a href="../pages/log/log-list.jsp" onclick="setSidebarActive(this)" target="iframe">
                 <i class="fa fa-cube"></i> <span>实时数据</span>
             </a>
         </li>

         <li class="treeview">
             <a href="../pages/user/user-update.jsp" onclick="setSidebarActive(this)" target="iframe">
                 <i class="fa fa-cube"></i> <span>个人设置</span>
             </a>
         </li>

                   </ul>

               </section>
               <!-- /.sidebar -->
</aside>
