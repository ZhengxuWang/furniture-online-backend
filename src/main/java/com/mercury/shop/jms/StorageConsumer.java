package com.mercury.shop.jms;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.mercury.core.bean.Storage;
import com.mercury.shop.bean.Product;
import com.mercury.shop.dao.ProductDao;

@Component
public class StorageConsumer {
	@Autowired
	ProductDao pd;
//	@Autowired
//	ProductService productService;
	@JmsListener(destination = "StorageQueue", containerFactory = "storageListener")
	public void receive(Message msg) throws JMSException {
		System.out.println("********  Getting MSG  ***********");
		ObjectMessage objectMessage = (ObjectMessage) msg;
		List<Storage> storages = (List<Storage>) objectMessage.getObject();
//		System.out.println(storages);
		for(Storage storage: storages) {
			Product p = pd.findById(storage.getPid()).get();
			p.setStock(storage.getPnum());
			pd.save(p);
//			Product p = productService.getProduct(storage.getPid());
//			p.setStock(storage.getPnum());
//			productService.updateProduct(p);
		}
//		System.out.println("success");
	}
}