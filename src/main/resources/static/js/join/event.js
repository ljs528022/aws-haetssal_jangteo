const emailInputTag = document.querySelector("input[name=userEmail]")
const emailMessage = document.getElementById("email-message");
const button = document.querySelector("button[type=submit]");
let check = false;

emailInputTag.addEventListener("blur", (e) => {
    userService.checkEmail(e.target.value, (isAvailable) => {
        check = isAvailable;
        emailMessage.classList.toggle("on", isAvailable);
        emailMessage.textContent = isAvailable ? "사용 가능한 이메일입니다." : "중복된 이메일입니다.";
    });
});

button.addEventListener("click", (e) => {
    if(check) {
        document.joinFormTag.submit();
    }
});