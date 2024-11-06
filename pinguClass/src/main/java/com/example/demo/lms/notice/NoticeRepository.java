package com.example.demo.lms.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Notice;
import com.example.demo.lms.entity.User;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
	
	@Query(value = "SELECT * FROM notice n WHERE n.delete_yn = 'n'",   nativeQuery = true)
	List<Notice> findAll();
	//Page<Notice> findByTitleContaining(String keyword, Pageable pageable);
	
//	@Query("select distinct n " 
//            + "from notice n " 
//            + "inner join n.admin a " 
//            + "where n.title like %:kw% " 
//            + "or a.id like %:kw%")
//	Page<Notice> findAllByTitleOrId(@Param("kw") String kw, Pageable pageable);
	
	//@Query("select distinct n " 
    //        + "from notice n " 
     //       + "inner join n.admin a " 
     //       + "where a.id like %:kw%")
	//Page<Notice>findAllById(@Param("kw") String kw, Pageable pageable);
	
	//Page<Notice> findByUser(User user, Pageable pageable);
	

}
