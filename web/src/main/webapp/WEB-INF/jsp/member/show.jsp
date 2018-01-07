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
    <c:out value="${member.firstName}" />
    <c:out value="${member.surname}" />
</c:set>

<x:pagetemplate>
<jsp:attribute name="title">
         ${name}
    </jsp:attribute>
    <jsp:attribute name="body">
   <br>
    <table class="table" style="width:60%">
        <tbody>
            <tr>
                <td>Id</td>
                <td><c:out value="${member.id}"/></td>
            </tr>
            <tr>
                <td>First name</td>
                <td><c:out value="${member.firstName}"/></td>
            </tr>
            <tr>
                <td>Surname</td>
                <td><c:out value="${member.surname}"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><c:out value="${member.email}"/></td>
            </tr>
            
            <tr>
                <td>Phone number</td>
                <td><c:out value="${member.phone}"/></td>
            </tr>
            
            <tr>
                <td>Address</td>
                <td><c:out value="${member.address}"/></td>
            </tr>
            
             <tr>
                <td>Date of registration</td>
                <td><c:out value="${member.joinedDate}"/></td>
            </tr>
             
        </tbody>
        
    </table>
            
            <form method="post" action="${pageContext.request.contextPath}/member/delete/${member.id}">
                <button type="submit" class="btn btn-default">Delete</button>
            </form>
<br/>
    </jsp:attribute>
</x:pagetemplate>
