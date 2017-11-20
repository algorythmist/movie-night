package com.tecacet.movie.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tecacet.movie.jpa.repository.GenreRepository;
import com.tecacet.movie.rest.RestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RestConfiguration.class })
@WebAppConfiguration
public class GenreControllerIntegrationTest {

	private static final String LIST_URL = "/genres/list";
	private static final String CREATE_URL = "/genres/";

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private GenreRepository genreRepository;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@After
	public void cleanUp() {
		genreRepository.deleteAll();
	}

	@Test
	public void test() throws Exception {
		MvcResult createResult = mockMvc.perform(post(CREATE_URL).content("Test1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();
		String responseBody = createResult.getResponse().getContentAsString();
		System.out.println(responseBody);

		Map<String, String> params = new HashMap<>();
		params.put("name", "test1");
		MvcResult findResult = mockMvc.perform(
				get("/genres/byname/test1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		responseBody = findResult.getResponse().getContentAsString();
		System.out.println(responseBody);

		MvcResult result = mockMvc.perform(get(LIST_URL)).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

}
