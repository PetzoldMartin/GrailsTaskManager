package de.fh_zwickau.pti.tbpv2
def tn = Task.count()
def task, tp
if(tn < 250) {
    task = new Task(name: "test$tn", description: 'dddd')
//    task.save()
}
if(TimePlanning.count() < 300) {
    tp = new TimePlanning(description: 'xyz', timeBudgetPlan: 43, start: new Date(), tstamp: new Date())
//    tp.save()
}
//task=Task.get(tn)
task.addToPlans tp
println task.name
def tps = TimePlanning.all
tps.each {tpl -> task.addToPlans tpl}
println "local: ${task.plans.size()} in db: ${TimePlanning.count()}"
task.save(flush:true)
println "after save: $TimePlanning.count"
//def query = TimePlanning.where {task.id == tn+1}
//query.find
//task.plans.findAllByIdLike("%",[sort: "id"])
//TimePlanning.findAllByDescriptionLike("%",[max: 1, sort: "id", order: 'desc'])