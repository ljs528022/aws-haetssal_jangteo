const userLayout = (() => {
    const userTypeToLabel = { buyer: '구매자', seller: '판매자', admin: '관리자' };
    const userStateToLabel = { active: '활성', inactive: '비활성', banned: '정지' };

    const showList = (userWithPaging) => {
        const listContainer = document.getElementById('user-list');
        const totalEl = document.getElementById('user-total');
        const pagination = document.getElementById('user-pagination');

        const users = userWithPaging.users || [];
        const criteria = userWithPaging.criteria;

        if (totalEl) totalEl.textContent = userWithPaging.total ?? 0;

        if (users.length === 0) {
            listContainer.innerHTML = '<p class="EmptyMessage">등록된 회원이 없습니다.</p>';
            if (pagination) pagination.innerHTML = '';
            return criteria;
        }

        let text = '';
        users.forEach((user) => {
            const userTypeVal = user.userType || '';
            const userStateVal = user.userState || '';
            text += `
                <div class="MainContent-ItemList user-row"
                     data-id="${user.id}"
                     data-name="${user.userName || ''}"
                     data-email="${user.userEmail || ''}"
                     data-phone="${user.userPhone || ''}"
                     data-type="${userTypeVal}"
                     data-state="${userStateVal}"
                     data-created="${user.createdDatetime || ''}"
                     data-updated="${user.updatedDatetime || ''}">
                    <div class="ItemList-MainRightInfo">
                        <div class="ItemList-MainInfoList">
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">회원 번호</p>
                                <p class="MainInfoList-Value">${user.id}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">회원 이름</p>
                                <p class="MainInfoList-Value">${user.userName || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">이메일</p>
                                <p class="MainInfoList-Value">${user.userEmail || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">전화번호</p>
                                <p class="MainInfoList-Value">${user.userPhone || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">회원 유형</p>
                                <p class="MainInfoList-Value">${userTypeToLabel[userTypeVal] || userTypeVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">상태</p>
                                <p class="MainInfoList-Value">${userStateToLabel[userStateVal] || userStateVal || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">가입일</p>
                                <p class="MainInfoList-Value">${user.createdDatetime || '-'}</p>
                            </div>
                            <div class="MainInfoList-Wrapper">
                                <p class="MainInfoList-Field">최근 로그인</p>
                                <p class="MainInfoList-Value">${user.userLatestLogin || '-'}</p>
                            </div>
                        </div>
                        <button type="button" class="Row-EditBtn user-edit-btn">수정</button>
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
