package io.spring.web.mvc.rest.integration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import io.spring.web.mvc.rest.model.Item;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class ItemRESTTest {

	private String uri = "http://localhost:8080/spring-mvc-rest/item";
	private RestTemplate template = new RestTemplate();
	private URI l;

	@Before
	public void init() {
		Item i = new Item();
		i.setName("initName");
		i.setNumber(1);
		l = template.postForLocation(uri, i);
	}

	@Test
	public void getTest() {
		Item i = template.getForObject(l, Item.class);
		assertEquals("initName", i.getName());
	}

	@Test
	public void putTest() {
		Item i = template.getForObject(l, Item.class);
		i.setName("newName");
		template.put(l, i);
		i = template.getForObject(l, Item.class);
		assertEquals("newName", i.getName());
	}

	@Test
	public void deleteTest() {
		template.delete(l);
		Item i = template.getForObject(l, Item.class);
		assertNull(i);
	}

	@Test
	public void postTest() {
		Item i = new Item();
		i.setName("postName");
		i.setNumber(1);
		URI l = template.postForLocation(uri, i);
		i = template.getForObject(l, Item.class);
		assertEquals("postName", i.getName());
	}

}
