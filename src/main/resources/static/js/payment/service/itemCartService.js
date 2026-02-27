const itemCartService = (() => {
    const deleteCartItem = async (id) => {
        try {
            const response = await fetch(`/api/cart/delete/${id}`, {
                method: "DELETE",
            });

            if (response.ok) {
                alert("해당 상품을 성공적으로 제거했습니다.");
                return response.ok;
            }
        } catch (e) {
            console.log(e);
        }
    }

    const deleteAll = async (cartId) => {
        try {
            const response = await fetch(`/api/cart/deleteAll/${cartId}`, {
                method: "DELETE",
            })
            if(response.ok) {
                alert("모든 상품을 장바구니에서 제거했습니다.");
                return response.ok;
            }
        } catch (e) {
            console.log(e);
        }
    }

    return {
        deleteCartItem: deleteCartItem,
        deleteAll: deleteAll
    }
})();