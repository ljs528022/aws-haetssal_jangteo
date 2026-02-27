const paymentService = (() => {
ㅣ

    const pay = async (payInfo) => {
        const price = payInfo.price;

        try {
            const response = await Bootpay.requestPayment({
                application_id: "69604bcaeaa52ce41d212a83",
                price: 1000,
                sandbox: true,
                order_name: "테스트결제",
                order_id: "TEST_ORDER_ID",
                pg: "토스",
                // method: "가상계좌",
                tax_free: 0,
                user: {
                    id: "회원아이디",
                    username: "회원이름",
                    phone: "01000000000",
                    email: "test@test.com",
                },
                items: [
                    {
                        id: "item_id",
                        name: "테스트아이템",
                        qty: 1,
                        price: 1000,
                    },
                ],
                extra: {
                    open_type: "iframe",
                    card_quota: "0,2,3",
                    escrow: false,
                },
            });
            switch (response.event) {
                case "issued":
                    break;
                case "done":
                    console.log(response);
                    break;
                case "confirm": //payload.extra.separately_confirmed = true; 일 경우 승인 전 해당 이벤트가 호출됨
                    console.log(response.receipt_id);

                    /**
                     * 2. 서버 승인을 하고자 할때
                     * // requestServerConfirm(); //예시) 서버 승인을 할 수 있도록  API를 호출한다. 서버에서는 재고확인과 로직 검증 후 서버승인을 요청한다.
                     * Bootpay.destroy(); //결제창을 닫는다.
                     */
                    break;
            }
        } catch (e) {
            // 결제 진행중 오류 발생
            // e.error_code - 부트페이 오류 코드
            // e.pg_error_code - PG 오류 코드
            // e.message - 오류 내용
            console.log(e.message);
            switch (e.event) {
                case "cancel":
                    // 사용자가 결제창을 닫을때 호출
                    console.log(e.message);
                    break;
                case "error":
                    // 결제 승인 중 오류 발생시 호출
                    console.log(e.error_code);
                    break;
            }
        }
    };

    return {
        pay: pay,
    }
});
