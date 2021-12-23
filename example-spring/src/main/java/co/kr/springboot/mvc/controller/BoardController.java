package co.kr.springboot.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.springboot.mvc.domain.Board;
import co.kr.springboot.mvc.repository.BoardRepository;
import co.kr.springboot.mvc.service.BoardService;

/*게시판 service*/
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	/*목록 리턴*/
	@GetMapping("/list")
	public List<Board> getList(){
		System.out.println("Controller");
		return boardService.getList();
	}
	/*상세정보 리턴*/
	@GetMapping("/{boardSeq}")
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}
	@GetMapping("/save")
	/*저장/업데이트 처리*/
	public int save(Board parameter) {
		boardService.save(parameter);
		return parameter.getBoardSeq();
	}
	/*삭제 처리*/
	@GetMapping("/delete/{boardSeq}")
	public boolean delete (@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return false;
		}
		boardService.delete(boardSeq);
		return true;
	}
	
}
