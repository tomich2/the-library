<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Loan Items">
    <jsp:attribute name="body">

      <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Author</th>
                <th>Title</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td><c:out value="${book.id}"/></td>
                    <td><c:out value="${book.name}"/></td>
                    <td><c:out value="${book.title}"/></td>
                    <td>
                        <my:a href="/books/details/${book.id}" class="btn btn-primary">Show details</my:a>
                    </td>
                    <td>
                        <c:if test="${authenticatedUser.isAdmin()}">
                            <my:a href="/books/update/${book.id}" class="btn btn-primary">Update</my:a>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${authenticatedUser.isAdmin()}">
                            <form method="post" action="${pageContext.request.contextPath}/books/delete/${book.id}">
                                <button type="submit" class="btn btn-primary">Delete</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


</jsp:attribute>
</my:pagetemplate>
