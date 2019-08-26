package com.hao.notificationcenter.Service;


import com.hao.notificationcenter.Model.VerificationCode;

public interface VerificationCodeService {

	VerificationCode generateCode(String phone);

	String matcheCodeAndGetPhone(String key, String code, Boolean delete, Integer second);
}
