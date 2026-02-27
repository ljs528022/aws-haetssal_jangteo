// 헤더 상품 등록버튼
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
const categoryA = document.querySelector(
    ".category-select-dropdown.category-a",
);
const categoryB = document.querySelector(
    ".category-select-dropdown.category-b",
);

// 상품 이미지 프리뷰 부분
const itemImageContainer = document.querySelector(".img-container");

// 상품 리셋 버튼
const itemThumbnailDiv = document.getElementById("item-image");
const imageResetButton = itemThumbnailDiv.querySelector(".reset-btn");

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
    const categoryList = categoryA.querySelectorAll(
        ".each-category-item.item-a",
    );

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

    const categoryList = categoryB.querySelectorAll(
        ".each-category-item.item-b",
    );

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

    // 최대 5장 제한 체크
    const remainingSlots = MAX_ITEM_IMAGES - itemInfo.itemThumbnail.length;
    const addImages = files.slice(0, remainingSlots);

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_ITEM_IMAGES}장 까지만 등록할 수 있습니다.`);
        return;
    }

    itemInfo.itemThumbnail.push(...addImages);
    itemImageContainer.nextElementSibling.classList.remove("off");

    renderImageCard();

    imageResetButton.classList.remove("off");
});

// 상품 이미지 Preview 카드 뿌리기
function renderImageCard() {
    itemImageContainer.innerHTML = "";

    if (itemInfo.itemThumbnail.length === 0) {
        itemImageContainer.nextElementSibling.classList.add("off");
    }

    itemInfo.itemThumbnail.forEach((img, i) => {
        const reader = new FileReader();

        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box";
            imgBox.dataset.index = i;

            imgBox.innerHTML = `
                <img src="${e.target.result}" alt="preview-${i}">
            `;

            itemImageContainer.appendChild(imgBox);
        };

        reader.readAsDataURL(img);
    });
}

// 이미지 초기화 버튼
imageResetButton.addEventListener("click", (e) => {
    e.preventDefault();

    itemImagesInput.value = "";
    itemInfo.itemThumbnail = [];

    renderImageCard();

    imageResetButton.classList.add("off");
})

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

// 공통 이미지 처리 함수
function handleImageUpload(files, targetArray, previewContainer) {
    const remainingSlots = MAX_DESC_IMAGES - targetArray.length;
    const filesToAdd = Array.from(files).slice(0, remainingSlots);

    if (files.length > remainingSlots) {
        alert(`최대 ${MAX_DESC_IMAGES}장까지만 업로드 가능합니다.`);
        return;
    }

    targetArray.push(...filesToAdd);
    renderDescImage(targetArray, previewContainer);
}

// 공통 렌더링 함수
function renderDescImage(imageArray, container) {
    container.innerHTML = "";

    imageArray.forEach((file, i) => {
        const reader = new FileReader();

        reader.onload = (e) => {
            const imgBox = document.createElement("div");
            imgBox.className = "img-box";
            imgBox.dataset.index = i;
            imgBox.style.order = i;

            imgBox.innerHTML = `
                <img src="${e.target.result}" alt="preview-${i}">
            `;

            container.appendChild(imgBox);
        };

        reader.readAsDataURL(file);
    });
}

// 상품 설명 이미지 이벤트
itemDescImagesInput.addEventListener("change", (e) => {
    handleImageUpload(
        e.target.files,
        itemInfo.itemDescImages,
        descPreviewContainer,
    );

    const resetBtn = descPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
    resetBtn.classList.remove("off");

    resetBtn.addEventListener("click", (e) => {
        e.preventDefault();

        itemDescImagesInput.value = "";
        itemInfo.itemDescImages = [];

        renderDescImage(itemInfo.itemDescImages, descPreviewContainer);

        resetBtn.classList.add("off");
    })
});

// 판매자 소개 이미지 이벤트
itemSellerImagesInput.addEventListener("change", (e) => {
    handleImageUpload(
        e.target.files,
        itemInfo.itemSellerImages,
        sellerPreviewContainer,
    );

    const resetBtn = sellerPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
    resetBtn.classList.remove("off");

    resetBtn.addEventListener("click", (e) => {
        e.preventDefault();

        itemSellerImagesInput.value = "";
        itemInfo.itemSellerImages = [];

        renderDescImage(itemInfo.itemSellerImages, sellerPreviewContainer);

        resetBtn.classList.add("off");
    })
});

// 환불/교환 정책 이미지 이벤트
itemRefundImagesInput.addEventListener("change", (e) => {
    handleImageUpload(
        e.target.files,
        itemInfo.itemRefundImages,
        refundPreviewContainer,
    );

    const resetBtn = refundPreviewContainer.previousElementSibling.previousElementSibling.lastElementChild;
    resetBtn.classList.remove("off");

    resetBtn.addEventListener("click", (e) => {
        e.preventDefault();

        itemRefundImagesInput.value = "";
        itemInfo.itemRefundImages = [];

        renderDescImage(itemInfo.itemRefundImages ,refundPreviewContainer);

        resetBtn.classList.add("off");
    })
});

// 이미지들 누르면 원본 크기로 보이게 하기
const imageViewer = document.querySelector(".item-image-viewer");
const viewerBackdrop = document.querySelector(".viewer-backdrop");
const viewerContainer = document.querySelector(".viewer-container");
const viewerCloseBtn = document.querySelector(".viewer-close-btn");

// 해당 이미지 더클클릭하면 원본 이미지 보이기
document.addEventListener("dblclick", (e) => {
    const imgBox = e.target.closest(".img-box");

    if (imgBox) {
        const img = imgBox.querySelector("img");

        if (img && img.src) {
            openImageViewer(img.src);
        }
    }
});

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


// 상품 등록
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

    console.log(itemImagesInput.files);
    console.log(itemDescImagesInput.files);
    console.log(itemSellerImagesInput.files);
    console.log(itemRefundImagesInput.files);

    itemForm.submit();
})