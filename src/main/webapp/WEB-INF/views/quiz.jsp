<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">

<div class="container">
<form:form method="post" action="${pageContext.request.contextPath}/quiz" modelAttribute="questionListWrapper">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <c:forEach items="${questionListWrapper.questions}" var="question" varStatus="i">
            <c:if test="${i.index == '0' || i.index mod perPage == '0'}">
            <c:if
                    test="${i.index != '0' && i.index mod perPage == '0'}"></div>
        </c:if>
        <div class="item <c:if
                            test="${i.index == '0'}">active</c:if>"> Page ${i.index / perPage}
            </c:if>
            <form:input type="hidden" path="questions[${i.index}].id" value="${question.id}"/>
            <form:input type="hidden" path="questions[${i.index}].question" value="${question.question}"/>
            <form:input type="hidden" path="questions[${i.index}].correctAnswer.id"
                        value="${question.correctAnswer.id}"/>
            <p class="question">${question.question}</p>
            <ul class="answers">
                <c:forEach items="${question.answersList}" var="answer">
                    <form:radiobutton path="questions[${i.index}].submittedAnswer.id"
                                      value="${answer.id}"/>${answer.answer}
                </c:forEach>
            </ul>

            </c:forEach>
        </div>
    </div>
    </div>
    <button href="#myCarousel" data-slide="prev">
        Previous page
    </button>
    <button href="#myCarousel" data-slide="next">
        Next page
    </button>
    <input type="submit" value="Submit answers">
</form:form>
</div>
