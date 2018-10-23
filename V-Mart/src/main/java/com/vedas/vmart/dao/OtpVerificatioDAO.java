package com.vedas.vmart.dao;

import java.util.List;

import com.vedas.vmart.model.OtpVerification;
import com.vedas.vmart.model.OtpVerifyList;

public interface OtpVerificatioDAO {
	
	public List<OtpVerifyList> verify(OtpVerification otp);

}
