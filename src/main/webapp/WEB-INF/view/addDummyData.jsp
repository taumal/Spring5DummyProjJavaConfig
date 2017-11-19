<%--
  Created by IntelliJ IDEA.
  User: taumal
  Date: 11/16/17
  Time: 6:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <title>Dummy Data</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form:form action="addDummyData" method="post" modelAttribute="dummy">
                <form:hidden path="id"/>
                <div class="form-group">
                    <label>First Name</label>
                    <form:input type="text" class="form-control" path="firstName" placeholder="First Name" />
                </div>
                <div class="form-group">
                    <label>Last Name</label>
                    <form:input type="text" class="form-control" path="lastName" placeholder="Last Name" />
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <form:input type="text" class="form-control" path="email" placeholder="Write your email" />
                </div>
                <div class="form-group">
                    <label>Username</label>
                    <form:input type="text" class="form-control" path="userName" placeholder="Username" />
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <form:input type="password" class="form-control" path="password" placeholder="Password" />
                </div>
                <div class="form-group">
                    <label>Country</label>
                    <form:select class="form-control" path="country">
                        <form:option value="Afganistan">Afganistan</form:option>
                        <form:option value="Bangladesh">Bangladesh</form:option>
                        <form:option value="India">India</form:option>
                        <form:option value="UK">United Kingdom</form:option>
                        <form:option value="USA">United States of America</form:option>
                    </form:select>
                </div>
                <div class="form-group text-right">
                    <button value="save" type="submit" name="save" id="send" class="btn btn-primary">Save</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
</body>
</html>
