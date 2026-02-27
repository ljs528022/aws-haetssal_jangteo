// 페이지가 로딩 될 시, 상품 뿌리기
document.addEventListener("DOMContentLoaded", (e) => {
    fetchItems();

    // 찜 버튼
    const likeButtons = document.querySelectorAll(".like-Button");

    // 모달 부분
    const modal = document.querySelector(".Modal-Container");

    // 찜 버튼 누르기 기능
    likeButtons.forEach((like) => {
        like.addEventListener("click", (e) => {
            e.preventDefault();

            // DB에서 찜 관련 값 받아오기
            like.classList.toggle("is-active");

            const isLiked = like.classList.contains("is-active");

            // 찜 추가 / 해제 로직
            if (!isLiked) {
                like.firstElementChild.classList.add("off");
                like.lastElementChild.classList.remove("off");

                // 추가 모달 띄우기
                modal.innerHTML = "해당 상품을 찜 했습니다.";
                modal.classList.add("show");

                setTimeout(() => {
                    modal.classList.remove("show");
                }, 3000);

                // 찜 추가 로직 넣어야 함
                addLike();
            } else {
                like.firstElementChild.classList.remove("off");
                like.lastElementChild.classList.add("off");

                // 해제 모달 띄우기
                modal.innerHTML = "해당 상품의 찜을 해제했습니다.";
                modal.classList.add("show");

                setTimeout(() => {
                    modal.classList.remove("show");
                }, 3000);

                // 찜 해제 로직 넣어야 함
                deleteLike();
            }
        });
    });
});

// 연관 검색어 버튼 기능
// 누르면 해당 검색어로 검색
const searchChips = document.querySelectorAll(".SearchRelation-Chip");

searchChips.forEach((chip) => {
    chip.addEventListener("click", (e) => {
        // DB에 해당 검색어 조회 로직
    });
});

// 임박 마감 버튼 누르면 스타일 변경
const clearanceBtn = document.querySelector(".Filter-Option.Clearance");
clearanceBtn.addEventListener("click", (e) => {
    clearanceBtn.classList.toggle("isClicked");
});

// 드롭다운 버튼 부분 -----------------------------
// 드롭다운 버튼 클릭 시, 해당 드로다운 메뉴 표시 토글
const dropDownBtns = document.querySelectorAll(".Trigger-Button");
const dropDownMenus = document.querySelectorAll(".DropDown-Container");
const dropDownTexts = document.querySelectorAll(".Trigger-Text");
const dropDownArrows = document.querySelectorAll(".Trigger-Arrow svg");

dropDownBtns.forEach((button, i) => {
    const dropDownOptions =
        dropDownMenus[i].querySelectorAll(".DropDown-Button");

    button.addEventListener("click", (e) => {
        // 눌린 버튼을 제외한 다른 메뉴 비활성화
        dropDownBtns.forEach((otherButton, j) => {
            if (i !== j) {
                dropDownArrows[j].classList.remove("isChecked");
                dropDownMenus[j].classList.remove("clicked");
            }
        });

        dropDownArrows[i].classList.toggle("isChecked");
        dropDownArrows[i].style.transition = `transform 0.5s`;
        dropDownMenus[i].classList.toggle("clicked");
    });

    dropDownOptions.forEach((option) => {
        option.addEventListener("click", (e) => {
            let selectedOption = option.innerHTML;
            let marketDropDown = document.querySelector("button[name=market]");
            let detailCate = document.querySelector("button[name=c-category]");

            // 지역을 선택했을 경우, 장터 카테고리 활성화
            if (button.name === "region") {
                marketDropDown.style.display = "flex";
            } else if (button.name === "p-category") {
                // 대분류 카테고리를 눌렀을 경우, 소분류 카테고리 활성화
                detailCate.style.display = "flex";
            }

            // 지역이 '전체 지역'이면 장터 카테고리 비활성화
            dropDownTexts[i].innerHTML = selectedOption;
            if (dropDownTexts[i].innerHTML === "전체 지역") {
                marketDropDown.style.display = "none";
                marketDropDown.nextElementSibling.classList.remove("clicked");
            } else if (dropDownTexts[i].innerHTML === "전체 보기") {
                detailCate.style.display = "none";
                detailCate.nextElementSibling.classList.remove("clicked");
            }

            dropDownArrows[i].classList.remove("isChecked");
            dropDownArrows[i].style.transition = `transform 0.5s`;
            dropDownMenus[i].classList.remove("clicked");
        });
    });
});

