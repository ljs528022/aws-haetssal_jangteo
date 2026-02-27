// 1. class=user-input 의 변수들
const marketInputs = document.querySelectorAll(".user-input");
// form-wrap 은(인풋감싼 눈에보이는거, border변화 줄거임)
const marketInputForm = document.querySelectorAll(".form-wrap");
// p태그(박스밑에 메세지뜨는거)
const marketInputMessage = document.querySelectorAll(".input-guide-message");
// 0/20 이런거 표시됨
const marketInputLength = document.querySelectorAll(".input-count-length");

// 2. 지역드롭다운 이벤트 변수들
const regionBoxes = document.querySelectorAll(".region-select-box");
const regionDdBoxes = document.querySelectorAll(".region-select-dropdown");

const firstRegionItems = document.querySelectorAll(".item-a");

const regionSpans = document.querySelectorAll(".region-input-wrapper");

// 3. 주판매 카테고리 드롭다운 이벤트
const categoryBox = document.querySelector(".category-select-box");

// 3-1. 장터 선택 드롭다운 이벤트
const marketIdContainer = document.getElementById("marketIdContainer");
const marketSelectBox = document.querySelector(".market-select");
const marketItems = document.querySelectorAll(".market-id");

// 4. 이미지(제품썸네일) 업로드 버튼
const uploadBtn = document.querySelector(".upload-btn");

// 5. 등록버튼 눌렀을때 이벤트
const registrationButtons = document.querySelectorAll(".registration-button");

// 6. 사진등록하는 이벤트
const imageInput = document.getElementById("imageUpload");
const thumbnail = document.querySelector(".profile-image-uploaded");

// 7. 다 쓰고나서 화면 우측 상단에 입점신청
const finalRegiBtn = document.querySelector(".market-application-btn");

// 8. 본인인증 관련
// const startCheck = document.querySelector(".info-check-btn");


function giveRightStyle(inputForm, inputMessage, inputLength) {
    inputForm.style.border = "1px solid rgb(13, 13, 13)";
    inputMessage.style.display = "none";
    inputLength.style.color = "rgb(84, 84, 84)";
}

function giveWrongStyle(inputForm, inputMessage, inputLength) {
    inputForm.style.border = "1px solid rgb(229, 60, 65)";
    inputMessage.style.display = "block";
    inputLength.style.color = "rgb(229, 60, 65)";
}

marketInputs.forEach((marketInput, i) => {
    // 1-1. 상점명 입력/소개 입력 이벤트
    // 값 있으면: border도 검정으로, 메시지 숨기고 span 검정으로 n/20
    marketInput.addEventListener("input", (e) => {
        if(i === 0) {
            marketInputLength[i].textContent = `${marketInput.value.length}/20`;
        } else if(i === 1) {
            marketInputLength[i].textContent = `${marketInput.value.length}/300`;
        }

        if(i === 0) {
            if (e.target.value) {
                giveRightStyle(marketInputForm[i], marketInputMessage[i], marketInputLength[i]);
            } else {
                giveWrongStyle(marketInputForm[i], marketInputMessage[i], marketInputLength[i]);
            }
        } else if(i === 1) {
            if(e.target.value.length >= 10){
                giveRightStyle(marketInputForm[i], marketInputMessage[i], marketInputLength[i]);
            } else {
                giveWrongStyle(marketInputForm[i], marketInputMessage[i], marketInputLength[i]);
            }
        }
    });

    // 1-2. 상점명 입력/소개 입력 이벤트
    // 인풋태그 벗어나면 테두리 변화
    marketInput.addEventListener("blur", (e) => {
        if (i === 0) {
            if(e.target.value) {
                marketInputForm[i].style.border = "1px solid rgb(228, 228, 228)";
            } else {
                marketInputForm[i].style.border = "1px solid rgb(229, 60, 65)";
            }
        } else if(i === 1) {
            if(e.target.value.length >= 10) {
                marketInputForm[i].style.border = "1px solid rgb(228, 228, 228)";
            } else {
                marketInputForm[i].style.border = "1px solid rgb(229, 60, 65)";
            }
        }
    });
});


