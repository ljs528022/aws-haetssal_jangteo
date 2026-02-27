// 헤더 상품 수정버튼
const submitButton = document.querySelector(".application-btn");

// 상품 이름 input
const itemNameInput = document.getElementById("itemName");
// 상품 내용 input
const itemContentInput = document.getElementById("itemContent");
// 상품 금액 input
const itemPriceInput = document.getElementById("itemPrice");
// 상품 수량 input
const itemStockInput = document.getElementById("itemStock");
// 상품 이미지 input
const itemImagesInput = document.getElementById("itemThumbnail");
// 상품 설명 이미지 input
const itemDescImagesInput = document.getElementById("itemDescImages");
// 상품 판매자 설명 이미지 input
const itemSellerImagesInput = document.getElementById("itemSellerImages");
// 상품 환불 설명 이미지 input
const itemRefundImagesInput = document.getElementById("itemRefundImages");

// 상품 카테고리
const itemCategory = document.getElementById("itemCategory");
const itemSubCategory = document.getElementById("itemSubCategory");
// 카테고리 값이 들어가는 input
const itemCategoryIdInput = document.getElementById("itemCategoryId");
const itemSubCategoryIdInput = document.getElementById("itemSubCategoryId");

// 상품 카테고리 선택창
const categoryA = document.querySelector(".category-select-dropdown.category-a");
const categoryB = document.querySelector(".category-select-dropdown.category-b");

// 상품 이미지 프리뷰 부분
const itemImageContainer = document.querySelector(".img-container");

// 상품 리셋 버튼
const itemThumbnailDiv = document.getElementById("item-image");
const imageResetButton = itemThumbnailDiv.querySelector(".reset-btn");

// 삭제할 파일 ID 저장 배열
const fileIdsToDelete = [];

// 전송 전 검증 용 배열
const itemInfo = {
    itemName: "",
    itemCategoryId: "",
    itemSubCategory: "",
    itemContent: "",
    itemPrice: "",
    itemStock: "",
    itemThumbnail: [],
    itemDescImages: [],
    itemSellerImages: [],
    itemRefundImages: [],
    itemOptions: [],
}

// 페이지 로드 시 초기 글자수 표시 및 리셋 버튼 활성화
window.addEventListener("DOMContentLoaded", () => {
    // 상품명 초기 길이 표시
    const nameFormWrapper = itemNameInput.closest(".form-wrap");
    const nameGuideDiv = nameFormWrapper.nextElementSibling;
    const nameValueCount = nameGuideDiv.lastElementChild;
    const nameValue = itemNameInput.value || "";
    nameValueCount.innerHTML = `${nameValue.length}/20`;
    if (nameValue) {
        itemInfo.itemName = nameValue;
    }

    // 상품 소개 초기 길이 표시
    const contentFormWrapper = itemContentInput.closest(".form-wrap");
    const contentGuideDiv = contentFormWrapper.nextElementSibling;
    const contentValueCount = contentGuideDiv.lastElementChild;
    const contentValue = itemContentInput.value || "";
    contentValueCount.innerHTML = `${contentValue.length}/50`;
    if (contentValue) {
        itemInfo.itemContent = contentValue;
    }

    // 기존 이미지가 있으면 리셋 버튼 활성화
    setupResetButtons();

    // 기존 이미지에 더블클릭 이벤트 등록
    setupImageDoubleClickEvents();
});

