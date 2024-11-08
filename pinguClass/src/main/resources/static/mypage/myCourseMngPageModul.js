
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼들

//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('page').value = this.dataset.page;
	    document.getElementById('searchForm').submit();
	});
});

//강좌 수정 버튼 클릭시
function courseModify(courseId){
	location.href = '/mypage/instructor/courseModify/' + courseId;
}

//강좌 삭제 버튼 클릭시
function courseDelete(courseId){
	
	if(confirm("정말로 삭제하시겠습니까?")){
		location.href = '/mypage/instructor/courseDelete/' + courseId;
	}
}

//QnA 내역 버튼 클릭시
function courseQnaSelect(courseId, qnaList){
	
	let count = 0;
	
	for(let i=0; i<qnaList.length; i++){
		
		if(qnaList[i].deleteYn == 'n') count++;
	}
	
	if(count == 0) {
		alert("QnA 내역이 없습니다.");
		return;
	}
	
	location.href = '/mypage/instructor/courseQnaSelect/' + courseId;
}

// 강의/추가 관리 버튼 클릭시
function lectureMng(courseId){
	location.href = '/mypage/instructor/lectureMng/' + courseId;
}








