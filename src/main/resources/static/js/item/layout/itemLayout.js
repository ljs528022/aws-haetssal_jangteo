const itemLayout = (() => {
    const showItemDescImages = (itemImages) => {
        // 상품 설명 이미지 div
        const allInfoImageDiv = document.querySelector(".story-card-inner");
        // 상품 소개 ~ 교환/환불 div
        const descInfoDiv = allInfoImageDiv.querySelector("div[name=item-desc]");
        const sellerInfoDiv = allInfoImageDiv.querySelector("div[name=item-seller]");
        const refundInfoDiv = allInfoImageDiv.querySelector("div[name=item-refund]");

        const itemDescImages = itemImages.itemDescImages;
        const itemSellerImages = itemImages.itemSellerImages;
        const itemRefundImages = itemImages.itemRefundImages;

        let text = ``;
        // 상품 소개 이미지 뿌리기
        itemDescImages.forEach((image) => {
            const srcPath = `/api/files/display?filePath=${encodeURIComponent(image.fileSavedPath)}&fileName=${encodeURIComponent(image.fileName)}`;

            text += `
                <img src="${srcPath}" alt="desc" class="story-content-img">
            `;
        });
        descInfoDiv.innerHTML = text;
        text = ``;

        // 상품 판매자 이미지 뿌리기
        itemSellerImages.forEach((image) => {
            const srcPath = `/api/files/display?filePath=${encodeURIComponent(image.fileSavedPath)}&fileName=${encodeURIComponent(image.fileName)}`;

            text += `
                <img src="${srcPath}" alt="seller" class="story-content-img">
            `;
        });
        sellerInfoDiv.innerHTML = text;
        text = ``;

        // 상품 교환/환불 이미지 뿌리기
        itemRefundImages.forEach((image) => {
            const srcPath = `/api/files/display?filePath=${encodeURIComponent(image.fileSavedPath)}&fileName=${encodeURIComponent(image.fileName)}`;

            text += `
                <img src="${srcPath}" alt="refund" class="story-content-img">
            `;
        });
        refundInfoDiv.innerHTML = text;
        text = ``;
    }

    const showItemReviews = (itemReview) => {
        const reviewTotal = document.querySelector(".simple-count");
        const reviewContainer = document.querySelector(".detail-reviews-wrapper");

        const reviews = itemReview.itemReviews.slice(0, 4);
        const criteria = itemReview.criteria;
        const total = itemReview.total;

        if(!reviews || reviews.length === 0) {
            reviewContainer.innerHTML = `
            <div class="empty-box">
                <div class="empty-box-wrapper">
                    <div class="empty-box-container">
                        <div class="empty-box-icon">
                            <div class="box-icon">
                                <svg viewBox="0 0 48 48"><path d="M42.1181 14.5537C42.1557 14.5161 42.1544 14.454 42.1154 14.415L33.7715 6.07113C33.7325 6.03207 33.6704 6.03085 33.6329 6.06839L29.6905 10.0107C29.653 10.0483 29.6542 10.1103 29.6933 10.1494L38.0371 18.4933C38.0762 18.5323 38.1383 18.5335 38.1758 18.496L42.1181 14.5537Z"></path><path d="M36.134 20.5378C36.1715 20.5003 36.1703 20.4382 36.1312 20.3992C36.0922 20.3601 27.7874 12.0553 27.7874 12.0553C27.7483 12.0163 27.6862 12.015 27.6487 12.0526C27.6111 12.0901 9.6289 30.0723 9.6289 30.0723C9.61462 30.0866 9.60538 30.1052 9.6025 30.1255L8.21584 39.856C8.20646 39.9218 8.2647 39.9801 8.33053 39.9707L18.061 38.584C18.0813 38.5811 18.0999 38.5719 18.1142 38.5576C18.1142 38.5576 36.0964 20.5754 36.134 20.5378Z"></path></svg>
                            </div>
                        </div>
                        <div class="empty-box-text">아직 해당 상품에 관한 리뷰가 없습니다.</div>
                    </div>
                </div>
            </div>
            `;
            return;
        }

        renderReviewKeywordStates(reviews);

        const scoreToQuality  = (score) => score == 3 ? '만족해요'  : (score == 2 ? '보통이에요' : '아쉬워요');
        const scoreToDelivery = (score) => score == 3 ? '빨랐어요'  : (score == 2 ? '보통이에요' : '아쉬워요');
        const scoreToKind     = (score) => score == 3 ? '만족해요'  : (score == 2 ? '보통이에요' : '아쉬워요');

        let text = ``;
        reviews.forEach(review => {

            let imageHtml = ``;
            if (review.reviewFiles && review.reviewFiles.length > 0) {
                review.reviewFiles.forEach(file => {
                    imageHtml += `
                    <div>
                        <img class="review-image" src="/api/files/display?filePath=${encodeURIComponent(file.fileSavedPath)}&fileName=${encodeURIComponent(file.fileName)}">
                    </div>
                `;
                });
            }

            text += `
        <div class="each-detail-review-wrap">
            <div class="customer-comment-wrap">
                <div class="exist-after-first-review">
                    <div class="review-customer-profile-wrap">
                        <div class="review-customer-profile">
                            <img class="customer-image" src="https://cdn.tumblbug.com/profile/default_avatar.png">
                            <div class="customer-info">
                                <div class="profile-name">
                                    <a href="/mypage/userPage?id=${review.reviewUserId}" class="goto-customer-profile">${review.userName}</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="review-comment-content-wrap">
                        <div class="keywords-box-in-comment">
                            <div class="keyword-in-comment">
                                <p class="keyword-name">상품</p>
                                <p class="keyword-content">${scoreToQuality(review.reviewScoreQuality)}</p>
                            </div>
                            <div class="keyword-in-comment">
                                <p class="keyword-name">주문/배송</p>
                                <p class="keyword-content">${scoreToDelivery(review.reviewScoreDelivery)}</p>
                            </div>
                            <div class="keyword-in-comment">
                                <p class="keyword-name">친절</p>
                                <p class="keyword-content">${scoreToKind(review.reviewScoreKind)}</p>
                            </div>
                        </div>
                        <div class="review-comment">${review.reviewContent}</div>
                    </div>
                    <div class="review-image-wrap">
                        <div class="image-list">${imageHtml}</div>
                    </div>
                    <div class="review-item-info">
                        <a class="product-link-in-review" href="/item/detail?id=${review.reviewItemId}">
                            <img class="goto-product-from-review-image" src="/images/TempItem-Image.png">
                            <div class="product-name-nextto-image">${review.itemName}</div>
                        </a>
                    </div>
                    <div class="thumbs-wrap">
                        <div class="thumbs-box">
                            <div class="review-date">
                                <span>${review.createdDatetime}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="seller-comment-wrap"></div>
        </div>
        `;
        });
        reviewTotal.innerHTML = total;

        // 맨 아래 footer
        const footer = reviewContainer.querySelector('.divider-after-reviews');
        footer.insertAdjacentHTML('beforebegin', text);
    }

    const renderReviewKeywordStates = (reviews) => {
        if(!reviews || reviews.length === 0) return;

        const categories = [
            { key: 'reviewScoreQuality',  selector: '.keyword-item:nth-child(1)' },
            { key: 'reviewScoreDelivery', selector: '.keyword-item:nth-child(2)' },
            { key: 'reviewScoreKind',     selector: '.keyword-item:nth-child(3)' },
        ];

        const scoreToOptionIndex = { 3: 0, 2: 1, 1: 2 };

        categories.forEach(({key, selector}) => {
            const counts = { 0: 0, 1: 0, 2: 0 };

            reviews.forEach(review => {
                const score = review[key];
                const idx = scoreToOptionIndex[score];
                if(idx !== undefined) counts[idx]++;
            });

            const total = reviews.length;
            const item = document.querySelector(selector);
            if(!item) return;

            const options = item.querySelectorAll('.review-option');

            options.forEach((optionEl, i) => {
                const rate = total > 0 ? Math.round((counts[i] / total) * 100) : 0;

                // 퍼센트 텍스트
                optionEl.querySelector('.progress-rate').textContent = `${rate}%`;

                // 프로그레스 바
                const bar = optionEl.querySelector('.progress-bar');
                bar.style.setProperty('--bar-width', `${rate}%`);
            });
        });
    }

    return {
        showItemDescImages: showItemDescImages,
        showItemReviews: showItemReviews
    };
})();
