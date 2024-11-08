	function getCookie(name) { 
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)'); return value? value[2] : null; } 

	function deleteCookie(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;'; }	

    
    const loginNav = document.querySelector(".loginNav");
    
    if(getCookie( 'jwtToken') !==null){
		console.log("확인");
		loginNav.innerHTML ="<a id = "+"logout "+"href="+"/user/logout"+">로그아웃</a>";
	}else{
		console.log("확인");
		loginNav.innerHTML ="<a href="+"/user/login"+">로그인</a><a href="+"http://192.168.17.254:8080/signup" +">회원가입</a>";
	}
    
    let logoutBtn = document.querySelector("#logout");
    
    logoutBtn.addEventListener("click", function(){
	
		deleteCookie('jwtToken');
		location.href = "/"
   })