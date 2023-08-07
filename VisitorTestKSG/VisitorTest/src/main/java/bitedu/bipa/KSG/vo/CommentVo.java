package bitedu.bipa.KSG.vo;

import java.util.ArrayList;

public class CommentVo {
	private int seq;
	private String writer;
	private String content;
	private int commentLike;
	private int commentHate;
	private int boardSeq;
	private int commentSeq;
	private ArrayList<CommentVo> cocoments;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCommentLike() {
		return commentLike;
	}
	public void setCommentLike(int commentLike) {
		this.commentLike = commentLike;
	}
	public int getCommentHate() {
		return commentHate;
	}
	public void setCommentHate(int commentHate) {
		this.commentHate = commentHate;
	}
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	public int getCommentSeq() {
		return commentSeq;
	}
	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}
	public ArrayList<CommentVo> getCocoments() {
		return cocoments;
	}
	public void setCocoments(ArrayList<CommentVo> cocoments) {
		this.cocoments = cocoments;
	}
	

}
