<%--
    Author     : xtlamich
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<x:pagetemplate title="Create loan">
    <jsp:attribute name="body">
        <form:form method="POST" modelAttribute="loan">
           <div class="form-group">
                <div class="form-group">
                    <form:label path="firstName">First name</form:label>
                    <form:input path="firstName" type="text" cssClass="form-control" />
                    <form:errors path="firstName" />
                </div>
                <div class="form-group">
                    <form:label path="surname">Surname</form:label>
                    <form:input path="surname" type="text" cssClass="form-control" />
                    <form:errors path="surname" />
                </div>
                <div class="form-group">
                    <form:label path="email">E-mail</form:label>
                    <form:input path="email" type="text" cssClass="form-control" />
                    <form:errors path="email" />
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input path="password" id="password" type="password" cssClass="form-control" />
                    <form:errors path="password" />
                </div>
                <div class="form-group">
                    <label for="passwordConfirmation">Password confirmation</label>
                    <input id="passwordConfirmation" type="password" class="form-control" />
                    <span id="passwordError" style="display: none">
                        Passwords do not match
                    </span>
                </div>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
          
        </form:form>
    </jsp:attribute>
</x:pagetemplate>