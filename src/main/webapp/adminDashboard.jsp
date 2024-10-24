<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="DBConnection.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Rank</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_Ranking.css">
    </head>
    <body>
        <div class="Back">
            <button class="back-button" onclick="window.location.href = '/FlappyShork/Home'">Back</button>
        </div>
        <sql:query var="result" dataSource="${DBC}">
            SELECT a.nickname, s.score, s.account_id as account_id
            FROM account a
            JOIN score s ON a.id = s.account_id
            ORDER BY s.score DESC;
        </sql:query>
        <div class="leaderboard">
            <h1>LEADERBOARD</h1>
            <div class="top3">
                <c:forEach var="user" items="${result.rows}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index == 0}">
                            <div class="first">
                                <span><c:out value="${status.index + 1}"/></span>
                                <span><c:out value="${user.nickname}"/></span>
                                <span><c:out value="${user.score}"/></span>
                                <button onclick="deleteScore(${user.account_id})">Delete</button>
                            </div>
                        </c:when>
                        <c:when test="${status.index == 1}">
                            <div class="second">
                                <span><c:out value="${status.index + 1}"/></span>
                                <span><c:out value="${user.nickname}"/></span>
                                <span><c:out value="${user.score}"/></span>
                                <button onclick="deleteScore(${user.account_id})">Delete</button>
                            </div>
                        </c:when>
                        <c:when test="${status.index == 2}">
                            <div class="third">
                                <span><c:out value="${status.index + 1}"/></span>
                                <span><c:out value="${user.nickname}"/></span>
                                <span><c:out value="${user.score}"/></span>
                                <button onclick="deleteScore(${user.account_id})">Delete</button>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
            <div class="overflow">
                <c:forEach var="user" items="${result.rows}" varStatus="status">
                    <c:if test="${status.index >= 3}">
                        <div class="leaderboard-item">
                            <span><c:out value="${status.index + 1}"/>. <c:out value="${user.nickname}"/></span>
                            <span><c:out value="${user.score}"/></span>
                            <button onclick="deleteScore(${user.account_id})">Delete</button>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>

        <script>
            function deleteScore(scoreId) {
                if (confirm('Are you sure you want to delete this score?')) {
                    // Gửi yêu cầu xóa điểm số bằng AJAX
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', '/DeleteController', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === XMLHttpRequest.DONE) {
                            if (xhr.status === 200) {
                                // Xử lý kết quả sau khi xóa thành công
                                alert('Score deleted successfully.');
                                // Reload lại trang để cập nhật leaderboard (tùy thuộc vào cách bạn cài đặt)

                                console.log('Server response:', xhr.responseText);
                                location.reload();
                            } else {
                                console.error('Error:', xhr.status);
                            }
                        }
                    };
                    xhr.send('scoreId=' + encodeURIComponent(scoreId));
                }
            }
        </script>

    </body>
</html>
