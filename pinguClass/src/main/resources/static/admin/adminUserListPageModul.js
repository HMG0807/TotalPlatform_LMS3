
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


// 모달(상세)창 열기 > 강사 등록 버튼 클릭시
function viewInstModal(user) {
	document.getElementById('modalUserId').innerText = user.id;
	document.getElementById('modalUserName').innerText = user.name;

	// 모달 표시
	document.getElementById('instructorModal').style.display = "block";
}

// 모달창 닫기
function closeModal() {
	document.getElementById('instructorModal').style.display = "none";
}


// 정지 or 정지해제 버튼 클릭시
function bannedUser(user) {
	if(user.bannedYn == "n"){
		if(confirm(`정말로 \"${user.id}\" 회원을 정지하시겠습니까?`)){
			location.href = "/admin/banned/" + user.userId;
		}
	} else {
		if(confirm(`정말로 \"${user.id}\" 회원을 정지해제 하시겠습니까?`)){
			location.href = "/admin/banncancel/" + user.userId;
		}
	}
}

// 삭제 or 삭제해제 버튼 클릭시
function signoutUser(user) {
	
	if(user.signoutYn == "n"){
		if(confirm(`정말로 \"${user.id}\" 회원을 삭제하시겠습니까?`)){
			location.href = "/admin/signout/" + user.userId;
		}
	} else {
		if(confirm(`정말로 \"${user.id}\" 회원을 삭제해제 하시겠습니까?`)){
			location.href = "/admin/signoutcancel/" + user.userId;
		}
	}
}

// 신고 내역 버튼 클릭시
function reportDetails(userId, reportCount){
	if(reportCount == 0) alert("신고 내역이 없습니다.");
	else location.href = "/admin/reportList/" + userId;
}