// 리셋 버튼 활성화 설정
function setupResetButtons() {
    // 상품 썸네일 이미지
    const existingThumbnails = itemImageContainer.querySelectorAll(".img-box");
    if (existingThumbnails.length > 0) {
        imageResetButton.classList.remove("off");
    }

    // 상품 설명 이미지
    const descPreviewContainer = itemDescImagesInput
        .closest(".uploader-container")
        .querySelector(".uploader-preview");
    const existingDescImages = descPreviewContainer.querySelectorAll(".img-box");
    if (existingDescImages.length > 0) {
        const descResetBtn = descPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
        descResetBtn.classList.remove("off");
    }

    // 판매자 소개 이미지
    const sellerPreviewContainer = itemSellerImagesInput
        .closest(".uploader-container")
        .querySelector(".uploader-preview");
    const existingSellerImages = sellerPreviewContainer.querySelectorAll(".img-box");
    if (existingSellerImages.length > 0) {
        const sellerResetBtn = sellerPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
        sellerResetBtn.classList.remove("off");
    }

    // 환불/교환 정책 이미지
    const refundPreviewContainer = itemRefundImagesInput
        .closest(".uploader-container")
        .querySelector(".uploader-preview");
    const existingRefundImages = refundPreviewContainer.querySelectorAll(".img-box");
    if (existingRefundImages.length > 0) {
        const refundResetBtn = refundPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
        refundResetBtn.classList.remove("off");
    }
}

// 기존 이미지에 더블클릭 이벤트 설정
function setupImageDoubleClickEvents() {
    document.addEventListener("dblclick", (e) => {
        const imgBox = e.target.closest(".img-box");

        if (imgBox) {
            const img = imgBox.querySelector("img");

            if (img && img.src) {
                openImageViewer(img.src);
            }
        }
    });
}

// 상품 이름 input Event
itemNameInput.addEventListener("input", (e) => {
    const formWrapper = itemNameInput.closest(".form-wrap");
    const guideDiv = formWrapper.nextElementSibling;
    const message = guideDiv.firstElementChild;
    const valueCount = guideDiv.lastElementChild;

    let value = e.target.value;
    if (value) {
        message.classList.add("off");
        formWrapper.style.border = "1px solid rgb(84, 84, 84)";
        valueCount.innerHTML = `${value.length}/20`;

        itemInfo.itemName = value;
    } else {
        message.classList.remove("off");
        formWrapper.style.border = "1px solid rgb(229, 60, 65)";
        valueCount.innerHTML = "0/20";
    }
});

// 상품 카테고리 Event
itemCategory.addEventListener("click", (e) => {
    const categoryList = categoryA.querySelectorAll(".each-category-item.item-a");

    itemCategory.nextElementSibling.classList.toggle("clicked");
    categoryA.classList.toggle("off");

    categoryList.forEach((category) => {
        category.addEventListener("click", (e) => {
            itemCategory.parentElement.classList.add("selected");
            itemCategory.value = category.innerHTML;

            // 값 저장
            itemCategoryIdInput.value = category.value;
            itemInfo.itemCategoryId = category.value;

            categoryA.classList.add("off");
            itemSubCategory
                .closest(".category-select-box")
                .classList.remove("disabled");
        });
    });
});

// 상세 카테고리 Event
itemSubCategory.addEventListener("click", (e) => {
    const categoryList = categoryB.querySelectorAll(".each-category-item.item-b");

    itemSubCategory.nextElementSibling.classList.toggle("clicked");
    categoryB.classList.toggle("off");

    categoryList.forEach((category) => {
        category.addEventListener("click", (e) => {
            itemSubCategory.parentElement.classList.add("selected");
            itemSubCategory.value = category.innerHTML;

            // 값 저장
            itemSubCategoryIdInput.value = category.value;
            itemInfo.itemSubCategory = category.value;

            categoryB.classList.add("off");
        });
    });
});

// 상품 소개 Event
itemContentInput.addEventListener("input", (e) => {
    const formWrapper = itemContentInput.closest(".form-wrap");
    const guideDiv = formWrapper.nextElementSibling;
    const message = guideDiv.firstElementChild;
    const valueCount = guideDiv.lastElementChild;

    let value = e.target.value;
    if (value) {
        message.classList.add("off");
        formWrapper.style.border = "1px solid rgb(84, 84, 84)";
        valueCount.innerHTML = `${value.length}/50`;

        itemInfo.itemContent = value;
    } else {
        message.classList.remove("off");
        formWrapper.style.border = "1px solid rgb(229, 60, 65)";
        valueCount.innerHTML = "0/50";
    }
});

