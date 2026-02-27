const category = document.querySelectorAll(".category");
const inputs = document.querySelectorAll(".typing-input");

category.forEach((category) => {
    category.addEventListener("change", (e) => {
        const condition = e.target.value === "직접 입력";
        if (!condition) {
            return;
        }

        const index = Number(category.id !== "selected-domain");
        inputs[index].classList.toggle("active", condition);
    });
});

/*버튼 선택 */
// const radios = document.querySelectorAll(".RadioSelector__StyledInput");
// radios.forEach((radio) => {
//     radio.addEventListener("change", (e) => {
//         if (e.target.checked) {
//             const wrapper = document.querySelector(
//                 ".RadioSelector__StyledRadioWrapper",
//             );
//         }
//     });
// });
// /* *
const deliveryRadio = document.querySelector(
    'input[name="paymentType"][value="delivery"]',
);
const qrRadio = document.querySelector('input[name="paymentType"][value="qr"]');
const wrapper = document.querySelector(".SettingForm__Wrapper");

deliveryRadio.checked = true;

function update() {
    const checked = document.querySelector('input[name="paymentType"]:checked');
    wrapper.classList.toggle("off", checked.value !== "qr");
}

update();

document.querySelectorAll('input[name="paymentType"]').forEach((radio) => {
    radio.addEventListener("change", update);
});

const pay = async () => {
    try {
        const response = await Bootpay.requestPayment({
            application_id: "69604c28b6279cebf60ad157",
            price: 1000,
            order_name: "테스트결제",
            order_id: "TEST_ORDER_ID",
            pg: "다날",
            // method: "계좌이체",
            tax_free: 0,
            user: {
                id: "회원아이디",
                username: "회원이름",
                phone: "01000000000",
                email: "test@test.com",
            },
            items: [
                {
                    id: "item_id",
                    name: "테스트아이템",
                    qty: 1,
                    price: 1000,
                },
            ],
            extra: {
                open_type: "iframe",
                card_quota: "0,2,3",
                escrow: false,
            },
        });
        switch (response.event) {
            case "issued":
                // 가상계좌 입금 완료 처리
                break;
            case "done":
                // 결제 완료 처리
                console.log(response);
                break;
            case "confirm": //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                console.log(response.receipt_id);
                /**
                 * 1. 클라이언트 승인을 하고자 할때
                 * // validationQuantityFromServer(); //예시) 재고확인과 같은 내부 로직을 처리하기 한다.
                 */
                const confirmedData = await Bootpay.confirm(); //결제를 승인한다
                if (confirmedData.event === "done") {
                    //결제 성공
                }

                /**
                 * 2. 서버 승인을 하고자 할때
                 * // requestServerConfirm(); //예시) 서버 승인을 할 수 있도록  API를 호출한다. 서버에서는 재고확인과 로직 검증 후 서버승인을 요청한다.
                 * Bootpay.destroy(); //결제창을 닫는다.
                 */
                break;
        }
    } catch (e) {
        // 결제 진행중 오류 발생
        // e.error_code - 부트페이 오류 코드
        // e.pg_error_code - PG 오류 코드
        // e.message - 오류 내용
        console.log(e.message);
        switch (e.event) {
            case "cancel":
                // 사용자가 결제창을 닫을때 호출
                console.log(e.message);
                break;
            case "error":
                // 결제 승인 중 오류 발생시 호출
                console.log(e.error_code);
                break;
        }
    }
};

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ""; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
                extraRoadAddr +=
                    extraRoadAddr !== ""
                        ? ", " + data.buildingName
                        : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (extraRoadAddr !== "") {
                extraRoadAddr = " (" + extraRoadAddr + ")";
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById("sample4_postcode").value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
            document.getElementById("sample4_jibunAddress").value =
                data.jibunAddress;

            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if (roadAddr !== "") {
                document.getElementById("sample4_extraAddress").value =
                    extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = "";
            }

            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if (data.autoRoadAddress) {
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                guideTextBox.innerHTML =
                    "(예상 도로명 주소 : " + expRoadAddr + ")";
                guideTextBox.style.display = "block";
            } else if (data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                guideTextBox.innerHTML =
                    "(예상 지번 주소 : " + expJibunAddr + ")";
                guideTextBox.style.display = "block";
            } else {
                guideTextBox.innerHTML = "";
                guideTextBox.style.display = "none";
            }
        },
    }).open();
}
