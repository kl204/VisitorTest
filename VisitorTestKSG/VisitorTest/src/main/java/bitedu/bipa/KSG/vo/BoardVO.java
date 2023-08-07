package bitedu.bipa.KSG.vo;

import java.sql.Timestamp;

public class BoardVO {
	private int seq;
	private String title;
	private String content;
	private String writer;
	private int readCount;
	private Timestamp createdDate;
	private String attatchPass;
	private String attatchData;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getAttatchPass() {
		return attatchPass;
	}
	public void setAttatchPass(String attatchPass) {
		this.attatchPass = attatchPass;
	}
	public String getAttatchData() {
		return attatchData;
	}
	public void setAttatchData(String attatchData) {
		this.attatchData = attatchData;
	}

	



}