// 상품 금액 Event
itemPriceInput.addEventListener("input", (e) => {
    const formWrapper = itemPriceInput.closest(".form-wrap");
    const guideDiv = formWrapper.nextElementSibling;
    const message = guideDiv.firstElementChild;

    let value = e.target.value;
    const numericValue = value.replace(/,/g, "");

    if (numericValue && !isNaN(numericValue)) {
        message.classList.add("off");
        formWrapper.style.border = "1px solid rgb(84, 84, 84)";
        itemPriceInput.value = parseInt(numericValue).toLocaleString();

        itemInfo.itemPrice = numericValue;
    } else {
        itemPriceInput.value = 0;
        message.classList.remove("off");
        formWrapper.style.border = "1px solid rgb(229, 60, 65)";
    }
});

// 상품 수량
itemStockInput.addEventListener("input", (e) => {
    const formWrapper = itemStockInput.closest(".form-wrap");
    const guideDiv = formWrapper.nextElementSibling;
    const message = guideDiv.firstElementChild;

    let value = e.target.value;
    const numericValue = value.replace(/,/g, "");

    if (numericValue && !isNaN(numericValue)) {
        message.classList.add("off");
        formWrapper.style.border = "1px solid rgb(84, 84, 84)";
        itemStockInput.value = parseInt(numericValue).toLocaleString();

        itemInfo.itemStock = numericValue;
    } else {
        itemStockInput.value = 0;
        message.classList.remove("off");
        formWrapper.style.border = "1px solid rgb(229, 60, 65)";
    }
});

// 상품 이미지 Event
const MAX_ITEM_IMAGES = 5;
itemImagesInput.addEventListener("change", (e) => {
    const files = Array.from(e.target.files);

    // 기존 이미지 개수 계산 (기존 + 이미 추가된 새 이미지)
    const totalImagesCount = itemImageContainer.querySelectorAll(".img-box").length;
    const remainingSlots = MAX_ITEM_IMAGES - totalImagesCount;

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_ITEM_IMAGES}장까지만 등록할 수 있습니다. (현재: ${totalImagesCount}장)`);
        itemImagesInput.value = "";
        return;
    }

    // 파일 배열에 추가
    itemInfo.itemThumbnail.push(...files);

    // 새로 추가된 파일만 렌더링
    files.forEach((img, i) => {
        const reader = new FileReader();

        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box add";
            imgBox.dataset.index = totalImagesCount + i;

            imgBox.innerHTML = `
                <img src="${e.target.result}" alt="preview-${i}">
            `;

            itemImageContainer.appendChild(imgBox);
        };

        reader.readAsDataURL(img);
    });

    itemImageContainer.nextElementSibling.classList.remove("off");
    imageResetButton.classList.remove("off");
});

// 썸네일 이미지 초기화 버튼
imageResetButton.addEventListener("click", (e) => {
    e.preventDefault();

    // 기존 이미지들의 ID를 삭제 목록에 추가
    const existingImages = itemImageContainer.querySelectorAll(".img-box:not(.add)");
    existingImages.forEach((imgBox) => {
        const img = imgBox.querySelector("img");
        if (img && img.id) {
            fileIdsToDelete.push(img.id);
        }
    });

    // 모든 이미지 제거
    itemImageContainer.innerHTML = "";

    // input과 배열 초기화
    itemImagesInput.value = "";
    itemInfo.itemThumbnail = [];

    itemImageContainer.nextElementSibling.classList.add("off");
    imageResetButton.classList.add("off");

    console.log("삭제할 파일 ID:", fileIdsToDelete);
});

// 상품 설명 이미지 Event 부분들
const MAX_DESC_IMAGES = 3;

// 각 input의 preview 컨테이너 선택
const descPreviewContainer = itemDescImagesInput
    .closest(".uploader-container")
    .querySelector(".uploader-preview");
const sellerPreviewContainer = itemSellerImagesInput
    .closest(".uploader-container")
    .querySelector(".uploader-preview");
const refundPreviewContainer = itemRefundImagesInput
    .closest(".uploader-container")
    .querySelector(".uploader-preview");

// 상품 설명 이미지 이벤트
itemDescImagesInput.addEventListener("change", (e) => {
    const files = Array.from(e.target.files);
    const totalCount = descPreviewContainer.querySelectorAll(".img-box").length;
    const remainingSlots = MAX_DESC_IMAGES - totalCount;

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_DESC_IMAGES}장까지만 업로드 가능합니다. (현재: ${totalCount}장)`);
        itemDescImagesInput.value = "";
        return;
    }

    itemInfo.itemDescImages.push(...files);

    files.forEach((img, i) => {
        const reader = new FileReader();
        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box add";
            imgBox.dataset.index = totalCount + i;
            imgBox.innerHTML = `<img src="${e.target.result}" alt="preview-${i}">`;
            descPreviewContainer.appendChild(imgBox);
        };
        reader.readAsDataURL(img);
    });

    const resetBtn = descPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
    resetBtn.classList.remove("off");
});

