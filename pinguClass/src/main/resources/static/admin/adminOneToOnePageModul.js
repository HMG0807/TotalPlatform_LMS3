const rows = document.querySelectorAll(".contentRows");
const searchBtn = document.querySelector("#searchBtn"); //검색 버튼
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼들

// //검색 버튼 클릭시
searchBtn.addEventListener('click', function() {
	document.getElementById('kw').value = document.getElementById('search_kw').value;
	document.getElementById('kwType').value = document.getElementById('searchSelect').value;
	document.getElementById('searchForm').submit();
});

//검색창에서 엔터 버튼 클릭시
function enterkey() {
	if (window.event.keyCode == 13) { //엔터키가 눌렸을 때 
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('kwType').value = document.getElementById('searchSelect').value;
		document.getElementById('searchForm').submit();
	}ㄴ
}

//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('page').value = this.dataset.page;
	    document.getElementById('searchForm').submit();
	});
});

//문의 /답변 조회 버튼 클릭시
function answerSelect(questionId, questionCount){
	location.href = "/admin/adminOneToOneListDetail/"+ questionId;
}

//삭제 버튼 클릭시
function deleteAnswer(questionTitle, answerId, deleteYn){
	if(deleteYn == "n"){
		if(confirm(`정말로 \"${questionTitle}\" 에 대한 답변을 삭제하시겠습니까?`)){
			location.href = "/admin/deleteAnswer/" + answerId;
		}					  
	} else {
		if(confirm(`정말로 \"${questionTitle}\" 에 대한 답변을 삭제해제 하시겠습니까?`)){
			location.href = "/admin/seleteAnswerCancel/"+ answerId;
		}
	}
}

// 수정 버튼 클릭시
function modifyAnswer(answerId){
	location.href = "/admin/adminOneToOneList/modifyPage/"+ answerId;
}

// 답변 버튼 클릭시
function answerQuestion(questionId){
	location.href = "/admin/adminOneToOneList/registerPage/"+ questionId;
}