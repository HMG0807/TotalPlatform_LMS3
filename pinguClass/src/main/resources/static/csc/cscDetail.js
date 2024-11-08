

// Tap Menu Event *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

const csSideNav = document.querySelectorAll(".csSideNav>ul>li");
const AllcsContent = document.querySelectorAll(".csContainer>ul>li");

console.log(csSideNav);

csSideNav.forEach(item => {
    item.addEventListener('click', function () {
        csSideNav.forEach(i => i.classList.remove("on"));
        item.classList.add("on");

        // 모든 콘텐츠에서 'on' 클래스를 제거
        AllcsContent.forEach(i => i.classList.remove("on"));

        // 현재 클릭한 아이템에 대응하는 콘텐츠를 찾아 'on' 클래스를 추가
        const index = Array.from(csSideNav).indexOf(item);
        if (index !== -1) {
            AllcsContent[index].classList.add("on");
        }
    });
});


// -----------------------------수정 삭제 버튼 활성화 기능 ----------------------------

const csContainer = document.querySelector(".csContainer"); // 질문 박스
const optionBtn = document.querySelector(".d-flex"); // 수정 삭제 버튼



// 수정 삭제 버튼 활성화 여부
if (csContainer.querySelector(".csAnswer")) { // 관리자 답변이 있으면
    optionBtn.style.display = "none"; // 수정 버튼 숨김

} else {
    optionBtn.style.display = "flex"; //관리자 답변이 없으면 수정 버튼 활성화
}

