// 이미지를 통한 개별 information을 modal창으로 가져오기
$('.test').on('click', '*', function (e) {
    let closestElement = e.target.closest('.col-lg-3');
    detailModalAniInfo(closestElement.id);
});

$(document).on('click', function (event) {
    if ($(event.target).closest('#modalstyle').length === 0 && !$(event.target).is('#modalstyle')) {
        $('#modalstyle').attr("hidden", true);

        $('#modal_left').empty();
        $('#modal_right').empty();
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
        success: function (data) { // {aniDetail: { // }, commentList: [{}, {}, {}]}
            console.log(aniPk);
            console.log(data);

            var grade = parseFloat(data.aniDetail.grade);

            var imgLink = data.aniDetail.imgUrl;
            var videoLink = data.aniDetail.videoUrl;

            var imgHtml = '<img src="' + imgLink + '">';
            var iframeHtml = videoLink ? '<iframe src="' + videoLink + '" frameborder="0" allowfullscreen></iframe>' : '';

            var commentHtml = '';
            for (var i=0; i< data.commentList.length; i++) {
                commentHtml +=               
                `<tr><td class="content_comment" id="comment_day">${data.commentList[i].commentDate}</td>
                <td class="content_comment" id="comment_id">${data.commentList[i].userPk}</td>
                <td class="content_comment" id="comment_text">${data.commentList[i].content}</td>
                <td class="content_comment" id="comment_rate">${data.commentList[i].initGrade}</td></tr>`
            }

            // 모달 열기
            $('#modalstyle').removeAttr("hidden");

            // 모달 내용 채우기
            $('.modal_body').css('display', 'flex').css('visibility', 'visible');

            if ($('.modal_thumbnail').length === 0) {
                $('#modal_left').append(
                    `<div class="modal_thumbnail">` +
                    (iframeHtml ? iframeHtml : imgHtml) +`</div>`+ // 유튜브 링크가 있을 경우 iframe, 없을 경우 이미지 표시
                    `<div class="infoShort"><div id="releaseDate">${data.aniDetail.startDate}"</div>` +
                    `<div id="titleInfo">${data.aniDetail.title}</div>` +
                    `<div id="rate"><ul><li><i class="fa fa-star"></i>${grade}</li></ul></div></div>` +
                    `<article id="introduction"><p>${data.aniDetail.detail}</p></article>`
                );

                $('#modal_right').append(
                    `<fieldset id="comment"><legend><h3>평론</h3></legend>` + 
                    `<table id="comment_table">
                        <tr>
                            <th class="comment_head" id="comment_day">날짜</th>
                            <th class="comment_head" id="comment_id">닉네임</th>
                            <th class="comment_head" id="comment_text">내용</th>
                            <th class="comment_head" id="comment_rate">평점</th>
                        </tr>
                        ${commentHtml}
                    </table>
                    <button id="write_cmt" onclick="writeComment()">댓글 쓰기</button>
                    </fieldset>
                    <fieldset id="commentWrite">평가 작성하기
                        <div id="comment_info">
                            <div class="write1" id="userId">
                                                        <input placeholder="닉네임" />
                            </div>
                            <div class="write1" id="star-rating">
                            <input type="radio" placeholder="별점" />
                            <input id="score" placeholder="평점" />
                            </div>
                        </div>
                        <textarea id="comment_context" placeholder="평가를 남겨주세요"></textarea>
                        <button type="submit" onclick="writeCompleteComment()">댓글 등록</button>
                    </fieldset>`
                    // `<td id="comment_day">${data.commentDate}</td>` +
                    // `<td id="comment_id">${data.commentId}</td>` +
                    // `<td id="comment_text">${data.commentText}</td>` +
                    // `<td id="comment_rate">${data.commentRate}</td>`
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
    $.ajax({
        url: 'DetailInfo/insertComment',
        type: 'post',
        contentType: 'application/json',
        dataType: 'json',
        data: {
            aniPk: aniPk,
            id:id,
            score: score,
            comment: comment            
        },
        error: function (error, status, msg) {
            alert("상태코드 " + status + " 에러메시지 " + msg);
        },
        success: function (data) { // {aniDetail: { // }, commentList: [{}, {}, {}]}
            console.log(aniPk);
            console.log(data);

            var grade = parseFloat(data.aniDetail.grade);

            var imgLink = data.aniDetail.imgUrl;
            var videoLink = data.aniDetail.videoUrl;

            var imgHtml = '<img src="' + imgLink + '">';
            var iframeHtml = videoLink ? '<iframe src="' + videoLink + '" frameborder="0" allowfullscreen></iframe>' : '';

            var commentHtml = '';
            for (var i=0; i< data.commentList.length; i++) {
                commentHtml +=               
                `<tr><td class="content_comment" id="comment_day">${data.commentList[i].commentDate}</td>
                <td class="content_comment" id="comment_id">${data.commentList[i].userPk}</td>
                <td class="content_comment" id="comment_text">${data.commentList[i].content}</td>
                <td class="content_comment" id="comment_rate">${data.commentList[i].initGrade}</td></tr>`
            }            
        }
    });
    
}