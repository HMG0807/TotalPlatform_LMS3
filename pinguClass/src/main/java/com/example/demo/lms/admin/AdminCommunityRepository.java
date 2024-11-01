package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Community;
import com.example.demo.lms.entity.User;

public interface AdminCommunityRepository extends JpaRepository<Community, Integer> {

	/* 커뮤니티 제목으로 커뮤니티 작성글 내역 조회 */
	@Query(value = "SELECT * FROM community WHERE title like %:title% limit :start, :idx", nativeQuery = true)
	List<Community> findCommunityLimitStartIdx(@Param("title") String title, @Param("start") int start, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM community where title like %:kw%" , nativeQuery = true)
	int countCommunityByKeyword(@Param("kw") String kw);
	
	

	/* 커뮤니티 관리 검색 기능 */
	/********************************** 글제목 또는 작성자로 커뮤니티 글 조회 **********************************/
	@Query(value = "SELECT c.title,u.name c left join user u on  c.user_id = u.user_id where c.title like %:kw% or u.name like like %:kw% limit :start, :idx", nativeQuery = true) // 커뮤니티와 유저 조인
	List<Community> findAllByKeyword(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);
	
	
	/********************************** 글제목으로 커뮤니티 글 조회 **********************************/
	@Query(value = "SELECT * FROM community c where c.title like %:kw% limit :start, :idx", nativeQuery = true)
	List<Community> findAllByCommunityTitle(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	
	/********************************** 이름으로 작성글 조회 **********************************/
	@Query(value = "SELECT * FROM user u where u.name like %:kw% limit :start, :idx", nativeQuery = true)
	List<Community> findAllByUserName(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	
}
