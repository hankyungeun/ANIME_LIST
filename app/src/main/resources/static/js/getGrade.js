function getMainListGrade(){
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
            var aniGradeElements = $('.ani_grade'); // ani_grade 클래스를 가진 요소들을 배열로 받아옴
            $(data).each(function (index, item) {
                var newGrade = parseFloat(item.grade);
                $(aniGradeElements[index]).html(`<i class="fa fa-star"></i> ${newGrade}`);
            });
        }
    });
}