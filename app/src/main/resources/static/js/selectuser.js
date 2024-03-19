
function updatecheckPw() {
    const ch = /^[ㄱ-ㅎㅏ-ㅣ가-힣]+$/;
    const pw = document.getElementById("update-pw").value;
    console.log(pw);
    console.log(ch.test(pw));

    if (ch.test(pw)) {
        console.log(222);
        document.getElementById("error2").innerHTML = "규정에 맞지 않는 비밀번호입니다";
    } else if (pw.length >= 8) {
        document.getElementById("error2").innerHTML = " ";
        return true;
    } else {
        document.getElementById("error2").innerHTML = "규정에 맞지 않는 비밀번호입니다";
    }

}

function updatecheckPw2() {

    const pw = document.getElementById("update-pw").value;
    const pw2 = document.getElementById("update-pw2").value;
    if (pw == pw2) {
        document.getElementById("error3").innerHTML = " ";
        return true;
    } else {
        document.getElementById("error3").innerHTML = "비밀번호가 일치하지 않습니다";
    }
}

function updatecheckName() {
    const name = document.getElementById("update-us").value;
    console.log(name);
    if (name != null && name != "") {
        return true;
    } else {
        return null;
    }
}


function updateuser(event) {
    event.preventDefault();
    console.log("--------")
    
    if (updatecheckPw() == true && updatecheckPw2() == true && updatecheckName() == true) {
        alert('회원 정보 수정에 성공하셨습니다');
        document.mForm.submit();
        
        console.log("--------")
        
        
    } else {

        if (updatecheckPw() == null) {
            document.getElementById("update-pw").focus();
            alert("잘못된 비밀번호입니다.");

        } else if (updatecheckPw2() == null) {
            document.getElementById("update-pw2").focus();
            alert("비밀번호 체크가 다릅니다.");

        } else if (updatecheckName() == null) {
            document.getElementById("update-us").focus();
            alert("잘못된 이름입니다");

        } else {
            alert("잘못된 입력입니다");
        }


    }


}