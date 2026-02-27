const storeService = (() => {
    const getMarkets = async (region, callback) => {
        const response = await fetch(`/api/store/region/${region}`);
        const markets = await response.json();
        if(callback) {
            callback(markets);
        }
    }

    const getCategories = async (callback) => {
        const response = await fetch(`/api/store/category`);
        const categories = await response.json();
        if(callback) {
            callback(categories);
        }
    }

    const getList = async (page, {region, categoryId, marketId, state, orderValue}, callback) => {
        page = page || 1;
        region = region || "";
        state = state || "";
        orderValue = orderValue || "";

        const params = new URLSearchParams();
        params.append("region", region);
        params.append("state", state);
        params.append("orderValue", orderValue);
        if (categoryId) params.append("categoryId", categoryId);
        if (marketId) params.append("marketId", marketId);

        const response = await fetch(`/api/store/list/${page}?${params.toString()}`);
        const storeWithPaging = await response.json();
        if(callback) {
            return callback(storeWithPaging);
        }
    }

    const getItemsForDetail = async (page, id, callback) => {
        page = page || 1;

        const response = await fetch(`/api/store/detail/items/${page}?id=${id}`);
        const itemWithPaging = await response.json();
        if(callback) {
            return callback(itemWithPaging);
        }
    }

    const getReviewsForDetail = async (page, id, callback) => {
        page = page || 1;

        const response = await fetch(`/api/store/reviews/${page}?id=${id}`);
        const storeReview = await response.json();
        if(callback) {
            return callback(storeReview);
        }
    }

    return {
        getMarkets: getMarkets,
        getCategories: getCategories,
        getList: getList,
        getItemsForDetail: getItemsForDetail,
        getReviewsForDetail: getReviewsForDetail
    };
})();