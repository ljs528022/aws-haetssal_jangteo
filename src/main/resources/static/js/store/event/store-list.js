const regionBoxes = document.querySelectorAll(".category-each-box");

const orderByBox = document.querySelector(".orderby-box");
const orderValueList = orderByBox.querySelectorAll(".filter-combo");

const ddFilter = document.querySelector(".dropdown-filter");
const filterBtn = document.querySelector(".condition-filter");

const heartButtons = document.querySelectorAll(".like-button");

// 검색한 값 표시하는 text
const searchParamText = document.querySelector(".current-region-market");

// ################# 검색용 값 #################
const region = document.querySelector("input[name=region]");
const categoryId = document.querySelector("input[name=categoryId]");
const marketId = document.querySelector("input[name=marketId]");
const state = document.querySelector("input[name=state]");
const orderValue = document.querySelector("input[name=orderValue]");

// ################# 무한 스크롤 #################
let criteria = {hasMore: true}

// 무한 스크롤 이벤트
let page = 1;
let checkScroll = true;

document.addEventListener("DOMContentLoaded", async (e) => {
    await document.querySelector(".category-each-box.all").click();
})

window.addEventListener("scroll", async (e) => {
    if(!checkScroll || !criteria.hasMore) {
        return;
    }
    // 현재 스크롤 위치
    const scrollCurrentPosition = window.scrollY;
    // 화면 높이
    const windowHeight = window.innerHeight;
    // 문서 높이
    const documentHeight = document.documentElement.scrollHeight;

    // 바닥에 닿았을 때 store 요청
    if(scrollCurrentPosition + windowHeight >= documentHeight - 1) {
        console.log("바닥!");
        checkScroll = false;
        criteria = await storeService.getList(++page,
            {region: region.value, marketId: marketId.value, categoryId: categoryId.value, state: state.value, orderValue: orderValue.value},
            storeLayout.showList);
    }

    setTimeout(() => {
        checkScroll = true;
    }, 1000);
});

// 1. 펼치고 닫고, 
// a를 펼치려고 클릭했을때, b가 펼쳐져있는걸 닫아버림
regionBoxes.forEach((regionBox) => {
    const regionInput = document.querySelector("input[name=region]");
    const marketInput = document.querySelector("inpt[name=marketId]");

    regionBox.addEventListener("click", async (e) => {
        const regionText = regionBox.querySelector(".region-text").innerHTML;

        // 자식과 부모에 둘다 이벤트가 주어졌을때, 자식을 눌렀을때, 부모의 이벤트가 발생하는걸 방지
        if(e.target.closest(".tab-button")) {
            e.stopPropagation(); 
            return;
        }
        let condition = regionBox.classList.contains("clicked");
        regionBoxes.forEach((box) => {
            box.classList.remove("clicked");
        });
        regionBox.classList.toggle("clicked", !condition);
        regionInput.value = regionText;

        // 지역으로 장터 찾는 rest api 호출
        if(regionInput.value == '전체') {
            // 전체를 누르면 모든 검색 값을 초기화 하고 모든 가게를 조회
            region.value = "";
            categoryId.value = "";
            marketId.value = "";
            state.value = "";
            orderValue.value = "";

            searchParamText.innerHTML = "전체 검색"

            // 전체 가게 조회
            page = 1;
            criteria.hasMore = true;
            await storeService.getList(page, {}, storeLayout.showList);
        } else {
            // 그외의 지역을 누르면 장터를 조회해서 뿌리기
            searchParamText.innerHTML = regionText;
            await storeService.getMarkets(regionInput.value, storeLayout.showMarketList);
        }

        // 지역에 따른 장터들 클릭 이벤트
        const marketList = regionBox.querySelectorAll(".dropdown-market");

        marketList.forEach(market => {
            market.addEventListener("click", async (e) => {
                let selectedMarketId = market.getAttribute("value");

                if(market.classList.contains("all-seoul")) {
                    page = 1;
                    marketId.value = "";
                    searchParamText.innerHTML = `${regionText} - ${market.innerHTML}`;
                    await storeService.getList(page,
                        {region: region.value, marketId: marketId.value},
                        storeLayout.showList);
                } else {
                    page = 1;
                    marketId.value = selectedMarketId;
                    console.log("장터 id: " + marketId.value);
                    await storeService.getList(page,
                        {region: region.value, marketId: marketId.value},
                        storeLayout.showList);
                }
                searchParamText.innerHTML = `${regionText} - ${market.innerHTML}`;
            })
        })

    });
});

