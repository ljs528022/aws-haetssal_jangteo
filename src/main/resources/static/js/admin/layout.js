// ################################# 관리자 공통 레이아웃 #################################

// 현재 활성화된 섹션
let currentSection = 'home';

// 모든 메인 섹션 name 목록
const SECTIONS = ['home', 'user', 'market', 'item', 'report'];

// 사이드 메뉴 name → 섹션 name 매핑
const MENU_MAP = {
    'homeMenu':   'home',
    'userMenu':   'user',
    'marketMenu': 'market',
    'itemMenu':   'item',
    'reportMenu': 'report',
};

// 특정 섹션만 보이게 하고 나머지 숨기기
function showSection(sectionName) {
    SECTIONS.forEach(name => {
        const el = document.querySelector(`[name="${name}"]`);
        if (el) {
            if (name === sectionName) {
                el.classList.remove('off');
            } else {
                el.classList.add('off');
            }
        }
    });
    currentSection = sectionName;
}

// 사이드 메뉴 활성화 스타일 적용
function setActiveMenu(menuName) {
    Object.keys(MENU_MAP).forEach(name => {
        const menuEl = document.querySelector(`[name="${name}"] .SideMenu-Icon`);
        if (menuEl) {
            if (name === menuName) {
                menuEl.classList.add('current');
            } else {
                menuEl.classList.remove('current');
            }
        }
    });
}

// 사이드 메뉴 클릭 이벤트 등록
function initSidebarMenu() {
    Object.entries(MENU_MAP).forEach(([menuName, sectionName]) => {
        const menuEl = document.querySelector(`[name="${menuName}"]`);
        if (menuEl) {
            menuEl.addEventListener('click', () => {
                showSection(sectionName);
                setActiveMenu(menuName);
            });
        }
    });
}

// 모달 열기
function openModal(modalName) {
    const modal = document.querySelector(`[name="${modalName}"]`);
    if (modal) {
        modal.classList.remove('off');
    }
}

// 모달 닫기
function closeModal(modalName) {
    const modal = document.querySelector(`[name="${modalName}"]`);
    if (modal) {
        modal.classList.add('off');
    }
}

// 모달 닫기 버튼 등록
function initModalClose() {
    const modals = ['user-edit', 'market-edit', 'item-edit', 'report-edit'];
    modals.forEach(modalName => {
        const modal = document.querySelector(`[name="${modalName}"]`);
        if (!modal) return;

        // X 버튼
        const closeBtn = modal.querySelector('.EditModal-CloseButton');
        if (closeBtn) {
            closeBtn.addEventListener('click', () => closeModal(modalName));
        }

        // 모달 바깥(레이아웃) 클릭 시 닫기
        modal.addEventListener('click', (e) => {
            if (e.target === modal) closeModal(modalName);
        });
    });
}

// 수정 모달 내 input 활성화/비활성화 토글
function initEditModalToggle() {
    const modals = ['user-edit', 'market-edit', 'item-edit'];
    modals.forEach(modalName => {
        const modal = document.querySelector(`[name="${modalName}"]`);
        if (!modal) return;

        const editBtn = modal.querySelector('.EditModal-Button.Edit');
        if (!editBtn) return;

        editBtn.addEventListener('click', () => {
            const inputs = modal.querySelectorAll('.EditModal-Input:not([id$="-id"]):not([id$="-createdAt"]):not([id$="-updatedAt"])');
            const selects = modal.querySelectorAll('.EditModal-SelectList');

            inputs.forEach(input => {
                if (input.disabled) {
                    input.disabled = false;
                    input.classList.remove('disabled');
                } else {
                    input.disabled = true;
                    input.classList.add('disabled');
                }
            });
        });
    });
}

// 드롭다운 select 옵션 토글
function initSelectDropdowns() {
    document.querySelectorAll('.EditModal-SelectContent').forEach(selectContent => {
        selectContent.addEventListener('click', () => {
            const list = selectContent.nextElementSibling;
            if (list && list.classList.contains('EditModal-SelectList')) {
                list.classList.toggle('off');
            }
        });
    });

    // 옵션 선택
    document.querySelectorAll('.EditModal-Option').forEach(option => {
        option.addEventListener('click', () => {
            const list = option.closest('.EditModal-SelectList');
            const selectText = list?.previousElementSibling?.querySelector('.EditModal-SelectText');
            if (selectText) {
                selectText.textContent = option.querySelector('p')?.textContent || '';
            }
            list?.classList.add('off');
        });
    });
}

// 초기 레이아웃 설정
function initLayout() {
    // 홈 제외 모든 섹션 숨기기
    showSection('home');
    setActiveMenu('homeMenu');

    initSidebarMenu();
    initModalClose();
    initEditModalToggle();
    initSelectDropdowns();
}
