package co.kr.springboot.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.kr.springboot.configuration.GlobalConfig;
import co.kr.springboot.configuration.http.BaseResponse;
import co.kr.springboot.framework.web.bind.annotaion.RequestConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
@Api(tags = "파일 API")
public class FileController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GlobalConfig config;

	/**
	 * 업로드 리턴
	 * @return
	 */
	@GetMapping
	@RequestConfig
	@ApiOperation(value = "업로드", notes = "")
	public BaseResponse<Boolean> save() {
		logger.debug("config : {}", config);
		String uploadFilePath = config.getUploadFilePath();
		logger.debug("config : {}", uploadFilePath);
		return new BaseResponse<Boolean>(true);
	}

}
