
const rows = document.querySelectorAll(".contentRows");
const searchBtn = document.querySelector("#searchBtn"); //검색 버튼
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼들
var userName; // 강사 등록할 유저 이름

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
function viewInstModal(id, name, userId) {
	document.getElementById('modalUserId').innerText = id;
	document.getElementById('modalUserName').innerText = name;
	document.getElementById('userId').value = userId;
	userName = name;

	// 모달 표시
	document.getElementById('instructorModal').style.display = "block";
}

// 모달창 닫기
function closeModal() {
	document.getElementById('instructorModal').style.display = "none";
}

// 모달에서 강사 등록 버튼 클릭시
function instSubmit(){
	
	let imgVal = document.getElementById('instImg').value;
	
	// 파일을 등록 하지 않았을 때
	if(imgVal == ""){
		alert("파일을 등록해주세요.");
		return;
	}
	
	// 이미지가 아닌 파일을 올렸을 때
	if(imgVal.substr(imgVal.length-3).toLowerCase() != "png" &&
		imgVal.substr(imgVal.length-3).toLowerCase() != "jpg" &&
		imgVal.substr(imgVal.length-3).toLowerCase() != "jpeg" &&
		imgVal.substr(imgVal.length-3).toLowerCase() != "gif"){
		alert("이미지 형식에 맞는 파일을 등록해주세요.");
		return;
	}
	
	// 강사 등록
	if(confirm(`정말로 \"${userName}\" 회원을 강사로 등록하시겠습니까?`)){
		document.getElementById('searchForm2').submit();
		alert("강사 등록이 완료되었습니다!");
	}
	
}


// 정지 or 정지해제 버튼 클릭시
function bannedUser(bannedYn, id, userId) {
	if(bannedYn == "n"){
		if(confirm(`정말로 \"${id}\" 회원을 정지하시겠습니까?`)){
			location.href = "/admin/banned/" + userId;
		}
	} else {
		if(confirm(`정말로 \"${id}\" 회원을 정지해제 하시겠습니까?`)){
			location.href = "/admin/banncancel/" + userId;
		}
	}
}

// 삭제 or 삭제해제 버튼 클릭시
function signoutUser(signoutYn, id, userId) {
	
	if(signoutYn == "n"){
		if(confirm(`정말로 \"${id}\" 회원을 삭제하시겠습니까?`)){
			location.href = "/admin/signout/" + userId;
		}
	} else {
		if(confirm(`정말로 \"${id}\" 회원을 삭제해제 하시겠습니까?`)){
			location.href = "/admin/signoutcancel/" + userId;
		}
	}
}

// 신고 내역 버튼 클릭시
function reportDetails(userId, reportCount){
	if(reportCount == 0) alert("신고 내역이 없습니다.");
	else location.href = "/admin/reportList/" + userId + "/0";
}



