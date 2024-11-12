const lecture = document.querySelector("#lectureVideo");
//lecture.currentTime : 현재 재생 시간
//lecture.duration : 총 재생 시간

function timeMove(second){
	lecture.currentTime = 0; //시간 초기화
	lecture.currentTime += second; //시간 변경 (초단위)
}