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

    <!-- Chat ref -->
    <link rel="stylesheet" type="text/css" media="screen" href="https://cdn.conversejs.org/css/converse.min.css">
    <script src="https://cdn.conversejs.org/dist/converse.min.js"></script>
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
                        Details
                    </h1>
                    <div class="landing-text wow fadeInLeft">
                        <p>Account:<label id="accountAdmin">${userDetails.ssoId}</label></p>
                        <p>First Name: <label id="firstNameAdmin">${userDetails.firstName}</label></p>
                        <p>Last Name: <label id="lastNameAdmin">${userDetails.lastName}</label></p>
                        <p>University :<label id="universityAdmin">${userDetails.university.longName}</label></p>
                        <p>Email:<label id="emailAdmin">${userDetails.email}</label></p>
                        <p>Phone:<label id="phoneAdmin">${userDetails.phone}</label></p>
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
                    <img src="${userDetails.photoLink}" alt="Unavailable" style="margin-top: 150px;"
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
                    <li><a href="#feature0">Admin Accounts</a></li>
                    <li><a href="#feature1">Teacher Accounts</a></li>
                    <li><a href="#feature2">Student Accounts</a></li>
                    <li><a href="#feature3">Manage Groups</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<!--/.feature0 section-->
<div id="feature0" style="padding-top:60px;padding-bottom: 60px; width: 40%;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center" style="margin-left: 0;">

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp" style="width: 100%;">
                    <h3 class="form-title text-center" style="margin-bottom: 7%;">Invite someone to create admin
                        account</h3>
                    <form:form method="POST" class="form-header" action="inviteAdmins" role="form"
                               modelAttribute="emailDto">
                        <div class="form-group">
                            <form:input class="form-control input-lg" name="MERGE1" id="adminEmails" type="text"
                                        placeholder="Email addresses to be invited" path="adminEmails"/>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn-primary" id="buttonInviteAdmins"
                                   value="Invite admins">
                        </div>
                    </form:form>
                </div>
            </div>

            <div class="col-md-6 ">
                <!-- /.feature 1 -->
                <div class="fadeInRight" style="width:30%; margin-right: 5%;">
                    <h3 class="form-title text-center">Current Admin Accounts</h3>
                    <i class="pe-7s-notebook pe-5x pe-va wow fadeInUp"></i>
                    <div class="inner" style="width:100%;">
                        <table style="margin-top:0px">
                            <thead>
                            <tr>
                                <th>Account</th>
                                <th>First name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Manage Accounts</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${adminUsers}" var="adminUser">
                                <tr>
                                    <td><strong>${adminUser.ssoId}</strong></td>
                                    <td>${adminUser.firstName}</td>
                                    <td>${adminUser.lastName}</td>
                                    <td>${adminUser.email}</td>
                                    <td>${adminUser.phone}</td>
                                    <td>
                                        <a href="<c:url value='/view-admin-${adminUser.id}' />"
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

<!--/.feature0 section-->
<div id="feature1" style="padding-top:60px;padding-bottom: 60px;width: 40%;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center" style="margin-left: 0;">

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp">
                    <h3 class="form-title text-center" style="margin-bottom: 7%;">Invite someone to create teacher
                        account</h3>
                    <form:form method="POST" class="form-header" action="inviteTeachers" role="form"
                               modelAttribute="emailDto">
                        <div class="form-group">
                            <form:input class="form-control input-lg" name="MERGE1" id="teacherEmails" type="text"
                                        placeholder="Email addresses to be invited" path="teacherEmails"/>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn-primary" id="buttonInviteTeachers"
                                   value="Invite teachers">
                        </div>
                    </form:form>
                </div>
            </div>

            <div class="col-md-6 ">
                <!-- /.feature 1 -->
                <div class="fadeInRight" style="width:30%; margin-right: 5%;">
                    <h3 class="form-title text-center">Current Teacher Accounts</h3>
                    <i class="pe-7s-notebook pe-5x pe-va wow fadeInUp"></i>
                    <div class="inner" style="width:100%;">
                        <table style="margin-top:0px">
                            <thead>
                            <tr>
                                <th>Account</th>
                                <th>First name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>View details</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${teacherUsers}" var="teacherUser">
                                <tr>
                                    <td><strong>${teacherUser.ssoId}</strong></td>
                                    <td>${teacherUser.firstName}</td>
                                    <td>${teacherUser.lastName}</td>
                                    <td>${teacherUser.email}</td>
                                    <td>${teacherUser.phone}</td>
                                    <td>
                                        <a href="<c:url value='/view-teacher-${teacherUser.id}' />"
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

