package cn.e3mall.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestSolrJ {
	/*@Test
	public void addDocument() throws Exception {
		// 1、把solrJ的jar包添加到工程。
		// 2、创建一个SolrServer对象。创建一个和sorl服务的连接。HttpSolrServer。
		//如果不带Collection默认连接Collection1
		SolrServer solrServer = new HttpSolrServer("http://119.23.52.45:8079/solr");
		// 3、创建一个文档对象。SolrInputDocument。
		SolrInputDocument document = new SolrInputDocument();
		// 4、向文档对象中添加域。必须有一个id域。而且文档中使用的域必须在schema.xml中定义。
		document.addField("id", "test001");
		document.addField("item_title", "测试商品");
		// 5、把文档添加到索引库
		solrServer.add(document);
		// 6、Commit。
		solrServer.commit();
	}*/
	
	@Test
	public void queryIndex() throws Exception{
		//创建一个solrserver对象
		SolrServer solrServer = new HttpSolrServer("http://119.23.52.45:8079/solr");
		//创建一个solrQuery对象
		SolrQuery solrQuery = new SolrQuery();
		//设置查询条件
		solrQuery.setQuery("*:*");
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList results = queryResponse.getResults();
		System.out.println("返回的总记录条数："+results.getNumFound());
		//遍历文档列表，取文档内容
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_sell_point"));
			System.out.println(solrDocument.get("item_price"));
			System.out.println(solrDocument.get("item_image"));
			System.out.println(solrDocument.get("item_category_name"));
		}
	}

}
