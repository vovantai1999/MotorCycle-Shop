<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Login | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- App favicon -->
    <link rel="shortcut icon" href="/assets\images\img\logo-xe-may.jpg">

    <!-- App css -->
    <link href="/assets\css\bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-stylesheet">
    <link href="/assets\css\icons.min.css" rel="stylesheet" type="text/css">
    <link href="/assets\css\app.min.css" rel="stylesheet" type="text/css" id="app-stylesheet">

</head>

<body>

<div class="account-pages mt-5 mb-5">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 col-lg-6 col-xl-5">
                <div class="card">

                    <div class="text-center account-logo-box">
                        <div class="mt-2 mb-2">
                            <a href="index.html" class="text-success">
                                <span><img src="/assets\images\img\avatar-login.jpg" alt="" height="100" width="100"></span>
                            </a>
                        </div>
                    </div>
                 <c:if test="${err!=null}">
                     <div class="alert alert-danger" role="alert">${err}</div>
                 </c:if>

                    <div class="card-body">

                        <form method="post" action="/login">

                            <div class="form-group">
                                <input class="form-control" type="text" id="username" required="" name="username">
                            </div>

                            <div class="form-group">
                                <input class="form-control" type="password" required="" id="password" name="password">
                            </div>

                            <div class="form-group">
                                <div class="custom-control custom-checkbox checkbox-success">
                                    <input type="checkbox" class="custom-control-input" id="checkbox-signin" checked="">
                                    <label class="custom-control-label" for="checkbox-signin">Remember me</label>
                                </div>
                            </div>

                            <div class="form-group text-center mt-4 pt-2">
                                <div class="col-sm-12">
                                    <a href="page-recoverpw.html" class="text-muted"><i class="fa fa-lock mr-1"></i> Forgot your password?</a>
                                </div>
                            </div>

                            <div class="form-group account-btn text-center mt-2">
                                <div class="col-12">
                                    <button class="btn width-md btn-bordered btn-danger waves-effect waves-light" type="submit">Log In</button>
                                </div>
                            </div>
                        </form>

                    </div>
                    <!-- end card-body -->
                </div>
                <!-- end card -->

                <div class="row mt-5">
                    <div class="col-sm-12 text-center">
                        <p class="text-muted">Don't have an account? <a href="page-register.html" class="text-primary ml-1"><b>Sign Up</b></a></p>
                    </div>
                </div>

            </div>
            <!-- end col -->
        </div>
        <!-- end row -->
    </div>
    <!-- end container -->
</div>
<!-- end page -->

<!-- Vendor js -->
<script src="/assets\js\vendor.min.js"></script>

<!-- App js -->
<script src="/assets\js\app.min.js"></script>

</body>

</html>





<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<!doctype html>--%>
<%--<html lang="en">--%>

<%--<head>--%>

<%--    <meta charset="utf-8">--%>
<%--    <title>Login | Skote - Responsive Bootstrap 4 Admin Dashboard</title>--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <meta content="Premium Multipurpose Admin & Dashboard Template" name="description">--%>
<%--    <meta content="Themesbrand" name="author">--%>
<%--    <!-- App favicon -->--%>
<%--    <link rel="shortcut icon" href="/assets/images/favicon.ico">--%>

<%--    <!-- Bootstrap Css -->--%>
<%--    <link href="/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css">--%>
<%--    <!-- Icons Css -->--%>
<%--    <link href="/assets/css/icons.min.css" rel="stylesheet" type="text/css">--%>
<%--    <!-- App Css-->--%>
<%--    <link href="/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css">--%>

<%--</head>--%>

