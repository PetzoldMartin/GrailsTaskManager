package de.fh_zwickau.pti.tbpv2
def task = new Task(name: 'test', description: 'dddd')
task.save()
for(err in task.errors.fieldErrors)
    println "${err.class}: ${err.field} => ${err.rejectedValue}, code: ${err.code}"

task=Task.get(1)
def tp = TimePlanning.get(1)
