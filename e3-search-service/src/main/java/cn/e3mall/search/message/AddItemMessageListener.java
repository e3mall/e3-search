package cn.e3mall.search.message;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.search.mapper.ItemMapper;

public class AddItemMessageListener implements MessageListener{
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private SolrServer solrServer;
	
	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String itemId = null;
			// 取消息的内容
			itemId = textMessage.getText();
			SearchItem searchItem = itemMapper.getItemById(Long.valueOf(itemId));
			//把item添加进索引库
			SolrInputDocument document = new SolrInputDocument();
			//向文档中添加域
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSell_point());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCategory_name());
			//写入索引库
			try {
				solrServer.add(document);
				solrServer.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 第八步：打印消息。
			System.out.println(itemId);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
