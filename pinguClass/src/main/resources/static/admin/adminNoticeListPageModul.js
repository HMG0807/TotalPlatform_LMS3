const rows = document.querySelectorAll(".contentRows");
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼들

//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('page').value = this.dataset.page;
	    document.getElementById('searchForm').submit();
	});
});

//작성글 조회 버튼 클릭시
function noticeSelect(noticeId, noticeCount){
	location.href = "/admin/adminNoticeListDetail/"+ noticeId;
}

//삭제 버튼 클릭시
function deleteNotice(noticeTitle, noticeId, deleteYn){
	if(deleteYn == "n"){
		if(confirm(`정말로 \"${noticeTitle}\" 강좌를 삭제하시겠습니까?`)){
			location.href = "/admin/deleteNotice/" + noticeId;
		}					  
	} else {
		if(confirm(`정말로 \"${noticeTitle}\" 강좌를 삭제해제 하시겠습니까?`)){
			location.href = "/admin/cancelNotice/"+ noticeId;
		}
	}
}
