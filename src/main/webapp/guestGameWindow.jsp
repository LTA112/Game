<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <title>Flappy Bird Game</title>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.ico"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_game.css">
         <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
        <script>
            // Define a global variable to store the base path for images
            var basePath = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/javaScript/guestGame.js" defer></script>
    </head>
    <body>
         <div class="Back">
        <button class="back-button" onclick="window.location.href='/FlappyShork'">Back</button>
    </div>

        <div class="foreground"></div>
        <div class="background">
           
        </div>
        <img src="${pageContext.request.contextPath}/images/Bird.png" alt="bird-img" class="bird" id="bird-1">
        <div class="message messageStyle">
            Space To Start Game <p><span style="color: red;">&uarr;</span> ArrowUp to Control</p>
        </div>
        <div class="score">
            <span class="score_title"></span>
            <span class="score_val">0</span>
        </div>
    </body>
</html>
