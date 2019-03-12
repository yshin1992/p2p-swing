package org.ysh.p2p.vo;

public class PageRequest {
	protected static final Integer DEFAULT_PAGE_SIZE = Integer.valueOf(10);
	
	protected static final Integer DEFAULT_PAGE_NUMBER = Integer.valueOf(1);
	
	private Integer pageSize = new Integer(DEFAULT_PAGE_SIZE.intValue());
	
	 private Integer currentPage = new Integer(DEFAULT_PAGE_NUMBER.intValue());
	 
	 public PageRequest() { this(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE); }
	 
	 public PageRequest(Integer currentPage, Integer pageSize){
		 setCurrentPage(currentPage);
		 setPageSize(pageSize);
	 }
	 
	 public void setPageSize(Integer pageSize){
		 this.pageSize = ((pageSize == null) || (pageSize.intValue() <= 0) ? DEFAULT_PAGE_SIZE : pageSize);
	 }
	 
	 public void setCurrentPage(Integer currentPage){
		 this.currentPage = ((currentPage == null) || (currentPage.intValue() <= 0) ? DEFAULT_PAGE_NUMBER : currentPage);
	 }
	 
	 public Integer getFirstResult(){
		 return Integer.valueOf((getCurrentPage().intValue() - 1) * getPageSize().intValue());
	 }

	public Integer getPageSize() {
		return pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	 
	 
}
