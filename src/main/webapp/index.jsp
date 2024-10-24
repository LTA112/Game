<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flappy Shork</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/style_index.css">
        <script src="javaScript/Register.js" defer></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
         <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    </head>
    <style>
        .alert-danger {
            color: red;
        }
    </style>
    <body>
        <c:if test="${not empty sessionScope.accountId}">
            <c:redirect url="${pageContext.request.contextPath}/FlappyShork/Home"/>
        </c:if>


        <div class="background"></div>

        <script>
            function showForm(formId) {
                document.getElementById('loginForm').classList.add('hidden');
                document.getElementById('registerForm').classList.add('hidden');
                document.getElementById(formId).classList.remove('hidden');
            }

            window.onload = function () {
                // Kiểm tra nếu có thông báo lỗi và hiển thị form đăng nhập
                if (${not empty errorMessage}) {
                    showForm('loginForm');
                }
            };
        </script>

        <div class="container">
            <h1>Flappy Shork</h1>
            <p> by GROUP3 </p>

            <div class="btn-row">
                <div class="btn-wrapper">
                    <a href="/FlappyShork/guestGame" class="btn custom-btn btn-play">Play the game</a>
                </div>
                <div class="btn-wrapper">
                    <a href="/FlappyShork/Rank" class="btn custom-btn btn-rank">
                        <img src="${pageContext.request.contextPath}/images/trophy.png" alt="Rank">
                    </a>
                </div>
            </div>

            <div class="btn-group">
                <button class="btn custom-btn btn-login" onclick="showForm('loginForm')">Login</button>
                <button class="btn custom-btn btn-register" onclick="showForm('registerForm')">Register</button>
            </div>

            <div id="loginForm" class="hidden mt-3 form-container">
                <h2>Login</h2>
                <form action="/LoginController" method="post" >
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger mt-3">${errorMessage}</div>
                    </c:if>
                    <div class="custom-spacing">
                        <input type="text" class="form-control" id="loginUserName" name="username" placeholder="Username" required>
                    </div>
                    <div class="custom-spacing">
                        <input type="password" class="form-control" id="loginPassword" name="password" placeholder="Password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>

            </div>

            <div id="registerForm" class="hidden mt-3 form-container">
                <h2>Register</h2>
                <form method="post" action="/RegisterController" onsubmit="return validateForm()">
                    <div class="custom-spacing">
                        <input type="text" class="form-control" id="registerUserName"
                               placeholder="User name" name="txtUserName" onkeyup="checkUsernameKeyup()" required>
                        <br/>
                        <small id="usernameTaken" style="color: red; display: none;">The Username has taken.</small>
                        <small id="usernameError" style="color: red; display: none;">Invalid username. Must be at least 6 characters. No spaces. No special characters.</small>
                    </div>
                    <div class="custom-spacing">
                        <input type="password" class="form-control" id="registerPassword" onkeyup="checkPassWordKeyup()"
                               placeholder="Password" name="txtPass1" required>
                        <small id="passwordError" style="color: red; display: none;">Invalid Password. Must be at least 3 characters. No spaces. No special characters.</small>
                    </div>
                    <div class="custom-spacing">
                        <input type="password" class="form-control" id="registerConfirmPassword"
                               placeholder="Confirm password" name="txtPass2" onkeyup="validatePasswords()" required>
                        <br/>
                        <small id="passwordNotMatch" style="color: red; display: none;">Passwords do not match.</small>
                    </div>

                    <div class="custom-spacing">
                        <input type="text" class="form-control" id="registerNickname" onkeyup="checkNicknameKeyup()"
                               placeholder="Nick name" name="txtNickName" required>
                        <br/>
                        <small id="nicknameTaken" style="color: red; display: none;">The Nick name has taken !</small>
                        <small id="nicknameError" style="color: red; display: none;">Invalid Nickname. Must be at least 6 characters. No spaces. No special characters.</small>
                    </div>

                    <button type="submit" class="btn btn-primary" name="btnReg">Register</button>
                </form>
            </div>

            <div class="mt-5 text-center">
                <h2>Advertisement</h2>
                <div class="text-center">
                    <a href="https://cantho.fpt.edu.vn/" target="_blank">
                        <img src="https://cantho.fpt.edu.vn/Data/Sites/1/Banner/hb18.jpg" alt="FPT Cần Thơ" style="width:500px;height:120px;">
                    </a>
                </div>
            </div>           

            <%--<div class="mt-5 text-center">
                   <h2>Quảng cáo</h2>
                   <div class="text-center">
                       <a href="https://188.166.232.132/" target="_blank">
                           <img src="https://188.166.232.132/wp-content/uploads/2024/06/giao-dien-mobile-euro-2024-moi.webp" alt="FPT Cần Thơ" style="width:500px;height:200px;">
                       </a>
                   </div>
               </div>
            --%>
        </div>
    </body>
</html>
