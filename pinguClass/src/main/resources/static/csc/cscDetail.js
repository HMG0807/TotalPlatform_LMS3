////////    문의글 삭제   //////////////////////////////////////////////
// 삭제시 alert창

const del = document.querySelector(".delete")

	del.addEventListener('click', function(){
		if(confirm("정말로 삭제?")){
			location.href = this.dataset.uri;
		}
	});




////// a팀 코드 슬쩍 //////////////


///////   답변 달리면 수정삭제 불가   //////////////////////////////////////////


/* 수정 삭제 제한 */
const modifyBtn = document.querySelector(".modify"); // 수정 삭제 버튼
const deleteBtn = document.querySelector(".delete"); // 수정 삭제 버튼
console.log("수정삭제확인");
// 수정 삭제 버튼 활성화 여부
if (document.querySelector(".answerBox")) { // 관리자 답변이 있으면
    modifyBtn.style.display = "none"; // 수정 버튼 숨김
    console.log("수정 안뜸");

} else {
    modifyBtn.style.display = "block"; // 답변x -> 수정 버튼 보임
    console.log("수정 뜸");
}

// 수정 삭제 버튼 활성화 여부
if (document.querySelector(".answerBox")) { // 관리자 답변이 있으면
    deleteBtn.style.display = "none"; // 수정 버튼 숨김
    console.log("삭제 안뜸");

} else {
    deleteBtn.style.display = "block"; // 답변x -> 수정 버튼 보임
    console.log("삭제 뜸");
}












