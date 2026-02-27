const mypageService = (() => {

    // 5번탭으로 가서 거래완료 상태 템들 뿌리기
    const goFifthTabAndCompleteItems = async (callback) => {
        const response = await fetch("/mypage/complete-items");
        const completes = await response.json();
        if (callback) {
            callback(completes);
        }
    };

    // 리뷰 작성
    const writeReview = async (formData, callback) => {
        const response = await fetch("/mypage/review", {
            method: "POST",
            body: formData
        });
        const result = await response.text();
        if (callback) {
            callback(result);
        }
    };

    return {
        goFifthTabAndCompleteItems: goFifthTabAndCompleteItems,
        writeReview: writeReview
    };
})();