<%--<body>--%>
<%--<div class="home-btn d-none d-sm-block">--%>
<%--    <a href="index.html" class="text-dark"><i class="fas fa-home h2"></i></a>--%>
<%--</div>--%>
<%--<div class="account-pages my-5 pt-sm-5">--%>
<%--    <div class="container">--%>
<%--        <div class="row justify-content-center">--%>
<%--            <div class="col-md-8 col-lg-6 col-xl-5">--%>
<%--                <div class="card overflow-hidden">--%>
<%--                    <div class="bg-soft-primary">--%>
<%--                        <div class="row">--%>
<%--                            <div class="col-7">--%>
<%--                                <div class="text-primary p-4">--%>
<%--                                    <h5 class="text-primary">Welcome Back !</h5>--%>
<%--                                    <p>Sign in to continue to Skote.</p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="row" style="position: fixed;right: -47px;top: 84px; z-index: 999">--%>
<%--                                <div class="col-12" style="width: 450px;">--%>
<%--                                    <c:if test="${requestScope.error!=null}">--%>
<%--                                        <div class="alert alert-icon alert-danger alert-dismissible fade show mb-0 col-10" role="alert">--%>
<%--                                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">--%>
<%--                                                <span>x</span>--%>
<%--                                            </button>--%>
<%--                                            <strong>Errors!</strong> <br>--%>
<%--                                            <li>${requestScope.error}</li>--%>
<%--                                        </div>--%>
<%--                                    </c:if>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            <div class="col-5 align-self-end">--%>
<%--                                <img src="assets\images\profile-img.png" alt="" class="img-fluid">--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="card-body pt-0">--%>
<%--                        <div>--%>
<%--                            <a href="#">--%>
<%--                                <div class="avatar-md profile-user-wid mb-4">--%>
<%--                                            <span class="avatar-title rounded-circle bg-light">--%>
<%--                                                <img src="assets\images\logo.svg" alt="" class="rounded-circle" height="34">--%>
<%--                                            </span>--%>
<%--                                </div>--%>
<%--                            </a>--%>
<%--                        </div>--%>
<%--                        <div class="p-2">--%>
<%--                            <form class="form-horizontal" method="post" action="/users?action=login">--%>

<%--                                <div class="form-group">--%>
<%--                                    <label for="username">Username</label>--%>
<%--                                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">--%>
<%--                                </div>--%>

<%--                                <div class="form-group">--%>
<%--                                    <label for="password">Password</label>--%>
<%--                                    <input type="password" class="form-control" id="password"  name="password" placeholder="Enter password">--%>
<%--                                </div>--%>

<%--                                <div class="custom-control custom-checkbox">--%>
<%--                                    <input type="checkbox" class="custom-control-input" id="customControlInline">--%>
<%--                                    <label class="custom-control-label" for="customControlInline">Remember me</label>--%>
<%--                                </div>--%>

<%--                                <div class="mt-3">--%>
<%--                                    <button class="btn btn-primary btn-block waves-effect waves-light" type="submit">Log In</button>--%>
<%--                                </div>--%>



<%--                                <div class="mt-4 text-center">--%>
<%--                                    <h5 class="font-size-14 mb-3">Sign in with</h5>--%>

<%--                                    <ul class="list-inline">--%>
<%--                                        <li class="list-inline-item">--%>
<%--                                            <a href="#" class="social-list-item bg-primary text-white border-primary">--%>
<%--                                                <i class="mdi mdi-facebook"></i>--%>
<%--                                            </a>--%>
<%--                                        </li>--%>
<%--                                        <li class="list-inline-item">--%>
<%--                                            <a href="#" class="social-list-item bg-info text-white border-info">--%>
<%--                                                <i class="mdi mdi-twitter"></i>--%>
<%--                                            </a>--%>
<%--                                        </li>--%>
<%--                                        <li class="list-inline-item">--%>
<%--                                            <a href="#" class="social-list-item bg-danger text-white border-danger">--%>
<%--                                                <i class="mdi mdi-google"></i>--%>
<%--                                            </a>--%>
<%--                                        </li>--%>
<%--                                    </ul>--%>
<%--                                </div>--%>

<%--                                <div class="mt-4 text-center">--%>
<%--                                    <a href="#" class="text-muted"><i class="mdi mdi-lock mr-1"></i> Forgot your password?</a>--%>
<%--                                </div>--%>
<%--                            </form>--%>
<%--                        </div>--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="mt-5 text-center">--%>

<%--                    <div>--%>
<%--                        <p>Don't have an account ? <a href="#" class="font-weight-medium text-primary"> Signup now </a> </p>--%>
<%--                        <p>© 2020 Skote. Crafted with <i class="mdi mdi-heart text-danger"></i> by Themesbrand</p>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<!-- JAVASCRIPT -->--%>
<%--<script src="/assets/libs/jquery/jquery.min.js"></script>--%>
<%--<script src="/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>--%>
<%--<script src="/assets/libs/metismenu/metisMenu.min.js"></script>--%>
<%--<script src="/assets/libs/simplebar/simplebar.min.js"></script>--%>
<%--<script src="/assets/libs/node-waves/waves.min.js"></script>--%>

<%--<!-- App js -->--%>
<%--<script src="/assets/js/app.js"></script>--%>
<%--</body>--%>
<%--</html>--%>
