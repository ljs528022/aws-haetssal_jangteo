// input들
const emailInput = document.getElementById("email");
const passInput = document.getElementById("password");

// 로그인 버튼
const loginBtn = document.querySelector(".LoginPage-LoginButton");
const kakaoLoginBtn = document.querySelector(".styled__KakaoButton");

// // 이메일 정규식이 로그인에서 필요하진 않을것 같아서 일단 주석
// const regEmail =
//     /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

// // 비밀번호 정규식도 같은이유로 일단 주석
// const regPassword =
//     /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#.~_-])[A-Za-z\d@$!%*?&#.~_-]{8,15}$/;

// 비밀번호 입력란 눈알
const passVisible = passInput.nextElementSibling.nextElementSibling;
let isPassVisible = false;

// 비밀번호 보이기 on/off
passVisible.addEventListener("click", () => {
    isPassVisible = !isPassVisible;
    passInput.type = isPassVisible ? "text" : "password";
    passVisible.firstElementChild.classList.toggle("off");
    passVisible.lastElementChild.classList.toggle("off");
});

emailInput.addEventListener("keyup", (e) => {

    if (e.target.value) {
        emailInput.style.border = "2px solid rgb(99, 156, 99)";
    }
});

emailInput.addEventListener("blur", (e) => {
    emailInput.style.border = "";
});

passInput.addEventListener("keyup", (e) => {
    if (e.target.value) {
        passVisible.classList.remove("off");
        passInput.style.border = "2px solid rgb(99, 156, 99)";
    }
});

passInput.addEventListener("blur", (e) => {
    passInput.style.border = "";
});

// 비밀번호 입력란에 값이 없으면 눈이 사라짐
passInput.addEventListener("blur", (e) => {
    if (!e.target.value) passVisible.classList.add("off");
});

// 로그인 버튼 기능
loginBtn.addEventListener("click", (e) => {
    if (!emailInput.value) {
        alert("이메일을 입력해주세요.");
        return;
    }
    if (!passInput.value) {
        alert("비밀번호를 입력해주세요.");
        return;
    }
    // 여기에 로그인 요청 로직 작성
    document.loginFormTag.submit();
});

//  카카오 로그인 버튼
kakaoLoginBtn.addEventListener("click", (e) =>{
    location.href = "https://kauth.kakao.com/oauth/authorize?client_id=e5b5077493c384bbc0c41bd0b00ef6fd&redirect_uri=http://localhost:10000/kakao/login&response_type=code";
});

// 로그인 실패
if(login) {
    document.querySelector(".login-fail-msg").classList.add("on");
}

if(alreadyExist) {
    document.querySelector(".kakao-fail-msg").classList.add("on");
}
