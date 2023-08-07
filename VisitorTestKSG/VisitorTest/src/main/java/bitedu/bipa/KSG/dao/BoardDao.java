package bitedu.bipa.KSG.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitedu.bipa.KSG.vo.*;

@Repository("visitorDao")
public class BoardDao {
	
	@Autowired
	public SqlSession sqlSession;
	
	public ArrayList<BoardVO> searchPagingBoard(int start, int end){
		ArrayList<BoardVO> list = null;
		
		HashMap<String, Object> paging = new HashMap<String, Object>();
		
		paging.put("start", start);
		paging.put("end", end);
		
		int getpgst = (Integer) paging.get("start");
		int getpged = (Integer) paging.get("end");
		
		
		if(getpgst < 0 || getpged <0) {
			System.out.println("start or end : -1");
			
			System.out.println("getpgst : " + getpgst);
			System.out.println("getpged : " + getpged);
			
			paging.put("start",start);
			
		}else {
		
		System.out.println(paging.get("start"));
		System.out.println(paging.get("end"));
				
		list = (ArrayList)sqlSession.selectList("mapper.board.searchPagingBoard",paging);
		System.out.println(list.size());
		
		}
		return list;
	}
	
	public ArrayList<BoardVO> searchPagingBoardSearch(int start, int end , String text){
		ArrayList<BoardVO> list = null;
		

			HashMap<String, Object> paging = new HashMap<String, Object>();
			
			paging.put("start", start);
			paging.put("end", end);
			paging.put("text", text);
			
			int getpgst = (Integer) paging.get("start");
			int getpged = (Integer) paging.get("end");
			
			
			if(getpgst < 0 || getpged <0) {
				
				System.out.println("getpgst : " + getpgst);
				System.out.println("getpged : " + getpged);
				
				System.out.println("start or end : -1");
				paging.put("start",start);
			}else {
			
			System.out.println("(dao)start : " + paging.get("start"));
			System.out.println("(dao)end : " + paging.get("end"));
					
			list = (ArrayList)sqlSession.selectList("mapper.board.searchPagingBoardSearch",paging);
			
			for(BoardVO vo : list) {
				System.out.println("(dao) search title : " + vo.getTitle());
			}
		}
		
		
		return list;
	}
	
	public ArrayList<BoardVO> selectAllText(){
		ArrayList<BoardVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.board.selectAllText");
		
		if(list!=null) {
		System.out.println("(dao)selectAllText content : " + list);
		System.out.println("(dao)image name : " + list.get(0).getAttatchData());
		}
		return list;
	}
	
	public BoardVO selectOneText(int textNum){
		BoardVO boardVo = null;
		
		boardVo = (BoardVO)sqlSession.selectOne("mapper.board.selectOneText", textNum);
		
		if(boardVo!=null) {
			System.out.println("(dao)selectOnebook content : " + boardVo.getContent());
			System.out.println("(dao)selectOnebook createdDate : " + boardVo.getCreatedDate());
			System.out.println("(dao)selectOnebook attatchData : " + boardVo.getAttatchData());
		}
		
		
		return boardVo;
	}
	
	public int registText(BoardVO boardVo) {
		int flag = 0;
		
		flag = sqlSession.insert("mapper.board.registText", boardVo);
		
		System.out.println("(dao)registText : " + flag);
		
		
		return flag;
	}
	
	
	public int updateView(int text) {
		int flag = 0;
		
		flag = sqlSession.update("mapper.board.updateView", text);
		
		System.out.println("(dao)updateView : " + flag);
		
		return flag;
	}
	
	public int deleteText(int textNum) {
		int flag = 0;
		
		flag = sqlSession.delete("mapper.board.deleteText", textNum);
		
		System.out.println("(dao)deleteText : " + flag);
		
		
		return flag;
	}
	
	public ArrayList<BoardVO> searchText(String text) {
		ArrayList<BoardVO> list = null;
		
		list = (ArrayList)sqlSession.selectList("mapper.board.searchText", text);
		
		if(list.isEmpty()) {
			System.out.println("no list!");
		}else {
			System.out.println("(dao)searchText content : " + list.get(0).getContent());
		}
		
		return list;
	}

	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		ArrayList<MemberVO> mem = new ArrayList<MemberVO>();
		 
		mem = (ArrayList)sqlSession.selectList("mapper.board.CheckLogin", memberVo);
		
		if(mem.isEmpty()) {
			return flag;
		}else {
			if(mem.get(0).getUserId().equals(memberVo.getUserId()) && mem.get(0).getUserPass().equals(memberVo.getUserPass())) {
				flag = 1;
			}
		}
		
		System.out.println("(dao)loginCheck : " + flag);
		
		return flag;
	}

	public int checkId(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
