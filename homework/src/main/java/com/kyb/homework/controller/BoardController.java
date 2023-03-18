package com.kyb.homework.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kyb.homework.service.BoardService;
import com.kyb.homework.vo.BoardVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {

	private final BoardService boardService;

	// 메인페이지 (게시글 전체 리스트가 보여지는 화면)
	@GetMapping("/")
	public String mainPage(Model model, String category, String keyword, HttpSession session) {
		List<BoardVO> boards = new ArrayList<>();
		if (category != null && keyword != null) {
			if (category.equals("title")) {
				for (int i = 0; i < boardService.listBoard().size(); i++) {
					if (boardService.listBoard().get(i) != null) {
						if (boardService.listBoard().get(i).getTitle().contains(keyword)) {
							boards.add(boardService.listBoard().get(i));
						}
					}
				}
			} else if (category.equals("nickname")) {
				for (int i = 0; i < boardService.listBoard().size(); i++) {
					if (boardService.listBoard().get(i) != null) {
						if (boardService.listBoard().get(i).getNickname().contains(keyword)) {
							boards.add(boardService.listBoard().get(i));
						}
					}
				}
			} else {
				boards = boardService.listBoard();
			}
		} else {
			boards = boardService.listBoard();
		}
		
		// 페이징을 위한 변수
		int total_board = 0;	// 전체 게시글
		int page_size = 4;		// 한 화면의 보여지는 게시글 수
		int total_page = 1;		// 전체 페이징 수

		total_board = boards.size();
		total_page = (int) Math.ceil((double) total_board / page_size);

		model.addAttribute("total_page", total_page);
		model.addAttribute("page_size", page_size);
		model.addAttribute("boards", boards);
		
		return "mainPage";
	}

	// 조회페이지(상세페이지)
	@GetMapping("/board/{id}")
	public String detailPage(@PathVariable int id, Model model) {
		model.addAttribute("boards", boardService.findById(id));
		return "detailPage";
	}

	// 게시글 수정페이지
	@GetMapping("/board/update/{id}")
	public String updatePage(@PathVariable int id, Model model) {
		model.addAttribute("boards", boardService.findById(id));
		return "updatePage";
	}

	// 게시글 수정페이지 수정기능
	@PostMapping("/board/update/{id}")
	public String updateBoard(@PathVariable int id, BoardVO board,
			@RequestParam(value = "imp", required = false) String imp) {
		
		if (imp != null) {
			board.setCheck("Y");
		} else {
			board.setCheck("N");
		}
		boardService.updateBoard(id, board.getTitle(), board.getContent(), board.getCheck());
		return "redirect:/";
	}

	// 삭제기능
	@GetMapping("/board/delete/{id}")
	public String deletePage(@PathVariable int id) {
		boardService.deleteBoard(id);
		return "redirect:/";
	}

	// 글쓰기 화면
	@GetMapping("/board/write")
	public String writePage() {
		return "writePage";
	}

	// 글쓰기 기능
	@PostMapping("/board/write")
	public String writeBoard(BoardVO board, String nickname,HttpSession session,
			@RequestParam(value = "imp", required = false) String imp) {
		
		if (imp != null) {
			board.setCheck("Y");
		} else {
			board.setCheck("N");
		}
		session.setAttribute("nickname", nickname);
		boardService.writeBoard(board);
		return "redirect:/";
	}
}
