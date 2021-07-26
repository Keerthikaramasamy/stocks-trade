package com.stock.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stock.dto.StockDto;
import com.stock.repository.StockWatchRepository;

public class StocksTradeServiceTest {

	@InjectMocks
	private StocksTradeService stocksTradeService;

	@Mock
	private StockWatchRepository stockWatchRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_getStockbyName() throws Exception {
		StockDto stock = stocksTradeService.getStockbyName("GE");

		Assertions.assertNotNull(stock);
	}
}
