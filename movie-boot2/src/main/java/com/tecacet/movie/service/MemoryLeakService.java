package com.tecacet.movie.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemoryLeakService {

    private final Map<NastyObject, Object> map = new HashMap<>();

    @Scheduled(fixedRate = 1000)
    public void storeObject() {
        map.put(new NastyObject("Hello"), new String[100000]);
    }


    class NastyObject {
        private final String name;

        public NastyObject(String name) {
            super();
            this.name = name;
        }
    }

}
