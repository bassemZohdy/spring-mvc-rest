package io.spring.web.mvc.rest.service;

import io.spring.web.mvc.rest.model.Item;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class ItemServiceInMemory {

	private static Map<Long, Item> storage = new ConcurrentHashMap<Long, Item>();
	private static Long seq = 1l;

	public Item get(Long id) {
		return storage.get(id);
	}

	public void delete(Long id) {
		storage.remove(id);
	}

	public void update(Long id, Item item) {
		item.setId(id);
		storage.put(id, item);
	}

	public Long add(Item item) {
		Long id = seq++;
		item.setId(id);
		storage.put(id, item);
		return id;
	}

}
