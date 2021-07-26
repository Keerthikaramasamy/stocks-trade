package com.stock.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stock.dto.StockDto;
import com.stock.repository.StockWatchRepository;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

@Data
@Slf4j
@Service
public class StocksTradeService {

	private final StockWatchRepository stockWatchRepository;

	/**
	 * Get Stock Details
	 * @param stockName
	 * @return
	 */
	public StockDto getStockbyName(final String stockName) {
		try {
			final Stock stock = YahooFinance.get(stockName);
			if (stock == null) {
				throw new IllegalArgumentException(String.format("Stock %s Not Found", stockName));
			} else {
				return StockDto.from(stock);
			}
		} catch (Exception e) {
			log.error("Exception in getStockbyName : ", e);
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * Calculate Stock Average for a given time interval - d,w,m
	 * @param userID
	 * @param symbol
	 * @param interval
	 * @return
	 */
	public BigDecimal calcAverage(final String userID, final String symbol, final String interval) {
		if (stockWatchRepository.contains(userID, symbol)) {
			try {
				final Stock stock = YahooFinance.get(symbol);
				if (stock == null) {
					throw new IllegalArgumentException(
							String.format("Stock %s Not found for interval %s", symbol, interval));
				}
				List<HistoricalQuote> historicalQuotes = stock.getHistory(getInterval(interval));
				if (historicalQuotes == null || historicalQuotes.isEmpty()) {
					return BigDecimal.ZERO;
				}
				
				BigDecimal sum = historicalQuotes.stream().map(HistoricalQuote::getClose)
						.reduce((next, total) -> total.add(next)).orElse(BigDecimal.ZERO);
				
				return sum.divide(BigDecimal.valueOf(historicalQuotes.size()),RoundingMode.HALF_UP);

			} catch (Exception e) {
				log.error("Exception getting stock quotes", e);
				throw new IllegalArgumentException(e);
			}
		} else {
			throw new IllegalArgumentException(
					String.format("Stock %s Not in your watchlist for interval %s", symbol, interval));
		}

	}

	private Interval getInterval(final String interval) {
		for (Interval value : Interval.values()) {
			if (value.getTag().equalsIgnoreCase(interval)) {
				return value;
			}
		}
		return null;
	}

}
