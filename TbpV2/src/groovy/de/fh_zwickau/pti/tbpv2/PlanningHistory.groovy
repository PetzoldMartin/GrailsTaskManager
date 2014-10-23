package de.fh_zwickau.pti.tbpv2

abstract class PlanningHistory implements Comparable<PlanningHistory> {
	Date tstamp
	String description
	
	int compareTo(other) {
		tstamp.compareTo(other.tstamp)
	}
}
