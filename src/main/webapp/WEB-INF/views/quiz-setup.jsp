<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form:form modelAttribute="quizDto" action="quiz-setup" method="post">
    <form:input path="questionsNumber" type="text" placeholder="Number of questions"></form:input>
    <br>
    <form:input path="questionsPerPage" type="text" placeholder="Questions per page"></form:input>
    <input type="submit" value="Go to quiz">
</form:form>
