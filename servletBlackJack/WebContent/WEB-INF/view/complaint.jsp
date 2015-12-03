<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:casualpage>
	<jsp:attribute name="header">Contact us</jsp:attribute>
	<jsp:body>
	<br />
	<c:if test="${not empty errormessage }">
	<div class="alert alert-warning" role="alert">${errormessage }</div>
	</c:if>
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
<label for="message" class="control-label text-center">Submit your message</label>
		<br />
<form class="form-horizontal" role="form" method="post"
				action="complain">
    <div class="form-group">


            <textarea class="form-control" rows="4" name="message"></textarea>
        </div>
        <div class="form-group">
        <div class="col-sm-12 text-center">
            <input id="submit" name="submit" type="submit" value="Send"
							class="btn btn-primary">
        </div>
		</div>
    <div class="col-sm-2"></div>

    
		
		</div>
</form>
</jsp:body>
</t:casualpage>