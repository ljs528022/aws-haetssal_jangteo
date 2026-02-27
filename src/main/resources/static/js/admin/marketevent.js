// ################################# 장터 관리 이벤트 #################################

// 무한 스크롤 페이징 처리
let marketPage = 1;
let marketCheckScroll = true;
let marketCriteria = null;

// 검색 필터 요소
const marketKeyword = document.getElementById('market-keyword');
const marketRegion = document.getElementById('market-region');
const marketState = document.getElementById('market-state');

// 초기 로딩
if (marketKeyword) {
    marketCriteria = marketService.getList(marketPage, {
        keyword: marketKeyword.value || '',
        region: marketRegion?.value || '',
        state: marketState?.value || ''
    }, marketLayout.showList);
}

// 무한 스크롤 이벤트
window.addEventListener("scroll", async (e) => {
    if (!marketCheckScroll || !marketCriteria?.hasMore) {
        return;
    }

    const scrollCurrentPosition = window.scrollY;
    const windowHeight = window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;

    // 바닥에 닿았을 때
    if (scrollCurrentPosition + windowHeight >= documentHeight - 1) {
        marketCheckScroll = false;
        marketCriteria = await marketService.getList(++marketPage, {
            keyword: marketKeyword?.value || '',
            region: marketRegion?.value || '',
            state: marketState?.value || ''
        }, marketLayout.showList);
    }

    setTimeout(() => {
        marketCheckScroll = true;
    }, 1000);
});

// 검색 버튼 클릭 이벤트
const marketSearchBtn = document.getElementById('market-search-btn');
if (marketSearchBtn) {
    marketSearchBtn.addEventListener('click', async () => {
        marketPage = 1;
        marketCriteria = await marketService.getList(marketPage, {
            keyword: marketKeyword?.value || '',
            region: marketRegion?.value || '',
            state: marketState?.value || ''
        }, marketLayout.showList);
    });
}

// 장터 수정 버튼 클릭 이벤트
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('market-edit-btn')) {
        const row = e.target.closest('.market-row');
        if (row) {
            const marketId = row.dataset.id;
            // 모달 열기
            openModal('market-edit');
            // 장터 정보 로드
            loadMarketData(marketId);
        }
    }
});

// 장터 정보 로드
async function loadMarketData(marketId) {
    const market = await marketService.getOne(marketId);
    // 모달에 데이터 채우기
    document.getElementById('market-edit-id').value = market.id;
    document.getElementById('market-edit-name').value = market.marketName;
    document.getElementById('market-edit-region').value = market.marketRegion;
    document.getElementById('market-edit-location').value = market.marketLocation;
    // ... 나머지 필드들
}
