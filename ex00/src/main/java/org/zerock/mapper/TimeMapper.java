package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {	
	/*
	 * 인터페이스를 이용한 Mapper 
	 */

	@Select("SELECT sysdate FROM dual")
	public String getTime();
	
	public String getTime2();
}
