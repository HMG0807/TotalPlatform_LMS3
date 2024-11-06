const categoryTop = document.querySelector('.categoryContentTop'); // categoryTop 요소 선택
const categoryDetailNavTitle = document.querySelector('.categoryDetailNavTitle'); // categoryDetailNavTitle 요소 선택

// categoryDetailNavTitle의 높이를 categoryTop의 높이로 설정
categoryTop.style.height = `${categoryDetailNavTitle.offsetHeight}px`;