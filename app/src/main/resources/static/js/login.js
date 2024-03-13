$(function(){
    login();
});

function login(){
    document.getElementById('loginForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 기본 제출 동작 방지

        var userId = document.getElementById('userId').value;
        var passwd = document.getElementById('passwd').value;

        // Ajax 요청
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    var user = JSON.parse(xhr.responseText);
                    displayUserInfo(user);
                } else {
                    console.error('로그인 요청 실패');
                }
            }
        };
        xhr.open('GET', '/loginUser?userId=' + userId + '&passwd=' + passwd, true); // 로그인 서블릿 엔드포인트 지정
        xhr.send();
    });

    // 사용자 정보를 화면에 표시하는 함수
    function displayUserInfo(user) {
        var userInfoDiv = document.getElementById('userInfo');
        if (user) {
            userInfoDiv.innerHTML = '<p>User ID: ' + user.userId + '</p>' +
                                    '<p>Name: ' + user.name + '</p>';
        } else {
            userInfoDiv.innerHTML = '<p>Login failed. Please check your credentials.</p>';
        }
    }
}