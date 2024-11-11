
const lectures = document.getElementsByClassName("lectureContent"); //페이징 버튼들

//강의 클릭시
Array.from(lectures).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('lectureId').value = this.dataset.page;
	    document.getElementById('lectureForm').submit();
	});
});