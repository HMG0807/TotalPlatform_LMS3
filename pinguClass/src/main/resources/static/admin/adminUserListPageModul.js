
const rows = document.querySelectorAll(".contentRows");
const searchBtn = document.querySelector("#searchBtn");

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



// 모달(상세)창 열기
function viewInstModal(user) {
	console.log(user);
	document.getElementById('modalUserId').innerText = user.id;
	document.getElementById('modalUserName').innerText = user.name;

	// 모달 표시
	document.getElementById('instructorModal').style.display = "block";
}

// 모달창 닫기
function closeModal() {
	document.getElementById('instructorModal').style.display = "none";
}









