// -----------------------------------------
// 유저 수정 페이지 기능

// 프로필 부분
const userImageDiv = document.getElementById("user-image");
const userNameDiv = document.getElementById("user-name");
const userIntroDiv = document.getElementById("user-intro");

// 프로필 부분의 변경 버튼들
const userInfoSettingBtns = userInfoDiv.querySelectorAll(
    ".UserSetting-FormButton",
);

let userImage = ""; // 유저 이미지 경로
let name = ""; // 유저 이름
let intro = ""; // 유저 소개글

// 현재 눌린 변경 버튼 식별용
let currentDiv = null;

// 회원 프로필 이미지 부분
const userAvatar = document.querySelector(".UserSetting-AvatarWrapper");
const userImageUpload = document.querySelector(".UserSetting-ImageUpload");
const userPrviewImage = userImageUpload.querySelector(".UserSetting-Avatar");
const userImageSubmitBtn = userImageDiv.lastElementChild;
const tempImagePath = "../../../static/images/haetsal-jangteo-logo.svg";

// 회원 이름 부분
const userName = userNameDiv.querySelector(
    ".UserSetting-FormHeader",
).nextElementSibling;
const userNameInput = document.querySelector(".UserSetting-InputWrapper");
const userNameSubmitBtn = userNameDiv.lastElementChild;

// 회원 소개글 부분
const emptyIntro = userIntroDiv.querySelector(".UserSetting-Empty");
const introTextArea = document.querySelector(".UserSetting-TextareaWrapper");
const introSubmitBtn = userIntroDiv.lastElementChild;

// 각 요소들의 입력요소
const imageInput = document.getElementById("userImage");
const nameInput = document.getElementById("name");
const introInput = document.getElementById("intro");

userPrviewImage.firstElementChild.src = tempImagePath;

// 변경 누르기 전에 숨겨야 할 것을 off
userImageUpload.classList.add("off");
userImageSubmitBtn.classList.add("off");
userNameInput.classList.add("off");
userNameSubmitBtn.classList.add("off");
intro !== "" && emptyIntro.classList.add("off");
introTextArea.classList.add("off");
introSubmitBtn.classList.add("off");

userInfoSettingBtns.forEach((button) => {
    button.addEventListener("click", (e) => {
        button.classList.toggle("onClick");
        button.innerHTML = button.classList.contains("onClick")
            ? "취소"
            : "변경";

        // 누른 버튼의 최상단 div의 id 값
        currentDiv = button.closest(".UserSetting-FormWrapper").id;

        switch (currentDiv) {
            case "user-image":
                userAvatar.classList.toggle("off");
                userImageUpload.classList.toggle("off");
                userImageSubmitBtn.classList.toggle("off");
                break;
            case "user-name":
                userName.classList.toggle("off");
                userNameInput.classList.toggle("off");
                userNameSubmitBtn.classList.toggle("off");
                break;
            case "user-intro":
                if(emptyIntro){
                    !emptyIntro.classList.contains("off")
                        ? emptyIntro.classList.add("off")
                        : emptyIntro.classList.remove("off");
                }

                introTextArea.classList.toggle("off");
                introSubmitBtn.classList.toggle("off");
                break;
        }
    });
});

imageInput.addEventListener("change", (e) => {
    // image 변경 input과 삭제버튼
    const previewImgDiv = userPrviewImage.firstElementChild;
    const imageDelete = previewImgDiv.nextElementSibling;

    // 받아온 이미지와 해당 이미지 경로
    const image = e.target.files[0];
    const previewUrl = URL.createObjectURL(image);

    userImage = previewUrl;
    previewImgDiv.src = previewUrl;

    // 삭제버튼 누르면 임시이미지로 변경
    imageDelete.addEventListener("click", (e) => {
        previewImgDiv.src = tempImagePath;
        userImage = "";
    });
});

nameInput.addEventListener("keyup", (e) => {
    name = e.target.value;
});

introInput.addEventListener("keyup", (e) => {
    intro = e.target.value;
});

// userImageSubmitBtn.addEventListener("click", (e) => {
//     if (userImage) {
//         alert("회원님의 프로필 이미지가 변경되었습니다.");
//         userInfoSettingBtns[0].click();
//     } else {
//         userInfoSettingBtns[0].click();
//     }
// });
//
// userNameSubmitBtn.addEventListener("click", (e) => {
//     if (name) {
//         alert("회원님의 이름이 변경되었습니다.");
//         userInfoSettingBtns[1].click();
//     } else {
//         userInfoSettingBtns[1].click();
//     }
// });
//
// introSubmitBtn.addEventListener("click", (e) => {
//     if (intro) {
//         alert("회원님의 소개글이 변경되었습니다.");
//         userInfoSettingBtns[2].click();
//     } else {
//         userInfoSettingBtns[2].click();
//     }
// });

userImageSubmitBtn.addEventListener("click", async (e) => {
    if (imageInput.files[0]) {
        const formData = new FormData();
        formData.append("file", imageInput.files[0]);
        const response = await profileService.updateImage(formData);
        const result = await response.text();
        if (result === "success") {
            alert("프로필 이미지가 변경되었습니다.");
            location.reload();
            return;
        }
        alert("이미지 변경에 실패했습니다.");
    }
    userInfoSettingBtns[0].click();
});

userNameSubmitBtn.addEventListener("click", async (e) => {
    if (nameInput.value) {
        const response = await profileService.updateName(nameInput.value);
        const result = await response.text();
        if (result === "success") {
            alert("이름이 변경되었습니다.");
            location.reload();
            return;
        }
        alert("이름 변경에 실패했습니다.");
    }
    userInfoSettingBtns[1].click();
});

introSubmitBtn.addEventListener("click", async (e) => {
    if (introInput.value) {
        const response = await profileService.updateIntro(introInput.value);
        const result = await response.text();
        if (result === "success") {
            alert("소개글이 변경되었습니다.");
            location.reload();
            return;
        }
        alert("소개글 변경에 실패했습니다.");
    }
    userInfoSettingBtns[2].click();
});
