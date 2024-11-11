const categiryBtn = document.querySelector(".categoryBtn");
const backgroundShadow = document.querySelector(".backgroundShadow");
const categoryContainer = document.querySelector(".categoryContainer");
const webBody = document.querySelector("body");
const headerHeight = document.querySelector("header").offsetHeight;
const mainContainer = document.querySelector("main");

let navBoolean = true;



mainContainer.style.marginTop = (headerHeight+5)+"px";



categiryBtn.addEventListener('click',function(e){
    e.preventDefault;
    if(navBoolean==true){
        categoryContainer.style.top = headerHeight + "px";
        backgroundShadow.classList.add('navOn');
        categoryContainer.classList.add('navOn');
        webBody.classList.add("scrollOff");

        navBoolean = false;
    }else{
        categoryContainer.style.top = "";
        backgroundShadow.classList.remove('navOn');
        categoryContainer.classList.remove('navOn');
        webBody.classList.remove('scrollOff');
        
        navBoolean = true;
    }

})

/* 준호님 mainPage.js 파일 */
function getCookie(name) { 
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)'); return value? value[2] : null;
} 

function deleteCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}	

 
const loginNav = document.querySelector(".loginNav");
    
if(getCookie( 'jwtToken') !==null){
	loginNav.innerHTML ="<li><a href="+"#"+">마이페이지</a></li><li><a id = "+"logout "+"href="+"/user/logout"+">로그아웃</a></li>";
}else{
	loginNav.innerHTML ="<li><a href="+"/user/login"+">로그인</a></li><li><a href="+"http://192.168.17.254:8080/signup" +">회원가입</a></li>";
}

const searchBtn = document.querySelector(".searchBtn");
const keywordForm = document.querySelector("#keywordForm");

searchBtn.addEventListener("click", function(){
	keywordForm.submit();
})
  

let logoutBtn = document.querySelector("#logout");
    
if(getCookie( 'jwtToken') !==null){
	logoutBtn.addEventListener("click", function(){
	deleteCookie('jwtToken');
	location.href = "/"

})
  
}    


  