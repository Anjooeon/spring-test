package co.kr.springboot.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.springboot.framework.data.domain.PageRequestParameter;
import co.kr.springboot.mvc.domain.Board;
import co.kr.springboot.mvc.parameter.BoardParameter;
import co.kr.springboot.mvc.parameter.BoardSearchParameter;
import co.kr.springboot.mvc.repository.BoardRepository;

/**
 * 게시판 Service
 * @author 안주연
 * */
@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository;
	/*목록 리턴*/
	public List<Board> getList(PageRequestParameter<BoardSearchParameter> pageRequestParameter){
		System.out.println("service");
		return boardRepository.getList(pageRequestParameter);
	}
	/*상세정보 리턴*/
	public Board get(int boardSeq) {
		return boardRepository.get(boardSeq);
	}
	/*저장/업데이트 처리*/
	public void save(BoardParameter parameter) {
		Board board= boardRepository.get(parameter.getBoardSeq());
		if(board == null) {			
			boardRepository.save(parameter);
		}else {
			boardRepository.update(parameter);
		}
	}
	/*저장/업데이트 처리*/
	public void save1(List<BoardParameter> list) {
		for(BoardParameter parameter : list) {
			boardRepository.save(parameter);
		}
	}
	/*저장/업데이트 처리*/
	public void saveList2(List<BoardParameter> list) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardList", list);
		boardRepository.saveList(paramMap);
	}
	/*삭제 처리*/
	public void delete (int boardSeq) {
		boardRepository.delete(boardSeq);
	}
	
}
