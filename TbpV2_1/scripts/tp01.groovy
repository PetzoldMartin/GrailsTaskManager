package de.fh_zwickau.pti.tbpv2
def tp = new TimePlanning(description: 'desc', tstamp: new Date(), timeBudgetPlan: 43, start: new Date())
tp.save()
def all = TimePlanning.getAll()
all.size()