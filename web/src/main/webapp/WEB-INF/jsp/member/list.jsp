<%-- 
    Document   : list
    Created on : Dec 18, 2017, 6:56:09 PM
    Author     : tchomo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<x:pagetemplate>
    <jsp:attribute name="title">
        Members
    </jsp:attribute>
    <jsp:attribute name="body">
        <div cssClass="form-control">

            <a href="create" class="btn btn-default">Create member</a>

        </div>
        <div class="panel panel-default">
           
            <table class="table filtered">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${members}" var="member">
                        <c:set var="name">
                            <c:out value="${member.firstName}" />
                            <c:out value="${member.surname}" />
                        </c:set>
                        <tr>
                            <td><a href="${member.id}"><c:out value="${name}" /></a></td>
                            <td><c:out value="${member.email}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>


        

    </jsp:attribute>
</x:pagetemplate>