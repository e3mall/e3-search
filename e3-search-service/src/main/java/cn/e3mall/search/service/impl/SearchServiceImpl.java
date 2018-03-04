package cn.e3mall.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.SearchDao;
import cn.e3mall.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao searchDao;
	
	@Override
	public SearchResult search(String keyword, int page, int rows) throws Exception {
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(keyword);
		solrQuery.set("df","item_title");
		if(page <=0)page=1;
		solrQuery.setStart((page-1)*rows);
		solrQuery.setRows(rows);
		
		solrQuery.setHighlight(true);
		solrQuery.addHighlightField("item_title");
		solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
		solrQuery.setHighlightSimplePost("</em>");
		SearchResult result = searchDao.queryIndex(solrQuery);
		//计算总页数
		long recordCount = result.getRecourdCount();
		int totalpage = (int) (recordCount/rows);
		if(recordCount%rows!=0){
			totalpage++;
		}
		result.setTotalPages(totalpage);
		return result;
	}

}
