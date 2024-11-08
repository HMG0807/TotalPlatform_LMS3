package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Community;


public interface AdminCommunityRepository extends JpaRepository<Community, Integer> {
	
	
	/* 커뮤니티 관리 검색 기능 */
	/********************************** 글제목 또는 작성자로 커뮤니티 글 조회 **********************************/
	@Query(value = "SELECT c.* FROM community c inner join user u on c.user_id = u.user_id where c.title like %:kw% or u.name like %:kw% limit :start, :idx", nativeQuery = true) 	
	List<Community> findAllByKeyword(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(c.cm_id) FROM community c inner join user u on c.user_id = u.user_id where c.title like %:kw% or u.name like %:kw%", nativeQuery = true)
	int countCommunityByKeyword(@Param("kw") String kw);
	
	
	/********************************** 글제목으로 커뮤니티 글 조회 **********************************/
	@Query(value = "SELECT * FROM community c where c.title like %:kw% limit :start, :idx", nativeQuery = true)
	List<Community> findAllByCommunityTitle(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	@Query(value = "SELECT count(*) FROM community c where c.title like %:kw%" , nativeQuery = true)
	int countCommunityByTitle(@Param("kw") String kw);
	
	/********************************** 이름으로 작성글 조회 **********************************/
	@Query(value = "SELECT c.* FROM community c inner join user u on c.user_id = u.user_id where u.name like %:kw% limit :start, :idx", nativeQuery = true)
	List<Community> findAllByUserName(@Param("kw") String keyword, @Param("start") int startNo, @Param("idx") int pageSize);

	
	@Query(value = "SELECT count(cm_id) FROM community c inner join user u on c.user_id = u.user_id where u.name like %:kw%", nativeQuery = true)
    int countCommunityByName(@Param("kw") String kw);
	
}
