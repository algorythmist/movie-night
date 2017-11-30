package com.tecacet.movie.boot.jmx;

import java.util.DoubleSummaryStatistics;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "movieStats:name=access")
public class AccessStatistics {

	private final DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
	
	public synchronized void addSample(double time) {
		stats.accept(time);
	}

	@ManagedAttribute
	public double getAverageTime() {
		return stats.getAverage();
	}

	@ManagedAttribute
	public long getInvocations() {
		return stats.getCount();
	}
	
	@ManagedAttribute
	public double getMax() {
		return stats.getMax();
	}
	
	
}
