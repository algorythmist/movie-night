package com.tecacet.movie.boot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MemoryLeakService {

	class NastyObject {
		private String name;

		public NastyObject(String name) {
			super();
			this.name = name;
		}
	}
	private Map<NastyObject, Object> map = new HashMap<>();
	
	@Scheduled(fixedRate = 1000)
	public void storeObject() {
		map.put(new NastyObject("Hello"), new String[100000]);
	}

}
