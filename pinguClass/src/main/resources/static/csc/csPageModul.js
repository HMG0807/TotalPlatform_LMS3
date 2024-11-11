const csSideNav = document.querySelector(".csSideNav");
const csSideNavUl = csSideNav.querySelector("ul");
const csSideNavLi = csSideNavUl.querySelectorAll("li");

const csCon = document.querySelector(".csCon");
const csConUl = csCon.querySelector("ul");
const csConLi = csConUl.querySelectorAll(".maintap");

csSideNavLi.forEach((item,index)=>{
    item.addEventListener('click',function(){
        csConLi.forEach(con =>{
            con.classList.remove('on');
        });
        console.log(index);
        csConLi[index].classList.add('on');
    });
});




///////////  페이징  ////////////////////////////////////////////
const page_elements = document.getElementsByClassName("page-link");
		Array.from(page_elements).forEach(function(element) {
    		element.addEventListener('click', function() {
        		document.getElementById('page').value = this.dataset.page;
        		document.getElementById('pagingForm').submit();
    			});
			});

