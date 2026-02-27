// 상품 옵션 추가 부분
const optionAddDiv = document.getElementById("item-option");
const addedOptionsContainer = document.querySelector(".option-list-container");
const optionAddBtn = optionAddDiv.querySelector(".category-button");

// 상품 옵션 리스트 부분
const optionListDiv = document.querySelector(".item-option-list");

// 상품 옵션 추가 모달 관련
const optionModal = document.querySelector(".item-option-modal");
const optionBackdrop = optionModal.firstElementChild;
const optionCloseBtn = optionModal.querySelector(".modal-close-btn");

// 상품 옵션 모달 input들
const optionNameInput = document.getElementById("optionName");
const optionDetailInput = document.getElementById("optionDetail");
const optionPriceInput = document.getElementById("optionPrice");
const optionStockInput = document.getElementById("optionStock");
const optionSubmitBtn = optionModal.querySelector(".modal-submit-btn");

// 각 옵션을 담을 배열
let optionValues = [];

// 삭제할 옵션 ID 저장 배열
const optionIdsToDelete = [];

let currentOption = {
    optionName: "",
    optionDetail: "",
    optionPrice: "",
    optionStock: "",
};

// 페이지 로드 시 기존 옵션 데이터를 optionValues에 담기
window.addEventListener("DOMContentLoaded", () => {
    const existingOptions = document.querySelectorAll(".option-group:not(.add)");

    existingOptions.forEach((optionGroup, index) => {
        const optionId = optionGroup.querySelector(".option-delete-button")?.id;
        const optionName = optionGroup.querySelector(".option-name").value;
        const optionDetail = optionGroup.querySelector(".option-detail").value;
        const optionPrice = optionGroup.querySelector(".option-price").value;
        const optionStock = optionGroup.querySelector(".option-stock").value;

        optionValues.push({
            id: optionId,
            optionName: optionName,
            optionDetail: optionDetail,
            optionPrice: optionPrice,
            optionStock: optionStock,
            isExisting: true // 기존 옵션 표시
        });
    });

    console.log("초기 옵션 데이터:", optionValues);
});

optionAddBtn.addEventListener("click", (e) => {
    e.preventDefault();
    optionModal.classList.remove("off");
});

optionBackdrop.addEventListener("click", (e) => {
    if (e.target === optionBackdrop) {
        optionModal.classList.add("off");
        resetCurrentOption();
    }
});

optionCloseBtn.addEventListener("click", (e) => {
    optionModal.classList.add("off");
});

// 옵션 이름
optionNameInput.addEventListener("input", (e) => {
    const inputDiv = optionNameInput.parentElement;
    const inputMessage = inputDiv.parentElement.nextElementSibling;

    let value = e.target.value;

    if (value) {
        inputDiv.style.border = "1px solid rgb(228, 228, 228)";
        inputMessage.classList.add("off");

        currentOption.optionName = value;
    } else {
        inputDiv.style.border = "1px solid rgb(229, 60, 65)";
        inputMessage.classList.remove("off");
    }
});

// 옵션 상세 내용
optionDetailInput.addEventListener("input", (e) => {
    const textareaDiv = optionDetailInput.parentElement;
    const textareaMessage = textareaDiv.parentElement.nextElementSibling;

    let value = e.target.value;

    if (value) {
        currentOption.optionDetail = value;
        textareaDiv.style.border = "1px solid rgb(228, 228, 228)";
        textareaMessage.classList.add("off");
    } else {
        textareaDiv.style.border = "1px solid rgb(229, 60, 65)";
        textareaMessage.classList.remove("off");
    }
});

// 옵션 가격
optionPriceInput.addEventListener("input", (e) => {
    const inputDiv = optionPriceInput.parentElement;
    const inputMessage = inputDiv.parentElement.nextElementSibling;

    let value = e.target.value;
    const numericValue = value.replace(/,/g, "");

    if (numericValue && !isNaN(numericValue)) {
        optionPriceInput.value = parseInt(numericValue).toLocaleString();
        currentOption.optionPrice = numericValue;
        inputDiv.style.border = "1px solid rgb(228, 228, 228)";
        inputMessage.classList.add("off");
    } else {
        optionPriceInput.value = 0;
        inputDiv.style.border = "1px solid rgb(229, 60, 65)";
        inputMessage.classList.remove("off");
    }
});

// 옵션 수량
optionStockInput.addEventListener("input", (e) => {
    const inputDiv = optionStockInput.parentElement;
    const inputMessage = inputDiv.parentElement.nextElementSibling;

    let value = e.target.value;
    const numericValue = value.replace(/,/g, "");

    if (numericValue && !isNaN(numericValue)) {
        optionStockInput.value = parseInt(numericValue).toLocaleString();
        currentOption.optionStock = numericValue;
        inputDiv.style.border = "1px solid rgb(228, 228, 228)";
        inputMessage.classList.add("off");
    } else {
        optionStockInput.value = 0;
        inputDiv.style.border = "1px solid rgb(229, 60, 65)";
        inputMessage.classList.remove("off");
    }
});

