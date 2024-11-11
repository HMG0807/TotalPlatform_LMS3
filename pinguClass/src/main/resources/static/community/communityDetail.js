/*--------------------글/댓글 추천 신고----------------------- */


// 추천 기능이 오류 :  댓글 없으면 원글에서도 오류가 일어남
/* 게시글추천 기능*/
/* 
let Qrecommondreplace = document.querySelector("#Qrecommend");
			
Qrecommondreplace.addEventListener('click', function(){
        if (Qrecommondreplace.src.match(/recommend_off\.png$/)) {
            Qrecommondreplace.src = "/files/recommend_on.png";
        } else {
            Qrecommondreplace.src = "/files/recommend_off.png";
        }
		
});
*/

/*게시글 신고 */
/*
let openModal = document.querySelector("#Qcomplaint");
let complaintModal = document.querySelector("#complaintModal");
let modalBtnBoolean = true;

const complaintimg = document.querySelector("#Qcomplaint");
const complaintBtn = document.querySelector("#complaintBtn");
const modalCloseBtn = document.querySelector(".modalCloseBtn");

openModal.addEventListener('click', function(){
	if(modalBtnBoolean){
		complaintModal.style.display = "block";		
	}
});

modalCloseBtn.addEventListener('click',function(){
	complaintModal.style.display = "none";
});

complaintBtn.addEventListener('click',function(){	
	console.log("click ok");
	if(confirm("해당 유저를 정말 신고하시겠습니까?")){
			complaintModal.style.display = "none";		
			modalBtnBoolean = false;
			alert("정상적으로 신고되었습니다.");
			complaintimg.style.border ="1px solid red";
	}
});
*/
// 나중에 스프링 들어갈때 세션 스토리지 사용해야되요
// 감사합니다~~~:)







/* 댓글 추천 기능*/ 
/*
let Arecommondreplace = document.querySelector("#Arecommend");
			
Arecommondreplace.addEventListener('click', function(){
        if (Arecommondreplace.src.match(/recommend_off\.png$/)) {
            Arecommondreplace.src = "/img/recommend_on.png";
        } else {
            Arecommondreplace.src = "/img/recommend_off.png";
        }
		
});
*/


/*댓글 신고 게시글이랑 중복됨 */

/*
let AopenModal = document.querySelector("#Acomplaint");
let AcomplaintModal = document.querySelector("#complaintModal");
let AmodalBtnBoolean = true;

const Acomplaintimg = document.querySelector("#Acomplaint");
const AcomplaintBtn = document.querySelector("#complaintBtn");
const AmodalCloseBtn = document.querySelector(".modalCloseBtn");

AopenModal.addEventListener('click', function(){
	if(AmodalBtnBoolean){
		AcomplaintModal.style.display = "block";		
	}
});

AmodalCloseBtn.addEventListener('click',function(){
	AcomplaintModal.style.display = "none";
});

AcomplaintBtn.addEventListener('click',function(){	
	console.log("click ok");
	if(confirm("해당 유저를 정말 신고하시겠습니까?")){
			AcomplaintModal.style.display = "none";		
			AmodalBtnBoolean = false;
			alert("정상적으로 신고되었습니다.");
			Acomplaintimg.style.border ="1px solid red";
	}
});
*/

// 나중에 스프링 들어갈때 세션 스토리지 사용해야되요
// 감사합니다~~~:)


// 글 삭제시 alert창
let dele = document.querySelector(".QoptionDelete");
console.log("성공1");
dele.addEventListener('click', function(){
	if(confirm("정말로 삭제?")){
		alert("삭제되었습니다.")
		location.href = this.dataset.uri;
	}
		
});





////////   커뮤니티 댓글 //////////////////////////////////////

// 댓글 삭제
let del = document.querySelectorAll(".deleteAnswer")
for(let el of del){
	el.addEventListener('click', function(){
		if(confirm("정말로 삭제?")){
			location.href = this.dataset.uri;
		}else{
			return false;
		}
	});
};





/*댓글 수정1 
let openModifyModal = document.querySelector(".modify");
let commentModifyModal = document.querySelectorAll(".answerModal");
let modalBtnBoolean = true;*/

/*const complaintimg = document.querySelector("#Qcomplaint");
const complaintBtn = document.querySelector("#complaintBtn");
const modalCloseBtn = document.querySelector(".modalCloseBtn");

openModal.addEventListener('click', function(){
	if(modalBtnBoolean){
		commentModifyModal.style.display = "block";		
	}
});*/

/*modalCloseBtn.addEventListener('click',function(){
	complaintModal.style.display = "none";
});*/

/*complaintBtn.addEventListener('click',function(){	
	console.log("click ok");
	if(confirm("해당 유저를 정말 신고하시겠습니까?")){
			complaintModal.style.display = "none";		
			modalBtnBoolean = false;
			alert("정상적으로 신고되었습니다.");
			complaintimg.style.border ="1px solid red";
	}
});*/



/*  댓글 수정2  */
/*  왜 최상위를 택해서 싹 다 지우면 먹히고 다른건 안돼?*/
/*const modifyBtn = document.querySelector(".modifyBtn"); // 수정버튼


modifyBtn.addEventListener('click', function(e){
	console.log("seccess"); // 이벤트 확인용

	// 원래 댓글 내용 저장
	var original = this.parentNode.parentNode.parentNode
	.childNodes[2].innerText;
	
	// 원래 댓글창 내용 리셋
	this.parentNode.parentNode.parentNode
	.childNodes[3].remove();
		
	this.parentNode.parentNode.parentNode
	.childNodes[2].innerText = "";
	
	
	// 삭제 버튼 제거
	this.parentNode.parentNode.childNodes[3].remove();
	

	
	// 새로운 공간에 원래 댓글창 내용 넣기
	this.parentNode.parentNode.parentNode
	.childNodes[2].innerText ='<textarea cols="10" rows="10" name="modifyBox">'+original+'</textarea>'
	

	
	
	

	
	
})*/










