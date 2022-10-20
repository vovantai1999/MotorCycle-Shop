<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<!-- Vendor js -->
<script src="/assets\js\vendor.min.js"></script>

<c:if test="${param.page == 'index'}">
    <script src="/assets\libs\morris-js\morris.min.js"></script>
    <script src="/assets\libs\raphael\raphael.min.js"></script>

    <script src="/assets\js\pages\dashboard.init.js"></script>
</c:if>

<c:if test="${param.toast =='create'}">
    <script src="assets\libs\toastr\toastr.min.js"></script>
    <script src="assets\js\pages\toastr.init.js"></script>
</c:if>
<!-- App js -->
<script src="/assets\js\app.min.js"></script>