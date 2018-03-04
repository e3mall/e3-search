package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;

public interface SearchService {
	//page 当前页 rows 每一页大小
	public SearchResult search(String keyword,int page,int rows) throws Exception;
}
