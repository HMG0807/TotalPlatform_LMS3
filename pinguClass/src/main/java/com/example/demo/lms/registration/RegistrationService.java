package com.example.demo.lms.registration;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.lms.entity.Registration;
import com.example.demo.lms.entity.User;
import com.example.demo.lms.paging.EzenPaging;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegistrationService {

	/*수강 중인 강좌 내역 조회 및 취소 기능을 위한 작성 코드 - 남동현 */
    private final RegistrationRepository registrationRepository;
    
    public List<Registration> getRegistrationsByUser(Integer userId, int startNo, int pageSize) {
        return registrationRepository.findLimitStartIdx(userId, startNo, pageSize);
    }

    public int countRegistrationByUser(Integer userId) {
        return registrationRepository.countRegistrationByUser(userId);
    }

    public void cancelRegistration(int rgId) {
        registrationRepository.deleteById(rgId); 
    }

    
    
    
    /*수강 중인 강좌 내역 조회 및 취소 기능을 위한 작성 코드 end - 남동현 */
    
    
    
    
    
}