// 2. 정렬 버튼 이벤트
orderByBox.addEventListener("click", (e) => {
    let condition = orderByBox.classList.contains("clicked");
    orderByBox.classList.toggle("clicked", !condition);
});

// 2-1. 정렬 옵션 선택 시 이벤트
orderValueList.forEach(value => {
    value.addEventListener("click", async (e) => {
        e.preventDefault();

        const orderBtn = document.querySelector(".combobox-opener");
        const orderInput = document.querySelector("input[name=orderValue]");

        let optionValue = value.getAttribute("value");

        orderBtn.firstElementChild.innerHTML = `
            ${value.innerHTML}
            <i class="combobox-arrow"></i>
        `;

        page = 1;
        orderInput.value = optionValue;

        // 정렬 값과 함께 상품 조회
        await storeService.getList(page,
            {region: region.value, marketId: marketId.value, categoryId: categoryId.value, state: state.value, orderValue: orderValue.value},
            storeLayout.showList);
    })
});

// 3. 필터 버튼 이벤트 
ddFilter.addEventListener("click", async (e) => {
    let condition = ddFilter.classList.contains("clicked");
    ddFilter.classList.toggle("clicked", !condition);

    if(!condition) {
        await storeService.getCategories(storeLayout.showCategories);
    }

    // 3-1. 필터 버튼 클릭 시 이벤트
    const categoryBoxes = document.querySelectorAll(".dd-filter-item");
    const ddFilterText = ddFilter.querySelector(".filter-text");
    categoryBoxes.forEach(category => {
        category.addEventListener("click", async (e) => {
            const id = category.getAttribute("value");
            const categoryName = category.innerHTML;

            ddFilterText.innerHTML = categoryName;
            categoryId.value = id;

            await storeService.getList(page, {region: region.value, marketId: marketId.value, categoryId: categoryId.value}, storeLayout.showList);
        })
    })
});

// 영업 중인 가게만 보기 버튼 클릭 이벤트
filterBtn.addEventListener("click", async (e) => {
    let condition = filterBtn.classList.contains("clicked");
    filterBtn.classList.toggle("clicked", !condition);

    if(!condition) {
        state.value = "open";
        await storeService.getList(page,
            {region: region.value, marketId: marketId.value, categoryId: categoryId.value, state: state.value},
            storeLayout.showList);
    } else {
        state.value = "";
        await storeService.getList(page,
            {region: region.value, marketId: marketId.value, categoryId: categoryId.value, state: state.value},
            storeLayout.showList);
    }
});

// 4. 바깥쪽 클릭했을때 펼쳐진거 닫는 이벤트
document.addEventListener("click", (e) => {
    if(!e.target.closest(".tab-button") && !e.target.closest(".category-each-box")) {
        regionBoxes.forEach((regionBox) => {
            regionBox.classList.remove("clicked");
        });
    }
    if(!e.target.closest(".filter-combobox") && !e.target.closest(".orderby-box")) {
        orderByBox.classList.remove("clicked");
    }
    if(!e.target.closest(".dd-filter-box") && !e.target.closest(".dropdown-filter")) {
        ddFilter.classList.remove("clicked");
    }
});

// 4-2. 스크롤 움직일때 펼쳐진거 닫는 이벤트
document.addEventListener("scroll", () => {
    regionBoxes.forEach((box) => box.classList.remove("clicked"));
    orderByBox.classList.remove("clicked");
    ddFilter.classList.remove("clicked");
});

// 모달함수임!
const showModal = (modalMessage) => {
    document.getElementById("content-wrap").innerHTML = modalMessage;
    document.querySelector("div.like-modal").style.animation = "popUp 0.5s";
    document.querySelector("div.modal").style.display = "flex";

    setTimeout(() => {
        document.querySelector("div.like-modal").style.animation =
            "popDown 0.5s";
        setTimeout(() => {
            document.querySelector("div.modal").style.display = "none";
        }, 450);
    }, 1000);
};