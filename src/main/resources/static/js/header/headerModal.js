const searchInput = document.querySelector("input[type=search]");
const inputResetBtn = document.querySelector(".search-button-reset span");

// 인기 검색어, 연관 검색어 부분
const favoriteList = document.querySelector(".FavoritSearchlList-Container");
const autoCompleteList = document.querySelector(".AutoComplete-ResultList");

// 연관 검색어 List
let searchList = null;

// 인기 검색어 List
let favSearchList = null;

searchInput.addEventListener("input", (e) => {
    if (searchInput.value) {
        inputResetBtn.style.display = "flex";

        // 검색어 있을 시, 연관 검색어 가져오기
        autoCompleteList.style.display = "flex";
        favoriteList.style.display = "none";

        // DB에서 검색어 가져와서 뿌려야함
        if (searchList) {
        }
    } else {
        inputResetBtn.style.display = "none";
        favoriteList.style.display = "flex";
        autoCompleteList.style.display = "none";
    }
});

inputResetBtn.addEventListener("click", (e) => {
    searchInput.value = "";
    inputResetBtn.style.display = "none";
    favoriteList.style.display = "flex";
    autoCompleteList.style.display = "none";
});
