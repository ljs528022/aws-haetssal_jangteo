const sideMenuDiv = document.querySelector(".SideBar-Menu");
const sideMenus = sideMenuDiv.querySelectorAll(".SideMenu-Wrapper");
const sideMenuIcons = sideMenuDiv.querySelectorAll(".SideMenu-Icon");

// 홈 부분 대쉬보드 버튼
const manageButtons = document.querySelectorAll(".MainContent-BottomButton");
const reportMngBtn = document.querySelector(".ContentBox-Button");

// 홈, 회원 관리, 가게 관리, 상품 관리, 신고 관리 페이지
const homeDiv = document.querySelector("div[name=home]");
const userMngDiv = document.querySelector("div[name=user]");
const marketMngDiv = document.querySelector("div[name=market]");
const itemMngDiv = document.querySelector("div[name=item]");
const reportMngDiv = document.querySelector("div[name=report]");

// 모든 페이지 모아놓기
const allSection = [
    homeDiv,
    userMngDiv,
    marketMngDiv,
    itemMngDiv,
    reportMngDiv,
];

// 검색용 키워드
let keyword = "";

// ---- TODO ----
// 각 페이지의 목록 요소들은 서버에서 받아와서 뿌려야함
// 로직은 여기에 작성

// 각 페이지의 상단 검색바
const searchBars = document.querySelectorAll(".DashBoard-HeaderContent");

sideMenus.forEach((menu) => {
    menu.addEventListener("click", (e) => {
        sideMenuIcons.forEach((i) => i.classList.remove("current"));
        menu.firstElementChild.classList.add("current");

        allSection.forEach((a) => a.classList.add("off"));
        let menuName = menu.getAttribute("name");

        switch (menuName) {
            case "homeMenu":
                homeDiv.classList.remove("off");
                break;
            case "userMenu":
                userMngDiv.classList.remove("off");
                break;
            case "marketMenu":
                marketMngDiv.classList.remove("off");
                break;
            case "itemMenu":
                itemMngDiv.classList.remove("off");
                break;
            case "reportMenu":
                reportMngDiv.classList.remove("off");
                break;
        }
    });

    clickAnimation(menu);
});

// 홈 부분의 관리 페이지 이동 버튼들
manageButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
        const wrapper = button.closest(".MainContent-Wrapper");
        const wrapperName = wrapper.getAttribute("name");

        switch (wrapperName) {
            case "userPreview":
                document.querySelector("div[name=userMenu]").click();
                break;
            case "marketPreview":
                document.querySelector("div[name=marketMenu]").click();
                break;
            case "itemPreview":
                document.querySelector("div[name=itemMenu]").click();
                break;
        }
    });
});

// 신고 관리 페이지로 이동
reportMngBtn.addEventListener("click", (e) => {
    document.querySelector("div[name=reportMenu]").click();
});

// 각 페이지 별 상단바 기능
searchBars.forEach((search) => {
    // 검색바 요소 선언 input, select들
    const searchInput = search.querySelector(".SeacrchPart-InputWrapper");
    const searchSelects = search.querySelectorAll(".SeacrchPart-SelectWrapper");
    const selectOptions = search.querySelectorAll(".SeacrchPart-SelectOption");

    selectOptions.forEach((s) => s.classList.add("off"));

    // 검색바 검색 기능
    searchInput?.addEventListener("keyup", (e) => {
        // 값이 입력된 곳의 페이지 구분
        const searchTarget = search
            .closest(".DashBoard-Wrapper")
            .getAttribute("name");

        keyword = e.target.value;

        // 여기에 검색 메소드 구현 (필요한 값: 검색페이지 구분값, 키워드)
    });

    // 각 드롭박스 선택 기능
    searchSelects.forEach((select) => {
        const selectedValue = select.querySelector(".SeacrchPart-Value");

        select.addEventListener("click", (e) => {
            e.preventDefault();

            const optionList = select.lastElementChild;
            const values = select.querySelectorAll(".SelectBody-OptionValue");
            const selectAllBtn = select.querySelector(".SelectBody-Buttons");

            optionList.classList.toggle("off");

            values.forEach((value) => {
                value.addEventListener("click", (e) => {
                    e.preventDefault();

                    // 여기에 검색 로직 구현

                    const optionValue = value.firstElementChild.innerHTML;
                    selectedValue.innerHTML = optionValue;
                });
            });

            selectAllBtn.addEventListener("click", (e) => {
                e.preventDefault();

                // 여기에 전체 검색 로직 구현

                selectedValue.innerHTML = "전체";
            });
        });
    });
});

// 불러온 정보 목록 div
const userListDiv = document.querySelector("div[name=user-list]");
const marketListDiv = document.querySelector("div[name=market-list]");
const itemListDiv = document.querySelector("div[name=item-list]");

// 신고 내역 부분
const marketApply = reportMngDiv.querySelector("div[name=report-market]");
const sellerApply = reportMngDiv.querySelector("div[name=report-seller]");
const reportList = reportMngDiv.querySelector("div[name=report-list]");

// 정보 수정 모달
const userEditModal = document.querySelector("div[name=user-edit]");
const marketEditModal = document.querySelector("div[name=market-edit]");
const itemEditModal = document.querySelector("div[name=item-edit]");
const reportEditModal = document.querySelector("div[name=report-edit]");

