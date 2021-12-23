package co.kr.springboot.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.kr.springboot.mvc.domain.Board;
import co.kr.springboot.mvc.parameter.BoardParameter;
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
	public List<Board> getList(){
		System.out.println("service");
		return boardRepository.getList();
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
	/*삭제 처리*/
	public void delete (int boardSeq) {
		boardRepository.delete(boardSeq);
	}
	
}
