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
                        ${userDetails.firstName} ${userDetails.lastName}
                    </h1>
                    <h2 class="wow fadeInLeft" style="color: white">
                        Teacher
                    </h2>
                    <div class="landing-text wow fadeInLeft">
                        <p>E-mail: ${userDetails.email}</p>
                        <p>Phone: ${userDetails.phone}</p>
                    </div>
                    <p><a href="<c:url value='/view-university-${student.user.university.id}'/>">University: ${userDetails.university.longName}</a></p>
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
                    <img src=${userDetails.photoLink} alt="phone" style="margin-top: 20%; width: 50%; height: 80%;"
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
                    <li><a href="#feature">Courses</a></li>
                    <li><a href="#feature1">Schedule</a></li>
                    <li><a href="#feature2">Public files</a></li>
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

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp">
                    <h3 class="form-title text-center">Invite people to create admin account</h3>
                    <form:form method="POST" class="form-header" action="addCourse" role="form"
                               modelAttribute="courseDto">
                        <div class="form-group">
                            <form:input class="form-control input-lg" name="MERGE1" id="teacherEmails" type="text"
                                        placeholder="Course name" path="courseName"/>
                            <form:input class="form-control input-lg" name="MERGE1" id="teacherEmails" type="text"
                                        placeholder="Syllabus link" path="syllabusLink"/>
                            <form:input class="form-control input-lg" name="MERGE1" id="teacherEmails" type="text"
                                        placeholder="Course materials link" path="courseMaterialsLink"/>
                            <div class="form-group">
                                <form:select path="studyYear" class="form-control" id="groupAdminCreate"
                                             placeholder="Study Year" value="Study Year">
                                    <option disabled selected>Study Year</option>
                                    <c:forEach items="${studyYears}" var="studyYearVar">
                                        <form:option value="${studyYearVar.year}">${studyYearVar.year}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn-primary" id="buttonInviteTeachers"
                                   value="Add course">
                        </div>
                    </form:form>
                </div>
            </div>

            <div class="col-md-6 ">
                <!-- /.feature 1 -->
                <div class="fadeInRight" style="width:100%;">
                    <h3 class="form-title text-center">Owned courses</h3>
                    <i class="pe-7s-notebook pe-5x pe-va wow fadeInUp"></i>
                    <div class="inner" style="width:100%;">
                        <table style="margin-top:0px">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Syllabus</th>
                                <th>Files</th>
                                <th>Study Year</th>
                                <th>Manage courses</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${courses}" var="course">
                                <tr>
                                    <td><strong>${course.name}</strong></td>
                                    <td><a href="${course.syllabus}">Syllabus Link</a></td>
                                    <td><a href="${course.courseMaterialsLink}">Materials Link</a></td>
                                    <td>${course.studyYear.year}</td>
                                    <td>
                                        <sec:authorize access="hasRole('TEACHER')">
                                            <a href="<c:url value='/delete-course-${course.id}' />"
                                               class="clsActionButton">Delete</a>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('TEACHER')">
                                            <a href="<c:url value='/view-course-${course.id}' />"
                                               class="clsActionButton">View</a>
                                        </sec:authorize>
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

<!-- /.feature 2 section -->
<div id="feature2">
    <div class="container">
        <div class="row">
            <!-- /.feature content -->
            <div class="col-md-6 wow fadeInLeft">
                <div class="btn-section"><a href="${schedule}"
                                            style="margin-left: 120px;" class="btn-default">Teacher Schedule</a>
                </div>
                <div class="btn-section"><a href="${materials}"
                                            style="margin-left: 120px; margin-top: 20px" class="btn-default">Teacher Public Files</a>
                </div>
            </div>
            <!-- /.feature image -->
            <div class="col-md-6 feature-2-pic wow fadeInRight">
                <img src="../../static/images/collegestudents.jpg" alt="image" class="img-responsive">
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