//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* header 이벤트 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
const headerDown = document.querySelector(".headerDown");
const header = document.querySelector(".header");
const headerDownHeight = headerDown.querySelector("ul").offsetHeight;
let isScrolling = false;

headerDown.style.height = `${headerDownHeight}px`;

// header Scroll event
document.addEventListener('scroll', () => {
    if (!isScrolling) {
        isScrolling = true;
        requestAnimationFrame(() => {
            const scrollTop = window.scrollY;
            if (scrollTop > 0) {
                headerDown.style.height = "0";
                header.classList.add("color");
            } else {
                headerDown.style.height = `${headerDownHeight}px`;
                header.classList.remove("color");
            }
            isScrolling = false;
        });
    }
});

//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Category 이벤트 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
const categoryBtn = document.querySelector(".categoryBtn");
const categoryContainer = document.querySelector(".categoryContainer");
const windowHeight = window.innerHeight;
const headerHeight = header.offsetHeight;
const categoryContainerHeight = windowHeight - headerHeight;

let categoryOn = false;


categoryBtn.addEventListener('click', function() {
    if (categoryOn === false) {
        categoryContainer.classList.add('on');
        categoryContainer.style.height = "100vh";
        document.querySelector('body').classList.add("stopScolling");
        categoryOn = true;
    } else {
        categoryContainer.classList.remove('on');
        categoryContainer.style.height = "0px";
        document.querySelector('body').classList.remove("stopScolling");
        categoryOn = false;
    }
});
//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-* Modal 이벤트 *-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*

const modalBtn = document.querySelectorAll(".loginBtn");
const modal = document.querySelector(".modalpage");
const closeBtn = document.querySelector(".closeBtn");

// Modal 실행시 스크롤 비활성화 
modalBtn.forEach(btn => {
    btn.addEventListener('click', function() {
        modal.classList.add('on');
        document.querySelector('body').classList.add("stopScolling");
    });
});

closeBtn.addEventListener('click', function() {
    modal.classList.remove('on');
    if (!categoryContainer.classList.contains('on')) {
        document.querySelector('body').classList.remove("stopScolling");
    }
});