<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

    <!-- /.website title -->
    <title>UniHub</title>
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
                    <div class="logo wow fadeInDown" style="margin-top: 20px;"><a href=""><img
                            src="../../static/images/white-logo.png" alt="logo" style="height: 120px;width: 250px;"></a>
                    </div>
                    <h1 class="wow fadeInLeft" style="margin-top: 60px;">
                        Account Details
                    </h1>
                    <div class="landing-text wow fadeInLeft">
                        <p>Account :<label id="account">${userDetails.ssoId}</label></p>
                        <p>University :<label id="shortName">${userDetails.university.longName}</label></p>
                        <p>Email :<label id="email">${userDetails.email}</label></p>
                        <p>Role :<label id="position">${userDetails.profileEnum}</label></p>
                    </div>
                </div>
                <div class="col-md-5">
                    <ul class="ul-address" style="margin-top: 0px;">
                        <li><i>${userDetails.university.shortName}</i></li>
                        <li><i class="pe-7s-map-marker"></i>${userDetails.university.address}<br>
                            ${userDetails.university.city}
                        </li>
                        <li><i class="pe-7s-phone"></i>
                            ${userDetails.university.phone}
                        </li>
                        <li><i class="pe-7s-mail"></i>${userDetails.university.email}</li>
                        <li><i class="pe-7s-look"></i><a href="#">www.yoursite.com</a></li>
                    </ul>
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
                <a class="navbar-brand site-name" href="#top"><img src="../../static/images/logo5.png" alt="logo"
                                                                   style="height: 50px;"></a>
            </div>

            <div id="navbar-scroll" class="collapse navbar-collapse navbar-backyard navbar-right">
                <ul class="nav navbar-nav" style="text-align: center;">
                    <li><a href="#form">Complete Registration</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#features">Features</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<!--/.feature0 section-->
<div id="form" style="padding-top:60px;padding-bottom: 60px;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center">

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp">
                    <h3 class="form-title text-center">Complete registration</h3>
                    <form:form method="POST" class="form-header" action="/create-account" role="form"
                               modelAttribute="newUserDto">
                        <div class="form-group">
                            <form:input type="hidden" value="${userDetails.email}" path="email"/>
                            <form:input type="hidden" value="${userDetails.ssoId}" path="ssoId"/>
                            <form:input type="hidden" value="${userDetails.university.shortName}" path="shortName"/>
                            <form:input type="hidden" value="${userDetails.profileEnum}" path="profileEnum"/>
                            <form:input type="hidden" value="${userDetails.schoolGroup.groupNumber}" path="groupNumber"/>
                            <form:input class="form-control input-lg" name="MERGE1" id="firstName" type="text"
                                        placeholder="First name" path="firstName"/>
                            <form:input class="form-control input-lg" name="MERGE1" id="lastName" type="text"
                                        placeholder="Last name" path="lastName"/>
                            <form:input class="form-control input-lg" name="MERGE1" id="phone" type="text"
                                        placeholder="Phone number" path="phone"/>
                            <form:input class="form-control input-lg" name="MERGE1" id="password" type="password"
                                        placeholder="Account password" path="password"/>
                            <form:input class="form-control input-lg" name="MERGE1" id="photoLink" type="text"
                                        placeholder="Web link to your photo" path="photoLink"/>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn btn-warning btn-block btn-lg" id="buttonInviteAdmins"
                                   value="Complete registration">
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="about">
    <div class="container">
        <div class="row">

            <!-- /.intro image -->
            <div class="col-md-6 intro-pic wow slideInLeft">
                <img src="../../static/images/intro-image.jpg" alt="image" class="img-responsive">
            </div>

            <!-- /.intro content -->
            <div class="col-md-6 wow slideInRight">
                <h2>Easy management</h2>
                <p>An easy way to manage everything regarding shortName , such as attendences , grades,
                    groups, files
                    ,schedule and any problems .
                </p>

                <div class="btn-section"><a href="#features" class="btn-default">More Features</a></div>

            </div>
        </div>
    </div>
</div>

<!-- /.feature section -->
<div id="features">
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12 text-center feature-title">

                <!-- /.feature title -->
                <h2>Make your work faster and be more efficient </h2>
                <p>Better teacher-student communication as well as easier task,homework and paperwork
                    management .</p>
            </div>
        </div>
        <div class="row row-feat">
            <div class="col-md-4 text-center">

                <!-- /.feature image -->
                <div class="feature-img">
                    <img src="../../static/images/feature-image.jpg" alt="image"
                         class="img-responsive wow fadeInLeft">
                </div>
            </div>

            <div class="col-md-8">

                <!-- /.feature 1 -->
                <div class="col-sm-6 feat-list">
                    <i class="pe-7s-chat pe-5x pe-va wow fadeInUp"></i>
                    <div class="inner">
                        <h4>Instant Chat</h4>
                        <p>Bringing students and teachers together in a modern way ,free of any delays ,
                            with instant
                            chat.
                        </p>
                    </div>
                </div>

                <!-- /.feature 2 -->
                <div class="col-sm-6 feat-list">
                    <i class="pe-7s-users pe-5x pe-va wow fadeInUp" data-wow-delay="0.2s"></i>
                    <div class="inner">
                        <h4>Announcements</h4>
                        <p>A way to know all announcements and notices made by teachers for your year or
                            study group
                            .</p>

                    </div>
                </div>

                <!-- /.feature 3 -->
                <div class="col-sm-6 feat-list">
                    <i class="pe-7s-upload pe-5x pe-va wow fadeInUp" data-wow-delay="0.4s"></i>
                    <div class="inner">
                        <h4>Upload</h4>
                        <p>A single platform for uploading files,tasks,projects,feedback,any shortName
                            files and even
                            asking questions and giving answers and ratings .</p>
                    </div>
                </div>

                <!-- /.feature 4 -->
                <div class="col-sm-6 feat-list">
                    <i class="pe-7s-study pe-5x pe-va wow fadeInUp" data-wow-delay="0.6s"></i>
                    <div class="inner">
                        <h4>License</h4>
                        <p>Consult and choose from any licence domanins available and get a list of teachers
                            to guide
                            you. Ask for a
                            1-on-1 discussion with the teacher and complete and begin and complete your
                            paper through
                            this site .</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- /.feature 2 section -->
<div id="feature-2">
    <div class="container">
        <div class="row">

            <!-- /.feature content -->
            <div class="col-md-6 wow fadeInLeft">
                <h2>Better centralisation</h2>
                <p>Now you don't have to surf through the entire web to find materials , because every link
                    , schedule,
                    pdf materials , assignements and
                    files needed by any teacher or student will be centralised and easily accessed from
                    uniHub .
                </p>
            </div>

            <!-- /.feature image -->
            <div class="col-md-6 feature-2-pic wow fadeInRight">
                <img src="../../static/images/feature2-image.jpg" alt="macbook" class="img-responsive">
            </div>
        </div>

    </div>
</div>

<footer id="footer">
    <div class="container">
        <div class="col-sm-4 col-sm-offset-4">
            <!-- /.social links -->
            <div class="social text-center">

            </div>
            <div class="text-center wow fadeInUp" style="font-size: 14px;">Copyright - <a href="" target="_blank">UniHub
                team </a></div>

        </div>
    </div>
</footer>
<a href="#" class="scrollToTop"><i class="pe-7s-up-arrow pe-va"></i></a>

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