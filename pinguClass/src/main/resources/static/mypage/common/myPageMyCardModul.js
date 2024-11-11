let myInfoDiv = document.querySelector('#myInfo');

/* 마이 카드 프로필 클릭시 > 회원정보 수정 */
myInfoDiv.addEventListener('click', function(){
	location.href = "/mypage/edit";
});
