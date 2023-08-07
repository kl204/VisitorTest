package bitedu.bipa.KSG.vo;

import java.util.ArrayList;

public class PageData<T> {
	private ArrayList<T> list;
	private String navBar;
	private int currentPage;

	public PageData(ArrayList<T> list, String navBar) {
		// TODO Auto-generated constructor stub
		this.list = list;
		this.navBar = navBar;
	}
	public ArrayList<T> getList() {
		return list;
	}
	public void setList(ArrayList<T> list) {
		this.list = list;
	}
	
	public String getNavBar() {
		return navBar;
	}
	public void setNavBar(String navBar) {
		this.navBar = navBar;
	}

}
