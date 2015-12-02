<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:casualpage>
	<jsp:attribute name="header">Contact us</jsp:attribute>
	<jsp:body>
<h3>Submit your message</h3>
		<br />
<form class="form-horizontal" role="form" method="post"
			action="complain">
  <div class="form-group">
        <label for="message" class="col-sm-2 control-label">Message</label>
    </div>
    <div class="form-group">
    <div class="col-sm-10">
            <textarea class="form-control" rows="4" name="message"></textarea>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-10 col-sm-offset-2">
            <input id="submit" name="submit" type="submit" value="Send"
						class="btn btn-primary">
        </div>
    </div>
</form>
</jsp:body>
</t:casualpage>