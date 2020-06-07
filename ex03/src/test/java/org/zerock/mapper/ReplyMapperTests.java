package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	private Long[] bnoArr = {217L, 216L, 215L, 214L, 213L}; 

	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
//	@Test
	public void testMapper() {
		log.info(mapper);
	}

//	@Test
	public void testCreate() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			log.info("------------------------------------------ testCreate() ------------------------------------------");
			ReplyVO vo = new ReplyVO();
			vo.setBno(bnoArr[i % 5]);
			vo.setReply("댓글 테스트 " + i);
			vo.setReplyer("replyer " + i);
			
			mapper.insert(vo);
			log.info("--------------------------------------- End : testCreate() ---------------------------------------");
		});
	}
	
//	@Test
	public void testRead() {
		Long targetRno = 22L;
		ReplyVO vo = mapper.read(targetRno);
		log.info(vo);
	}
	
//	@Test
	public void testDelete() {
		Long targetRno = 13L;
		mapper.delete(targetRno);
	}
	
//	@Test
	public void testUpdate() {
		Long targetRno = 14L;
		ReplyVO vo = mapper.read(targetRno);
		vo.setReply("Update Reply ");
		int count = mapper.update(vo);
		log.info("UPDATE COUNT: " + count);
	}
	
//	@Test
	public void testList() {	//댓글 페이징 처리전
		Criteria cri = new Criteria();
		
		//bnoArr[0] = (bno = 217)
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
	
	@Test
	public void testList2() {	//댓글 페이징 처리후
		Criteria cri = new Criteria(1, 2);
		
		//bnoArr[0] = (bno = 217)
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
		replies.forEach(reply -> log.info(reply));
	}
}
