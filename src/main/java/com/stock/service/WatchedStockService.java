package com.stock.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.stock.dto.StockDto;
import com.stock.repository.StockWatchRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Data
public class WatchedStockService {

	private final StocksTradeService stocksTradeService;

	private final StockWatchRepository stockWatchRepository;

	/**
	 * Add stock to Watch List for a given user
	 * @param userID
	 * @param stock
	 * @return
	 */
	public boolean add(String userID, String stock) {

		stockWatchRepository.add(userID, stocksTradeService.getStockbyName(stock));
		log.info("Added stock - {}to the watched list.", stock);
		return true;
	}

	/**
	 * Remove stock from Watch List for a given user
	 * @param userID
	 * @param stockName
	 * @return
	 */
	public boolean delete(String userID, String stockName) {
		return stockWatchRepository.delete(userID, stockName);
	}

	/**
	 * Return WatchList for a given user
	 * @param userID
	 * @return
	 */
	public Optional<Set<StockDto>> get(String userID) {
		return stockWatchRepository.get(userID);
	}

}
