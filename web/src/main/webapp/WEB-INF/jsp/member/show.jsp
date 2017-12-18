<%-- 
    Document   : show
    Created on : Dec 18, 2017, 5:40:49 PM
    Author     : tchomo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="name">
    <c:out value="${member.givenName}" />
    <c:out value="${member.surname}" />
</c:set>

<my:pagetemplate title="${member.name} Member details">

</my:pagetemplate>
