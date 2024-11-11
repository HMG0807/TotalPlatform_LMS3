////////    문의글 삭제   //////////////////////////////////////////////
// 삭제시 alert창
alert("울면암바");
console.log(window.location.href);
let del = document.querySelectorAll(".delete")
for(let el of del){
	el.addEventListener('click', function(){
		if(confirm("정말로 삭제?")){
			location.href = this.dataset.uri;
		}
	});
};



////// a팀 코드 슬쩍 //////////////
///////   답변 달리면 수정삭제 불가   //////////////////////////////////////////


const csQuestion = document.querySelector(".csQuestion"); // 질문 박스
const retouchBtn = document.querySelector(".recommend"); // 수정 삭제 버튼


// 수정 삭제 버튼 활성화 여부
if (csQuestion.querySelector("border-bottommy-3py-2")) { // 관리자 답변이 있으면
    retouchBtn.style.display = "none"; // 수정 버튼 숨김

} else {
    retouchBtn.style.display = "block"; //관리자 답변이 없으면 수정 버튼 활성화
}












