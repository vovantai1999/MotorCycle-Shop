
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Motor Bike - Sport | Shop</title>

    <jsp:include page="/layout/css_head.jsp"></jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"/>
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
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Motor</a></li>
                                    <li class="breadcrumb-item"><a href="javascript: void(0);">Speed</a></li>
                                    <li class="breadcrumb-item active">World style</li>
                                </ol>
                            </div>
                            <h4 class="page-title">Motor Bike</h4>
                        </div>
                    </div>
                </div>
                <!-- end page title -->

                <div class="row">
                    <div class="col-sm-12">
                        <div class="card-box">
                            <div class="table-responsive">
                                <table class="table m-0" style="text-align: center">

                                    <thead class="thead-light">
                                    <tr> <a href="/products?action=create"><i class="fa-regular fa-square-plus"></i> Add Product</a> </tr>
                                    <tr>
                                        <th>ID</th>
                                        <th>IMAGE</th>
                                        <th>PRODUCT TITLE</th>
                                        <th>QUANTITY</th>
                                        <th>PRICE</th>
                                        <th>Actions</th>
                                    </tr>
                                    <c:forEach var="product" items="${listProduct}">
                                    <tr>
                                        <td><c:out value="${product.id}"/></td>
                                        <td><img width="150px" height="auto" src="<c:out value="${product.img}"/>" alt=""> </td>
                                        <td><c:out value="${product.title}"/></td>
                                        <td><c:out value="${product.quantity}"/></td>
                                        <td>
                                            <fmt:setLocale value = "fr_CA"/>
                                            <fmt:formatNumber value = "${product.price}" type = "currency"/>
                                        </td>
                                        <td>
                                            <a href="/products?action=edit&id=${product.id}" style="margin-right:25px"><i class="fa-regular fa-pen-to-square"></i> </a>
                                            <a href="/products?action=delete&id=${product.id}" onclick="return confirm('Do you want to delete?')"><i class="fa-regular fa-trash-can"></i> </a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">

                                        <c:if test="${requestScope.currentPage != 1}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="/products?page=${requestScope.currentPage - 1}&searchproduct=${requestScope.searchproduct}">Previous</a>
                                            </li>
                                        </c:if>
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${requestScope.currentPage eq i}">
                                                    <li class="page-item"><a class="page-link"
                                                                             href="/products?page=${i}&searchproduct=${requestScope.searchproduct}">${i}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link"
                                                                             href="/products?page=${i}&searchproduct=${requestScope.searchproduct}">${i}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                                            <li class="page-item"><a class="page-link"
                                                                     href="/products?page=${requestScope.currentPage + 1}&searchproduct=${requestScope.searchproduct}">Next</a>
                                            </li>
                                        </c:if>
                                    </ul>
                                </nav>
                            </div>
                            <!-- end row -->

                        </div>
                        <!-- end card-box -->
                    </div>
                    <!-- end col -->
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

<jsp:include page="/layout/footer_js.jsp">
    <jsp:param name="page" value="list"/>
</jsp:include>

</body>

</html>
