package co.kr.springboot.configuration.exception;

import co.kr.springboot.configuration.http.BaseResponseCode;

public class BaseException extends AbstractBaseException{
	private static final long serialVersionUID = 8342235231880246631L;
	
	public BaseException() {
	}
	
	public BaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	public BaseException(BaseResponseCode responseCode, String[] args) {
		this.responseCode = responseCode;
		this.args = args;
	}
}
