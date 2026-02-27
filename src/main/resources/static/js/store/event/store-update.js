document.addEventListener("DOMContentLoaded", () => {

    // 입력 길이 카운터 초기화
    const marketInputs       = document.querySelectorAll(".user-input");
    const marketInputLength  = document.querySelectorAll(".input-count-length");
    const marketInputMessage = document.querySelectorAll(".input-guide-message");

    // 상점명 (index 0): 최대 20자
    if (marketInputs[0] && marketInputLength[0]) {
        const nameLen = marketInputs[0].value.length;
        marketInputLength[0].textContent = `${nameLen}/20`;

        // 초기값이 있으면 안내 메시지 숨김
        if (nameLen > 0 && marketInputMessage[0]) {
            marketInputMessage[0].style.display = "none";
        }
    }

    // 상점 소개 (index 1): 최대 300자
    if (marketInputs[1] && marketInputLength[1]) {
        const introLen = marketInputs[1].value.length;
        marketInputLength[1].textContent = `${introLen}/300`;

        // 초기값이 10자 이상이면 안내 메시지 숨김
        if (introLen >= 10 && marketInputMessage[1]) {
            marketInputMessage[1].style.display = "none";
        }
    }


    // ── 2. 기존 프로필 이미지 초기화 버튼 ───────────────────────────────────
    const profileImageUploaded = document.querySelector(".profile-image-uploaded");
    const profileImageBox = profileImageUploaded.querySelector("img");
    const fileId = profileImageBox.getAttribute("value");

    const imageInput = document.getElementById("imageUpload");
    const uploaderWrapper = document.querySelector(".uploader-wrapper");

    if (profileImageBox && uploaderWrapper) {
        // 초기화 버튼 생성
        const resetBtn = document.createElement("button");
        resetBtn.type      = "button";
        resetBtn.className = "image-reset-btn registration-button";
        resetBtn.innerHTML = "이미지 초기화";

        // 업로드 버튼 바로 뒤에 삽입
        uploaderWrapper.appendChild(resetBtn);

        // 초기화 버튼 클릭 이벤트
        resetBtn.addEventListener("click", () => {
            // 파일 input 초기화
            imageInput.value = "";

            // 이미지 요소 제거
            profileImageBox.remove();

            // 서버에 삭제 요청할 파일 ID를 hidden input으로 form에 추가
            // 이미 추가된 경우 중복 생성 방지
            if (!document.getElementById("toDeleteFileId")) {
                const fileIdInput = document.createElement("input");
                fileIdInput.type  = "hidden";
                fileIdInput.name  = "toDeleteFileId";
                fileIdInput.id    = "toDeleteFileId";
                // HTML에 <input type="hidden" name="fileId" th:value="*{fileId}"> 형태로
                // 파일 ID를 미리 넣어두고 아래에서 읽어오는 방식을 권장합니다.
                fileIdInput.value  = fileId ? fileId : "";
                document.querySelector("form").appendChild(fileIdInput);
            }

            resetBtn.remove();
        });
    }
});