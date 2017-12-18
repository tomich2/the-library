<%-- 
    Document   : login
    Created on : Dec 18, 2017, 5:45:32 PM
    Author     : tchomo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<my:pagetemplate title="User Login">
    
    <jsp:attribute name="body">
        <h1>Log in</h1>
        <c:if test="${param.error != null}">
        <div>Invalid credentials.</div>
    </c:if>
        <form method="POST">
            <div class="form-group">
                <label for="username">E-mail:</label>
                <input name="username" type="text" class="form-control" />

                <label for="password">Password:</label>
                <input name="password" type="password" class="form-control" />
            </div>
            <button type="submit" class="btn btn-default">Log in</button>
        </form>
    </jsp:attribute>
</my:pagetemplate>
