package bitedu.bipa.KSG.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.KSG.vo.CommentVo;

@Repository
public class CommentDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insertComment(CommentVo commentVo) {
		int flag = 0;
				
		System.out.println("(dao) comment content : " + commentVo.getContent());
		
		flag = sqlSession.insert("mapper.board.insertComment", commentVo);
		
		System.out.println("(commentDao) insert : " + flag);
	}
	
	public void commentLike(int seq) {
		int flag = 0;
				
		System.out.println("(dao) comment like : " + seq);
		
		flag = sqlSession.update("mapper.board.updateLike", seq);
		
		System.out.println("(commentDao) like : " + flag);
	}
	
	public ArrayList<CommentVo> SearchAllComment(int boardSeq){
		ArrayList<CommentVo> list = null;
		
		CommentVo test = new CommentVo();
		
		test.setBoardSeq(boardSeq);
		
		System.out.println("(commentDao) boardSeq : " + test.getBoardSeq());
		
		list = (ArrayList)sqlSession.selectList("mapper.board.SearchAllComment", test);
		
		if(list!=null) {
			System.out.println("(dao)comment selectAll : " + list);
		}else {
			System.out.println("list is null : " +list);
		}
		
		return list;
	}
}
