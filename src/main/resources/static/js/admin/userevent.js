// ################################# 회원 관리 이벤트 #################################

// 무한 스크롤 페이징 처리
let userPage = 1;
let userCheckScroll = true;
let userCriteria = null;

// 검색 필터 요소
const userKeyword = document.getElementById('user-keyword');
const userType = document.getElementById('user-type');
const userState = document.getElementById('user-state');

// 초기 로딩
if (userKeyword) {
    userCriteria = userService.getList(userPage, {
        keyword: userKeyword.value || '',
        userType: userType?.value || '',
        userState: userState?.value || ''
    }, userLayout.showList);
}

// 무한 스크롤 이벤트
window.addEventListener("scroll", async (e) => {
    if (!userCheckScroll || !userCriteria?.hasMore) {
        return;
    }

    const scrollCurrentPosition = window.scrollY;
    const windowHeight = window.innerHeight;
    const documentHeight = document.documentElement.scrollHeight;

    // 바닥에 닿았을 때
    if (scrollCurrentPosition + windowHeight >= documentHeight - 1) {
        userCheckScroll = false;
        userCriteria = await userService.getList(++userPage, {
            keyword: userKeyword?.value || '',
            userType: userType?.value || '',
            userState: userState?.value || ''
        }, userLayout.showList);
    }

    setTimeout(() => {
        userCheckScroll = true;
    }, 1000);
});

// 검색 버튼 클릭 이벤트
const userSearchBtn = document.getElementById('user-search-btn');
if (userSearchBtn) {
    userSearchBtn.addEventListener('click', async () => {
        userPage = 1;
        userCriteria = await userService.getList(userPage, {
            keyword: userKeyword?.value || '',
            userType: userType?.value || '',
            userState: userState?.value || ''
        }, userLayout.showList);
    });
}

// 회원 수정 버튼 클릭 이벤트
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('user-edit-btn')) {
        const row = e.target.closest('.user-row');
        if (row) {
            const userId = row.dataset.id;
            // 모달 열기
            openModal('user-edit');
            // 회원 정보 로드
            loadUserData(userId);
        }
    }
});

// 회원 정보 로드
async function loadUserData(userId) {
    const user = await userService.getOne(userId);
    // 모달에 데이터 채우기
    document.getElementById('user-edit-id').value = user.id;
    document.getElementById('user-edit-name').value = user.userName;
    document.getElementById('user-edit-email').value = user.userEmail;
    document.getElementById('user-edit-phone').value = user.userPhone;
    // ... 나머지 필드들
}
