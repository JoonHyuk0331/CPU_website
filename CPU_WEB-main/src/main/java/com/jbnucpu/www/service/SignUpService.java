package com.jbnucpu.www.service;

import com.jbnucpu.www.dto.SignUpDTO;
import com.jbnucpu.www.entity.UserEntity;
import com.jbnucpu.www.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SignUpService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Boolean processSignUp(SignUpDTO signUpDTO){

        String studentNumber = signUpDTO.getStudentnumber();
        String password = signUpDTO.getPassword();
        String college = signUpDTO.getCollege();
        String phoneNumber = signUpDTO.getPhonenumber();

        // 학번 유효한지(길이, 20으로 시작하는지)
        if(!studentNumber.matches("^20\\d{7}$")){
            System.out.println("학번 잘못입력함 새꺄");
            return false;
        }

        // 비밀번호 유효 (길이, 특수문자 안되는 값 못 넣게 등)
        if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d).{8,16}$")){
            System.out.println("비번 똑바로 입력하셈");
            return false;
        }

        // 단과대학 유효한지
        if(college == null){
            return false;
        }else if(college.isEmpty()){
            return false;
        }else if(college.isBlank()){
            return false;
        }

        // 전화번호 유효한지
//        if(!phoneNumber.matches("^0(2|1[0-9]{2}|[0-9]{3})-[0-9]{3,4}-[0-9]{4}$\n")){
//            phoneNumber = phoneNumber.replaceAll("[^0-9]","");
//            if(phoneNumber.length() == 11)
//            }
//        }

        // 학과는 일단 검증 X 서버단에서 선택할 수 있는 학과 보기 제공

        //이미 존재하는지 검증
        Boolean isExist = userRepository.existsByStudentnumber(signUpDTO.getStudentnumber());

        if (isExist) {
            //존재한다면 false 응답
            return false;
        }


        //암호화
        signUpDTO.setPassword(bCryptPasswordEncoder.encode(password));
        //judgement key setting


        UserEntity userEntity = signUpDTO.toEntity();

        userRepository.save(userEntity);

        return true;
    }
}
