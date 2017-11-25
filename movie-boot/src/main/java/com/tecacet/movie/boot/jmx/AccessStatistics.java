package com.tecacet.movie.boot.jmx;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "movieStats:name=access")
public class AccessStatistics {

	private double totalTime = 0;
	private int invocations = 0;
	
	public synchronized void addSample(double time) {
		totalTime += time;
		invocations++;
	}

	@ManagedAttribute
	public double getAverageTime() {
		return invocations == 0 ? 0 : totalTime /invocations;
	}

	@ManagedAttribute
	public int getInvocations() {
		return invocations;
	}
	
	
}
