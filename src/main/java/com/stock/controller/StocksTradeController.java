package com.stock.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.service.StocksTradeService;

import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/stock")
@Slf4j
@RequiredArgsConstructor
public class StocksTradeController {

	private final StocksTradeService stocksTradeService;

	@ApiOperation(value = "Get Average Price of a watched stock symbol", notes = "Get Average Price of a watched stock symbol")
	@GetMapping(value = "/{symbol}/{interval}")
	public ResponseEntity<BigDecimal> getStockAverage(@PathVariable @NonNull String symbol,
			@PathVariable @NonNull String interval) {
		log.debug("Get Average Price of a watched stock symbol");
		return ResponseEntity.ok(stocksTradeService.calcAverage("Keerthika", symbol, interval));
	}
}
