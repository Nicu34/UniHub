<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container-fluid">

    <div class="row">
        <div class="col-md-6">
            <h2>Add Keyword</h2>
            <h3 class="form-title text-center">Add template</h3>
            <form:form method="POST" class="form-horizontal" action="addTemplate" role="form"
                       modelAttribute="template">
                <div class="form-group">
                    <form:input class="form-control input-lg" type="text"
                                placeholder="Template name" path="name"/>
                    <form:input class="form-control input-lg" type="text"
                                placeholder="Template text " path="textContent"/>
                    <form:input class="form-control input-lg" type="text"
                                placeholder="Is private" path="isPrivate"/>
                    <form:select path="documentId" class="form-control" id="groupAdminCreate"
                                 placeholder="Study Year" value="Study Year">
                        <option disabled selected>Document</option>
                        <c:forEach items="${documents}" var="document">
                            <form:option value="${document.id}">${document.title}</form:option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="form-group last">
                    <input type="submit" class="btn-primary"
                           value="Add template">
                </div>
            </form:form>
        </div>
    </div>
</div>