
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼들

//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('page').value = this.dataset.page;
	    document.getElementById('searchForm').submit();
	});
});




