function changeHeaderColor(){
    document.addEventListener('DOMContentLoaded', function() {
        // 현재 URL 가져오기
        var currentUrl = window.location.pathname;

        // 모든 링크 요소 가져오기
        var links = document.querySelectorAll('.main-nav .nav li a');

        // 링크 요소에 이벤트 리스너를 추가합니다.
        links.forEach(function(link) {
            // 각 링크의 href 속성 값을 가져옵니다.
            var linkUrl = link.getAttribute('href');

            // 현재 URL과 링크의 href 속성 값을 비교하여 'active' 클래스를 추가합니다.
            if (currentUrl === linkUrl) {
                link.classList.add('active');
            }
        });
    });

}
changeHeaderColor();