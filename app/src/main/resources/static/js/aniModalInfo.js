// 이미지를 통한 개별 information을 modal창으로 가져오기
$('.test').on('click', '*', function (e) {
    let closestElement = e.target.closest('.col-lg-3');
    $("#commentWrite input[name=anipk]").val(closestElement.id);
    detailModalAniInfo(closestElement.id);
});

$(document).on('click', function (event) {
    if ($(event.target).closest('.modal_body').length === 0 && !$(event.target).is('.modal_body')) {
        $('#modalstyle').attr("hidden", true);
        $('body').css('overflow', 'auto');

        $('#modal_left').empty();
        $('#comment_table').empty();
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

            if (data.aniDetail && data.aniDetail.grade !== undefined) {

                var imgLink = data.aniDetail.imgUrl;
                var videoLink = data.aniDetail.videoUrl;

                var imgHtml = '<img src="' + imgLink + '">';
                var iframeHtml = videoLink ? '<iframe src="' + videoLink + '" frameborder="0" allowfullscreen></iframe>' : '';

                var commentHtml = '';
                for (var i=0; i< data.commentList.length; i++) {
                    commentHtml +=               
                    `<tr><td class="content_comment">${data.commentList[i].commentDate}</td>
                    <td class="content_comment">${data.commentList[i].userPk}</td>
                    <td class="content_comment" title="${data.commentList[i].content}" onclick="">${data.commentList[i].content}</td>
                    <td class="content_comment"><i class="fa fa-star" id="gstar"></i>&nbsp;${data.commentList[i].initGrade}</td></tr>`
                }
            } else {
                // aniDetail이 없거나 grade가 undefined인 경우 처리
                console.error("aniDetail 또는 grade가 없습니다.");
            }
            
            // 모달 열기 & 'body'파트 스크롤 방지
            $('#modalstyle').removeAttr("hidden");
            $('body').css('overflow', 'hidden');

            // 모달 내용 채우기
            $('.modal_body').css('display', 'flex').css('visibility', 'visible');

            $('#modal_left').html('');
            $('#comment_table').html('');

            if ($('.modal_thumbnail').length === 0) {
                $('#modal_left').append(
                    `<div class="modal_thumbnail">` +
                    (iframeHtml ? iframeHtml : imgHtml) +`</div>`+ // 유튜브 링크가 있을 경우 iframe, 없을 경우 이미지 표시
                    `<div class="infoShort"><div id="releaseDate">${data.aniDetail.startDate}</div>` +
                    `<div id="titleInfo" title="${data.aniDetail.title}">${data.aniDetail.title}</div>` +
                    `<div id="rate"><ul><li><i class="fa fa-star" id="gstar"></i>&nbsp;${data.aniDetail.grade}</li></ul></div></div>` +
                    `<article id="introduction"><p>${data.aniDetail.detail}</p></article>`
                );

                $('#comment_table').append(
                    `<thead>
                        <tr>
                            <th class="comment_head">날짜</th>
                            <th class="comment_head">닉네임</th>
                            <th class="comment_head">내용</th>
                            <th class="comment_head">평점</th>
                        </tr>
                    </thead>
                    ${commentHtml}`
                );
            }

            $('#'+aniPk+' ul li').html('<i class="fa fa-star"></i>'+data.aniDetail.grade);
        }
    });
}

function showWriteComment() {
    const commentOpen = document.querySelector("#commentWrite");
    commentOpen.style.visibility = 'visible';
}

function writeCompleteComment() {
    $.ajax({
        url: 'DetailInfo/insertComment',
        type: 'POST',
        data: {
            aniPk: $("#commentWrite input[name=anipk]").val(),
            initGrade: $("#score").val(),
            content: $("#comment_context").val()
        },
        error: function (error, status, msg) {
            alert("상태코드 " + status + " 에러메시지 " + msg);
        },
        success: function (data) { // {aniDetail: { // }, commentList: [{}, {}, {}]}
            if (data !== "error") {
                // 입력요소 비우기
                $("#score").val('');
                $("#comment_context").val('');


                var commentHtml = '';
                for (var i=0; i< data.length; i++) {
                    commentHtml +=               
                    `<tr><td class="content_comment">${data[i].commentDate}</td>
                    <td class="content_comment">${data[i].userPk}</td>
                    <td class="content_comment">${data[i].content}</td>
                    <td class="content_comment"><i class="fa fa-star" id="gstar"></i>&nbsp;${data[i].initGrade}</td></tr>`
                }

                $("#comment_table tbody").html(commentHtml);
            }

            detailModalAniInfo(data[0].aniPk);

        }
    });
    
}