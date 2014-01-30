package io.spring.web.mvc.rest.controller;

import io.spring.web.mvc.rest.model.Item;
import io.spring.web.mvc.rest.service.ItemServiceInMemory;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping("/item")
public class ItemRESTController {

	@Autowired
	ItemServiceInMemory service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Item get(@PathVariable Long id) {
		return service.get(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody Item item) {
		service.update(id, item);
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody Item item, HttpServletRequest request) {
		Long id = service.add(item);
		UriComponents uriComponents = ServletUriComponentsBuilder
				.fromServletMapping(request).path("/item/{id}").buildAndExpand(id);
		URI uri = uriComponents.encode().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.add("location", uri.toString());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
