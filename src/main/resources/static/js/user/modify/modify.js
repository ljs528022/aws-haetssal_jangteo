// 회원 정보 수정 네비게이션
const menus = document.querySelectorAll(".Navigation-Span");
const underbar = document.querySelector(".menu-bar");
const currnetSpan = document.querySelector(".Navigation-Span.current");

// 회원 정보 수정 div들
const userInfoDiv = document.getElementById("user-info");
const userAccountDiv = document.getElementById("user-account");
const userDeliveryDiv = document.getElementById("user-delivery");

// 유저 정보 담을 변수
let userInfo = null;

const allSection = [userInfoDiv, userAccountDiv, userDeliveryDiv];

allSection.forEach((section) => section.classList.add("off"));

// 네비게이션 언더바 움직임 애니메이션
const moveUnderBar = (element) => {
    const left = element.offsetLeft;
    const width = element.offsetWidth;
    underbar.style.left = `${left}px`;
    underbar.style.width = `${width}px`;
};

if (currnetSpan) {
    moveUnderBar(currnetSpan);
}

menus.forEach((menu, i) => {
    menu.addEventListener("click", (e) => {
        e.preventDefault();

        // 각 페이지 누를 때마다 표시되는 div 변경
        let spanName = menu.getAttribute("name");
        allSection.forEach((section) => section.classList.add("off"));

        switch (spanName) {
            case "user-info":
                userInfoDiv.classList.remove("off");
                break;
            case "user-account":
                userAccountDiv.classList.remove("off");
                break;
            case "user-delivery":
                userDeliveryDiv.classList.remove("off");
                break;
        }

        menus.forEach((m) => m.classList.remove("current"));
        menu.classList.add("current");

        moveUnderBar(menu);
    });
});

// const getUserInfo = async (() => {
//     // 유저 정보 받아오는 로직 짜야함
// })();

document.querySelector('.Navigation-Span[name="user-info"]')?.click();
