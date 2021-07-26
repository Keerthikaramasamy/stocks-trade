package com.stock.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import yahoofinance.Stock;

@Data
@Builder
public class StockDto {

	private final String symbol;
	private String name;
	private String currency;
	private String stockExchange;
	private BigDecimal currentPrice;
	private BigDecimal previousClosedPrice;
	private BigDecimal changePercent;

	public static StockDto from(final Stock stock) {
		return StockDto.builder().name(stock.getName()).currency(stock.getCurrency()).symbol(stock.getSymbol())
				.stockExchange(stock.getStockExchange()).currentPrice(stock.getQuote().getPrice())
				.previousClosedPrice(stock.getQuote().getPreviousClose())
				.changePercent(stock.getQuote().getChangeInPercent()).build();
	}
}
