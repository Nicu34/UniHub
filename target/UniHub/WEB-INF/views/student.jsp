<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

    <!-- /.website title -->
    <title>Student</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

    <!-- CSS Files -->
    <!--<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">-->
    <link href="../../static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="../../static/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../static/fonts/icon-7-stroke/css/pe-icon-7-stroke.css" rel="stylesheet">
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
        function hide(elementId) {
            document.getElementById(elementId).style.display = "none";
        }
        function show(elementId) {
            hide(elementId);
            document.getElementById(elementId).style.display = "block";
        }

    </script>
    <script>
        var currentCourse;

        function setCurrent(current) {
            currentCourse = current;
        }
        function getCurrent() {
            return currentCourse;
        }
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
                    <div class="logo wow fadeInDown" style="margin-top: 8%;"><a href=""><img
                            src="../../static/images/white-logo.png" alt="logo" style="height: 120px;width: 250px;"></a>
                    </div>
                    <h1 class="wow fadeInLeft" style="margin-top: 60px;">
                        Details
                    </h1>
                    <div class="landing-text wow fadeInLeft">
                        <p>First Name: ${userDetails.firstName}</p>
                        <p>Last Name: ${userDetails.lastName}</p>
                        <p>University: ${userDetails.university.longName}</p>
                        <p>Year of study: ${student.schoolGroup.studyYear.year}</p>
                        <p>Group: ${student.schoolGroup.groupNumber}</p>
                    </div>
                </div>
                <a href="<c:url value="/logout"/>" class="logg">
                    <img class="photo" src="../../static/images/logout-512 (1).png"
                         style="float:right; width: 2%; height: 2%; margin-top: 2%; margin-right: 2%;">
                </a>
                <p style="float:right; margin-right: 2%;margin-top: 2%;">Log out</p>
                <a href="/" class="logg">
                    <img class="photo" src="../../static/images/home-5-xxl.png"
                         style="float:right; width: 2%; height: 2%; margin-top: 6%; margin-right: -8%;">
                </a>
                <p style="float:right;margin-top: 6%;margin-right: -4%;">Home</p>
                <!--/.phone image-->
                <div class="col-md-5">
                    <img src="${userDetails.photoLink}" alt="phone" style="margin-top: 150px;"
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
                <a class="navbar-brand site-name" href="#top"><img src="../../static/images/logo5.png" alt="logo"
                                                                   style="height: 50px;"></a>
            </div>

            <div id="navbar-scroll" class="collapse navbar-collapse navbar-backyard navbar-right">
                <ul class="nav navbar-nav" style="text-align: center;">
                    <li><a href="#feature0">Academic situation</a></li>
                    <li><a href="#feature1">Schedule</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<div id="feature0" style="padding-top:60px;padding-bottom: 60px;">
    <div class="container">
        <div class="row">
            <div class="row row-feat" style="padding-top:0px">
                <div class="col-md-4 text-center ">
                    <div class="feature-img">
                        <img src="../../static/images/pexels-photo-256467.jpeg" alt="image"
                             style="margin-top: 22px; margin-bottom:12px; height: 88%;width: 100%;"
                             class="img-responsive wow fadeInLeft">
                    </div>
                </div>
                <!-- /.feature 1 -->
                <
                <div class="col-md-6 ">
                    <!-- /.feature 1 -->
                    <div class="fadeInRight" style="width:100%;">
                        <h3 class="form-title text-center">Academic situation</h3>
                        <div class="inner" style="width:100%;">
                            <table style="margin-top:0px">
                                <thead>
                                <tr>
                                    <th>Course</th>
                                    <th>Teacher</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${courses}" var="course">
                                    <tr>
                                        <td><p><a href="<c:url value='/view-course-${course.id}'/>">${course.name}</a></p></td>
                                        <td><p><a href="<c:url value='/view-teacher-${course.teacher.id}'/>">${course.teacher.user.firstName} ${course.teacher.user.lastName}</a></p></td>
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
</div>
<!-- /.feature 2 section -->
<div id="feature1">
    <div class="container">
        <div class="row">

            <!-- /.feature content -->
            <div class="col-md-6 wow fadeInLeft">
                <h2>Schedule</h2>
                <p>See the academic schedule of the current year for students, teachers and also the rooms
                    schedule. </p>
                <div class="btn-section"><a href="${userDetails.university.scheduleLink}" style="margin-left: 120px;"
                                            class="btn-default">University Schedule</a></div>
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