// ----- 찜 버튼 부분 ------------------------
// 찜 버튼
const likeButtons = document.querySelectorAll(".like-Button");

// 찜 버튼 누르기 기능
likeButtons.forEach((like) => {
    like.addEventListener("click", (e) => {
        console.log("snffla!");
        // DB에서 찜 관련 값 받아오기

        const isLiked = e.currentTarget.classList.contains("is-active");

        // 찜 추가 / 해제 로직
        if (!isLiked) {
            // 찜 추가 로직 넣어야 함
            addLike();
        } else {
            // 찜 해제 로직 넣어야 함
            deleteLike();
        }
    });
});
// -----------------------------------------

// 무한 스크롤 부분 -------------------------
// 일정 수준 스크롤이 내려가면 상품 더 가져오기
const getMoreItems = async () => {};

// -----------------------------------------

// 페이지를 클릭, 스크롤 하면 드롭다운 비활성화
document.addEventListener("scroll", (e) => {
    dropDownMenus.forEach((menu) => {
        menu.classList.remove("clicked");
    });
});

// --------------------------------------
// 상품 뿌리기
const fetchItems = () => {
    const itemWrapper = document.querySelector(".SearchResult-Wrapper");
    const itemCount = itemWrapper.firstElementChild.firstElementChild;
    const itemContainer = itemCount.nextElementSibling;

    // 상품 받아오는 메소드 사용
    // let items = await getItems();
    // items.forEach((item) => {
    //     itemContainer.innerHTML += itemCardLayout(item);
    // });

    // test용
    let count = 120;
    for (let i = 0; i < count; i++) {
        itemContainer.innerHTML += itemCardLayout();
    }
    for (let i = 0; i < 12; i++) {
        itemContainer.innerHTML += skeletonCardLayout();
    }
};

// 검색 결과 상품 가져오기
const getItems = async () => {};

// 찜 상품들 가져오기
const getLikes = async () => {};

// 찜 상품 추가
const addLike = async () => {};

// 찜 상품 해제
const deleteLike = async () => {};

