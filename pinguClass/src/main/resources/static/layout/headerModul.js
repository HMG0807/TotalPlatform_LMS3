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


const searchBtn = document.querySelector(".searchBtn");
const keywordForm = document.querySelector("#keywordForm");

searchBtn.addEventListener("click", function(){
	keywordForm.submit();
})