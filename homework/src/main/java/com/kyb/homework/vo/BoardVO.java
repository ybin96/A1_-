package com.kyb.homework.vo;

import lombok.Data;

@Data
public class BoardVO {

	// 게시글을 구분하기 위한 id값
	private int id;
	
	private String nickname;
	private String title;
	private String content;
	
	// 중요도 체크를 위한 값(Y,N으로 구분)
	private String check;
}