<!--/.feature0 section-->
<div id="feature2" style="padding-top:60px;padding-bottom: 60px; width: 40%;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center" style="margin-left: 0;">

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp">
                    <h3 class="form-title text-center" style="margin-bottom: 7%;">Invite people to create student
                        account</h3>
                    <form:form method="POST" class="form-header" action="inviteStudents" role="form"
                               modelAttribute="emailDto">
                        <div class="form-group">
                            <form:input class="form-control input-lg" name="MERGE1" id="studentEmails" type="text"
                                        placeholder="Email addresses to be invited" path="studentEmails"/>
                        </div>
                        <div class="form-group">
                            <form:select path="groupNumber" class="form-control" id="groupAdminCreate"
                                         placeholder="Group number" value="Group number">
                                <option disabled selected>Group number</option>
                                <c:forEach items="${groupsList}" var="group">
                                    <form:option value="${group.groupNumber}">${group.groupNumber}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn-primary" id="buttonInviteStudent"
                                   value="Invite students">
                        </div>
                    </form:form>
                </div>
            </div>

            <div class="col-md-6 ">
                <!-- /.feature 1 -->
                <div class="fadeInRight" style="width:30%; margin-right: 5%;">
                    <h3 class="form-title text-center">Current Student Accounts</h3>
                    <i class="pe-7s-notebook pe-5x pe-va wow fadeInUp"></i>
                    <div class="inner" style="width:100%;">
                        <table style="margin-top:0px">
                            <thead>
                            <tr>
                                <th>Account</th>
                                <th>First name</th>
                                <th>Last Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>View details</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${studentUsers}" var="studentUser">
                                <tr>
                                    <td><strong>${studentUser.ssoId}</strong></td>
                                    <td>${studentUser.firstName}</td>
                                    <td>${studentUser.lastName}</td>
                                    <td>${studentUser.email}</td>
                                    <td>${studentUser.phone}</td>
                                    <td>
                                        <a href="<c:url value='/view-student-${studentUser.id}' />"
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


<!-- /.subscribe section -->
<div id="feature3" style="height: 30%;">
    <div class="subscribe fullscreen parallax" style="background-image:url('../../static/images/bg.jpg');"
         data-img-width="1920" data-img-height="1281" data-diff="100">
        <div class="overlay">
            <div class="container" style="padding-bottom: 60px;width: 100%;">
                <div class="row row-feat">
                    <div class="col-md-6 text-center" style="margin-left: 0;">
                        <!-- /.feature image -->
                        <div class="signup-header wow fadeInUp">
                            <h3 class="form-title text-center" style="margin-bottom: 7%;">Add new group class</h3>
                            <form:form class="form-header" action="addGroup" modelAttribute="schoolGroupDto"
                                       method="POST">
                                <div class="form-group">
                                    <form:input class="form-control input-lg" id="addGroupID" type="text"
                                                placeholder="Group number" path="groupName"/>
                                </div>
                                <div class="form-group">
                                    <form:select path="studyYear" class="form-control" id="groupAdminCreate"
                                                 placeholder="Study Year" value="Study Year">
                                        <option disabled selected>Study Year</option>
                                        <c:forEach items="${studyYears}" var="studyYearVar">
                                            <form:option value="${studyYearVar.year}">${studyYearVar.year}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="form-group last">
                                    <input type="submit" class="btn-primary"
                                           id="buttonCreateCourse" value="Add group">
                                </div>
                            </form:form>
                        </div>
                    </div>

                    <div class="col-md-6 ">
                        <!-- /.feature 1 -->
                        <div class="fadeInRight" style="width:30%;">
                            <h3 class="form-title text-center" style="margin-bottom: 5%;">Current Groups</h3>
                            <i class="pe-7s-notebook pe-5x pe-va wow fadeInUp"></i>
                            <div class="inner" style="width:90%; margin-top: 5%;">
                                <br>
                                <table style="margin-top:5%;border-radius: 10px;">
                                    <thead>
                                    <tr>
                                        <th>Group Number</th>
                                        <th>Study Year</th>
                                        <th>View details</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${groupsList}" var="group">
                                        <tr style="color: #777">
                                            <td><strong>${group.groupNumber}</strong></td>
                                            <td><strong>${group.studyYear.year}</strong></td>
                                            <td>
                                                <a href="<c:url value='/view-group-${group.id}' />"
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
<!-- Chat initializer -->
<script>
    converse.initialize({
        bosh_service_url: 'https://conversejs.org/http-bind/',
        show_controlbox_by_default: true,
        allow_registration: false,
        locked_domain: '007jabber.com',
        hide_muc_server: true
    });
</script>
</html>