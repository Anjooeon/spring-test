package co.kr.springboot.framework.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MySQLPageReqeust {
	private int page;
	private int size;
	
	@JsonIgnore
	@ApiModelProperty(hidden= true)
	private int limit;
	
	@JsonIgnore
	@ApiModelProperty(hidden= true)
	private int offset;
	
	public MySQLPageReqeust(int page, int size, int limit, int offset) {
		this.page = page;
		this.size = size;
		this.limit = limit;
		this.offset = offset; 
	}
	
}
