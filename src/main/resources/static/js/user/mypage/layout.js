const mypageLayout = (() => {

    const completeItemList = document.querySelector("#complete .ContentItems-ItemList");
    const reviewWriteModal = document.querySelector(".Custom-ModalLayer");

    // 거래완료 목록 그리기
    const showCompleteItems = (items) => {
        let text = ``;

        if (items.length === 0) {
            text = `<div class="EmptyContent-Wrapper">거래완료된 상품이 없습니다.</div>`;
            completeItemList.innerHTML = text;
            return;
        }

        items.forEach((item) => {
            text += `
                <div class="ContentItems-ItemCardWrapper">
                    <div class="ItemCard-Wrapper">
                        <div class="ItemCard-Container">
                            <div class="ItemCard-Content">
                                <div class="ItemCard-ItemInfo">
                                    <div class="ItemCard-MainContent">
                                        <div class="ItemCard-MarketInfo">${item.marketName}</div>
                                        <div class="ItemCard-SellerInfo">${item.storeName}</div>
                                    </div>
                                    <div class="ItemCard-InfoWrapper">
                                        <div class="ItemCard-Title">${item.itemName}</div>
                                        <div class="ItemCard-Description">${item.itemContent || ''}</div>
                                    </div>
                                    <div class="ItemCard-PriceInfo">
                                        <div class="ItemCard-Price">${item.itemPrice} 원</div>
                                    </div>
                                    <div class="ItemCard-ButtonWrapper">
                                        <button type="button" class="ItemCard-Button ReviewWrite" data-item-id="${item.itemId}" data-item-name="${item.itemName}">후기 작성</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `;
        });

        completeItemList.innerHTML = text;

        // 후기 작성 버튼 이벤트
        const reviewBtns = completeItemList.querySelectorAll(".ReviewWrite");
        reviewBtns.forEach((button) => {
            button.addEventListener("click", (e) => {
                e.preventDefault();
                document.querySelector(".ReviewItemInfo-Title").innerText = button.dataset.itemName;
                document.getElementById("reviewItemId").value = button.dataset.itemId;
                reviewWriteModal.classList.remove("off");
            });
        });
    };

    return { showCompleteItems: showCompleteItems };
})();