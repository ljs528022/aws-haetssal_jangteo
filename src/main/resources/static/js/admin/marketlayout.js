const marketLayout = (() => {
    const stateToLabel = { pending: '입점 대기', active: '운영 중', inactive: '운영 중단' };

    const showList = (marketWithPaging) => {
        const listContainer = document.getElementById('market-list');
        const totalEl = document.getElementById('market-total');
        const pagination = document.getElementById('market-pagination');

        const markets = marketWithPaging.markets || [];
        const criteria = marketWithPaging.criteria;

        if (totalEl) totalEl.textContent = marketWithPaging.total ?? 0;

        if (markets.length === 0) {
            listContainer.innerHTML = '<p class="EmptyMessage">등록된 장터가 없습니다.</p>';
            if (pagination) pagination.innerHTML = '';
            return criteria;
        }

        let text = '';
        markets.forEach((market) => {
            const stateVal = market.marketState?.value || market.marketState || '';
            text += `
                <div class="MainContent-ItemList market-row"
                     data-id="${market.id}"
                     data-name="${market.marketName || ''}"
                     data-region="${market.marketRegion || ''}"
                     data-location="${market.marketLocation || ''}"
                     data-state="${stateVal}"
                     data-created="${market.createdDatetime || ''}"
                     data-updated="${market.updatedDatetime || ''}">
                    <div class="ItemList-MainRightInfo">
                        <div class="ItemList-MainInfoList">
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">장터 번호</p>
                                <p class="MainInfoList-Value">${market.id}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">장터 이름</p>
                                <p class="MainInfoList-Value">${market.marketName || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">위치</p>
                                <p class="MainInfoList-Value">${market.marketLocation || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">지역</p>
                                <p class="MainInfoList-Value">${market.marketRegion || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상태</p>
                                <p class="MainInfoList-Value">${stateToLabel[stateVal] || stateVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">등록일</p>
                                <p class="MainInfoList-Value">${market.createdDatetime || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">정보 수정일</p>
                                <p class="MainInfoList-Value">${market.updatedDatetime || '-'}</p>
                            </div>
                        </div>
                        <button type="button" class="Row-EditBtn market-edit-btn">수정</button>
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