// 옵션 추가 버튼 기능
optionSubmitBtn.addEventListener("click", (e) => {
    e.preventDefault();

    const isEmpty = Object.values(currentOption).some(
        (value) => !value || value.trim() === "",
    );

    if (isEmpty) {
        alert("모든 옵션 항목을 입력해주세요.");
        return;
    }

    // 새로운 옵션은 isExisting: false로 추가
    optionValues.push({
        ...currentOption,
        isExisting: false // 새로 추가된 옵션 표시
    });

    itemInfo.itemOptions.push({...currentOption});

    resetCurrentOption();

    generateOptionCard();

    optionModal.classList.add("off");
    optionListDiv.classList.remove("off");
});

// 입력 초기화
function resetCurrentOption() {
    currentOption = {
        optionName: "",
        optionDetail: "",
        optionPrice: "",
        optionStock: "",
    };

    optionNameInput.value = "";
    optionDetailInput.value = "";
    optionPriceInput.value = "";
    optionStockInput.value = "";
}

// 옵션 뿌리기 기능
function generateOptionCard() {
    const optionListContainer = document.querySelector(".option-list-container");
    optionListContainer.innerHTML = "";

    let newOptionIndex = 0;

    optionValues.forEach((option, index) => {
        // 기존 옵션과 새 옵션 구분
        const addClassName = option.isExisting ? "" : "add";
        const optionId = option.id || "";

        const nameAttrName    = option.isExisting ? "" : `name="itemOptions[${newOptionIndex}].optionName"`;
        const nameAttrDetail  = option.isExisting ? "" : `name="itemOptions[${newOptionIndex}].optionDetail"`;
        const nameAttrPrice   = option.isExisting ? "" : `name="itemOptions[${newOptionIndex}].optionPrice"`;
        const nameAttrStock   = option.isExisting ? "" : `name="itemOptions[${newOptionIndex}].optionStock"`;

        if (!option.isExisting) newOptionIndex++;

        const optionCard = `
            <div class="option-group ${addClassName}">
                <div class="option-group-upper">
                    <input class="option-name" type="text" ${nameAttrName} value="${option.optionName}" readonly>
                    <input class="option-detail" type="text" ${nameAttrDetail} value="${option.optionDetail}" readonly>
                </div>
                <div class="option-group-bottom">
                    <label class="option-label"> 가격
                        <input class="option-price" type="text" ${nameAttrPrice} value="${option.optionPrice}" readonly>
                    </label>
                    <label class="option-label"> 재고
                        <input class="option-stock" type="text" ${nameAttrStock} value="${option.optionStock}" readonly>
                    </label>
                </div>
                <button type="button" id="${optionId}" class="option-delete-button" data-index="${index}">
                    <div class="option-delete-icon">
                        <svg viewBox="0 0 48 48"><path fill-rule="evenodd" clip-rule="evenodd" d="M38.814 42.172C38.814 42.946 38.064 43.574 37.144 43.574H10.856C9.936 43.574 9.186 42.946 9.186 42.172V12.218H38.814V42.172ZM17.564 4.426L30.542 4.524V9.794H17.462L17.564 4.426ZM44.786 9.794H32.968V4.524C32.968 3.13 31.832 2 30.436 2H17.564C16.168 2 15.03 3.13 15.03 4.524V9.794H3.212C2.542 9.794 2 10.336 2 11.006C2 11.676 2.542 12.218 3.212 12.218H6.76V42.172C6.76 44.284 8.598 46 10.856 46H37.144C39.402 46 41.24 44.284 41.24 42.172V12.218H44.786C45.456 12.218 46 11.676 46 11.006C46 10.336 45.456 9.794 44.786 9.794ZM18.857 36.9338C19.527 36.9338 20.069 36.3918 20.069 35.7218V20.0738C20.069 19.4038 19.527 18.8618 18.857 18.8618C18.187 18.8618 17.645 19.4038 17.645 20.0738V35.7218C17.645 36.3918 18.187 36.9338 18.857 36.9338ZM30.3542 35.7218C30.3542 36.3918 29.8122 36.9338 29.1422 36.9338C28.4722 36.9338 27.9302 36.3918 27.9302 35.7218V20.0738C27.9302 19.4038 28.4722 18.8618 29.1422 18.8618C29.8122 18.8618 30.3542 19.4038 30.3542 20.0738V35.7218Z"></path></svg>
                    </div>
                </button>
            </div>
        `;

        optionListContainer.insertAdjacentHTML("beforeend", optionCard);
    });

    console.log("현재 옵션 목록:", optionValues);
}

// 옵션 삭제 버튼 구현
optionListDiv.addEventListener("click", (e) => {
    const removeBtn = e.target.closest(".option-delete-button");

    if (removeBtn) {
        const index = parseInt(removeBtn.dataset.index);
        const deletedOption = optionValues[index];

        // 기존 옵션인 경우 삭제할 ID 목록에 추가
        if (deletedOption.isExisting && deletedOption.id) {
            optionIdsToDelete.push(deletedOption.id);
            console.log("삭제할 옵션 ID:", optionIdsToDelete);
        }

        // 배열에서 해당 인덱스 삭제
        optionValues.splice(index, 1);

        // itemInfo.itemOptions도 동기화
        if (itemInfo && itemInfo.itemOptions) {
            itemInfo.itemOptions.splice(index, 1);
        }

        // 옵션이 모두 삭제되면 목록 숨기기
        if (optionValues.length === 0) {
            optionListDiv.classList.add("off");
            generateOptionCard();
        } else {
            // 옵션 카드 다시 그리기
            generateOptionCard();
        }
    }
});
