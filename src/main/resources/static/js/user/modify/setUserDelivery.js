// -----------------------------------------
// 배송지 추가 부분

// 배송지 div, 버튼, 추가 모달
const addButton = userDeliveryDiv.querySelector(".UserSetting-FormButton");
const emptyDelivery = document.querySelector(".UserSetting-EmptyDelivery");
const deliveryAddModal = document.querySelector(".Delivery-ModalLayer");

// 배송지 추가 모달 내의 요소들
const receiverNameDiv = document.getElementById("receiver-name");
const addressDiv = document.getElementById("receiver-address");
const addressDetailDiv = document.getElementById("receiver-addressDetail");
const receiverPhoneDiv = document.getElementById("receiver-phone");
const deliveryCheckBoxs = document.querySelectorAll(".CheckBox-Wrapper");
const closeButton = deliveryAddModal.querySelector(".Delivery-CloseButton");
const addAddressBtn = deliveryAddModal.querySelector(".Delivery-AddButton");

// 모달 내의 input 들
const receiverNameInput = document.getElementById("receiverName");
const addressInput = document.getElementById("address");
const addressDetailInput = document.getElementById("addressDetail");
const receiverPhoneInput = document.getElementById("receiverPhone");

let deliveryList = [];

// 모달 내의 저장 값들
let receiverName = "";
let address = "";
let addressDetail = "";
let receiverPhone = "";

deliveryList.length != 0 && emptyDelivery.classList.add("off");
address
    ? addressDetailDiv.classList.remove("off")
    : addressDetailDiv.classList.add("off");

addButton.addEventListener("click", (e) => {
    deliveryAddModal.classList.remove("off");
});

closeButton.addEventListener("click", (e) => {
    deliveryAddModal.classList.add("off");
});

receiverNameInput.addEventListener("keyup", (e) => {
    receiverName = e.target.value;
});

addressDetailInput.addEventListener("keyup", (e) => {
    addressDetail = e.target.value;
});

receiverPhoneInput.addEventListener("keyup", (e) => {
    receiverPhone = e.target.value;
});

deliveryCheckBoxs.forEach((box) => {
    const boxSpan = box.querySelector(".CheckBox-Icon");
    box.addEventListener("click", (e) => {
        boxSpan.classList.toggle("checked");
    });
});

addAddressBtn.addEventListener("click",async (e) => {
    // 배송지 추가하는 로직 넣어야 함.
    if (!receiverName || !address || !receiverPhone) {
        alert("필수 항목을 입력해주세요.");
        return;
    }

    const deliveryData = {
        deliveryReceiver: receiverName,
        deliveryAddress: address,
        deliveryDetailAddress: addressDetail,
        receiverPhone: receiverPhone
    };

    const response = await profileService.addDelivery(deliveryData);
    const result = await response.text();
    if (result === "success") {
        alert("배송지가 추가되었습니다.");
        deliveryAddModal.classList.add("off");
        loadDeliveryList();
        return;
    }
    alert("배송지 추가에 실패했습니다.");
});

// 다음 주소 api 부분
document.addEventListener("DOMContentLoaded", function () {
    const addressDiv = document.getElementById("receiver-address");
    const mainModal = document.querySelector(".Delivery-Modal:not(.off)");
    const apiModal = document.querySelector(".Delivery-Modal.api");
    const apiBody = apiModal.querySelector(".Delivery-ModalBody");

    addressDiv.addEventListener("click", () => {
        openAddrApi();
    });

    function openAddrApi() {
        apiBody.innerHTML = "";

        mainModal.style.display = "none";
        apiModal.classList.remove("off");
        apiModal.style.display = "block";

        new daum.Postcode({
            oncomplete: function (data) {
                let addr =
                    data.userSelectedType === "R"
                        ? data.roadAddress
                        : data.jibunAddress;

                addressInput.value = addr;
                address = addr;

                addressDetailDiv.classList.remove("off");
                addressDetailInput.focus();

                closeAddrApi();
            },
            width: "100%",
            height: "100%",
            maxSuggestItems: 5,
        }).embed(apiBody);
    }

    const apiCloseBtn = apiModal.querySelector(".Delivery-CloseButton");
    apiCloseBtn.addEventListener("click", (e) => {
        closeAddrApi();
    });

    const closeAddrApi = () => {
        mainModal.classList.remove("off");
        apiModal.classList.add("off");
        apiModal.style.display = "none";
        mainModal.style.display = "block";

        apiBody.innerHTML = "";
    };
});


// 배송지 목록뿌리기
const loadDeliveryList = async () => {
    const response = await profileService.getDeliveryList();
    const list = await response.json();
    const deliveryListDiv = document.querySelector(".UserSetting-DeliveryList");

    // 내용 비우기
    deliveryListDiv.innerHTML = "";

    if (list.length === 0) {
        deliveryListDiv.innerHTML = `
            <div class="UserSetting-EmptyDelivery">
                등록된 배송지가 없습니다.<br/>배송지를 추가해주세요.
            </div>
        `;
        return;
    }

    for (let i = 0; i < list.length; i++) {
        const delivery = list[i];

        const card = document.createElement("div");
        card.classList.add("Delivery-Card");

        const info = document.createElement("div");
        info.classList.add("Delivery-CardInfo");
        info.innerHTML = delivery.deliveryReceiver + " / " + delivery.receiverPhone
            + "<br/>" + delivery.deliveryAddress + " " + (delivery.deliveryDetailAddress || "");

        const deleteBtn = document.createElement("button");
        deleteBtn.classList.add("Delivery-DeleteBtn");
        deleteBtn.innerText = "삭제";

        deleteBtn.addEventListener("click", async () => {
            if (!confirm("배송지를 삭제하시겠습니까?")) return;

            const delResponse = await profileService.removeDelivery(delivery.id);
            const delResult = await delResponse.text();

            if (delResult === "success") {
                alert("배송지가 삭제되었습니다.");
                loadDeliveryList();
            }
        });

        card.appendChild(info);
        card.appendChild(deleteBtn);
        deliveryListDiv.appendChild(card);
    }
};
loadDeliveryList();
