package com.tecacet.movie.boot.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName="jmxDemo:name=hello")
public class JmxDemo {

	@ManagedAttribute 
	public int getCount() {
		return 666;
	}
}
