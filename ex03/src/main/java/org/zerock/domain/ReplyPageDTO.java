package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {
	//댓글의 페이징 처리에는 댓글 목록과 전체 댓글의 수가 같이 전달해야만 한다
	
	private int replyCnt;	//전체 댓글 수
	private List<ReplyVO> list;	//댓글 목록
}
