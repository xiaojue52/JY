package com.station.pagebean;

import java.util.List;

/**
 * 保存分页信息
 * 
 */
public class PageBean {

	@SuppressWarnings("unchecked")
	private List list;// 返回某一页的记录列表
	private int TotalCount;// 总记录数
	private int TotalPage;// 总页数
	private int CurrentPage;// 当前页
	private int CountPerpage;// 每页显示记录数
	@SuppressWarnings("unused")
	private boolean isFirstPage;// 是否为第一页
	@SuppressWarnings("unused")
	private boolean isLastPage;// 是否为最后一页
	@SuppressWarnings("unused")
	private boolean hasPreviousPage;// 是否有前一页
	@SuppressWarnings("unused")
	private boolean hasNextPage;// 是否有下一页

	@SuppressWarnings("unchecked")
	public List getList() {
		return list;
	}

	@SuppressWarnings("unchecked")
	public void setList(List list) {
		this.list = list;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	public int getTotalPage() {
		return TotalPage;
	}

	public void setTotalPage(int totalPage) {
		TotalPage = totalPage;
	}

	public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}

	public int getCountPerpage() {
		return CountPerpage;
	}

	public void setCountPerpage(int countPerpage) {
		CountPerpage = countPerpage;
	}

	/**
	 * 初始化分页信息
	 */
	public void init() {
		this.isFirstPage = isFirstPage();
		this.isLastPage = isLastPage();
		this.hasPreviousPage = isHasNextPage();
		this.hasNextPage = isHasNextPage();
	}

	// 判断页的信息
	public boolean isFirstPage() {
		return CurrentPage == 1;// 如果当前页是第一页
	}

	public boolean isLastPage() {
		return CurrentPage == TotalPage;// 如果当前页是最后一页
	}

	public boolean isHasPreviousPage() {
		return CurrentPage != 1;// 当前页不是第一页
	}

	public boolean isHasNextPage() {
		return CurrentPage != TotalPage;// 当前页不是最后一页
	}

	/**
	 * 计算总页数
	 */
	public static int countTotalPage(final int countPerpage,
			final int TotalCount) {
		int TotalPage = (TotalCount + countPerpage - 1) / countPerpage;
		return TotalPage;
	}

	/**
	 * 计算当前页开始记录
	 */
	public static int countStartRow(final int countPerpage,
			final int currentPage) {
		final int startRow = countPerpage * (currentPage - 1);
		return startRow;
	}

	/**
	 * 计算当前页,若为0或没有?page请求，用1代替
	 */
	public static int countCurrentPage(int page) {
		final int curPage = (page == 0 ? 1 : page);
		return curPage;
	}
}
