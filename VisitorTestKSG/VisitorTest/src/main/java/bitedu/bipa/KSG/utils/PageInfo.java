
package bitedu.bipa.KSG.utils;

public class PageInfo {
	
	private int itemsPerPage;
	private int groupsPerPage;
	private int endPage;

	public PageInfo(int itemsPerPage, int groupsPerPage, int totalCount) {
		this.itemsPerPage = itemsPerPage;
		this.groupsPerPage = groupsPerPage;
		this.endPage = (int)(Math.ceil(totalCount/(float)itemsPerPage));
	}
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public int getGroupsPerPage() {
		return groupsPerPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setCount(int count) {
		this.endPage = (int)(Math.ceil(count/(float)itemsPerPage));
	}
	
}
