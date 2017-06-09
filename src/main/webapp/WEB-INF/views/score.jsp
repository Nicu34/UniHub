<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div>
   <p> Correct questions: ${score.correctAnswers}</p>
</div>
<div>
    <p> Wrong questions: ${score.wrongAnswers}</p>
</div>
<div>
    <p> Best all time score: ${bestScore.correctAnswers} of ${bestScore.answersNumber} </p>
</div>
