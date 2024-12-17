package com.cookyuu.morning_routine.domain.auth.dto;

import com.cookyuu.morning_routine.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class SignupDto {

    @Getter
    @AllArgsConstructor
     public static class Request {
         private String name;
         private String loginId;
         private String password;
         private String email;
         private String phoneNumber;

        public Member of() {
            return Member.builder()
                    .name(name)
                    .loginId(loginId)
                    .password(password)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .build();
        }

        public void setEncPassword(String encPassword) {
            this.password = encPassword;
        }
    }
}
