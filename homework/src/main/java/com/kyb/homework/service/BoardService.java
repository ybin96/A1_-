package com.kyb.homework.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.kyb.homework.repository.BoardRepository;
import com.kyb.homework.vo.BoardVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시글 전체 조회 기능
	public List<BoardVO> listBoard(	){
		return boardRepository.findAll();
	}
	
	// 아이디로 게시글 찾기
	public BoardVO findById(int id) {
		return boardRepository.findById(id);
	}
	
	// 게시물 쓰기
	public void writeBoard(BoardVO board) {
		boardRepository.save(board);
	}

	// 게시글 수정
	public void updateBoard(int id, String title, String content,String check) {
		BoardVO board = boardRepository.findById(id);
		board.setTitle(title);
		board.setContent(content);
		board.setCheck(check);
		boardRepository.update(id, board);
	}
	
	// 게시글 삭제
	public void deleteBoard(int id) {
		boardRepository.delete(id);
	}

}
