package org.zerock.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Lombok을 이용해서 getter, setter 생성
@Getter
@Setter
@ToString
public class Criteria {
	
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	public Criteria() {
		this(1, 10);	//생성자를 통해서 기본값을 1페이지, 10개로 지정
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")		//UriComponentsBuilder는 여러개의 파라미터들을 연결해서 URL의 형태로 만들어주는 클래스
				.queryParam("pageNum", this.pageNum)		//queryParam() 메서드를 이용해서 필요한 파라미터를 추가
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
