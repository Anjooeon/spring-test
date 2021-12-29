package co.kr.springboot.mvc.parameter;

import java.util.List;

import co.kr.springboot.mvc.domain.BoardType;
import lombok.Data;

@Data
public class BoardSearchParameter {
	private String keyword;
	private List<BoardType> boardTypes;
	
	public BoardSearchParameter() {
		
	}
}
