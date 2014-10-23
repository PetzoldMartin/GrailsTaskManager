package de.fh_zwickau.pti.tbpv2

class TimePlanning extends PlanningHistory {

	int timeBudgetPlan
	Date start
	
    static constraints = {
		description size: 2..200
		tstamp nullable: false
		timeBudgetPlan min:0 
		start nullable:true  
    }
//	Task task
//	static belongsTo = [plans: Task]
	static mapping = {
		sort id:"desc"
	}
}
