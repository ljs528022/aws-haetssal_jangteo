const userService = (() => {

    // ===== 회원 목록 조회 =====
    const getList = async (page, search, callback) => {
        page = page || 1;
        const keyword = search?.keyword || "";
        const userType = search?.userType || "";
        const userState = search?.userState || "";

        let queryString = `?keyword=${encodeURIComponent(keyword)}`;
        queryString += `&userType=${encodeURIComponent(userType)}`;
        queryString += `&userState=${encodeURIComponent(userState)}`;

        const response = await fetch(`/api/admin/users/${page}${queryString}`);
        const userWithPaging = await response.json();

        if (callback) {
            return callback(userWithPaging);
        }

        return userWithPaging;
    };


    // ===== 회원 단건 조회 =====
    const getOne = async (id, callback) => {

        const response = await fetch(`/api/admin/users/${id}`);
        const user = await response.json();

        if (callback) {
            return callback(user);
        }

        return user;
    };


    // ===== 회원 수정 =====
    const update = async (userData, callback) => {

        const response = await fetch(`/api/admin/users/${userData.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(userData)
        });

        const result = await response.json();

        if (callback) {
            return callback(result);
        }

        return result;
    };


    return {
        getList,
        getOne,
        update
    };

})();
