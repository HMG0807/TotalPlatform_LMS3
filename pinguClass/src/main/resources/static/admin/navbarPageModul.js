const navBtns = document.querySelectorAll(".navBtn");

/* 왼쪽 네비 바 클릭했을 때 페이지 이동 */
navBtns[0].addEventListener('click', function() {
	location.href = "/admin/userList";
});
navBtns[1].addEventListener('click', function() {
	location.href = "/admin/courseList";
});
navBtns[2].addEventListener('click', function() {
	location.href = "/admin/communityList";
});

navBtns[3].addEventListener('click', function() {
	location.href = "/admin/adminNoticeList";
});

navBtns[4].addEventListener('click', function() {
	location.href = "/admin/adminOneToOneList";
});