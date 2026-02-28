// 로그인 버튼 부분
const loginButton = document.querySelector("div.UserProfile-Button-Wrapper");
const loginBtnContent = loginButton.firstElementChild;
const loginBtnArrow = document.querySelector(
    "div.UserProfile-Button-Wrapper svg",
);

// 찜 버튼
const likeBtn = document.querySelector(`div[aria-label="찜한 상품"]`);

// 유저 메뉴바 부분
const userMenuBar = document.querySelector("div.UserProfile-Menu-Wrapper");

// 카테고리 부분
const headerLayout = document.querySelector(".Header-Layout");

// 로그인 버튼 클릭 여부
let isClicked = false;

// 토글용 값
let isActive = false;

// 로그인 버튼 클릭 시 이벤트
loginButton.addEventListener("click", (e) => {
    // 로그인 버튼 옆 화살표 애니메이션
    if (!isClicked) {
        loginBtnArrow.style.transform = "rotate(180deg)";
        loginBtnArrow.style.transition = `transform 0.5s`;
    } else {
        loginBtnArrow.style.transform = "rotate(0deg)";
        loginBtnArrow.style.transition = `transform 0.5s`;
    }
    isClicked = !isClicked;

    userMenuBar.classList.toggle("off", !isClicked);
});

likeBtn.addEventListener("click", (e) => {
    e.preventDefault();

    alert("추후 업데이트 예정입니다");
    return;
});

userMenuBar.addEventListener("click", (e) => {
    const userMenus = userMenuBar.querySelectorAll(".MenuList-Content");

    userMenus.forEach((menu) => {
        if(menu.classList.contains("mypage")) location.href = "/mypage/userpage";
        if(menu.classList.contains("likeItems")) {
            alert("추후 업데이트 예정입니다");
            return;
        }
        if(menu.classList.contains("editInfo")) location.href = "/profile/modify";
    })
})
