package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	/*
	 * 아래 sql구문은 primary key를 사용하도록 유도하는 sql구문으로
	 * 자세한 내용은 페이징 처리에서 다룬다.
	 */
	
	//@Select("select * from tbl_board where bno > 0")	//BoardMapper.xml에서 sql구문을 대신 처리해줬기 때문에 주석처리
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//insert만 처리되고 생성된 PK값을 알 필요가 없는 경우
	public void insert(BoardVO board);
	
	//insert문이 실행되고 생성된 PK값을 알아야 하는 경우
	public void insertSelectKey(BoardVO board);
	
	//insert된 데이터를 조회하는 작업은 PK를 이용해서 처리하므로 BoardVO의 bno타입 정보를 이용해서 처리한다
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public int getTotalCount(Criteria cri);
	
	public void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
}
