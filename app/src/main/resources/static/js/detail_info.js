
// 이미지를 통한 개별 information을 modal창으로 가져오기
$('#ani-list').on('click', '*', function (e) {
        let closestElement = e.target.closest('.col-lg-3');
        detailModalAniInfo(closestElement.id);
});


function detailModalAniInfo(aniPk) {
    $.ajax({
        url: 'DetailInfo/ani_detail?aniPk='+aniPk,
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

            document.querySelector('.modal_body').style.display = 'flex';
            document.querySelector('.modal_body').style.visibility = 'visible';
            
            if(!document.getElementsByClassName('.modal_thumbnail')) {
                $('#modal_left').append(
                    `<div class=\"modal_thumbnail"><a href=\"${data.imgUrl}">` +
                    `<div class=\"infoShort"><div id=\"releaseDate"><a href="${data.startDate}"` +
                    `<div id=\"titleInfo">${data.title}` +
                    `<div id=\"rate"><ul><li><i class="fa fa-star></i>${grade}</li></ul>` +
                    `<article id=\"introduction"><p>${data.detail}</p>`
                );
                $('#modal_right').append(
                    `<td id=\"comment_day">${data.commentDate}</td>"` +
                    `<td id=\"comment_id">${data.commentId}</td>"` +
                    `<td id=\"comment_text">${data.commentText}</td>"` +
                    `<td id=\"comment_rate">${data.commentRate}</td>"`
                )

    
            }
        }
    })
}
