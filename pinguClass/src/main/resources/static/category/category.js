let categoryItem = document.querySelectorAll(".categoryItem");
let categoryInput = document.querySelector("#categoryInput");
let CategoryForm = document.querySelector("#CategoryForm");


categoryItem.forEach(item => {
    item.addEventListener("click", function(e) {
        e.preventDefault(); // 링크 클릭 시 페이지 이동 방지
        // 선택한 카테고리의 값을 숨겨진 input 필드에 설정
        categoryInput.value = item.getAttribute('data-value');
        console.log("선택된 카테고리 값:", categoryInput.value);
        // 폼 제출
        CategoryForm.submit();
    });
});


let categorySelectId = document.querySelector("#categorySelectId");
let categorySelectForm = document.querySelector("#categorySelectForm");


categorySelectId.addEventListener("change",function(){
	alert(categorySelectId.value)
	categorySelectForm.submit();
	
})
