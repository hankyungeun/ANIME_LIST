//인스턴스사용량가져오기
$(function(){
    latestAniList();
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
