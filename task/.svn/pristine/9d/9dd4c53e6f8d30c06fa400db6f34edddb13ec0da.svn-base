package utry.task.utils;

import java.util.ArrayList;
import java.util.List;

/****
 * 分页功能控制器
 * @author dingxinfa
 * @date 2014-07-01
 *
 */
public class PageController {
	/**
	 * 总记录数
	 */
	private Integer totalRowsAmount; 
	/**
	 * 总页数
	 */
	private Integer totalPages=1; 
	/***
	 * 每页行数
	 */
	private Integer pageSize=12; 
	/***
	 * 当前页码
	 */
	private Integer currentPage=1;
	/**
	 * 当前页开始行
	 */
	private Integer pageStartRow=0; 
	
	/**
	 * 当前页结束行
	 */
	private Integer pageEndRow=0; 
	/***
	 * 分页视图列表
	 */
	private List<String> pageViewList;
	
	/***
	 * 分页功能跳转链接
	 */
	private String url;
	
	public PageController(){}

	/**
	    * 功能：初始化分页类，
	    * @param totalRows 总记录数
	    * @param cPage 当前页码
	    * @param cPageSize 每页显示条数
	    * @param cPageUrl 分页跳转链接
	    */
	public PageController(Integer totalRows,Integer cPage,Integer cPageSize,String cPageUrl){		
		url=cPageUrl;//跳转链接
		this.pageController(totalRows,cPage,cPageSize);
		}
	/**
	    * 功能：初始化分页类
	    * @param totalRows 总记录数
	    * @param cPage 当前页码
	    * @param cPageSize 每页显示条数
	    */
	public  PageController(Integer totalRows, Integer cPage, Integer cPageSize) {
		this.pageController(totalRows,cPage,cPageSize);
	}

	/**
	    * 功能：计算各分页参数
	    * @param totalRows 总记录数
	    * @param cPage 当前页码
	    * @param cPageSize 每页显示条数
	    */
	private void pageController(Integer totalRows,Integer cPage,Integer cPageSize){		
		currentPage = cPage;//当前页
		pageSize = cPageSize;//每页显示条数
		totalRowsAmount = totalRows;//总数
		if(totalRowsAmount>0){
			if(totalRowsAmount%pageSize == 0){
				totalPages = totalRowsAmount/pageSize ;
			} else {
				totalPages = totalRowsAmount/pageSize + 1;
			}
		}else{
			totalPages=1;
		}
		if(currentPage>totalPages){
			currentPage=totalPages;
		}
		//计算当前页的开始行和结束行
		if(totalRowsAmount>0){
			if(currentPage*pageSize < totalRowsAmount){//判断当前页*页大小是否小于总行数
				pageEndRow = currentPage * pageSize;//当前页结束行
			} else {			
				pageEndRow = totalRowsAmount;
			}
			if((currentPage-1)*pageSize+1 < pageEndRow){//判断当前页*页大小是否小于总行数
				pageStartRow = (currentPage-1) * pageSize+1;//当前页开始行
			} else {			
				pageStartRow = pageEndRow;
			}
		}
		boolean beforecurrentPage=true;
		boolean aftercurrentPage=true;
		pageViewList=new ArrayList<String>();

		for(int i=1;i<=totalPages;i++){
			if(i<3 || i>totalPages-2 || i==currentPage+1 || i==currentPage-1 || i==currentPage){
				pageViewList.add(Integer.toString(i));
			}
			else if(i>2 && i<currentPage-1 && beforecurrentPage){
				pageViewList.add("...");
				beforecurrentPage=false;

			}else if(i>currentPage+1 && i<=totalPages-2 && aftercurrentPage){
				pageViewList.add("...");
				aftercurrentPage=false;
			}
		}	
	}

	public Integer getTotalRowsAmount() {
		return totalRowsAmount;
	}

	public void setTotalRowsAmount(Integer totalRowsAmount) {
		this.totalRowsAmount = totalRowsAmount;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(Integer pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public Integer getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(Integer pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	public List<String> getPageViewList() {
		return pageViewList;
	}

	public void setPageViewList(List<String> pageViewList) {
		this.pageViewList = pageViewList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
