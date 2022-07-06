package com.saeyan.controller;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.action.BoardCheckPassAction;
import com.saeyan.controller.action.BoardCheckPassFormAction;
import com.saeyan.controller.action.BoardDeleteAction;
import com.saeyan.controller.action.BoardListAction;
import com.saeyan.controller.action.BoardUpdateAction;
import com.saeyan.controller.action.BoardUpdateFormAction;
import com.saeyan.controller.action.BoardViewAction;
import com.saeyan.controller.action.BoardWriteAction;
import com.saeyan.controller.action.BoardWriteFormAction;

public class ActionFactory {
private static ActionFactory instance = new ActionFactory();
	//싱글톤 
	//ActionFactory의 인자를 instance이란 이름으로 만들어서 static으로 단 한번만 
	//인스턴스화한 후 private로 지정하여 ActionFactory class(현재클래스)에서만 사용하게 한다. 
	//static은 본인 자신을 사용한다.(정적)

	private ActionFactory() { //자동생성으로 만든 "기본 생성자"는 super()가 생긴다.
		super();			  //혹시 상속이나 인터페이스가 생성된다면 바로 사용되라고 만든것같다 
    }                         //여기서의 super는 필요없다.(단순 기본생성자)
	
	public static ActionFactory getInstance() {
		return instance;	  //싱글톤 static으로 이 파라메타를 사용(따로 인스턴스화 안 시켜도 된다)
	}						  //return 으로 private의 instance를 사용한다.
	
	
	public Action getAction(String command) {//Action클래스을 불러와서 getAction함수를 만들어 command인자 값을 받게한다 
		Action action = null;				 //request와 response를 사용할 있는 Action을 action변수선언 초기화
		System.out.println("ActionFactory : " + command); //ActionFactory의 command값을 확인한다.
		
		if(command.equals("board_list")) {	//만약 command키의 값이 equals(문자열 비교) "boad_list" 라면
			action = new BoardListAction(); 
		//Action클래스의 기능 action에 BoardListAction.java의 BoardListAction()메소드 실행
			
		} else if(command.equals("board_write_form")) { //<-글을 쓰는 폼 경로가기
			action = new BoardWriteFormAction();
		} else if(command.equals("board_write")) {      //글을 쓰고 list로 보내는 경로가기
			action = new BoardWriteAction();
		} else if(command.equals("board_view")) {
			action = new BoardViewAction();
		} else if(command.equals("board_check_pass_form")) {
			action = new BoardCheckPassFormAction();
		} else if(command.equals("board_check_pass")) {
			action = new BoardCheckPassAction();
		} else if(command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		} else if(command.equals("board_update")) {
			action = new BoardUpdateAction();
		} else if(command.equals("board_delete")) {
			action = new BoardDeleteAction();
		}
		return action;						//action에 담긴 값을 돌려준다. 
	}
}
