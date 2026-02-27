const itemService = (() => {

    // ===== 상품 목록 조회 =====
    const getList = async (page, search, callback) => {
        page = page || 1;
        const keyword = search?.keyword || "";
        const type = search?.type || "";
        const category = search?.category || "";
        const state = search?.state || "";

        let queryString = `?keyword=${encodeURIComponent(keyword)}`;
        queryString += `&type=${encodeURIComponent(type)}`;
        queryString += `&category=${encodeURIComponent(category)}`;
        queryString += `&state=${encodeURIComponent(state)}`;

        const response = await fetch(`/api/admin/items/${page}${queryString}`);
        const itemWithPaging = await response.json();

        if (callback) {
            return callback(itemWithPaging);
        }

        return itemWithPaging;
    };


    // ===== 상품 단건 조회 =====
    const getOne = async (id, callback) => {

        const response = await fetch(`/api/admin/items/${id}`);
        const item = await response.json();

        if (callback) {
            return callback(item);
        }

        return item;
    };


    // ===== 상품 수정 =====
    const update = async (itemData, callback) => {

        const response = await fetch(`/api/admin/items/${itemData.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(itemData)
        });

        const result = await response.json();

        if (callback) {
            return callback(result);
        }

        return result;
    };


    // ===== 상품 상태 변경 =====
    const updateState = async (id, state, callback) => {

        const response = await fetch(`/api/admin/items/${id}/state`, {
            method: "PATCH",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ itemState: state })
        });

        const result = await response.json();

        if (callback) {
            return callback(result);
        }

        return result;
    };


    // ===== 카테고리 목록 조회 =====
    const getCategories = async (callback) => {

        const response = await fetch(`/api/admin/items/categories`);
        const categories = await response.json();

        if (callback) {
            return callback(categories);
        }

        return categories;
    };


    return {
        getList,
        getOne,
        update,
        updateState,
        getCategories
    };

})();
