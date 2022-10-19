
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
                                    <li class="breadcrumb-item"><a href="/products">List Products</a></li>
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Sport</a></li>
                                    <li class="breadcrumb-item active">Speed</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Form Add Product</h4>
                            <div>
                                <c:if test="${requestScope['success'] == true}">
                                    <span class="alert alert-success "
                                          style="font-size: 15px">Add success</span>
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
                            <form class="form-horizontal" method="post" action="/products?action=create">
                                <div class="form-group row">
                                    <label class="col-md-2 control-label">Image</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" name="img">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="title">Product Title</label>
                                    <div class="col-md-10">
                                        <input type="text" id="title" name="title" class="form-control" placeholder="Product Title">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="quantity">Quantity</label>
                                    <div class="col-md-10">
                                        <input type="text" id="quantity" name="quantity" class="form-control" placeholder="Quantity">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label class="col-md-2 control-label" for="price">Price</label>
                                    <div class="col-md-10">
                                        <input type="text" id="price" name="price" class="form-control" placeholder="Price">
                                    </div>
                                </div>
                                <button onclick="return confirm('DO YOU WANT ADD PRODUCTS?')">Add Product</button>
                            </form>
                            <!-- end row -->

                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="row" style="margin-top: 20px">
                            <div class="col-12">
<%--                                <c:if test="${requestScope['success'] == true}">--%>
<%--                                    <span class="alert alert-success "--%>
<%--                                          style="font-size: 15px">Thêm mới thành công</span>--%>
<%--                                </c:if>--%>
                                <c:if test="${!requestScope['errors'].isEmpty()}">
                                    <c:forEach items="${requestScope['errors']}" var="item">
                                        <div class="alert alert-danger" style="margin-top: -20px;">
                                            <span style="font-size: 15px;">
                                                    ${item}
                                            </span>
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