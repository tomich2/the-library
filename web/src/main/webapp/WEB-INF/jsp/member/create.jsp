<%-- 
    Document   : create
    Created on : Dec 18, 2017, 7:10:34 PM
    Author     : tchomo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--  <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> --%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>

<x:pagetemplate title="Create member">
    <jsp:attribute name="head">
        <script>
            function checkPassword() {
                if ($("#password").val() !== $("#passwordConfirmation").val()) {
                    $("#passwordError").css("display", "inline");
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </jsp:attribute>
    <jsp:attribute name="body">
        <form:form method="post" action="${pageContext.request.contextPath}/member/create"  modelAttribute="member">
           <div class="form-group">
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="firstName">First name</form:label>
                    <form:input path="firstName" type="text" cssClass="form-control" required="required"/>
                    <form:errors path="firstName" cssClass="help-block" />
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="surname">Surname</form:label>
                    <form:input path="surname" type="text" cssClass="form-control" required="required" />
                    <form:errors path="surname" cssClass="help-block"/>
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="email">E-mail</form:label>
                    <form:input path="email" type="text" cssClass="form-control" required="required" />
                    <form:errors path="email" cssClass="help-block" />
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="address">Address</form:label>
                    <form:input path="address" type="text" cssClass="form-control" required="required" />
                    <form:errors path="address" cssClass="help-block"/>
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="password">Password</form:label>
                    <form:input path="password" id="password" type="password" cssClass="form-control" required="required"/>
                    <form:errors path="password" cssClass="help-block"/>
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <label for="passwordConfirmation">Password confirmation</label>
                    <input id="passwordConfirmation" type="password" class="form-control" required="required"/>
                    <span id="passwordError" style="display: none">
                        Passwords do not match
                    </span>
                </div>
                <%-- <sec:authorize access="hasRole('ADMIN')">
                    <div class="checkbox">
                        <form:label path="admin">
                            <form:checkbox path="admin" />
                        </form:label>
                        <form:errors path="admin" />
                    </div>
                </sec:authorize>--%>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
          
        </form:form>
    </jsp:attribute>
</x:pagetemplate>