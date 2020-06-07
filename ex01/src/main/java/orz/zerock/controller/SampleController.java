package orz.zerock.controller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import orz.zerock.domain.SampleDTO;
import orz.zerock.domain.SampleDTOList;
import orz.zerock.domain.TodoDTO;


@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	/*
	 * 126, 127p - @RequestMapping�� ���� �ڵ�
	 * 
	 * sample ��Ű���� ������ �ʾҾ ���� �ȵǴ� ��...
	 */
	
	@RequestMapping("")
	public void basic() {
		log.info("basic............");
	}
	
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get............");
	}

	@GetMapping("/basicOnlyGet")	//RequestMapping�� ����� 
	public void basicGet2() {
		log.info("basic get only get............");
	}
	
	@GetMapping("/ex01")	
	public String ex01(SampleDTO dto) {	//�Է°� -> http://localhost:8181/sample/ex01?name=AAA&age=10
		log.info("" + dto);				//��°� -> INFO : orz.zerock.controller.SampleController - SampleDTO(name=AAA, age=10)
		
		return "ex01";	
	}
	
	@GetMapping("/ex02")	
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {	//�Է°� -> http://localhost:8181/sample/ex02?name=AAA&age=10
		log.info("name: " + name);	//��°� -> INFO : orz.zerock.controller.SampleController - name: AAA
		log.info("age: " + age);	//��°� -> INFO : orz.zerock.controller.SampleController - age: 10
		
		return "ex02";	
	}
	
	@GetMapping("/ex02List")
	//public String ex02List(@RequestParam("ids")String[] ids) �� �����ϰ� ó���ȴ�
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {	//�Է°� -> /ex02List?ids=111&ids=222&ids=333
		log.info("ids: " + ids);	//��°� -> INFO : orz.zerock.controller.SampleController - ids: [111, 222, 333]
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);	
		/* �Է°�
		 * http://localhost:8181/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb
		 * 
		 * '[]'�� ��Ĺ���� Ư�����ڷ� ó���Ǳ� ������ �Ʒ��� ���� ����
		 * '[' = %5B
		 * ']' = %5D
		 * 
		 * ��°� 
		 * INFO : orz.zerock.controller.SampleController - 
		 * list dtos: SampleDTOList(list=[SampleDTO(name=aaa, age=0), SampleDTO(name=null, age=0), SampleDTO(name=bbb, age=0)])
		 */
		
		return "ex02Bean";
	}
	
	
//	@InitBinder	//@DateTimeFormat�� ����ϰ� �Ǹ� '@InitBinder'�� ������ ������ֱ� ������ �ʿ����� �ʴ� 
//	public void initBinder(WebDataBinder binder) { 
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		
		/* �Է°�
		 * @InitBinder
		 * http://localhost:8181/sample/ex03?title=test&dueDate=2020-04-07
		 * 
		 * @DateTimeFormat
		 * http://localhost:8181/sample/ex03?title=test&dueDate=2020/04/07
		 * 
		 * ��°�
		 * INFO : orz.zerock.controller.SampleController - todo: TodoDTO(title=test, dueDate=Tue Apr 07 00:00:00 KST 2020)
		 */
		
		return "ex03";
	}
	
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	// @GetMapping("/ex05")�� notion�� ����
	
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06................");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("ȫ�浿");
		
		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		log.info("/ex07................");
		
		//{"name":"ȫ�浿"}
		String msg = "{\"name\":\"ȫ�浿\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload................");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("-----------------------------------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:" + file.getSize());
		});
	}
}
