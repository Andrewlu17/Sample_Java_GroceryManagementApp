
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.grocerymanagement.dto.UserDTO" %>
<h2>Header</h2>    

<h3>welcome <c:out value="${sessionScope.loggedUser.userName}"/> </h3>
<hr/>    