const allListSection = [userListDiv, marketListDiv, itemListDiv];
const editModals = [userEditModal, marketEditModal, itemEditModal];
const reportContents = [marketApply, sellerApply, reportList];

// 누른 목록에 종류에 따라 해당 모달 활성화
allListSection.forEach((listItem) => {
    const listName = listItem.getAttribute("name");
    const listItems = listItem.querySelectorAll(".MainContent-ItemList");
    const listCount = listItem.querySelector(".MainContent-CountText span");

    listCount.innerHTML = listItems.length;

    listItems.forEach((item) => {
        item.addEventListener("click", (e) => {
            switch (listName) {
                case "user-list":
                    userEditModal.classList.remove("off");
                    break;
                case "market-list":
                    marketEditModal.classList.remove("off");
                    break;
                case "item-list":
                    itemEditModal.classList.remove("off");
                    break;
            }
        });

        // 클릭 애니메이션
        clickAnimation(item);
    });
});

// 수정 모달 기능
editModals.forEach((modal) => {
    const modalBackdrop = modal.querySelector(".EditModal-Wrapper");
    const editButton = modal.querySelector(".EditModal-Button.Edit");
    const saveButton = modal.querySelector(".EditModal-Button.Save");
    const closeButton = modal.querySelector(".EditModal-CloseButton");
    const editElements = modal.querySelectorAll(
        ".EditModal-Input.disabled, .EditModal-Select.disabled",
    );

    editButton?.addEventListener("click", (e) => {
        editElements.forEach((element) => {
            element.classList.toggle("disabled");
            element.disabled = false;
        });
    });

    saveButton.addEventListener("click", (e) => {
        e.preventDefault();

        let result = confirm("수정한 내용을 저장 하시겠습니까?");

        if (result) {
            // 내용 수정 요청 로직 작성 필요

            // 성공 시 출력
            alert("수정한 내용이 저장 되었습니다.");

            editElements.forEach((element) => {
                element.classList.add("disabled");
                element.disabled = true;
            });
            modal.classList.add("off");
        }
    });

    closeButton.addEventListener("click", (e) => {
        editElements.forEach((element) => {
            element.classList.add("disabled");
            element.disabled = true;
        });

        modal.classList.add("off");
    });

    modalBackdrop.addEventListener("click", (e) => {
        if (e.target === modalBackdrop) {
            editElements.forEach((element) => {
                element.classList.add("disabled");
                element.disabled = true;
            });

            modal.classList.add("off");
        }
    });
});

// 신고 관리 페이지 기능
reportContents.forEach((reportContent, i) => {
    const contentName = reportContent.getAttribute("name");
    const contentItems = reportContent.querySelectorAll(
        ".ReportContent-CardWrapper, .MainContent-ItemList",
    );
    const reportContentDiv =
        reportEditModal.querySelector(".EditModal-Content");
    const reportTextarea = document.getElementById("report-content");

    // ---- TODO ----
    // 가져온 신고 정보에 분류 값이 '판매자 등록' 나 '가게 등록'이면 신고 내용 off
    // '일반 신고' 면 승인, 거부 버튼 off
    const reportTextareaDiv = reportTextarea.closest(".EditModal-SingleInput");
    const reportButtons = reportContentDiv.lastElementChild;

    contentItems.forEach((item) => {
        if (item.classList[0] === "ReportContent-CardWrapper") {
            const itemButton = item.querySelector(".ReportContent-Button");

            itemButton.addEventListener("click", (e) => {
                reportEditModal.classList.remove("off");
            });

            clickAnimation(itemButton);
        } else {
            item.addEventListener("click", (e) => {
                reportEditModal.classList.remove("off");
            });

            clickAnimation(item);
        }
    });
});

// 신고 조회 버튼
const deniedButton = reportEditModal.querySelector(".EditModal-Button.Denied");
const applyButton = reportEditModal.querySelector(".EditModal-Button.Apply");
const editElements = reportEditModal.querySelectorAll(
    ".EditModal-Input.disabled, .EditModal-Select.disabled",
);
const reportModalBackdrop = reportEditModal.querySelector(".EditModal-Wrapper");

deniedButton.addEventListener("click", (e) => {
    let result = confirm("해당 등록 요청을 거부합니까?");

    if (result) {
        // 해당 신고 상태를 "거부"로 변경하는 요청

        // 성공하면 alert
        alert("등록 요청이 거부 되었습니다.");
        reportEditModal.classList.add("off");
    }
});

applyButton.addEventListener("click", (e) => {
    let result = confirm("해당 등록 요청을 승인합니까?");

    if (result) {
        // 해당 신고 상태를 "완료"로 변경하는 요청

        // 성공하면 alert
        alert("등록 요청이 승인 되었습니다.");
        reportEditModal.classList.add("off");
    }
});

reportModalBackdrop.addEventListener("click", (e) => {
    if (e.target === reportModalBackdrop) {
        editElements.forEach((element) => {
            element.classList.add("disabled");
            element.disabled = true;
        });

        reportEditModal.classList.add("off");
    }
});

// 클릭 시 크기 변하는 애니메이션
function clickAnimation(item) {
    item.addEventListener("mousedown", () => {
        item.style.transform = "scale(0.96)";
    });
    item.addEventListener("mouseup", () => {
        item.style.transform = "scale(1)";
    });
    item.addEventListener("mouseleave", () => {
        item.style.transform = "scale(1)";
    });
}

document.querySelector("div[name=homeMenu]").click();
