<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

    <!-- /.website title -->
    <title>Backyard App Landing Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <!-- CSS Files -->
    <!--<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">-->
    <link href="../../static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../../static/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../static/css/animate.css" rel="stylesheet" media="screen">
    <link href="../../static/css/owl.theme.css" rel="stylesheet">
    <link href="../../static/css/owl.carousel.css" rel="stylesheet">
    <link href="../../static/css/spec.css" rel="stylesheet">
    <!-- Colors -->
    <link href="../../static/css/css-app.css" rel="stylesheet" media="screen">
    <!-- Google Fonts -->
    <link rel="stylesheet"
          href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic"/>

    <script type="text/javascript">
        $(document).ready(
            function () {
                $('#show').click(function () {
                    $('#table-enroll').toggle();
                });
            });
    </script>
</head>

<body data-spy="scroll" data-target="#navbar-scroll">

<!-- /.preloader -->
<div id="preloader"></div>
<div id="top"></div>

<!-- /.parallax full screen background image -->
<div class="fullscreen landing parallax" style="background-image:url('../../static/images/bg.jpg');"
     data-img-width="2000" data-img-height="1325" data-diff="100">

    <div class="overlay">
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <!-- /.logo -->
                    <div class="logo wow fadeInDown" style="margin-top: 0%;"><a href=""><img
                            src="../../static/images/white-logo.png" alt="logo" style="height: 80%;width: 40%;"></a>
                    </div>
                    <h1 class="wow fadeInLeft" style="margin-top: 0%">
                        ${teacher.user.firstName} ${teacher.user.lastName}
                    </h1>
                    <h2 class="wow fadeInLeft" style="color: white">
                        Teacher
                    </h2>
                    <div class="landing-text wow fadeInLeft">
                        <p>E-mail: ${teacher.user.email}</p>
                        <p>Phone: ${teacher.user.phone}</p>
                    </div>
                    <p>
                        <a href="<c:url value='/view-university-${teacher.user.university.id}'/>">University: ${teacher.user.university.longName}</a>
                    </p>
                    <p><a href="<c:url value='${teacher.scheduleLink}'/>">Schedule</a></p>
                    <p><a href="<c:url value='${teacher.filesLink}'/>">Files</a></p>
                    <a href="<c:url value="/logout"/>" class="logg">
                        <img class="photo" src="../../static/images/logout-512 (1).png"
                             style="float:right; width: 2%; height: 2%; margin-top: 2%; margin-right: 2%;">
                    </a>
                    <p style="float:right; margin-right: 2%;margin-top: 2%;">Log out</p>
                    <a href="/" class="logg">
                        <img class="photo" src="../../static/images/home-5-xxl.png"
                             style="float:right; width: 2%; height: 2%; margin-top: 6%; margin-right: -8%;">
                    </a>
                </div>
                <!--/.phone image-->
                <div class="col-md-5">
                    <img src=${teacher.user.photoLink} alt="phone" style="margin-top: 20%; width: 50%; height: 80%;"
                         class="header-phone img-responsive wow fadeInRight">
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
                <a class="navbar-brand site-name" href="#top"><img src="../../static/images/logo5.png" alt="logo"></a>
            </div>

            <div id="navbar-scroll" class="collapse navbar-collapse navbar-backyard navbar-right">
                <ul class="nav navbar-nav" style="text-align: center;">
                    <li><a href="#feature">Courses taught</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<!--/.feature0 section-->
<div id="feature1" style="padding-top:60px;padding-bottom: 60px;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center">
                <div class="col-md-6 ">
                    <!-- /.feature 1 -->
                    <div class="fadeInRight" style="width:100%;">
                        <h3 class="form-title text-center">Courses</h3>
                        <i class="pe-7s-notebook pe-5x pe-va wow fadeInUp"></i>
                        <div class="inner" style="width:100%;">
                            <table style="margin-top:0px">
                                <thead>
                                <tr>
                                    <th>Course name</th>
                                    <th>Syllabus</th>
                                    <th>Files</th>
                                    <th>Study Year</th>
                                    <th>View details</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${teacher.courses}" var="course">
                                    <tr>
                                        <td><strong>${course.name}</strong></td>
                                        <td><a href="${course.syllabus}">Syllabus Link</a></td>
                                        <td><a href="${course.courseMaterialsLink}">Materials Link</a></td>
                                        <td>${course.studyYear.year}</td>
                                        <td>
                                            <a href="<c:url value='/view-course-${course.id}' />"
                                               class="clsActionButton">View</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- /.footer -->
    <footer id="footer">
        <div class="container">
            <div class="col-sm-4 col-sm-offset-4">
                <!-- /.social links -->
                <div class="social text-center">
                    <ul>
                        <li><a class="wow fadeInUp" href="https://twitter.com/">
                            <img class="fa fa-twitter"
                                 src="../../static/images/twitter-512.png"
                                 style="width: 14px; height: 14px; margin-bottom: 4px;"/></a></li>
                        <li><a class="wow fadeInUp" href="https://www.facebook.com/UniHubb/" data-wow-delay="0.2s">
                            <img src="../../static/images/4c25d18059353799aff91dcdd8f19bb4.png"
                                 style="width: 19px; height: 20px; margin-bottom: 5px;"
                                 class="fa fa-facebook"/></a></li>
                        <li><a class="wow fadeInUp" href="https://plus.google.com/" data-wow-delay="0.4s">
                            <img src="../../static/images/google-plus-google-icon.png"
                                 style="width: 15px; height: 15px; margin-bottom: 5px;"
                                 class="fa fa-google-plus"/></a></li>
                        <li><a class="wow fadeInUp" href="https://instagram.com/" data-wow-delay="0.6s">
                            <img src="../../static/images/824273_original_512x512.png"
                                 style="width: 30px; height: 28px; margin-bottom: 11px;"
                                 class="fa fa-instagram"/></a></li>
                    </ul>
                </div>
                <div class="text-center wow fadeInUp" style="font-size: 14px;">Copyright UniHub 2017</div>
                <a href="#" class="scrollToTop"><i class="pe-7s-up-arrow pe-va"></i></a>
            </div>
        </div>
    </footer>

    <!-- /.javascript files -->
    <script src="../../static/js/jquery.js"></script>
    <script src="../../static/js/bootstrap.min.js"></script>
    <script src="../../static/js/custom.js"></script>
    <script src="../../static/js/jquery.sticky.js"></script>
    <script src="../../static/js/wow.min.js"></script>
    <script src="../../static/js/owl.carousel.min.js"></script>
    <script src="../../static/js/ekko-lightbox-min.js"></script>
    <script type="text/javascript">
        $(document).delegate('*[data-toggle="lightbox"]', 'click', function (event) {
            event.preventDefault();
            $(this).ekkoLightbox();
        });
    </script>
    <script>
        new WOW().init();
    </script>
</body>
</html>