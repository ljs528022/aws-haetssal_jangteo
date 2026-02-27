const itemService = (() => {
    const getCategories = async (callback) => {
        const response = await fetch(`/api/item/category`);
        const categories = await response.json();
        if(callback) {
            callback(categories);
        }
    }

    const getSubCategories = async (id, callback) => {
        const response = await fetch(`/api/item/category/${id}`);
        const subCategories = await response.json();
        if(callback) {
            callback(subCategories);
        }
    }

    const getItemDescImages = async (id, callback) => {
        const response = await fetch(`/api/item/images/${id}`)
        const itemImages = await response.json();
        if(callback) {
            callback(itemImages);
        }
    }

    const getItemReviews = async (id, callback) => {
        page = page || 1;

        const response = await fetch(`/api/item/reviews/${id}`)
        const itemReview = await response.json();
        if(callback) {
            callback(itemReview);
        }
    }

    const addCartItem = async (cartItemDTO) => {
        try {
            const response = await fetch("/api/cart/item", {
                method: "POST",
                body: JSON.stringify(cartItemDTO),
                headers: { "Content-Type": "application/json" }
            });

            if(response.ok) {
                let result = confirm("해당 상품을 성공적으로 장바구니에 추가했습니다.\n바로 장바구니로 이동하시겠습니까?");
                if(result) {
                    location.href = `/cart/list?userId=${cartItemDTO.userId}`;
                }
            }
        } catch (e) {
            console.log(e);
        }
    }

    return {
        getCategories: getCategories,
        getSubCategories: getSubCategories,
        getItemDescImages: getItemDescImages,
        getItemReviews: getItemReviews,
        addCartItem: addCartItem
    }
})();