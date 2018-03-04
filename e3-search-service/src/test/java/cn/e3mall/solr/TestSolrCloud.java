package cn.e3mall.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class TestSolrCloud {
	/*@Test
	public void testSolrCloudAddDocument() throws Exception {
		// 第一步：把solrJ相关的jar包添加到工程中。
		// 第二步：创建一个SolrServer对象，需要使用CloudSolrServer子类。构造方法的参数是zookeeper的地址列表。
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.128:2181,192.168.25.128:2182,192.168.25.128:2183");
		// 第三步：需要设置DefaultCollection属性。
		solrServer.setDefaultCollection("collection2");
		// 第四步：创建一SolrInputDocument对象。
		SolrInputDocument document = new SolrInputDocument();
		// 第五步：向文档对象中添加域
		document.addField("item_title", "测试商品");
		document.addField("item_price", "100");
		document.addField("id", "test001");
		// 第六步：把文档对象写入索引库。
		solrServer.add(document);
		// 第七步：提交。
		solrServer.commit();
	}

	@Test
	public void testSolrCloudQueryDocument() throws Exception {
		// 创建一个solrserver对象
		CloudSolrServer solrServer = new CloudSolrServer("192.168.25.128:2181,192.168.25.128:2182,192.168.25.128:2183");
		solrServer.setDefaultCollection("collection2");
		// 创建一个solrQuery对象
		SolrQuery solrQuery = new SolrQuery();
		// 设置查询条件
		solrQuery.setQuery("*:*");
		QueryResponse queryResponse = solrServer.query(solrQuery);
		SolrDocumentList results = queryResponse.getResults();
		System.out.println("返回的总记录条数：" + results.getNumFound());
		// 遍历文档列表，取文档内容
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("item_title"));
			System.out.println(solrDocument.get("item_price"));
		}
	}*/
}
