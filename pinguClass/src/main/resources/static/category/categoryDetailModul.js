// MAIN SLIDE EVENT 
//slideEvent(".mainSlideContainer");


//SECTION SLIDE EVENT
//sectionslideEvent(".section");


/* 구매하기 버튼 클릭시 강의리스트 페이지로 이동 > 한민기 추가 */
function coursrList(courseId){
	location.href = '/course/lectureList/' + courseId;
}