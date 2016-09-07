<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ЛЛО</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/recept/styles.css" rel="stylesheet" type="text/css">
    <style>
        .main-wrap {
            padding: 40px;
            padding: 4rem;
            font-size: 0;
            text-align: center;
            height: 100%;
        }

        .main-wrap:before {
            content: "";
            display: inline-block;
            height: 100%;
            width: 1px;
            vertical-align: middle;
            margin-right: -1px;
        }

        .main-wrap > * {
            display: inline-block;
            vertical-align: middle;
            font-size: 16px;
            font-size: 1.6rem;
        }

        .main-wrap > * {
            text-align: left;
        }
    </style>
</head>
<body>
<div class="main-wrap">
    <div class="login-form">
        <div class="site-logo site-logo_large"><a href="/" title="Главная" class="site-logo__image"
                                                  href="${req.contextPath}"></a>
            <h1 class="site-logo__title">Система ЛЛО</h1>
        </div>
        <form class="login-form__body" action="/recept/j_security_check" method=post>
            <div class="form-group form-group-lg">
                <label for="email">Имя пользователя</label>
                <input type="text" id="login" class="form-control" name="j_username">
            </div>
            <div class="form-group form-group-lg">
                <label for="pwd">Пароль</label>
                <input type="password" id="pwd" class="form-control" name="j_password">
            </div>
            <button type="submit" class="btn btn-primary btn-lg btn-block">Войти в систему</button>
        </form>
    </div>
</div>
<%
    Object obj = request.getAttribute("javax.servlet.forward.request_uri");
    String address = String.valueOf(request.getAttribute("javax.servlet.forward.request_uri"));
    if (obj != null && !address.contains("recept/login")) {
        response.setHeader("Location", "/recept/login");
        response.sendError(401, "/recept/login");
    }
%>
</body>
</html>