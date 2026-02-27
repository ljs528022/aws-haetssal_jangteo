let test = document.getElementById("Btn");

test.addEventListener(
    "mouseover",
    function (event) {
        // highlight the mouseover target
        event.target.style.backgroundColor = "#e5e7eb";
    },
    false,
);

test.addEventListener(
    "mouseout",
    function (event) {
        // highlight the mouseout target
        event.target.style.backgroundColor = "";
    },
    false,
);

const qrBtn = document.querySelector(".QRcode");
const qrBox = document.getElementById("qrBox");

qrBtn.addEventListener("click", () => {
    if (qrBox.style.display === "none") {
        qrBox.style.display = "block";
    } else {
        qrBox.style.display = "none";
    }
});
