document.addEventListener('DOMContentLoaded', function() {
const page_elements = document.getElementsByClassName("page-link"); //페이징 버튼 배열



//페이징 버튼 클릭시
Array.from(page_elements).forEach(function(element) {
	element.addEventListener('click', function() {
		document.getElementById('kw').value = document.getElementById('search_kw').value;
		document.getElementById('page').value = this.dataset.page;
	    	document.getElementById('searchForm').submit();
	});
});



 // 수정 버튼 클릭 이벤트
    document.querySelectorAll('.editButton').forEach(button => {
        button.addEventListener('click', function() {
            const cmId = this.getAttribute('data-cmid');
            const title = this.getAttribute('data-title');
            const content = this.getAttribute('data-content');
            showEditForm(cmId, title, content);
        });
    });

    // 삭제 버튼 클릭 이벤트
    document.querySelectorAll('.deleteButton').forEach(button => {
        button.addEventListener('click', function() {
            const cmId = this.getAttribute('data-cmid');
            if (confirm('정말로 이 글을 삭제하시겠습니까?')) {
                deleteCommunity(cmId);
            }
        });
    });

    // 수정 폼 제출 이벤트
    document.getElementById('communityEditForm').addEventListener('submit', function(e) {
        e.preventDefault();
        const cmId = document.getElementById('editCmId').value;
        const title = document.getElementById('editTitle').value;
        const content = document.getElementById('editContent').value;
        updateCommunity(cmId, title, content);
    });

    // 수정 취소 버튼 클릭 이벤트
    document.getElementById('cancelEdit').addEventListener('click', function() {
        document.getElementById('editForm').style.display = 'none';
    });

    // 작성글/작성댓글 탭 전환
    document.getElementById('showPosts').addEventListener('click', function(e) {
        e.preventDefault();
        document.getElementById('postsContent').style.display = 'block';
        document.getElementById('commentsContent').style.display = 'none';
    });

    document.getElementById('showComments').addEventListener('click', function(e) {
        e.preventDefault();
        document.getElementById('postsContent').style.display = 'none';
        document.getElementById('commentsContent').style.display = 'block';
    });

    // 전체 선택 체크박스
    document.getElementById('selectAllPosts').addEventListener('change', function() {
        const checkboxes = document.querySelectorAll('.boxLayout input[type="checkbox"]');
        checkboxes.forEach(checkbox => {
            checkbox.checked = this.checked;
        });
        updateSelectedCount();
    });

    // 개별 체크박스 변경 시 선택된 항목 수 업데이트
    document.querySelectorAll('.boxLayout input[type="checkbox"]').forEach(checkbox => {
        checkbox.addEventListener('change', updateSelectedCount);
    });

    // 삭제하기 버튼 클릭 이벤트
    document.getElementById('deleteButton').addEventListener('click', function() {
        const selectedPosts = document.querySelectorAll('.boxLayout input[type="checkbox"]:checked');
        if (selectedPosts.length > 0 && confirm(`선택한 ${selectedPosts.length}개의 항목을 삭제하시겠습니까?`)) {
            // 여기에 선택된 항목 삭제 로직 구현
            console.log('선택된 항목 삭제');
        }
    });
});

function showEditForm(cmId, title, content) {
    document.getElementById('editCmId').value = cmId;
    document.getElementById('editTitle').value = title;
    document.getElementById('editContent').value = content;
    document.getElementById('editForm').style.display = 'block';
}

function updateCommunity(cmId, title, content) {
    fetch(`/mypage/community/edit/${cmId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `title=${encodeURIComponent(title)}&content=${encodeURIComponent(content)}`
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('수정에 실패했습니다.');
        }
    });
}

function deleteCommunity(cmId) {
    fetch(`/mypage/community/delete/${cmId}`, {
        method: 'POST',
    }).then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('삭제에 실패했습니다.');
        }
    });
}

function updateSelectedCount() {
    const selectedCount = document.querySelectorAll('.boxLayout input[type="checkbox"]:checked').length;
    document.getElementById('postSelectedCount').textContent = selectedCount > 0 ? `(${selectedCount})` : '';
}