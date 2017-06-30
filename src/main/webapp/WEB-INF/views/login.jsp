<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <!-- /.website title -->
    <title>UniHub</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <!-- CSS Files -->
    <link href="../../static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../../static/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../static/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
    <link href="../../static/css/animate.css" rel="stylesheet" media="screen">
    <link href="../../static/css/owl.theme.css" rel="stylesheet">
    <link href="../../static/css/owl.carousel.css" rel="stylesheet">

    <!-- Colors -->
    <link href="../../static/css/css-index.css" rel="stylesheet" media="screen">
    <!-- <link href="css/css-index-green.css" rel="stylesheet" media="screen"> -->
    <!-- <link href="css/css-index-purple.css" rel="stylesheet" media="screen"> -->
    <!-- <link href="css/css-index-red.css" rel="stylesheet" media="screen"> -->
    <!-- <link href="css/css-index-orange.css" rel="stylesheet" media="screen"> -->
    <!-- <link href="css/css-index-yellow.css" rel="stylesheet" media="screen"> -->

    <!-- Google Fonts -->
    <link rel="stylesheet"
          href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic"/>
</head>

<body data-spy="scroll" data-target="#navbar-scroll">

<!-- /.preloader -->
<div id="preloader"></div>
<div id="top"></div>

<!-- /.parallax full screen background image -->
<div class="fullscreen landing parallax"
     style="background-image:url('../../static/images/bg.jpg');background-blend-mode: 0.2" data-img-width="2000"
     data-img-height="1333" data-diff="100">

    <div class="overlay">
        <div class="container">
            <div class="row">
                <div class="col-md-7">

                    <!-- /.logo -->
                    <div class="logo wow fadeInDown"><a href=""><img src="../../static/images/logo.png" alt="mylogo"
                                                                     style="height: 110px; width: 350px;"></a></div>

                    <!-- /.main title -->
                    <h1 class="wow fadeInLeft">
                        Exam
                    </h1>

                    <!-- /.header paragraph -->
                    <div class="landing-text wow fadeInUp">
                        <p>Wb programming exam</p>
                    </div>

                    <!-- /.header button -->
                    <div class="head-btn wow fadeInLeft">
                        <a href="#register" class="btn-default">Register</a>
                    </div>


                </div>

                <!-- /.signup form -->
                <div class="col-md-5">
                    <div class="signup-header wow fadeInUp">
                        <h3 class="form-title text-center">Log In</h3>
                        <form action="login" method="post" class="form-horizontal">
                            <div class="form-group">
                                <input class="form-control input-lg" type="text" id="username" name="ssoId"
                                       placeholder="Username" required>
                            </div>
                            <div class="form-group">
                                <input class="form-control input-lg" name="password" id="password" type="password"
                                       placeholder="Password" required>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="form-group last">
                                <input type="submit" class="btn btn-warning btn-block btn-lg" value="Login"
                                       name="LOGIN">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- NAVIGATION -->
<div id="menu">
    <nav class="navbar-wrapper navbar-default" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-backyard">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <div id="navbar-scroll" class="collapse navbar-collapse navbar-backyard navbar-right">
                <ul class="nav navbar-nav">
                    <li><a href="#register">Register</a></li>

                </ul>
            </div>
        </div>
    </nav>
</div>

<!-- /.download section -->
<div id="register">
    <div class="action fullscreen parallax" style="background-image:url('../../static/images/bg.jpg');"
         data-img-width="2000" data-img-height="1333" data-diff="100">
        <div class="overlay">
            <div class="container">
                <div id="pt-main" class="pt-perspective container">
                    <div>
                        <form:form method="POST" action="newuser" modelAttribute="user" id="contact-form"
                                   class="form-horizontal">
                        <div class=" cls1 container contact-row" id='page1' style="width: 80%; margin-left: 10%;">
                            <div class="form-group">
                                <form:input type="text" class="cls1 form-control wow fadeInUp"
                                            placeholder="Username" path="ssoId"/>
                            </div>
                            <div class="form-group">
                                <form:input type="text" class="cls1 form-control wow fadeInUp"
                                            placeholder="First Name" path="firstName"/>
                            </div>
                            <div class="form-group">
                                <form:input type="text" class="cls1 form-control wow fadeInUp"
                                            placeholder="Last Name" path="lastName"/>
                            </div>
                            <div class="form-group">
                                <form:input type="text" class="cls1 form-control wow fadeInUp"
                                            placeholder="Password" path="password"/>
                            </div>
                            <div class="form-group">
                                <input style="width: 20%; margin-left: 38%; margin-top: 5%;" type="submit"
                                       class="btn-primary wow fadeInUp" value="Submit" name="REGISTER"/>
                            </div>
                        </div>
                    </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- /.contact section -->


<!-- /.footer -->

<a href="#" class="scrollToTop"><i class="pe-7s-up-arrow pe-va"></i></a>
<!-- /.javascript files -->
<script src="../../static/js/jquery.js"></script>
<script src="../../static/js/bootstrap.min.js"></script>
<script src="../../static/js/custom.js"></script>
<script src="../../static/js/jquery.sticky.js"></script>
<script src="../../static/js/wow.min.js"></script>
<script src="../../static/js/owl.carousel.min.js"></script>
<script>
    new WOW().init();
</script>
</body>
</html>