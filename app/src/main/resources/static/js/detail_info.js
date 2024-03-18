
// 이미지를 통한 개별 information을 modal창으로 가져오기
$('#ani-list').on('click', '*', function (e) {
    let closestElement = e.target.closest('.col-lg-3');
    detailModalAniInfo(closestElement.id);
});

$(document).on('click', function (event) {
    if ($(event.target).closest('#modalstyle').length === 0 && !$(event.target).is('#modalstyle')) {
        $('#modalstyle').attr("hidden", true);
    }
});

function detailModalAniInfo(aniPk) {
    $.ajax({
        url: 'DetailInfo/ani_detail?aniPk=' + aniPk,
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        dataSrc: '',
        error: function (error, status, msg) {
            alert("상태코드 " + status + " 에러메시지 " + msg);
        },
        success: function (data) {
            console.log(aniPk);
            console.log(data);

            var grade = parseFloat(data.grade);

            // 모달 열기
            $('#modalstyle').removeAttr("hidden");

            // 모달 내용 채우기
            $('.modal_body').css('display', 'flex').css('visibility', 'visible');

            if ($('.modal_thumbnail').length === 0) {
                $('#modal_left').append(
                    `<div class="modal_thumbnail"><a href="${data.imgUrl}">` +
                    `<div class="infoShort"><div id="releaseDate"><a href="${data.startDate}">` +
                    `<div id="titleInfo">${data.title}</div>` +
                    `<div id="rate"><ul><li><i class="fa fa-star"></i>${grade}</li></ul>` +
                    `<article id="introduction"><p>${data.detail}</p>`
                );

                $('#modal_right').append(
                    `<td id="comment_day">${data.commentDate}</td>` +
                    `<td id="comment_id">${data.commentId}</td>` +
                    `<td id="comment_text">${data.commentText}</td>` +
                    `<td id="comment_rate">${data.commentRate}</td>`
                );
            }
        }
    });
}

function writeComment() {
    const commentOpen = document.querySelector("#commentWrite");
    commentOpen.style.visibility = 'visible';
}

function writeCompleteComment() {

    
}