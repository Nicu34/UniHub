<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url var="fooUrl" value="/FOO"/>
<form:form id="frmFoo" action="${fooUrl}" method="POST" modelAttribute="fooListWrapper">


    <c:forEach items="${fooListWrapper.fooList}" varStatus="i">
        <form:input path="fooList[${i.index}].name" type="text"/>
    </c:forEach>


    <button>submit</button>
</form:form>
