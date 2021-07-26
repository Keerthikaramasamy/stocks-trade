package com.stock.repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.stock.dto.StockDto;

import lombok.NonNull;

@Component
public class StockWatchRepository {

	private Map<String, Set<StockDto>> watchedStocks = new ConcurrentHashMap<>();

	public boolean addAll(@NonNull final String userID, @NonNull final Set<StockDto> stocks) {

		watchedStocks.put(userID, stocks);
		return true;

	}

	public boolean add(@NonNull final String userID, @NonNull final StockDto stockDto) {

		watchedStocks.compute(userID, (key, stocks) -> {
			if (stocks == null) {
				stocks = new HashSet<>();
			}
			stocks.add(stockDto);

			return stocks;
		});
		return true;
	}

	public boolean delete(String userID, String stockName) {
		watchedStocks.compute(userID, (key, stocks) -> {
			if (stocks == null) {
				return null;
			}
			stocks.removeIf(stock -> stock.getSymbol().equalsIgnoreCase(stockName));
			return stocks;
		});

		return true;
	}

	public Optional<Set<StockDto>> get(String userID) {
		return Optional.ofNullable(watchedStocks.get(userID));
	}

	public boolean contains(final String userID, String stockName) {
		return get(userID).map(stocks -> stocks.stream().anyMatch(stock -> stock.getSymbol().equalsIgnoreCase(stockName)))
				.orElse(false);
	}

}
