package bitedu.bipa.KSG.service;

import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitedu.bipa.KSG.dao.BoardDao;
import bitedu.bipa.KSG.vo.*;


@Service("visitorService")
public class BoardService {
	
	@Autowired
	private BoardDao visiterDao;	
	
	
	//하드코딩으로 grade 체크
	public String selectMemberGrade(String id) {
		String grade = null;
		
		return grade;
	}
	
	//하드코딩으로 id 체크
	public  MemberVO selectMemberById(String id) {
		 MemberVO member = null;
		
		return member;
	}
	
	
	public ArrayList<BoardVO> searchPagingBoard(int start, int end){
		ArrayList<BoardVO> list = null;
		list = visiterDao.searchPagingBoard(start, end);
		return list;
	}

	public ArrayList<BoardVO> searchPagingBoardSearch(int start, int end , String text){
		ArrayList<BoardVO> list = null;
		
		list = visiterDao.searchPagingBoardSearch(start, end, text);
		
		return list;
	}
	
	
	public int createText(BoardVO boardVo) {
		int flag = 0;
		
		visiterDao.registText(boardVo);
		
		return flag;
	}

	public ArrayList<BoardVO> selectAllText(){
		ArrayList<BoardVO> allText = null;
		
		allText = visiterDao.selectAllText();
		
		return allText;
	}
	
	public BoardVO selectOneText(int textNum){
		BoardVO OneText = null;
		
		OneText = visiterDao.selectOneText(textNum);
		
		return OneText;
	}
	
	public ArrayList<BoardVO> searchText(String text){
		ArrayList<BoardVO> OneText = null;
		
		OneText = visiterDao.searchText(text);
		
		return OneText;
	}

	//U : no update
	public int updateView(int text) {
		int flag = 0;
		
		flag = visiterDao.updateView(text);
		
		return flag;
	}
	
	//D : delete
	public int removeText(int textNums) {
		int flag = 0;
		
		flag = visiterDao.deleteText(textNums);
		
		return flag;
	}

	public int loginCheck(MemberVO memberVo) {
		int flag = 0;
		String id = memberVo.getUserId();
		String pw = memberVo.getUserPass();
		
		 if(id.equals("admin") || id.equals("user1")) {
			 if(pw.equals("1234")) {
				 flag = 1;
			 }
			 
		 }
		return flag;
	}

	public int checkId(String memberId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public BoardVO upload(List<FileItem> items) throws Exception {
		// TODO Auto-generated method stub
		BoardVO copy = new BoardVO();
		
		for(FileItem item : items) {
			if(item!=null&item.isFormField()) {
				//일반적인 Form값 추출
				String fieldName = item.getFieldName();
				if(fieldName.equals("title")) {
					copy.setTitle(item.getString("UTF-8"));
					
					
				} else if(fieldName.equals("content")) {
					copy.setContent(item.getString("UTF-8"));
					
					
				} else if(fieldName.equals("writer")) {
					copy.setWriter(item.getString("UTF-8"));
					
					
				} 
				
			} else {
				

				// uplode할 데이터에 대한 정보 추출
				String fieldName = item.getFieldName();
	
				
				if(fieldName.equals("dataImage")) {
					String temp = item.getName();
					
					if(temp!="") {
					
					System.out.println("attatchData : "+temp);
					int index = temp.lastIndexOf("\\");
					String fileName = temp.substring(index+1);
					copy.setAttatchData(temp);
					
					File uploadFile = new File("C:\\upload\\data"+fileName);
					item.write(uploadFile);
					}else {
						System.out.println("NO file!");
					}
					}
				
		

			}
		}
		return copy;
	}


}
