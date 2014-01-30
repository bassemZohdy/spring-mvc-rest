package io.spring.web.mvc.rest.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.net.URI;

import io.spring.web.mvc.rest.model.Item;
import io.spring.web.mvc.rest.service.ItemServiceInMemory;

import org.junit.Before;
import org.junit.Test;

public class ItemServiceTest {

	private ItemServiceInMemory service;
	private Long id;

	@Before
	public void init() {
		service = new ItemServiceInMemory();
		Item i = new Item();
		i.setName("initName");
		i.setNumber(1);
		id = service.add(i);
	}

	@Test
	public void getTest() {
		Item i = service.get(id);
		assertEquals("initName", i.getName());
	}

	@Test
	public void putTest() {
		Item i = service.get(id);
		i.setName("newName");
		service.update(id, i);
		i = service.get(id);
		assertEquals("newName", i.getName());
	}

	@Test
	public void deleteTest() {
		service.delete(id);
		Item i = service.get(id);
		assertNull(i);
	}

	@Test
	public void postTest() {
		Item i = new Item();
		i.setName("postName");
		i.setNumber(1);
		Long id = service.add(i);
		Item newI = service.get(id);
		assertEquals("postName", newI.getName());
	}

}
