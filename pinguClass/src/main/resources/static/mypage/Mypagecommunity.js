document.addEventListener("DOMContentLoaded", function() {
    const showPostsButton = document.getElementById("showPosts");
    const showCommentsButton = document.getElementById("showComments");

    // 기본적으로 '작성 글 목록'을 먼저 표시
    loadPosts();  // 글 목록을 로딩

    showPostsButton.addEventListener("click", function() {
        loadPosts();
    });

    showCommentsButton.addEventListener("click", function() {
        loadComments();
    });

    // 작성 글 목록을 비동기적으로 로딩
    function loadPosts() {
        // 버튼 활성화 처리
        showPostsButton.classList.add("active");
        showCommentsButton.classList.remove("active");

        // 서버로부터 작성 글 목록을 비동기적으로 요청
        fetch('/mypage/community/posts')
            .then(response => response.json())
            .then(data => {
                const postsContent = document.getElementById("postsContent");
                const commentsContent = document.getElementById("commentsContent");

                // 작성 글 목록을 표시
                postsContent.innerHTML = `
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>내용</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${data.posts.map((post, index) => `
                                <tr>
                                    <td>${index + 1}</td>
                                    <td><a href="/mypage/community/${post.cmId}">${post.title}</a></td>
                                    <td>${post.content}</td>
                                    <td>${new Date(post.lastUpdate).toLocaleDateString()}</td>
                                </tr>
                            `).join('')}
                        </tbody>
                    </table>
                `;
                
                // '작성 글 목록' 보이고, '작성 댓글 목록' 숨기기
                postsContent.style.display = 'block';
                commentsContent.style.display = 'none';
            })
            .catch(error => console.error('Error loading posts:', error));
    }

    // 작성 댓글 목록을 비동기적으로 로딩
    function loadComments() {
        // 버튼 활성화 처리
        showCommentsButton.classList.add("active");
        showPostsButton.classList.remove("active");

        // 서버로부터 작성 댓글 목록을 비동기적으로 요청
        fetch('/mypage/community/comments')
            .then(response => response.json())
            .then(data => {
                const postsContent = document.getElementById("postsContent");
                const commentsContent = document.getElementById("commentsContent");

                // 작성 댓글 목록을 표시
                commentsContent.innerHTML = `
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>내용</th>
                                <th>작성일</th>
                            </tr>
                        </thead>
                        <tbody>
                            ${data.comments.map((comment, index) => `
                                <tr>
                                    <td>${index + 1}</td>
                                    <td>${comment.content}</td>
                                    <td>${new Date(comment.lastUpdate).toLocaleString()}</td>
                                </tr>
                            `).join('')}
                        </tbody>
                    </table>
                `;
                
                // '작성 댓글 목록' 보이고, '작성 글 목록' 숨기기
                postsContent.style.display = 'none';
                commentsContent.style.display = 'block';
            })
            .catch(error => console.error('Error loading comments:', error));
    }
});