// 상품 설명 이미지 초기화 버튼
const descResetBtn = descPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
descResetBtn.addEventListener("click", (e) => {
    e.preventDefault();

    // 기존 이미지들의 ID를 삭제 목록에 추가
    const existingImages = descPreviewContainer.querySelectorAll(".img-box:not(.add)");
    existingImages.forEach((imgBox) => {
        const img = imgBox.querySelector("img");
        if (img && img.id) {
            fileIdsToDelete.push(img.id);
        }
    });

    // 모든 이미지 제거
    descPreviewContainer.innerHTML = "";

    itemDescImagesInput.value = "";
    itemInfo.itemDescImages = [];

    descResetBtn.classList.add("off");

    console.log("삭제할 파일 ID:", fileIdsToDelete);
});

// 판매자 소개 이미지 이벤트
itemSellerImagesInput.addEventListener("change", (e) => {
    const files = Array.from(e.target.files);
    const totalCount = sellerPreviewContainer.querySelectorAll(".img-box").length;
    const remainingSlots = MAX_DESC_IMAGES - totalCount;

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_DESC_IMAGES}장까지만 업로드 가능합니다. (현재: ${totalCount}장)`);
        itemSellerImagesInput.value = "";
        return;
    }

    itemInfo.itemSellerImages.push(...files);

    files.forEach((img, i) => {
        const reader = new FileReader();
        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box add";
            imgBox.dataset.index = totalCount + i;
            imgBox.innerHTML = `<img src="${e.target.result}" alt="preview-${i}">`;
            sellerPreviewContainer.appendChild(imgBox);
        };
        reader.readAsDataURL(img);
    });

    const resetBtn = sellerPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
    resetBtn.classList.remove("off");
});

// 판매자 소개 이미지 초기화 버튼
const sellerResetBtn = sellerPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
sellerResetBtn.addEventListener("click", (e) => {
    e.preventDefault();

    // 기존 이미지들의 ID를 삭제 목록에 추가
    const existingImages = sellerPreviewContainer.querySelectorAll(".img-box:not(.add)");
    existingImages.forEach((imgBox) => {
        const img = imgBox.querySelector("img");
        if (img && img.id) {
            fileIdsToDelete.push(img.id);
        }
    });

    // 모든 이미지 제거
    sellerPreviewContainer.innerHTML = "";

    itemSellerImagesInput.value = "";
    itemInfo.itemSellerImages = [];

    sellerResetBtn.classList.add("off");

    console.log("삭제할 파일 ID:", fileIdsToDelete);
});

// 환불/교환 정책 이미지 이벤트
itemRefundImagesInput.addEventListener("change", (e) => {
    const files = Array.from(e.target.files);
    const totalCount = refundPreviewContainer.querySelectorAll(".img-box").length;
    const remainingSlots = MAX_DESC_IMAGES - totalCount;

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_DESC_IMAGES}장까지만 업로드 가능합니다. (현재: ${totalCount}장)`);
        itemRefundImagesInput.value = "";
        return;
    }

    itemInfo.itemRefundImages.push(...files);

    files.forEach((img, i) => {
        const reader = new FileReader();
        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box add";
            imgBox.dataset.index = totalCount + i;
            imgBox.innerHTML = `<img src="${e.target.result}" alt="preview-${i}">`;
            refundPreviewContainer.appendChild(imgBox);
        };
        reader.readAsDataURL(img);
    });

    const resetBtn = refundPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
    resetBtn.classList.remove("off");
});

