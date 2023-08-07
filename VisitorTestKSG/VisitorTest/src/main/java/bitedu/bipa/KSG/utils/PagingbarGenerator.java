package bitedu.bipa.KSG.utils;

public class PagingbarGenerator {
	public static String generatePagingInfo(int group,PageInfo info) {
		String result = null;
		StringBuilder sb = new StringBuilder();
		
		int endGroup = (int)Math.ceil((float)info.getEndPage()/info.getGroupsPerPage());	
	
		int temp1 = info.getEndPage() %	info.getGroupsPerPage() == 0	?	5	:	info.getEndPage() % info.getGroupsPerPage();
	
		int limit = endGroup==group ? temp1 : (info.getGroupsPerPage());			
		
		if(group>1) {
			String prev = "<a href='./list.do?group="+(group-1)+"&page="+((group-1)*info.getGroupsPerPage())+"'>Prev</a>\n";
			sb.append(prev);
		}
	
		for(int i=1;i<=limit;i++) {
			String temp = "<a href='./list.do?&group="+group+"&page="+((group-1)*info.getGroupsPerPage()+i)+"'>"+((group-1)*info.getGroupsPerPage()+i)+"</a>\n";
			sb.append(temp);
		}
		if(group<endGroup) {
			String next = "<a href='./list.do?group="+(group+1)+"&page="+((group*info.getGroupsPerPage())+1)+"'>Next</a>\n";
			sb.append(next);
		}
		
		result = sb.toString();
		
		
		return result;
	}
}
