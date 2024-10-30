package com.example.demo.lms.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Report;

public interface AdminReportRepository extends JpaRepository<Report, Integer> {

	/* ID로 신고 내역 조회 */
	@Query(value = "SELECT * FROM report r WHERE r.reportee_user_id = :id limit :start, :idx", nativeQuery = true)
	List<Report> findLimitStartIdx(@Param("id") int userId, @Param("start") int start, @Param("idx") int pageSize);
	
	@Query(value = "SELECT count(*) FROM report r WHERE r.reportee_user_id = :id", nativeQuery = true)
	int countReport(@Param("id") int userId);
}
