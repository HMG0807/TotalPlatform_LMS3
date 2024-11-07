/*--------------------글/댓글 추천 신고----------------------- */



/* 게시글추천 기능*/ 
let Qrecommondreplace = document.querySelector("#Qrecommend");
			
Qrecommondreplace.addEventListener('click', function(){
        if (Qrecommondreplace.src.match(/recommend_off\.png$/)) {
            Qrecommondreplace.src = "/files/recommend_on.png";
        } else {
            Qrecommondreplace.src = "/files/recommend_off.png";
        }
		
});

/*게시글 신고 */
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
// 나중에 스프링 들어갈때 세션 스토리지 사용해야되요
// 감사합니다~~~:)







/* 댓글 추천 기능*/ 
let Arecommondreplace = document.querySelector("#Arecommend");
			
Arecommondreplace.addEventListener('click', function(){
        if (Arecommondreplace.src.match(/recommend_off\.png$/)) {
            Arecommondreplace.src = "/img/recommend_on.png";
        } else {
            Arecommondreplace.src = "/img/recommend_off.png";
        }
		
});


/*댓글 신고 게시글이랑 중복됨 */

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

// 나중에 스프링 들어갈때 세션 스토리지 사용해야되요
// 감사합니다~~~:)



// 커뮤니티 글 더보기 드롭다운(수정/삭제)

const dropdown = document.querySelector(".option");
let optiondropdown = document.querySelector(".optionDropdown");

dropdown.addEventListener('click', function(){
	optiondropdown.style.display = "block";
});


// 삭제시 alert창
let del = document.querySelectorAll(".delete")
for(let el of del){
	el.addEventListener('click', function(){
		if(confirm("정말로 삭제?")){
			location.href = this.dataset.uri;
		}
	});
};

const drop = document.querySelector(".comment");
let down = document.querySelector(".commentBox");

drop.addEventListener('click', function(){
	down.style.display = "block";
});


// 삭제시 alert창
let dele = document.querySelectorAll(".getout")
for(let le of dele){
	le.addEventListener('click', function(){
		if(confirm("정말로 삭제?")){
			location.href = this.dataset.uri;
		}
	});
};