// 2-1. 펼치고 닫고,
regionBoxes.forEach((regionBox) => {
    regionBox.addEventListener("click", (e) => {
        // 자식과 부모에 둘다 이벤트가 주어졌을때, 자식을 눌렀을때, 부모의 이벤트가 발생하는걸 방지
        if(e.target.closest(".region-select-dropdown")) {
            e.stopPropagation();
            return;
        }
        let condition = regionBox.classList.contains("clicked");
        regionBoxes.forEach((box) => {
            box.classList.remove("clicked");
        });
        regionBox.classList.toggle("clicked", !condition);
    });
});

// 2-2. 첫번째 지역 드롭다운에서 요소 선택했을때
firstRegionItems.forEach((item) => {
    item.addEventListener("click", async (e) => {
        // 사용자가 선택한 요소내용 담기
        const inputBox = regionBoxes[0].querySelector("input[type='text']");
        const hiddenInput = document.querySelector("input[name='storeAddress']");
        const text = item.textContent;
        inputBox.value = text;

        // 서버로 보낼 진짜 input에 값 적용
        if(hiddenInput) {
            hiddenInput.value = text;
        }

        // 박스닫아야하니까 clicked 떼고
        regionBoxes[0].classList.remove("clicked");
        // 첫번째 dd 선택했으면 2차지역 선택가능하게 disabled 떼버리고
        if(regionBoxes[1]) {
            regionBoxes[1].classList.remove("disabled");
        }
        // 요소클릭(선택)했으면 selected줘서 테두리 스타일 변경되게끔 하고
        regionSpans[0].classList.add("selected");

        // 첫번째 지역 선택시 marketIdContainer 활성화
        if(marketIdContainer) {
            await storeService.getMarkets(text, storeLayout.showMarketDropdown);
            marketIdContainer.classList.remove("off");
        }
    });
});

// 2-3. 두번째 지역 input에서 입력했을때 hidden input 업데이트
const secondRegionInput = document.querySelector("input[name='store-detail-address']");
if(secondRegionInput) {
    secondRegionInput.addEventListener("input", (e) => {
        const firstRegionValue = regionBoxes[0].querySelector("input[type='text']").value;
        const secondRegionValue = e.target.value;
        const hiddenInput = document.querySelector("input[name='storeAddress']");

        // 모든 값이 있으면 해당 값으로 적용
        if(hiddenInput && firstRegionValue && secondRegionValue) {
            // "시/도 시/군/구" 형태로 저장
            hiddenInput.value = `${firstRegionValue} ${secondRegionValue}`;
        }

        // 값이 입력되면 selected 클래스 추가
        if(secondRegionValue) {
            regionSpans[1].classList.add("selected");
        } else {
            regionSpans[1].classList.remove("selected");
        }
    });
}

// 3-1. 주판매 카테고리 드롭다운 이벤트
categoryBox.addEventListener("click", async (e) => {
    if(e.target.closest(".category-select-dropdown")) {
        e.stopPropagation();
        return;
    }

    await storeService.getCategories(storeLayout.showCategories);

    let condition = categoryBox.classList.contains("clicked");
    categoryBox.classList.toggle("clicked", !condition);

    const categoryItems = document.querySelectorAll(".item-category");
    const categorySpan = document.querySelector(".category-input-wrapper");

    // 3-2. 주판매 드롭다운에서 요소 선택했을때
    categoryItems.forEach((item) => {
        item.addEventListener("click", (e) => {
            // 사용자가 선택한 요소내용 담기
            const inputBox = categoryBox.querySelector("input[type='text']");
            const hiddenInput = document.getElementById("storeCategoryId");
            const text = item.textContent;
            const value = item.getAttribute("value");

            inputBox.value = text;

            // 서버로 보낼 진짜 input에 value 설정
            if(hiddenInput && value) {
                hiddenInput.value = value;
            }

            // 박스닫아야하니까 clicked 떼고
            categoryBox.classList.remove("clicked");
            // 요소클릭(선택)했으면 selected줘서 테두리 스타일 변경되게끔 하고
            categorySpan.classList.add("selected");
        });
    });
});

