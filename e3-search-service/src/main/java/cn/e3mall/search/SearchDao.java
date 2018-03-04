package cn.e3mall.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;

@Repository
public class SearchDao {
	@Autowired
	private SolrServer solrServer;	 
	
	/**
	 * 根据查询条件查询索引库
	 * @param solrQuery
	 * @return
	 * @throws Exception
	 */
	public SearchResult queryIndex(SolrQuery solrQuery) throws Exception{
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList results = queryResponse.getResults();
		
		SearchResult result = new SearchResult();
		
		result.setRecourdCount(results.getNumFound());
		//设置高亮显示
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		List<SearchItem> list = new ArrayList<>();
		//遍历文档列表，取文档内容
		for (SolrDocument solrDocument : results) {
			SearchItem item = new SearchItem();
			item.setId((String) solrDocument.get("id"));
			//显示高亮
			List<String> list2 = highlighting.get(solrDocument.get("id")).get("item_title");
			if(list2 != null && !list2.isEmpty()){
				item.setTitle(list2.get(0));
			}else{
				item.setTitle((String) solrDocument.get("item_title"));
			}
			item.setSell_point((String) solrDocument.get("item_sell_point"));
			item.setPrice((long) solrDocument.get("item_price"));
			item.setImage((String) solrDocument.get("item_image"));
			item.setCategory_name((String) solrDocument.get("item_category_name"));
			list.add(item);
		}
		result.setItemList(list);
		return result;
	}
}
