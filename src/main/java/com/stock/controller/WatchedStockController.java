package com.stock.controller;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stock.dto.StockDto;
import com.stock.service.WatchedStockService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/watched")
@Slf4j
public class WatchedStockController {

	@Autowired
	WatchedStockService watchedStockService;

	@PostMapping("/{stock}")
	public ResponseEntity<Boolean> addWatchedStock(@PathVariable @NonNull String stock) {
		log.debug("Adding stock to watchlist");
		watchedStockService.add("Keerthika", stock);
		return ResponseEntity.ok(true);
	}

	@DeleteMapping("/{stock}")
	public ResponseEntity<Boolean> deleteWatchedStock(@PathVariable @NonNull String stock) {
		log.debug("Deleting stock from watchlist");
		watchedStockService.delete("Keerthika", stock);
		return ResponseEntity.ok(true);
	}

	@GetMapping
	public ResponseEntity<Set<StockDto>> getWatchedStockList() {
		log.debug("Get watchlist");
		return ResponseEntity.ok(watchedStockService.get("Keerthika").orElse(Collections.emptySet()));
	}

}
