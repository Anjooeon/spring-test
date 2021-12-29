package co.kr.springboot.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.kr.springboot.mvc.domain.Board;
import co.kr.springboot.mvc.parameter.BoardParameter;

/*게시판 repository*/
@Repository
public interface BoardRepository {
	List<Board> getList();
	Board get(int boardSeq);
	void save(BoardParameter board);
	void saveList(Map<String,Object> paramMap);
	void update(BoardParameter board);
	void delete (int boardSeq);
	
	
}
