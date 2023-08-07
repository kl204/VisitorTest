package bitedu.bipa.KSG.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonValue;

import bitedu.bipa.KSG.service.CommentService;
import bitedu.bipa.KSG.vo.CommentVo;



@Controller("commentController")
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public void regist(@RequestParam String writer, String content, int boardSeq,  HttpServletResponse res) {
			
		JSONObject list = new JSONObject();		
		CommentVo commentVo = new CommentVo();
		
		commentVo.setWriter(writer);
		commentVo.setContent(content);
		commentVo.setBoardSeq(boardSeq);
		
		System.out.println("(commentController) writer : " + commentVo.getWriter());
		System.out.println("(commentController) content : " + commentVo.getContent());
		System.out.println("(commentController) boardSeq : " + commentVo.getBoardSeq());
		
		// 댓글 등록 하기
		
		commentService.insertComment(commentVo);
		
		
		// 댓글 리스트 가져오기
		
		ArrayList<CommentVo> commentList = commentService.SearchAllComment(boardSeq);
		
		System.out.println("(commentController) comment List : " + commentList.size());
		
		for(int i = 1 ; i < commentList.size() ; i++) {
			JSONObject jsob = new JSONObject();
			jsob.put("writer", commentList.get(i).getWriter());
			jsob.put("content", commentList.get(i).getContent());
			
			String str = "cm" + Integer.toString(i);
			
			list.put(str, jsob);
		}
			
		res.setCharacterEncoding("UTF-8");
     	PrintWriter out;
		try {
			out = res.getWriter();
			out.print(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
	}
	
	@RequestMapping(value = "like")
	public void like(@RequestParam int seq,  HttpServletResponse res) {
		
		commentService.commentLike(seq);
		
	}
	
	
	
	@RequestMapping(value = "commentDelete")
	public void commentDelete() {
		
		
		
	}
}
