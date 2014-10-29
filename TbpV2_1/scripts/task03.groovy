package de.fh_zwickau.pti.tbpv2
def tn = Task.count()
def task, tp
if(tn < 25) {
    task = new Task(name: "test$tn", description: 'dddd')
//    task.save()
}
if(TimePlanning.count() < 30) {
    tp = new TimePlanning(description: 'xyz', timeBudgetPlan: TimePlanning.count()*7, start: new Date(), tstamp: new Date())
//    tp.save()
}
//task=Task.get(tn)
task.addToPlans tp
println task.name
def tps = TimePlanning.all
tps.each {tpl -> task.addToPlans tpl}
println "local: ${task.plans.size()} in db: ${TimePlanning.count()}"
task.save(flush:true)
for(err in task.errors.fieldErrors)
    println "task: ${err.field} => ${err.rejectedValue}, code: ${err.code}"
for(err in tp.errors.fieldErrors) 
    println "TimePlanning: ${err.field} => ${err.rejectedValue}, code: ${err.code}"
println "after save: $TimePlanning.count"
task.plans
task.timeBudgetPlan
//TimePlanning.all