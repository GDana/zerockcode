package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {

	private Long rno;
	private Long bno;	//해당 댓글이 어떤 게시물의 댓글인지 명시
	
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
