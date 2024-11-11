 function showCancelModal(rgId) {
            // 모달을 열고, 폼의 action 속성을 설정
            document.getElementById('cancelForm').action = '/mypage/courses/cancel/' + rgId;
            
            document.getElementById('cancelModal').style.display = 'block';
        }

        function closeModal() {
            // 모달을 닫음
            document.getElementById('cancelModal').style.display = 'none';
        }

        const page_elements = document.getElementsByClassName("page-link");
        Array.from(page_elements).forEach(function(element) {
            element.addEventListener('click', function() {
                document.getElementById('page').value = this.dataset.page;
                document.getElementById('searchForm').submit();
            });
        });