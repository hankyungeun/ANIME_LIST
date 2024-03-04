//인스턴스사용량가져오기
$(function(){
    latestAniList();
});

function latestAniList(){
    $.ajax({
        url: 'AniList',
        type: 'GET',
        contentType: 'application/json;',
        dataType: 'json',
        dataSrc: '',
        error: function (error, status, msg) {
            alert("상태코드 " + status + "에러메시지" + msg);
        },
        success: function (data) {
            $(data).each(function (index, item) {
                $('.item').append('<img src="'+ item.imgUrl + '" alt="noImage>"')
                console.log(item)
            })
        }
    });
}
