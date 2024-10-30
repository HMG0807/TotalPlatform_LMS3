
// 삭제 or 삭제해제 버튼 클릭시
function deleteLecture(lecture, courseId, page) {
	
	if(lecture.deleteYn == "n"){
		if(confirm(`정말로 \"${lecture.title}\" 강의를 삭제하시겠습니까?`)){
			location.href = `/admin/deletelecture/${lecture.lectureId}/${courseId}/${page}`;
		}
	} else {
		if(confirm(`정말로 \"${lecture.title}\" 강의를 삭제해제 하시겠습니까?`)){
			location.href = `/admin/deletelecture/cancel/${lecture.lectureId}/${courseId}/${page}`;
		}
	}
}


