// ################################# 상품 관리 이벤트 #################################

// 무한 스크롤 페이징 처리
let itemPage = 1;
let itemCheckScroll = true;
let itemCriteria = null;

// 검색 필터 요소
const itemKeyword = document.getElementById('item-keyword');
const itemType = document.getElementById('item-type');
const itemCategory = document.getElementById('item-category');
const itemState = document.getElementById('item-state');

// 초기 로딩
if (itemKeyword && itemType) {
    itemCriteria = itemService.getList(itemPage, {
        keyword: itemKeyword.value || '',
        type: itemType.value || '',
        category: itemCategory?.value || '',
        state: itemState?.value || ''
    }, itemLayout.showList);
}

// 무한 스크롤 이벤트
window.addEventListener("scroll", async (e) => {
    if (!itemCheckScroll || !itemCriteria?.hasMore) {
        return;
    }

    const scrollCurrentPosition = window.scrollY;
    const windowHeight = window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;

    // 바닥에 닿았을 때
    if (scrollCurrentPosition + windowHeight >= documentHeight - 1) {
        itemCheckScroll = false;
        itemCriteria = await itemService.getList(++itemPage, {
            keyword: itemKeyword?.value || '',
            type: itemType?.value || '',
            category: itemCategory?.value || '',
            state: itemState?.value || ''
        }, itemLayout.showList);
    }

    setTimeout(() => {
        itemCheckScroll = true;
    }, 1000);
});

// 검색 버튼 클릭 이벤트
const itemSearchBtn = document.getElementById('item-search-btn');
if (itemSearchBtn) {
    itemSearchBtn.addEventListener('click', async () => {
        itemPage = 1;
        itemCriteria = await itemService.getList(itemPage, {
            keyword: itemKeyword?.value || '',
            type: itemType?.value || '',
            category: itemCategory?.value || '',
            state: itemState?.value || ''
        }, itemLayout.showList);
    });
}

// 상품 수정 버튼 클릭 이벤트
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('item-edit-btn')) {
        const row = e.target.closest('.item-row');
        if (row) {
            const itemId = row.dataset.id;
            // 모달 열기 또는 수정 페이지로 이동
            openModal('item-edit');
            // 상품 정보 로드
            loadItemData(itemId);
        }
    }
});

// 상품 정보 로드
async function loadItemData(itemId) {
    const item = await itemService.getOne(itemId);
    // 모달에 데이터 채우기
    document.getElementById('item-edit-id').value = item.id;
    document.getElementById('item-edit-name').value = item.itemName;
    document.getElementById('item-edit-price').value = item.itemPrice;
    document.getElementById('item-edit-stock').value = item.itemStock;
    // ... 나머지 필드들
}
