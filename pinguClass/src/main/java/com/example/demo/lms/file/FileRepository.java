package com.example.demo.lms.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.lms.entity.File;

public interface FileRepository extends JpaRepository<File, Integer> {

	/* 파일명으로 파일ID 조회 */
	@Query(value = "SELECT file_id FROM file WHERE file_name = :fileName", nativeQuery = true)
	Integer findFileIdByFileName(@Param("fileName") String fileName);
}
