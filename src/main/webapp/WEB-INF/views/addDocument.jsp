<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container-fluid">

    <div class="row">
        <div class="col-md-6">
            <h2>Add Keyword</h2>
            <h3 class="form-title text-center">Add document</h3>
            <form:form method="POST" class="form-horizontal" action="addDocument" role="form"
                       modelAttribute="document">
                <div class="form-group">
                    <form:input class="form-control input-lg" type="text"
                                placeholder="Document title" path="title"/>
                </div>
                <div class="form-group last">
                    <input type="submit" class="btn-primary"
                           value="Add document">
                </div>
            </form:form>
        </div>
    </div>
</div>