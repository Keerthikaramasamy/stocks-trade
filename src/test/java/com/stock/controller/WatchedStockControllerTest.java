package com.stock.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.stock.service.WatchedStockService;

@WebMvcTest(controllers = { WatchedStockController.class })
@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
public class WatchedStockControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private WatchedStockService watchedStockService;

	@Mock
	private WebApplicationContext wac;

	@InjectMocks
	private WatchedStockController ac;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_addWatchedStock() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/watched/GE")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
	}
	
	@Test
	void test_deleteWatchedStock() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete("/watched/GE")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
	}
	
	@Test
	void test_getWatchedStockList() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/watched")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andReturn();

		String response = result.getResponse().getContentAsString();
		assertNotNull(response);
	}

}
