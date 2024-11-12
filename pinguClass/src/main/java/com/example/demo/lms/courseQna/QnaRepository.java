package com.example.demo.lms.courseQna;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.Qna;
import com.example.demo.lms.entity.User;

public interface QnaRepository extends JpaRepository<Qna, Integer> {
    
    @Query(value = "SELECT * FROM qna q WHERE q.user_id = :userId AND q.delete_yn = 'N' ORDER BY q.last_update DESC LIMIT :start, :size", 
           nativeQuery = true)
    List<Qna> findByUserIdOrderByLastUpdateDesc(@Param("userId") Integer userId, 
                                              @Param("start") int startNo, 
                                              @Param("size") int pageSize);

    @Query(value = "SELECT COUNT(*) FROM qna q WHERE q.user_id = :userId AND q.delete_yn = 'N'", 
           nativeQuery = true)
    int countByUserId(@Param("userId") Integer userId);
}