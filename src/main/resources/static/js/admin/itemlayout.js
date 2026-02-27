const itemLayout = (() => {
    const stateToLabel = { pending: '판매 대기', active: '판매 중', inactive: '판매 중단' };
    const typeToLabel = { normal: '일반', deadline: '임박 마감' };

    const showList = (itemWithPaging) => {
        const listContainer = document.getElementById('item-list');
        const totalEl = document.getElementById('item-total');
        const pagination = document.getElementById('item-pagination');

        const items = itemWithPaging.items || [];
        const criteria = itemWithPaging.criteria;

        if (totalEl) totalEl.textContent = itemWithPaging.total ?? 0;

        if (items.length === 0) {
            listContainer.innerHTML = '<p class="EmptyMessage">등록된 상품이 없습니다.</p>';
            if (pagination) pagination.innerHTML = '';
            return criteria;
        }

        let text = '';
        items.forEach((item) => {
            const stateVal = item.itemState || '';
            const typeVal = item.itemType || '';
            text += `
                <div class="MainContent-ItemList item-row"
                     data-id="${item.id}"
                     data-name="${item.itemName || ''}"
                     data-price="${item.itemPrice ?? ''}"
                     data-stock="${item.itemStock ?? ''}"
                     data-delivery-fee="${item.itemDeliveryFee ?? ''}"
                     data-type="${typeVal}"
                     data-category="${item.categoryName || ''}"
                     data-state="${stateVal}"
                     data-created="${item.createdDatetime || ''}"
                     data-updated="${item.updatedDatetime || ''}">
                    <div class="ItemList-MainRightInfo">
                        <div class="ItemList-MainInfoList">
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상품 번호</p>
                                <p class="MainInfoList-Value">${item.id}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상품 이름</p>
                                <p class="MainInfoList-Value">${item.itemName || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">가격 (원)</p>
                                <p class="MainInfoList-Value">${item.itemPrice ?? '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">재고</p>
                                <p class="MainInfoList-Value">${item.itemStock ?? '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상품 유형</p>
                                <p class="MainInfoList-Value">${typeToLabel[typeVal] || typeVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">카테고리</p>
                                <p class="MainInfoList-Value">${item.categoryName || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상태</p>
                                <p class="MainInfoList-Value">${stateToLabel[stateVal] || stateVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">등록일</p>
                                <p class="MainInfoList-Value">${item.createdDatetime || '-'}</p>
                            </div>
                        </div>
                        <button type="button" class="Row-EditBtn item-edit-btn">수정</button>
                    </div>
                </div>
            `;
        });

        if (criteria && criteria.page === 1) {
            listContainer.innerHTML = text;
        } else {
            listContainer.innerHTML += text;
        }

        return criteria;
    };

    return { showList };
})();
