// $(function(){
//     latestAniList();
// });

// 페이지가 로드될 때 실행되는 코드
$(document).ready(function() {
    latestAniList();

    // 현재 날짜와 시간 가져오기
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    var currentQuarter = Math.floor((currentDate.getMonth() / 3)) + 1; // 분기 계산

    // allAniList 함수 초기 호출
    allAniList(currentYear, currentQuarter);
});



function latestAniList(){
    $.ajax({
        url: 'AniList/latest',
        type: 'GET',
        contentType: 'application/json;',
        dataType: 'json',
        dataSrc: '',
        error: function (error, status, msg) {
            alert("상태코드 " + status + "에러메시지" + msg);
        },
        success: function (data) {
            $(data).each(function (index, item) {
                var grade = parseFloat(item.grade);
                $('#ani-list').append(
                    `<div class=\"col-lg-3 col-sm-6\"><div id="${item.aniPk}" class=\"item\">`+
                    `<img src="${item.imgUrl}" alt="noImage">`+
                    `<div><div class="ani_title">${item.title}</div></>`+
                    `<ul><li><i class="fa fa-star"></i> ${grade}</li>`
                )
            })
        }
    });
}

function allAniList(year, quarter){
    $('#selected-list').empty();
    $.ajax({
        url: 'AniList/select?year='+year+'&quarter='+quarter,
        type: 'GET',
        contentType: 'application/json;',
        dataType: 'json',
        dataSrc: '',
        error: function (error, status, msg) {
            alert("상태코드 " + status + "에러메시지" + msg);
        },
        success: function (data) {
            $(data).each(function (index, item) {
                var grade = parseFloat(item.grade);
                $('#selected-list').append(
                    `<div class=\"col-lg-3 col-sm-6\"><div class=\"item\">`+
                    `<img src="${item.imgUrl}" alt="noImage">`+
                    `<div><div class="ani_title">${item.title}</div></>`+
                    `<ul><li><i class="fa fa-star"></i> ${grade}</li>`
                )
            })
        }
    });
}
