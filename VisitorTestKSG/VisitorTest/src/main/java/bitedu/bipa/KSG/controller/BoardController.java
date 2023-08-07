package bitedu.bipa.KSG.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import bitedu.bipa.KSG.service.*;
import bitedu.bipa.KSG.utils.PageInfo;
import bitedu.bipa.KSG.utils.PagingbarGenerator;
import bitedu.bipa.KSG.vo.*;


@Controller("visitorController")
@RequestMapping("/visitor")
public class BoardController {
	
	@Autowired
	private BoardService visitorService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/viewLogin.do", method=RequestMethod.GET)
	public ModelAndView viewLogin() {
		ModelAndView mav = null;
		mav = new ModelAndView();
		
		mav.setViewName("/visitor/loginForm");
		return mav;
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam("id") String id, 
			@RequestParam("pass") String pass, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MemberVO member = new MemberVO();			

			String grade = "";
			if(id.equals("admin")) {
				grade = "2";
			}else if (id.equals("user1")) {
				grade = "1";
			}
			
			member.setUserId(id);
			member.setUserPass(pass);
			member.setUserGrade(grade);
			
			mav.addObject("flag","true");
			mav.addObject("id",id);
			mav.addObject("pw",pass);
			mav.addObject("member",member);
			
			if(grade.equals("2")) {
				mav.setViewName("/admin/main");			
			}else {
				mav.setViewName("/member/main");
			}	
			
		
		return mav;

	}
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(@RequestParam String flag) {		
		ModelAndView model = new ModelAndView();
		
		model.addObject("flag",flag);
		model.setViewName("main");
		
		return model;
	}	
	

	@RequestMapping("/list.do")
	public ModelAndView list(@RequestParam(required = false) String group,@RequestParam(required = false) String page,HttpSession session) {
		ModelAndView mav = null;
		mav = new ModelAndView();
		//flag는 로그아웃, 방명록은 누구나 접근 가능이기 때문에 분기에 따른 true 설정 없음
		String flag = "false";
		
		int totalCount = visitorService.selectAllText().size();
		int itemsPerPage = 2;
		int groupsPerPage = 5;
		int firstPage = 1;
		int currentPageGroup = 1;
		
		System.out.println("(controller) totalCount : " + totalCount);
		
		PageInfo pi = new PageInfo(itemsPerPage, groupsPerPage, totalCount);
		
		if(group!=null) {
			currentPageGroup = Integer.parseInt(group);
		}

		String result = PagingbarGenerator.generatePagingInfo(currentPageGroup, pi);		
		
		int start = page==null? firstPage : (Integer.parseInt(page)-1) * itemsPerPage; 
		int end = itemsPerPage;
		ArrayList<BoardVO> list = visitorService.searchPagingBoard(start, end);
		
		PageData<BoardVO> pd = new PageData<BoardVO>(list, result);	
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		if(member!=null) {
			String grade = member.getUserGrade();
			
			if(grade.equals("2")) {
				mav.setViewName("/adminVisitor/list");		
				}else {
					mav.setViewName("/memberVisitor/list");
			
			}
		}else {
			mav.setViewName("/memberVisitor/list");
		}
		
		
		mav.addObject("flag",flag);
		mav.addObject("pd",pd);
		mav.addObject("member",member);

		return mav;
	}
	
	
	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public ModelAndView contentView(@RequestParam int textNum ,HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		BoardVO list = visitorService.selectOneText(textNum);
		visitorService.updateView(textNum);
		
		System.out.println("(Controller)controrller selectOne : " + list.getContent());
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		if(member!=null) {
			String grade = member.getUserId();
			
			if(grade.equals("2")) {
				mav.setViewName("/adminVisitor/content");		
				}else {
					mav.setViewName("/content");
			
			}
		}else {
			mav.setViewName("/content");
		}
		
		//댓글 리스트 가져오기
		ArrayList<CommentVo> commentLists = commentService.SearchAllComment(textNum);
		
		if(commentLists!=null) {
			
		System.out.println("(boardController) commentList : " + commentLists);
		
		}	
		
		
		
		mav.addObject("commentLists",commentLists);
		mav.addObject("list", list );
		
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int textNum) {
		ModelAndView model = new ModelAndView();
		int success = 0;
		boolean flag = false;
		
		success = visitorService.removeText(textNum);	
		
		System.out.println("succecss : " + success);
		
		if(success!=0) {
			flag = true;
		}
		
		System.out.println("flag : " + flag);
	    model.setViewName("redirect:list.do");
		
		return model;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(required = false) String group,@RequestParam(required = false) String page,@RequestParam(required = false) String text, HttpSession session) {
		ModelAndView mav = null;
		mav = new ModelAndView();
	
		String flag = "false";
		
		int totalCount = visitorService.searchText(text).size();
		int itemsPerPage = 2;
		int groupsPerPage = 5;
		int firstPage = 1;
		int currentPageGroup = 1;
		
		System.out.println("(controller) totalCount : " + totalCount);
		
		PageInfo pi = new PageInfo(itemsPerPage, groupsPerPage, totalCount);
		
		if(group!=null) {
			currentPageGroup = Integer.parseInt(group);
		}

		String result = PagingbarGenerator.generatePagingInfo(currentPageGroup, pi);		
		
		int start = page==null? firstPage : (Integer.parseInt(page)-1) * itemsPerPage; 
		int end = itemsPerPage;
		ArrayList<BoardVO> list = visitorService.searchPagingBoardSearch(start, end , text);
		
		PageData<BoardVO> pd = new PageData<BoardVO>(list, result);	
		
		MemberVO member = (MemberVO)session.getAttribute("login");

		if(member!=null) {
			String grade = member.getUserGrade();
			
			if(grade.equals("2")) {
				mav.setViewName("/adminVisitor/list");		
				}else {
					mav.setViewName("/memberVisitor/list");
			
			}
		}else {
			mav.setViewName("/memberVisitor/list");
		}
		
		
		mav.addObject("flag",flag);
		mav.addObject("pd",pd);
		mav.addObject("member",member);

		return mav;
	}

	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		String path = "C:\\upload";

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024*10);
		ServletFileUpload update = new ServletFileUpload(factory);
		List<FileItem> items = null;
		try {
			items = update.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BoardVO copy = null;
		try {
			copy = visitorService.upload(items);
			
			System.out.println("(controller)image name : " + copy.getAttatchData());
			
			visitorService.createText(copy);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mav.setViewName("redirect:list.do");
		
		return mav;
	}
	
	@RequestMapping(value = "/regist_view", method = RequestMethod.GET)
	public ModelAndView regist_view(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		MemberVO member = (MemberVO)session.getAttribute("login");

			if(member!=null) {
				String grade = member.getUserId();		
		
				if(grade.equals("2")) {
					mav.setViewName("/adminVisitor/regist");
				}else {
					mav.setViewName("/memberVisitor/regist");
				}
			}else {
				mav.setViewName("/memberVisitor/regist");
			}


		return mav;
	}
	
	@RequestMapping(value="/download.do",method = RequestMethod.GET)
	public void download(@RequestParam("fileName") String fileName,HttpServletResponse resp) {
		
		File downloadFile = new File("d:\\dev\\upload_files\\images\\"+fileName);
		
		try {
			fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html; charset=UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.addHeader("Content-Disposition", "attatchment;filename="+fileName);
		
		try {
			FileInputStream fis = new FileInputStream(downloadFile);
			OutputStream os = resp.getOutputStream();
			byte[] buffer = new byte[256];
			int length = 0;

			while((length=fis.read(buffer))!=-1) {
				os.write(buffer, 0, length);
			}
			os.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
