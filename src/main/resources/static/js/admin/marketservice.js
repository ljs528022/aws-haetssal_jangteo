const marketService = (() => {

    // ===== 장터 목록 조회 =====
    const getList = async (page, search, callback) => {
        page = page || 1;
        const keyword = search?.keyword || "";
        const region = search?.region || "";
        const state = search?.state || "";

        let queryString = `?keyword=${encodeURIComponent(keyword)}`;
        queryString += `&region=${encodeURIComponent(region)}`;
        queryString += `&state=${encodeURIComponent(state)}`;

        const response = await fetch(`/api/admin/markets/${page}${queryString}`);
        const marketWithPaging = await response.json();

        if (callback) {
            return callback(marketWithPaging);
        }

        return marketWithPaging;
    };


    // ===== 장터 단건 조회 =====
    const getOne = async (id, callback) => {

        const response = await fetch(`/api/admin/markets/${id}`);
        const market = await response.json();

        if (callback) {
            return callback(market);
        }

        return market;
    };


    // ===== 장터 수정 =====
    const update = async (marketData, callback) => {

        const response = await fetch(`/api/admin/markets/${marketData.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(marketData)
        });

        const result = await response.json();

        if (callback) {
            return callback(result);
        }

        return result;
    };


    // ===== 장터 지역 목록 조회 =====
    const getRegions = async (callback) => {

        const response = await fetch(`/api/admin/markets/regions`);
        const regions = await response.json();

        if (callback) {
            return callback(regions);
        }

        return regions;
    };


    return {
        getList,
        getOne,
        update,
        getRegions
    };

})();
