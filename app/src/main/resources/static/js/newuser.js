
function chId() {
  const id = document.getElementById("login-id").value;
  document.getElementById("error").innerHTML = "";
  const ch = /^[a-z0-9]+$/;
  if (ch.test(id)) {
    if (id.length < 5) {
      alert("5자 이상의 아이디만 사용 가능합니다");
      document.getElementById("error").innerHTML = "규정에 맞지 않는 아이디입니다";
    } else {
      if(id.length > 4){
        $.ajax({
          type: 'POST',
          url: '/chId',
          data: { name: id },
          dataType: 'json',
          success: function (data, status, msg) {
      
            alert("사용 가능한 아이디 입니다.");
          
          },
          error: function (error, status, msg) {
            alert("중복된 아이디 입니다");
          },
        });
      }
    }
  } else {
    alert("규정에 맞지 않는 아이디입니다.");
    document.getElementById("error").innerHTML = "규정에 맞지 않는 아이디입니다";
  }
  0
}


function checkId() {
  const ch = /^[a-z0-9]+$/;
  const id = document.getElementById("login-id").value;
  document.getElementById("error").innerHTML = "";

  if (ch.test(id)) {

    if (id.length < 5) {
      document.getElementById("error").innerHTML = "규정에 맞지 않는 아이디입니다";

    } else {
      return true;
    }

  } else {
    document.getElementById("error").innerHTML = "규정에 맞지 않는 아이디입니다";
  }

}

function checkPw() {
  const ch = /^[ㄱ-ㅎㅏ-ㅣ가-힣]+$/;
  const pw = document.getElementById("login-pw").value;

  if (ch.test(pw)) {
    document.getElementById("error2").innerHTML = "규정에 맞지 않는 비밀번호입니다";
  } else if (pw.length >= 8) {
    document.getElementById("error2").innerHTML = " ";
    return true;
  } else {
    document.getElementById("error2").innerHTML = "규정에 맞지 않는 비밀번호입니다";
  }

}

function checkPw2() {

  const pw = document.getElementById("login-pw").value;
  const pw2 = document.getElementById("login-pw2").value;
  if (pw == pw2) {
    document.getElementById("error3").innerHTML = " ";
    return true;
  } else {
    document.getElementById("error3").innerHTML = "비밀번호가 일치하지 않습니다";
  }


}


function checkName() {
  const name = document.getElementById("login-us").value;
  if (name != null && name != "") {
    return true;
  } else {
    return null;
  }
}

function newuser() {
  const id = document.getElementById("login-id").value;
  if (checkId() == true && checkPw() == true && checkPw2() == true && checkName() == true) {
    if(id.length > 4){
      $.ajax({
        type: 'POST',
        url: '/insertUesr',
        data: { 
          userId: $("#login-id").val(),
          passwd: $("#login-pw").val(),
          name: $("#login-us").val() },
      
        dataType: 'json',
        success: function (data, status, msg) {
          
          alert("회원가입에 성공했습니다");
          location.href = '/login';
        },
        error: function (error, status, msg) {
          alert("중복된 아이디 입니다");
        },
      });
    }
  } else {

    if (checkId() == null) {
      document.getElementById("login-id").focus();
      alert("잘못된 아이디입니다.");

    } else if (checkPw() == null) {
      document.getElementById("login-pw").focus();
      alert("잘못된 비밀번호입니다.");

    } else if (checkPw2() == null) {
      document.getElementById("login-pw2").focus();
      alert("비밀번호 체크가 다릅니다.");

    } else if (checkName() == null) {
      document.getElementById("login-us").focus();
      alert("잘못된 이름입니다");

    } 


  }

}



