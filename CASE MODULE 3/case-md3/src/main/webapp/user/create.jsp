<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Motor Bike - Sport | Shop</title>
    <jsp:include page="/layout/css_head.jsp"></jsp:include>
    <link href="/assets\libs\toastr\toastr.min.css" rel="stylesheet" type="text/css">

</head>

<body data-layout="horizontal">


<!-- Begin page -->
<div id="wrapper">

    <!-- Navigation Bar-->
    <jsp:include page="/layout/top_nav.jsp"></jsp:include>
    <!-- End Navigation Bar-->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">

                <!-- start page title -->
                <div class="row">
                    <div class="col-12">
                        <div class="page-title-box">
                            <div class="page-title-right">
                                <ol class="breadcrumb m-0">
                                    <li class="breadcrumb-item"><a href="/users">List Users</a></li>
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">--</a></li>
                                    <li class="breadcrumb-item active">--</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Form Add User</h4>
                            <div>
                                <c:if test="${requestScope['success'] == true}">
                                    <span class="alert alert-success" style="font-size: 15px">Add success</span>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-sm-12">
                        <div class="card-box">
                            <h4 class="header-title"></h4>
                            <form class="form-horizontal" method="post" action="/users?action=create">
                                <div class="form-group row">
                                    <label class="col-md-2 control-label">Full Name</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="fullName">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="userName">User Name</label>
                                    <div class="col-md-10">
                                        <input type="text" id="userName" name="userName" class="form-control" placeholder="User Name">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="passWord">Password</label>
                                    <div class="col-md-10">
                                        <input type="password" id="passWord" name="passWord" class="form-control" placeholder="Password">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="email">Email</label>
                                    <div class="col-md-10">
                                        <input type="email" id="email" name="email" class="form-control" placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="phone">Phone</label>
                                    <div class="col-md-10">
                                        <input type="text" id="phone" name="phone" class="form-control" placeholder="Phone Number">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="address">Address</label>
                                    <div class="col-md-10">
                                        <input type="text" id="address" name="address" class="form-control" placeholder="Address">
                                    </div>
                                </div>
                                <button onclick="return confirm('DO YOU WANT ADD USER?')">Add User</button>
                            </form>
                            <!-- end row -->

                        </div>
                    </div>
                    <div class="col-3">
                        <div class="row" >
                            <div class="col-12">
<%--                                <c:if test="${requestScope['success'] == true}">--%>
<%--                                    <span class="alert alert-success" style="font-size: 15px">Thêm mới thành công</span>--%>
<%--                                </c:if>--%>
                                <c:if test="${!requestScope['errors'].isEmpty()}">
                                    <c:forEach items="${requestScope['errors']}" var="item">
                                        <div class="alert alert-danger" style="margin-top: -20px;">
                                            <span style="font-size: 15px;">${item}</span>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end row -->

            </div>
            <!-- end container-fluid -->

        </div>
        <!-- end content -->



        <!-- Footer Start -->
        <jsp:include page="/layout/footer.jsp"></jsp:include>
        <!-- end Footer -->

    </div>

    <!-- ============================================================== -->
    <!-- End Page content -->
    <!-- ============================================================== -->

</div>
<!-- END wrapper -->

<!-- Right Sidebar -->
<jsp:include page="/layout/right_bar.jsp"></jsp:include>

<c:if test="${requestScope.message !=null}">
    <script>

        window.onload = function(e){
            toastr["success"]("ADD SUCCESS!");
        }
    </script>
</c:if>

<jsp:include page="/layout/footer_js.jsp">
    <jsp:param name="page" value="create"/>
    <jsp:param name="toast" value="create"/>
</jsp:include>
<script>

    toastr.options = {
        "closeButton": true,
        "debug": false,
        "newestOnTop": false,
        "progressBar": true,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    }

</script>

</body>

</html>
