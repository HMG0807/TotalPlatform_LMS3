
const rows = document.querySelectorAll(".contentRows");
const searchBtn = document.querySelector("#searchBtn"); //검색 버튼
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼들

//검색 버튼 클릭시
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
	}
}

//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('page').value = this.dataset.page;
	    document.getElementById('searchForm').submit();
	});
});

//강의 내역 버튼 클릭시
function lectureSelect(courseId, lectureCount){
	if(lectureCount == 0) alert("조회된 강의가 없습니다.");
	else location.href = "/admin/lectureList/" + courseId + "/0";
}

//삭제 버튼 클릭시
function deleteCourse(course){
	if(course.deleteYn == "n"){
		if(confirm(`정말로 \"${course.title}\" 강좌를 삭제하시겠습니까?`)){
			location.href = "/admin/deletecourse/" + course.courseId;
		}
	} else {
		if(confirm(`정말로 \"${course.title}\" 강좌를 삭제해제 하시겠습니까?`)){
			location.href = "/admin/deletecourse/cancel/" + course.courseId;
		}
	}
}





