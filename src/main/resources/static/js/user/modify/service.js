const profileService = (() => {

    // 프로필 뿌려버리기
    const getProfile = async () => {
        try {
            const response = await fetch("/profile/info");
            return await response.json();
        } catch (error) {
            console.error("에러:", error);
        }
    };
    // 프사 바꾸기
    const updateImage = async (formData) => {
        try {
            const response = await fetch("/profile/image", {
                method: "POST",
                body: formData
            });
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

    // 이름이나 닉 변경
    const updateName = async (userName) => {
        try {
            const response = await fetch("/profile/name", {
                method: "POST",
                body: JSON.stringify({ userName: userName }),
                headers: {
                    "Content-Type": "application/json"
                }
            });
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

    // 유저소개글
    const updateIntro = async (userIntro) => {
        try {
            const response = await fetch("/profile/intro", {
                method: "POST",
                body: JSON.stringify({ userIntro: userIntro }),
                headers: {
                    "Content-Type": "application/json"
                }
            });
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

    // DB 의 비번이랑 입력한거랑 확인
    const checkPassword = async (oldPassword) => {
        try {
            const response = await fetch("/profile/check-password", {
                method: "POST",
                body: JSON.stringify({ userPassword: oldPassword }),
                headers: {
                    "Content-Type": "application/json"
                }
            });
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

    // 비번바꾸기
    const updatePassword = async (newPassword) => {
        try {
            const response = await fetch("/profile/password", {
                method: "POST",
                body: JSON.stringify({ userPassword: newPassword }),
                headers: {
                    "Content-Type": "application/json"
                }
            });
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

    // 배송지 목록보기
    const getDeliveryList = async () => {
        try {
            const response = await fetch("/profile/delivery-list");
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

    // 배송지 추가
    const addDelivery = async (deliveryData) => {
        try {
            const response = await fetch("/profile/delivery", {
                method: "POST",
                body: JSON.stringify(deliveryData),
                headers: {
                    "Content-Type": "application/json"
                }
            });
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

// 배송지 삭제
    const removeDelivery = async (id) => {
        try {
            const response = await fetch("/profile/delivery?id=" + id, {
                method: "DELETE"
            });
            return response;
        } catch (error) {
            console.error("에러:", error);
        }
    };

    return {getProfile: getProfile, updateImage: updateImage, updateName: updateName,
        updateIntro: updateIntro, checkPassword: checkPassword, updatePassword: updatePassword,
        getDeliveryList: getDeliveryList, addDelivery: addDelivery, removeDelivery: removeDelivery};
})();