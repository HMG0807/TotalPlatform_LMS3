const lecture = document.querySelector("#lectureVideo");
//lecture.currentTime : 현재 재생 시간
//lecture.duration : 총 재생 시간

function timeMove(second){
	lecture.currentTime = 0;
	lecture.currentTime += second;
}