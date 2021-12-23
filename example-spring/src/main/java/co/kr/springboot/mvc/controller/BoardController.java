package co.kr.springboot.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.springboot.configuration.exception.BaseException;
import co.kr.springboot.configuration.http.BaseResponse;
import co.kr.springboot.configuration.http.BaseResponseCode;
import co.kr.springboot.mvc.domain.Board;
import co.kr.springboot.mvc.parameter.BoardParameter;
import co.kr.springboot.mvc.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 게시판 Controller
 * @author 안주연
 * */
@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {
	@Autowired
	BoardService boardService;
	/**
	 * 목록 리턴
	 * @author 안주연
	 * */
	@GetMapping("/list")
	@ApiOperation(value = "목록 조회", notes = "게시물에 모든 목록을 조회할 수 있습니다.")
	
	public BaseResponse<List<Board>> getList(){
		return new BaseResponse<List<Board>>(boardService.getList());
	}
	/**
	 * 상세정보 리턴
	 * @author 안주연
	 * */
	@GetMapping("/{boardSeq}")
	@ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="boardSeq", value="게시물 번호", example = "1")
	})
	public BaseResponse<Board> get(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		
		if(board == null) {
			throw new BaseException(BaseResponseCode.Data_IS_NULL, new String[] {"게시물"});
		}
		
		return new BaseResponse<Board>(boardService.get(boardSeq));
	}
	/**
	 * 저장/업데이트 처리 
	 * @author 안주연
	 * */
	@PutMapping
	@ApiOperation(value = "등록/ 수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트 가능합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="boardSeq", value="게시물 번호", example = "1"),
		@ApiImplicitParam(name ="title", value="제목", example = "spring"),
		@ApiImplicitParam(name ="contents", value="내용", example = "spring 강좌")
	})
	public BaseResponse<Integer> save(BoardParameter parameter) {
		/*제목 필수 체크*/
		if(StringUtils.isEmpty(parameter.getTitle())) {
			throw new BaseException(BaseResponseCode.VALICATE_REQUIRED, new String[] {"title", "제목"});
		}
		if(StringUtils.isEmpty(parameter.getContents())) {
			throw new BaseException(BaseResponseCode.VALICATE_REQUIRED, new String[] {"contents", "내용"});
		}
		
		boardService.save(parameter);
		return new BaseResponse<Integer>(parameter.getBoardSeq());
	}
	/**
	 * 삭제 처리 
	 * @author 안주연
	 * */
	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name ="boardSeq", value="게시물 번호", example = "1")
	})
	public BaseResponse<Boolean> delete (@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return new BaseResponse<Boolean>(false);
		}
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
	
}
