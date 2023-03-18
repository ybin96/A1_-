package com.kyb.homework.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.kyb.homework.vo.BoardVO;

@Repository
public class BoardRepository {

	// Map을 이용하여 데이터를 저장한다
	private static Map<Integer, BoardVO> boards = new HashMap<>(); 
	// Map의 Key값
	private static int sequence = 0;
	
	// 게시글 전체 조회 기능
	public List<BoardVO> findAll(){
		return new ArrayList<>(boards.values());
	}
	
	// 아이디로 게시글찾기 기능
	public BoardVO findById(int id) {
		return boards.get(id);
	}
	
	// 저장기능
	public void save(BoardVO board) {
		board.setId(++sequence);
		boards.put(board.getId(), board);
	}
	
	// 수정기능
	public void update(int id,BoardVO board) {
		boards.put(id, board);
	}
	
	// 삭제기능
	public void delete(int id) {
		boards.remove(id);
	}
	
}
