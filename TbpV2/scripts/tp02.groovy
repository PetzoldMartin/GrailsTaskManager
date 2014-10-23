package de.fh_zwickau.pti.tbpv2
def tp = new TimePlanning(description: 'xyz', timeBudgetPlan: 43, start: new Date(), tstamp: new Date())
tp.save()
for(err in tp.errors.fieldErrors) 
    println "${err.class}: ${err.field} => ${err.rejectedValue}, code: ${err.code}"
def all = TimePlanning.getAll()
println all.id
all.size()
