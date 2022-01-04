package co.kr.springboot.framework.data.domain;

import lombok.Data;

@Data
public class PageRequestParameter<T> {
	private MySQLPageReqeust pageReqeust;
	private T parameter;
	
	public PageRequestParameter(MySQLPageReqeust pageReqeust, T parameter) {
		this.pageReqeust = pageReqeust;
		this.parameter = parameter;
	}
}
