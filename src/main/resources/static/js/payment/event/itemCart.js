// 장바구니 상품 담기는 div
const itemListContainer = document.querySelector(".ItemCart-ItemListWrapper");

// 모든 상품 리스트
const itemList = document.querySelectorAll(".ItemCart-ItemList");

// 오른쪽 상품 금액 표시 span
const totalPriceSpan = document.querySelector("span[name=total-price]");
const totalCountSpan = document.querySelector("span[name=total-itemCount]");
const sumPriceSpan = document.querySelector("span[name=total-sumPrice]");
const totalFeeSpan = document.querySelector("span[name=total-totalFee]");
const disCountSpan = document.querySelector("span[name=total-discount]");
const purPriceSpan = document.querySelector("span[name=total-purPrice]");

// 장바구니 비우기 버튼
const resetBtn = document.querySelector(".ItemCart-ResetButton");

// 구매하기 버튼
const purchaseBtn = document.querySelector(".ItemCart-SubmitButton");

let totalPrice = 0; // 최종 결제 금액
let sumPrice = 0; // 상품 합산 금액
let totalFee = 0; // 총 배송비
let totalCount = 0; // 최종 상품 개수
let itemDisount = 0; // 총 할인 금액

// 선택된 상품들 금액 계산
const calculateTotalPrice = () => {
    sumPrice = 0;
    totalFee = 0;
    totalCount = 0;

    const selectedItems = document.querySelectorAll(
        ".ItemList-ItemContainer.selected",
    );

    selectedItems.forEach((item) => {
        console.log(item);
        const count = parseInt(item.querySelector(".Button-Current").innerHTML);
        const price = parseInt(
            item.querySelector("span[name='item-price']").innerHTML,
        );
        const fee = parseInt(
            item.querySelector("span[name='item-fee']").innerHTML,
        );

        sumPrice += price * count;
        totalFee += fee;
        totalCount += count;
    });

    totalPrice = sumPrice + totalFee - itemDisount;

    totalCountSpan.innerHTML = totalCount.toLocaleString();
    sumPriceSpan.innerHTML = sumPrice.toLocaleString();
    totalFeeSpan.innerHTML = totalFee.toLocaleString();
    disCountSpan.innerHTML = itemDisount.toLocaleString();
    purPriceSpan.innerHTML = totalPrice.toLocaleString();
    totalPriceSpan.innerHTML = totalPrice.toLocaleString();
};

calculateTotalPrice();

itemList.forEach((item) => {
    // 상품 이름 span
    const itemNameSpan = item.querySelector(".ItemCart-ItemTitle span");
    // 상품 제거 버튼
    const removeItemBtn = item.querySelector(".Button-Container.Remove");
    // 상품 개수 감소 버튼
    const minusBtn = item.querySelector(".Button-MinusBtn");
    // 상품 현재 개수
    const itemCount = item.querySelector(".Button-Current");
    // 상품 개수 추가 버튼
    const plusBtn = item.querySelector(".Button-PlusBtn");
    // 상품 선택 버튼
    const selectBtn = item.querySelector(".Button-SelectBtn");

    // 선택 버튼 클릭 여부
    let isClicked = false;

    itemNameSpan.addEventListener("click", (e) => {
        const itemId = itemNameSpan.dataset.id;
        location.href = `/item/detail?id=${itemId}`;
    })

    minusBtn.addEventListener("click", (e) => {
        let currentCount = itemCount.innerHTML;

        if (currentCount <= 1) {
            return;
        } else {
            itemCount.innerHTML = parseInt(currentCount) - 1;
            calculateTotalPrice();
        }
    });

    plusBtn.addEventListener("click", (e) => {
        let currentCount = itemCount.innerHTML;
        itemCount.innerHTML = parseInt(currentCount) + 1;
        calculateTotalPrice();
    });

    selectBtn.addEventListener("click", (e) => {
        const container = item.firstElementChild;
        isClicked = !isClicked;
        container.classList.toggle("selected", isClicked);
        selectBtn.classList.toggle("clicked", isClicked);
        selectBtn.innerHTML = isClicked ? "선택 됨" : "선택";

        calculateTotalPrice();
    });

    removeItemBtn.addEventListener("click", async (e) => {
        const cartItemId = removeItemBtn.firstElementChild.dataset.id;

        let check = confirm("해당 상품을 장바구니에서 제외하시겠습니까?");

        if (check) {
            const result = await itemCartService.deleteCartItem(cartItemId);
            if(result) {
                location.reload();
            }
        }
    });
});

resetBtn.addEventListener("click", async (e) => {
    const cartId = resetBtn.id;

    let check = confirm("장바구니를 비우시겠습니까?");
    if(check) {
        let result = await itemCartService.deleteAll(cartId);
        if(result) {
            location.reload();
        };
    }
});

purchaseBtn.addEventListener("click", (e) => {
    // 부트 페이 API 연동
    let result = confirm("선택하신 상품들도 거래를 진행합니다.\n이대로 결제를 진행하시겠습니까?");

    if(result) {

    } else return;
});
