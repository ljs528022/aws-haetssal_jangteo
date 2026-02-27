const phoneInput = document.getElementById("phone");
const nicknameInput = document.getElementById("nickname");

// 가입하기 버튼
const joinBtn = document.querySelector(".JoinPage-JoinButton");

// 전화번호 정규식
const regPhone = /^0\d{1,2}\d{3,4}\d{4}$/;

// 연락처 입력란
phoneInput.addEventListener("keyup", (e) => {
    // 연락처 경고문
    const errorSpan = phoneInput.nextElementSibling;

    if (regPhone.test(e.target.value)) {
        phoneInput.style.border = "1px solid rgb(99, 156, 99)";
        errorSpan.style.color = "rgb(99, 156, 99)";
        errorSpan.innerHTML = "올바른 전화번호 형식입니다.";
    } else {
        phoneInput.style.border = "1px solid rgb(255, 87, 87)";
        errorSpan.style.color = "rgb(255, 87, 87)";
        errorSpan.innerHTML = "올바르지 않은 전회번호 형식입니다";
        return;
    }
});

// 가입하기 버튼 기능
joinBtn.addEventListener("click", (e) => {
    // 값이 모두 입력되었는지 검증하기
    if (!phoneInput.value) {
        alert("전화번호를 입력해주세요.");
        return;
    }
    if (!regPhone.test(phoneInput.value)) {
        alert("올바른 전화번호 형식이 아닙니다.");
        return;
    }

    alert("회원가입에 성공했습니다.");
    // 여기에 가입 요청 로직 만들어야함.
    document.kakaoJoinFormTag.submit();
    // 성공하면 alert후 홈으로

});
