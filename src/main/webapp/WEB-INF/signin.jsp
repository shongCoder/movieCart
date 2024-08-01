<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 2024-08-01
  Time: 오후 3:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<style>
    .card {
        width: 80vw;
        margin: 0 auto;
        padding: 2em;
        background: #ECEFF3;
    }
    h2 {
        text-align: center;
        padding-bottom: 2em;
    }
    .signin {
        padding-top: 5vh;
        display: flex;
        width: 100%;
    }
    button{
        margin-left: auto;
        width: 100%;
    }
</style>
<div class="card">
    <h2>LOGIN</h2>
    <div class="car-body">
        <form action="/signin" method="post">
            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="text" name="uid" class="form-control" placeholder="Please write title">
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="upw" class="form-control" placeholder="Please write writer">
            </div>
            <div class="signin">
                <button type="submit" class="btn btn-primary">SignIn</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
