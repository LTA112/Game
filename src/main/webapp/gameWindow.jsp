<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flappy Bird Game</title>
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/favicon.ico"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_game.css">
         <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
        <script>
            // Định nghĩa biến basePath từ contextPath
            var basePath = '${pageContext.request.contextPath}/images';
        </script>
        <script src="${pageContext.request.contextPath}/javaScript/game.js" defer></script>

    </head>
    <body>
        <div class="Back">
            <button class="back-button" onclick="window.location.href = '/FlappyShork/Home'">Back</button>
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
