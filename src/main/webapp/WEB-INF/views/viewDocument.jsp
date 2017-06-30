<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<div class="container-fluid">
    <div class="row">

        <div class="col-md-12">
            <h1>
                Id: ${document.id}
            </h1>
            <h1>
                Title: ${document.title}
            </h1>
            <div class="table-responsive">
                <table id="mytable" class="table table-bordered" style="margin:10px;width:60%;">
                    <thead>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Text Content</th>
                    <th>IsPrivate</th>
                    </thead>
                    <tbody>
                    <c:forEach var="template" items="${document.templatesList}">
                        <tr>
                            <td>${template.id}</td>
                            <td>${template.name}</td>
                            <td>${template.textContent}</td>
                            <td>${template.isPrivate}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
