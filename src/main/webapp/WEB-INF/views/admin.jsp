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
                        Details
                    </h1>
                    <div class="landing-text wow fadeInLeft">
                        <p>Account :<label id="accountAdmin">${userDetails.ssoId}</label></p>
                        <p>First Name : <label id="firstNameAdmin">${userDetails.firstName}</label></p>
                        <p>Last Name : <label id="lastNameAdmin">${userDetails.lastName}</label></p>
                        <p>University :<label id="universityAdmin">${userDetails.university.longName}</label></p>
                        <p>Email :<label id="emailAdmin">${userDetails.email}</label></p>
                        <p>Phone :<label id="phoneAdmin">${userDetails.phone}</label></p>

                    </div>
                </div>

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
                    <li><a href="#feature0">Admin Accounts</a></li>
                    <li><a href="#feature1">Teacher Accounts</a></li>
                    <li><a href="#feature2">Student Accounts</a></li>
                    <li><a href="#feature3">Manage Courses</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>

<!--/.feature0 section-->
<div id="feature0" style="padding-top:60px;padding-bottom: 60px;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center">

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp">
                    <h3 class="form-title text-center">Invite people to create admin account</h3>
                    <form:form method="POST" class="form-header" action="inviteAdmins" role="form"
                               modelAttribute="emailDto">
                        <div class="form-group">
                            <form:input class="form-control input-lg" name="MERGE1" id="adminEmails" type="text"
                                        placeholder="Email addresses to be invited" path="adminEmails"/>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn btn-warning btn-block btn-lg" id="buttonInviteAdmins"
                                   value="Invite admins">
                        </div>
                    </form:form>
                </div>
            </div>

            <div class="col-md-6 ">
                <!-- /.feature 1 -->
                <div class="fadeInRight" style="width:100%;">
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
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <a href="<c:url value='/delete-user-${adminUser.ssoId}' />"
                                               class="btn btn-danger custom-width">Delete</a>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <a href="<c:url value='/view-user-${adminUser.ssoId}' />"
                                               class="btn btn-success custom-width">View</a>
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

<!--/.feature0 section-->
<div id="feature1" style="padding-top:60px;padding-bottom: 60px;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center">

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp">
                    <h3 class="form-title text-center">Invite people to create admin account</h3>
                    <form:form method="POST" class="form-header" action="inviteTeachers" role="form"
                               modelAttribute="emailDto">
                        <div class="form-group">
                            <form:input class="form-control input-lg" name="MERGE1" id="teacherEmails" type="text"
                                        placeholder="Email addresses to be invited" path="teacherEmails"/>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn btn-warning btn-block btn-lg" id="buttonInviteTeachers"
                                   value="Invite teachers">
                        </div>
                    </form:form>
                </div>
            </div>

            <div class="col-md-6 ">
                <!-- /.feature 1 -->
                <div class="fadeInRight" style="width:100%;">
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
                                <th>Manage Accounts</th>
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
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <a href="<c:url value='/delete-user-${teacherUser.ssoId}' />"
                                               class="btn btn-danger custom-width">Delete</a>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <a href="<c:url value='/view-user-${teacherUser.ssoId}' />"
                                               class="btn btn-success custom-width">View</a>
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

<!--/.feature0 section-->
<div id="feature2" style="padding-top:60px;padding-bottom: 60px;">
    <div class="container">
        <div class="row row-feat" style="padding-top:0px">
            <div class="col-md-6 text-center">

                <!-- /.feature image -->
                <div class="signup-header wow fadeInUp">
                    <h3 class="form-title text-center">Invite people to create student account</h3>
                    <form:form method="POST" class="form-header" action="inviteStudents" role="form"
                               modelAttribute="emailDto">
                        <div class="form-group">
                            <form:input class="form-control input-lg" name="MERGE1" id="studentEmails" type="text"
                                        placeholder="Email addresses to be invited" path="studentEmails"/>
                        </div>
                        <div class="form-group last">
                            <input type="submit" class="btn btn-warning btn-block btn-lg" id="buttonInviteAdmins"
                                   value="Invite students">
                        </div>
                    </form:form>
                </div>
            </div>

            <div class="col-md-6 ">
                <!-- /.feature 1 -->
                <div class="fadeInRight" style="width:100%;">
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
                            <c:forEach items="${studentUsers}" var="studentUser">
                                <tr>
                                    <td><strong>${studentUser.ssoId}</strong></td>
                                    <td>${studentUser.firstName}</td>
                                    <td>${studentUser.lastName}</td>
                                    <td>${studentUser.email}</td>
                                    <td>${studentUser.phone}</td>
                                    <td>
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <a href="<c:url value='/delete-user-${studentUser.ssoId}' />"
                                               class="btn btn-danger custom-width">Delete</a>
                                        </sec:authorize>
                                        <sec:authorize access="hasRole('ADMIN')">
                                            <a href="<c:url value='/view-user-${studentUser.ssoId}' />"
                                               class="btn btn-success custom-width">View</a>
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


<!-- /.subscribe section -->
<div id="feature3" style="height: 30%;">
    <div class="subscribe fullscreen parallax" style="background-image:url('../../static/images/bg.jpg');"
         data-img-width="1920" data-img-height="1281" data-diff="100">
        <div class="overlay">
            <div class="container" style="padding-bottom: 60px;">
                <div class="row row-feat">
                    <div class="col-md-6 text-center">
                        <!-- /.feature image -->
                        <div class="signup-header wow fadeInUp">
                            <h3 class="form-title text-center">Add new group class</h3>
                            <form:form class="form-header" action="addGroup" modelAttribute="schoolGroupDto" method="POST">
                                <div class="form-group">
                                    <form:input class="form-control input-lg" id="addGroupID" type="text"
                                           placeholder="Group number" path="groupName"/>
                                </div>
                                <div class="form-group">
                                    <form:select path="studyYear" class="form-control" id="groupAdminCreate" placeholder="Study Year" value="Study Year">
                                        <option disabled selected>Study Year</option>
                                        <c:forEach items="${studyYears}" var="studyYearVar">
                                            <form:option value="${studyYearVar.year}">${studyYearVar.year}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="form-group last">
                                    <input type="submit" class="btn btn-warning btn-block btn-lg"
                                           id="buttonCreateCourse" value="Add group">
                                </div>
                            </form:form>
                        </div>
                    </div>

                    <div class="col-md-6 ">
                        <!-- /.feature 1 -->
                        <div class="fadeInRight" style="width:100%;">
                            <h3 class="form-title text-center">Current Groups</h3>
                            <i class="pe-7s-notebook pe-5x pe-va wow fadeInUp"></i>
                            <div class="inner" style="width:100%;">
                                <table style="margin-top:0px;border-radius: 10px;">
                                    <thead>
                                    <tr>
                                        <th>Group Number</th>
                                        <th>Study Year</th>
                                        <th>Manage groups</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${groupsList}" var="group">
                                        <tr style="color: #777">
                                            <td><strong>${group.groupNumber}</strong></td>
                                            <td><strong>${group.studyYear.year}</strong></td>
                                            <td>
                                                <sec:authorize access="hasRole('ADMIN')">
                                                    <a href="<c:url value='/delete-group-${group.groupNumber}' />"
                                                       class="btn btn-danger custom-width">Delete</a>
                                                </sec:authorize>
                                                <sec:authorize access="hasRole('ADMIN')">
                                                    <a href="<c:url value='/view-group-${group.groupNumber}' />"
                                                       class="btn btn-success custom-width">View</a>
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