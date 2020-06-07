package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper; 
	
	@Test
	public void testGetList() {
		log.info("------------------------------------------ testGetList() ------------------------------------------");
		mapper.getList().forEach(board -> log.info(board));
	}
	
//	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info("------------------------------------------ testInsert() ------------------------------------------");
		log.info(board);
		log.info("---------------------------------------------------------------------------------------------------------");
	}
	
//	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info("------------------------------------------ testInsertSelectKey() ------------------------------------------");
		log.info(board);
		log.info("---------------------------------------------------------------------------------------------------------");
	}
	
//	@Test
	public void testRead() {
		BoardVO board = mapper.read(3L);	//public BoardVO read(Long bno); 의 매개변수 자료형이 Long 이기 때문에 3L
		
		log.info("------------------------------------------ testRead() ------------------------------------------");
		log.info(board);
		log.info("---------------------------------------------------------------------------------------------------------");
	}
	
//	@Test
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(3L));
	}
	
//	@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setBno(4L);
		board.setTitle("수정한 제목");
		board.setContent("수정한 내용");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		log.info("------------------------------------------ testUpdate() ------------------------------------------");
		log.info("UPDATE COUNT: " + count);
	}

//	@Test
	public void testPaging() {
		Criteria cri = new Criteria();
		//10개씩 3페이지
		cri.setPageNum(3);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);

		log.info("------------------------------------------ testPaging() ------------------------------------------");
		list.forEach(board -> log.info(board));
	}
	
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("새로");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board -> log.info(board));
	}
	
}



















