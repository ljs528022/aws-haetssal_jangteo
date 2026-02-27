// 첨에 인증하기 버튼
const startCheck =  document.querySelector(".info-check-btn");
// 취소 버튼(누르면 창 닫힘), 입력했던거 날라감
const closeBtn = document.querySelector(".cancel-btn");

// 이름 검사하기-한글 또는 영어만, 최소 2글자, 숫자 미포함
const nameInputWrap = document.querySelectorAll(".full-input-wrap")[0];
const nameInput = document.querySelector(".full-input.name-input");
const nameRegex = /^[가-힣a-zA-Z]{2,}$/;

// 폰번호 검사하기
const phoneInputWrap = document.querySelectorAll(".full-input-wrap")[1];
const phoneInput = document.querySelector(".full-input.phone-input");
const phoneRegex = /^01[016789][0-9]{7,8}$/;

let nameCheck = false;
let phoneCheck = false;

// 인증번호 전송버튼
const doCheckBtn = document.querySelector(".do-check-btn");

// 인증번호 검사(일단은 111111 로 설정)
const checkNumberInput = document.querySelector(".check-number-input");
const checkNumberBtn = document.querySelector(".do-check-btn-in-check");

// 1. 인증하기 눌렀을때 창열기
startCheck.addEventListener("click", (e) => {
    e.preventDefault();
    document.querySelector(".info-check-container").style.display = "block";
});
// 1-2. 취소눌러서 닫기
closeBtn.addEventListener("click", (e) => {
    e.preventDefault();

    // 취소 시 타이머 정지
    if (timerId) {
        clearInterval(timerId);
    }
    document.querySelector(".info-check-container").style.display = "none";
    document.querySelector(".when-do-check-wrap").style.display ="none";

    // 입력했던거 다 없애고
    nameInput.value = "";
    phoneInput.value = "";
    checkNumberInput.value = "";
    // 넴,폰체크도 폴스로 하고
    nameCheck = false;
    phoneCheck = false;
    // 버튼안눌리게하기
    // doCheckBtn.classList.add("disabled");
    // checkNumberBtn.classList.add("disabled");
});

// 2. 이름 입력 유효성검사
nameInput.addEventListener("input", (e) => {
    const value = e.target.value;

    if (nameRegex.test(value)) {
        nameInput.style.border = "1px solid rgb(13, 13, 13)";
        nameCheck = true;
    } else {
        nameInput.style.border = "1px solid rgb(229, 60, 65)";
        nameCheck = false;
    }
    checkNamePhone();
});

nameInput.addEventListener("blur", (e) => {
    const value = e.target.value;

    if (nameRegex.test(value)) {
        nameInput.style.border = "1px solid rgb(228, 228, 228)";
    } else {
        nameInput.style.border = "1px solid rgb(229, 60, 65)";
    }
});

// 3. 폰번호 유효성검사
phoneInput.addEventListener("input", (e) => {
    const value = e.target.value;
    
    if (phoneRegex.test(value)) {
        phoneInput.style.border = "1px solid rgb(13, 13, 13)";
        phoneCheck = true;
    } else {
        phoneInput.style.border = "1px solid rgb(229, 60, 65)";
        phoneCheck = false;
    }

    checkNamePhone();
});

phoneInput.addEventListener("blur", (e) => {
    const value = e.target.value;

    if (phoneRegex.test(value)) {
        phoneInput.style.border = "1px solid rgb(228, 228, 228)";
    } else {
        phoneInput.style.border = "1px solid rgb(229, 60, 65)";
    }

});

// 폰이랑 이름 통과하면 버튼 활성화
const checkNamePhone = () => {
    if(nameCheck && phoneCheck) {
        doCheckBtn.classList.remove("disabled");
    } else {
        doCheckBtn.classList.add("disabled");
    }
};
// 전송 버튼 클릭 시 타이머 시작
doCheckBtn.addEventListener("click", (e) => {
    e.preventDefault();

    document.querySelector(".when-do-check-wrap").style.display ="flex";
    startTimer();
});

// 4. 인증번호 인풋칸 6자리면 버튼 활성화하고 
// 일단은 111111치면 인증완료 그외에는 인증실패하기
checkNumberInput.addEventListener("input", (e) => {
    if(checkNumberInput.value.length === 6) {
        document.querySelector(".do-check-btn-in-check").classList.remove("disabled");
    } else {
        document.querySelector(".do-check-btn-in-check").classList.add("disabled");
    }
});

checkNumberBtn.addEventListener("click", (e) => {
    e.preventDefault();

    // 인증 완료 시 타이머 정지
    if (checkNumberInput.value === "111111") {
        if (timerId) {
            clearInterval(timerId);
        }   
    }
    
    if (checkNumberInput.value === "111111") {
        alert("인증이 완료되었습니다.");
        document.querySelector(".info-check-container").style.display = "none";
        document.querySelector(".when-do-check-wrap").style.display = "none";
        document.querySelector(".info-intro-message").textContent = "인증을 완료하였습니다.";
        document.querySelector(".info-intro-message").classList.add("complete");
        document.querySelector(".info-icon-type").classList.add("complete");
        startCheck.textContent = "인증완료";
        startCheck.classList.add("disabled");
    } else {
        alert("인증번호가 일치하지 않습니다.");
        checkNumberInput.value = "";
    }
});

// 5. 인증번호 타이머
// 타이머 관련 변수
let timeLeft = 180; // 5분 = 300초
let timerId = null;
const timeDisplay = document.querySelector(".time-left");
const moreTimeBtn = document.querySelector(".more-time-btn-in-check");

// 타이머 시작 함수
function startTimer() {
    // 기존 타이머 있으면 제거
    if (timerId) {
        clearInterval(timerId);
    }
    
    timeLeft = 180;
    updateTimeDisplay();
    
    timerId = setInterval(() => {
        timeLeft--;
        updateTimeDisplay();
        
        if (timeLeft <= 0) {
            clearInterval(timerId);
            alert("인증 시간이 만료되었습니다.");
            document.querySelector(".when-do-check-wrap").style.display = "none";
            checkNumberInput.value = "";
            checkNumberBtn.classList.add("disabled");
        }
    }, 1000);
}

// 시간 표시 업데이트
function updateTimeDisplay() {
    const minutes = Math.floor(timeLeft / 60);
    const seconds = timeLeft % 60;
    timeDisplay.textContent = `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
}

// 연장하기 버튼
moreTimeBtn.addEventListener("click", (e) => {
    e.preventDefault();

    timeLeft += 180; // 3분 추가
    if (timeLeft > 180) timeLeft = 180; // 최대 5분
    updateTimeDisplay();
});
