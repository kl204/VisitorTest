package bitedu.bipa.KSG.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.KSG.dao.CommentDao;
import bitedu.bipa.KSG.vo.CommentVo;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;
	
	public void insertComment(CommentVo commentVo) {
		
		commentDao.insertComment(commentVo);
		
	}
	
	public void commentLike(int seq) {
		
		commentDao.commentLike(seq);
		
	}

	public ArrayList<CommentVo> SearchAllComment(int boardSeq){
		ArrayList<CommentVo> list = null;
		
		list = commentDao.SearchAllComment(boardSeq);
		
		return list;
	}
	
	
	
}
