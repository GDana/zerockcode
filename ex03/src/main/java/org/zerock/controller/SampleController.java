package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

	@GetMapping(value = "/getText", produces = "text/plain; charset = UTF-8") 
	public String getText() {
		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
	/*
	 * 기존의 @Controller는 문자열을 반환하는 경우에는 JSP 파일의 이름으로 처리하지만
	 * @RestController는 순수한 데이터가 된다
	 */
	
	@GetMapping(value = "/getSample")
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");	
	}
	/* 
	 * XML과 JSON 방식의 데이터 생성
	 * 기본은 xml 타입의 데이터로 출력된다
	 * .json을 붙이면(http://localhost:8181/sample/getSample.json) json 타입의 데이터로 출력된다	  
	 */
	
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + " Last")).collect(Collectors.toList());
	}
	// 내부적으로 1부터 10미만까지의 루프를 처리하면서 SampleVO 객체를 만들어서 List<SampleVO>로 만든다
	
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));
		
		return map;
	}
	
	@GetMapping(value = "/check", params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		SampleVO vo = new SampleVO(0, "" + height, "" + weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath(
			@PathVariable("cat") String cat, @PathVariable("pid") Integer pid) {
		return new String[] {"category: " + cat, "productid: " + pid};
	}
	/*
	 * @PathVariable 을 적용하고 싶은 경우에는 '{}'를 이용해서 변수명을 지정
	 * @PathVariable 을 이용해서 지정된 이름의 변수값을 얻는다
	 * 값을 얻을 때 int, double과 같은 기본 자료형은 사용할 수 없다
	 */
	
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {	//convert()는 JSON으로 전달되는 데이터를 받아서 Ticket타입으로 변환한다
		log.info("convert.......ticket " + ticket);
		
		return ticket;
	}
	/*
	 * @RequestBody 는 전달된 요청(request)의 내용(body)을 이용해서 해당 파라미터의 타입으로 변환을 요구한다
	 * 대부분 JSON 데이터를 서버에 보내서 원하는 타입의 객체로 변환하는 용도로 사용된다
	 * 다른 메서드와 달리 @PostMapping 가 적용된 것은, 말 그대로 요청한 내용을 처리하기 때문에 일반적인 파라미터 전달방식을 사용할 수 없다
	 */
}