// 환불/교환 정책 이미지 초기화 버튼
const refundResetBtn = refundPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
refundResetBtn.addEventListener("click", (e) => {
    e.preventDefault();

    // 기존 이미지들의 ID를 삭제 목록에 추가
    const existingImages = refundPreviewContainer.querySelectorAll(".img-box:not(.add)");
    existingImages.forEach((imgBox) => {
        const img = imgBox.querySelector("img");
        if (img && img.id) {
            fileIdsToDelete.push(img.id);
        }
    });

    // 모든 이미지 제거
    refundPreviewContainer.innerHTML = "";

    itemRefundImagesInput.value = "";
    itemInfo.itemRefundImages = [];

    refundResetBtn.classList.add("off");

    console.log("삭제할 파일 ID:", fileIdsToDelete);
});

// 이미지들 누르면 원본 크기로 보이게 하기
const imageViewer = document.querySelector(".item-image-viewer");
const viewerBackdrop = document.querySelector(".viewer-backdrop");
const viewerContainer = document.querySelector(".viewer-container");
const viewerCloseBtn = document.querySelector(".viewer-close-btn");

// 모달 열기
function openImageViewer(imageSrc) {
    viewerContainer.innerHTML = `<img src="${imageSrc}" alt="원본 이미지">`;
    imageViewer.classList.remove("off");

    // body 스크롤 방지
    document.body.style.overflow = "hidden";
}

// 모달 닫기
function closeImageViewer() {
    imageViewer.classList.add("off");
    viewerContainer.innerHTML = "";

    // body 스크롤 복원
    document.body.style.overflow = "";
}

// 닫기 버튼 클릭
viewerCloseBtn.addEventListener("click", (e) => {
    e.stopPropagation();
    closeImageViewer();
});

// 배경 클릭 시 닫기
viewerBackdrop.addEventListener("click", (e) => {
    if (e.target === viewerBackdrop) {
        closeImageViewer();
    }
});

// 상품 수정
submitButton.addEventListener("click", (e) => {
    const itemForm = document.querySelector("form[name=itemDTO]");

    const requiredFields = {
        itemName: '상품명',
        itemCategoryId: '카테고리',
        itemContent: '상품 설명',
        itemPrice: '가격',
        itemStock: '재고'
    };

    // 유효성 검사
    for (const [field, label] of Object.entries(requiredFields)) {
        if (!itemInfo[field] || itemInfo[field].toString().trim() === '') {
            alert(`${label}을(를) 입력해주세요.`);
            return;
        }
    }

    // 삭제할 파일 ID들을 hidden input으로 추가
    fileIdsToDelete.forEach((fileId) => {
        const input = document.createElement("input");
        input.setAttribute("type", "hidden");
        input.setAttribute("name", "fileIdsToDelete");
        input.value = fileId;
        itemForm.appendChild(input);
    });

    // 삭제할 옵션 ID들을 hidden input으로 추가
    if (typeof optionIdsToDelete !== 'undefined') {
        optionIdsToDelete.forEach((optionId) => {
            const input = document.createElement("input");
            input.setAttribute("type", "hidden");
            input.setAttribute("name", "optionIdsToDelete");
            input.value = optionId;
            itemForm.appendChild(input);
        });
        console.log("삭제할 옵션 IDs:", optionIdsToDelete);
    }

    console.log("삭제할 파일 IDs:", fileIdsToDelete);
    console.log("최종 옵션 정보:", itemInfo.itemOptions);

    itemForm.submit();
});