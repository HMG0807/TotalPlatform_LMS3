package com.example.demo.lms.mypage;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidFileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

	@Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return file != null && !file.isEmpty();
    }
}





