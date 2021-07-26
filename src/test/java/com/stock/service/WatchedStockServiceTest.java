package com.stock.service;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stock.dto.StockDto;
import com.stock.repository.StockWatchRepository;

public class WatchedStockServiceTest {

	@InjectMocks
	private WatchedStockService watchedStockService;

	@Mock
	private StocksTradeService stocksTradeService;

	@Mock
	private StockWatchRepository stockWatchRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test_add() throws Exception {
		boolean addStockStatus = watchedStockService.add("Keerthika", "GE");
		Assertions.assertTrue(addStockStatus);
	}
	
	@Test
	void test_get() throws Exception {
		Optional<Set<StockDto>> getStockStatus = watchedStockService.get("Keerthika");
		Assertions.assertNotNull(getStockStatus);
	}
}
