package com.example.demo.lms.file;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileService {
	@Autowired
	private FileRepository fr;
	
	public void save(HttpServletRequest request, MultipartFile files) throws Exception {
		com.example.demo.lms.entity.File f = new com.example.demo.lms.entity.File();

		String sourceFileName = files.getOriginalFilename();
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl = "C:\\Users\\504호\\Desktop\\TEST\\";
		do {
			destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + sourceFileNameExtension;
			destinationFile = new File(fileUrl + destinationFileName);
		} while (destinationFile.exists());
			
		destinationFile.getParentFile().mkdirs();
		files.transferTo(destinationFile);
			
		f.setFileName(destinationFileName);
		f.setOriginalFileName(sourceFileName);
		f.setFileUrl(fileUrl);
		f.setFileType(sourceFileNameExtension);
		System.out.println(fr);
		this.fr.save(f);
	}
}
