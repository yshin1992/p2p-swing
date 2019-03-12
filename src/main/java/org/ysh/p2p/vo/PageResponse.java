package org.ysh.p2p.vo;

import java.util.ArrayList;
import java.util.List;

public class PageResponse<T> {
	private final PageRequest pageRequest;
	private List<T> records = new ArrayList<T>();
	
	 private Long totalCount = Long.valueOf(0L);
	 
	 public PageResponse(PageRequest pageRequest) {
		 this.pageRequest = pageRequest;
	 }
	 
	 public long getTotalPage(){
		 return totalCount % pageRequest.getPageSize() == 0 ? totalCount/pageRequest.getPageSize() : (totalCount/pageRequest.getPageSize()+1);
	 }
	 
	 public Integer getpageSize(){
		 return pageRequest.getPageSize();
	 }
	 
	 public Integer getCurrentPage(){
		 return pageRequest.getCurrentPage();
	 }

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	
	public Integer getFirstResult(){
		return this.pageRequest.getFirstResult();
	}
	 
	 
}