// 상품 레이아웃
const itemCardLayout = (item) => {
    let itemLayout = `
    <div class="InfinityScroll-ItemCardWrapper">
        <div class="ItemCard-Wrapper">
            <a href="" class="ItemCard-Container">
                <div class="ItemCard-ImageWrapper">
                    <img class="ItemCard-Image" src="${item?.image ? "" : "../../static/images/TempItem-Image.png"}" alt="">
                    <button type="button" class="like-Button">
                        <!-- 누르면 찜 등록하면서 색깔 변환 -->
                        <!-- 빈하트 -->
                        <svg class="like-empty" width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M19.463 15.3087L19.9993 15.7933L20.5356 15.3087L22.2643 13.747C22.2643 13.747 22.2643 13.7469 22.2643 13.7469C23.615 12.5269 25.9852 12.7532 27.3097 14.2118L27.3156 14.2182L27.3216 14.2246C28.9912 15.9843 29.0145 19.0158 27.2167 20.9218L19.9995 27.9864L12.7818 20.9218C10.9839 19.0158 11.0075 15.984 12.6769 14.2246L12.6829 14.2182L12.6888 14.2118C14.0133 12.7532 16.3836 12.5269 17.7343 13.7469C17.7343 13.7469 17.7343 13.7469 17.7344 13.747L19.463 15.3087Z" fill="black" fill-opacity="0.25" stroke="white" stroke-width="1.6"></path><g filter="url(#filter0_dd_909_4706012)"><path fill-rule="evenodd" clip-rule="evenodd" d="M19.9992 17.111L17.0789 14.4725C17.0789 14.4725 17.079 14.4726 17.0789 14.4725C16.1903 13.67 14.4337 13.7447 13.4126 14.8691L13.3995 14.8835L13.3861 14.8976C12.0903 16.2633 12.039 18.6949 13.4805 20.2374L19.9994 26.6181L26.518 20.2374C27.9595 18.6949 27.9084 16.2637 26.6123 14.8976L26.5989 14.8835L26.5859 14.8691C25.5648 13.7447 23.8085 13.6699 22.9198 14.4725C22.9198 14.4725 22.9198 14.4724 22.9198 14.4725L19.9992 17.111ZM21.728 13.1533C23.4567 11.5918 26.3291 11.942 27.902 13.6739C29.8776 15.7563 29.8775 19.2796 27.7872 21.4827L19.9995 29.1058L12.2112 21.4827C10.1209 19.2796 10.121 15.7559 12.0965 13.6739C13.6694 11.942 16.5419 11.5917 18.2706 13.1533L19.9993 14.7151L21.728 13.1533Z" fill="white"></path></g><defs><filter id="filter0_dd_909_4706012" x="8.62891" y="10.1571" width="22.7422" height="20.9487" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB"><feFlood flood-opacity="0" result="BackgroundImageFix"></feFlood><feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="1"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"></feColorMatrix><feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_909_4706012"></feBlend><feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="0.5"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.2 0"></feColorMatrix><feBlend mode="normal" in2="effect1_dropShadow_909_4706012" result="effect2_dropShadow_909_4706012"></feBlend><feBlend mode="normal" in="SourceGraphic" in2="effect2_dropShadow_909_4706012" result="shape"></feBlend></filter></defs></svg>
                        <!-- 찬 하트 -->
                        <svg class="like-fill off" width="40" height="40" viewBox="0 0 40 40" fill="none" xmlns="http://www.w3.org/2000/svg"><g filter="url(#filter0_dd_909_4706251)"><path fill-rule="evenodd" clip-rule="evenodd" d="M19.9993 14.7154L18.2706 13.1536C16.5419 11.592 13.6694 11.9423 12.0965 13.6742C10.121 15.7562 10.1209 19.2799 12.2112 21.483L19.9995 29.1061L27.7872 21.483C29.8775 19.2799 29.8776 15.7566 27.902 13.6742C26.3291 11.9423 23.4567 11.5921 21.728 13.1536L19.9993 14.7154Z" fill="#F86453"></path></g><defs><filter id="filter0_dd_909_4706251" x="-2" y="-2" width="44" height="44" filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB"><feFlood flood-opacity="0" result="BackgroundImageFix"></feFlood><feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="1"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"></feColorMatrix><feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_909_4706251"></feBlend><feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0" result="hardAlpha"></feColorMatrix><feOffset></feOffset><feGaussianBlur stdDeviation="0.5"></feGaussianBlur><feComposite in2="hardAlpha" operator="out"></feComposite><feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.2 0"></feColorMatrix><feBlend mode="normal" in2="effect1_dropShadow_909_4706251" result="effect2_dropShadow_909_4706251"></feBlend><feBlend mode="normal" in="SourceGraphic" in2="effect2_dropShadow_909_4706251" result="shape"></feBlend></filter></defs></svg>
                    </button>
                </div>
                <div class="ItemCard-Content">
                    <div class="ItemCard-ItemInfo">
                        <div class="ItemCard-MainContent">
                            <!-- 장터, 가게 이름 -->
                            <div class="ItemCard-MarketInfo">${"장터 이름"}</div>
                            <div class="ItemCard-SellerInfo">${"가게 이름"}</div>
                        </div>
                        <div class="ItemCard-InfoWrapper">
                            <!-- 상품 제목, 설명 -->
                            <div class="ItemCard-Title">여기에 상품 제목이 들어갑니다.</div>
                            <div class="ItemCard-Description">여기엔 상품의 간단한 설명이 들어갑니다.</div>
                        </div>
                        <div class="ItemCard-BadgeWrapper">
                            <!-- 수령 방법 -->
                            <div class="Info-badge delivery">
                                무료배송
                            </div>
                        </div>
                        <div class="ItemCard-PriceInfo">
                            <!-- 가격 -->
                            <div class="ItemCard-Price">10000 원</div>
                        </div>
                        <div class="ItemCard-UnderBar"></div>
                    </div>
                </div>
            </a>
        </div>
    </div>
    `;

    return itemLayout;
};

// 임시 상품 레이아웃
const skeletonCardLayout = () => {
    let skeletonCard = `
    <div class="InfinityScroll-ItemCardWrapper">
        <div class="SkeletonCard-Wrapper">
            <div class="SkeletonCard-Image gray"></div>
            <div class="SkeletionCard-Content">
                <div class="SkeletionCard-MarketInfo gray"></div>
                <div class="SkeletionCard-Title gray"></div>
                <div class="SkeletionCard-Percent gray"></div>
            </div>
        </div>
    </div>
    `;

    return skeletonCard;
};
