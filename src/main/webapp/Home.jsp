<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home - Flappy Bird Game</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_Home.css">
        <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">

    </head>
    <body>
        <div class="logout">
            <form action="/LogoutController" method="post">
                <button class="custom-btn logout-button" type="submit">Logout</button>
            </form>
        </div>
        <div class="container">
            <h1>Welcome to Flappy Shork GRARW!!!</h1>
            <div class="options">
                <!-- Sử dụng thẻ a cho nút điều hướng -->
                <div class="btn-wrapper">
                    <a href="/FlappyShork/Home/Game" class="btn custom-btn btn-play">Play The Game</a>
                </div>
                <div class="btn-wrapper">
                    <a href="/FlappyShork/Home/Rank" class="btn custom-btn btn-rank">
                        <img src="/images/trophy.png" alt="Rank">
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