// 3-3. 장터 선택 드롭다운 이벤트
if(marketSelectBox) {
    const marketIdInputBox = marketSelectBox.closest(".category-select-box");

    if(marketIdInputBox) {
        marketIdInputBox.addEventListener("click", (e) => {
            if(e.target.closest(".category-select-dropdown")) {
                e.stopPropagation();
                return;
            }
            let condition = marketIdInputBox.classList.contains("clicked");
            marketIdInputBox.classList.toggle("clicked", !condition);
        });
    }

    // 장터 항목 선택 이벤트
    marketSelectBox.addEventListener("click", (e) => {
        const item = e.target.closest(".market-id");
        if(!item) return;

        const inputBox = marketIdInputBox.querySelector("input[type='text']");
        const hiddenInput = document.getElementById("storeMarketId");
        const text = item.textContent.trim();
        const value = item.getAttribute("value");

        inputBox.value = text;

        // 서버로 값을 보낼 input에 value 설정
        if(hiddenInput && value) {
            hiddenInput.value = value;
        }

        // 박스 닫기
        marketIdInputBox.classList.remove("clicked");

        // selected 클래스 추가
        const marketSpan = marketIdInputBox.querySelector(".category-input-wrapper");
        if(marketSpan) {
            marketSpan.classList.add("selected");
        }

        console.log(hiddenInput.value);
    });
}

// 4. 바깥쪽 클릭했을때 펼쳐진거 닫는 이벤트
document.addEventListener("click", (e) => {
    // 카테고리 박스 닫기
    if(!e.target.closest(".category-select-dropdown") && !e.target.closest(".category-select-box")) {
        const allCategoryBoxes = document.querySelectorAll(".category-select-box");
        allCategoryBoxes.forEach(box => box.classList.remove("clicked"));
    }

    // 지역 박스 닫기
    if(!e.target.closest(".region-select-dropdown") && !e.target.closest(".region-select-box")) {
        regionBoxes.forEach((regionBox) => {
            regionBox.classList.remove("clicked");
        });
    }
});

// 5. 등록버튼들
registrationButtons.forEach((eachButton) => {
    clickChangeColor(eachButton);
});

function clickChangeColor(button) {
    button.addEventListener("mousedown", (e) => {
        button.style.background = "rgb(244, 244, 244)";
    });

    button.addEventListener("mouseup", (e) => {
        button.style.background = "white";
    });

    button.addEventListener("mouseleave", (e) => {
        button.style.background = "white";
    });
}

// 6. 사진 등록하기
imageInput.addEventListener("change", (e) => {
    const [file] = e.target.files;

    if(!file) return;

    const reader = new FileReader();

    reader.readAsDataURL(file);
    reader.addEventListener("load", (e) => {
        const path = e.target.result;

        if(path.includes("image")) {
            thumbnail.style.backgroundImage = `url(${path})`;
        }
    });
});

// 7. 입력, 등록란들이랑 본인인증 다 끝내기 전,후 알러트
finalRegiBtn.addEventListener("click", (e) => {
    e.preventDefault(); // form 제출 방지

    // 본인인증 버튼 확인 (store-create-info-check.js에서 정의될 것으로 예상)
    const startCheck = document.querySelector(".info-check-btn");

    // 모든 필수 항목 확인
    const isStoreNameValid = marketInputs[0] && marketInputs[0].value.trim() !== '';
    const isStoreIntroValid = marketInputs[1] && marketInputs[1].value.length >= 10;
    const isImageUploaded = thumbnail && thumbnail.style.backgroundImage !== '';
    const isRegionSelected = regionSpans[1] && regionSpans[1].classList.contains("selected");
    const isCategorySelected = categoryBox.firstElementChild && categoryBox.firstElementChild.classList.contains("selected");
    const isMarketSelected = document.getElementById("storeMarketId") &&
        document.getElementById("storeMarketId").value !== '';
    const isAuthCompleted = startCheck && startCheck.classList.contains("disabled");

    if(isStoreNameValid && isStoreIntroValid && isImageUploaded &&
        isRegionSelected && isCategorySelected && isMarketSelected && isAuthCompleted) {
        // 모든 조건이 충족되면 form 제출
        document.querySelector("form").submit();
    } else {
        let missingFields = [];
        if(!isStoreNameValid) missingFields.push("가게명");
        if(!isStoreIntroValid) missingFields.push("가게 소개(최소 10자)");
        if(!isImageUploaded) missingFields.push("가게 프로필 이미지");
        if(!isRegionSelected) missingFields.push("지역");
        if(!isCategorySelected) missingFields.push("주판매 카테고리");
        if(!isMarketSelected) missingFields.push("장터 선택");
        if(!isAuthCompleted) missingFields.push("본인 인증");

        alert(`다음 항목을 완료해주세요:\n${missingFields.join(", ")}`);
    }
});