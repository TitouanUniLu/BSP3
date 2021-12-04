from Cycle import Cycle
from pprint import pprint


userInput = input("What notes are you using? ")
moduleList = []
for chr in userInput:
    if chr.isdigit(): moduleList.append(int(chr))

notesInput = int(input("How many notes do you want? "))
cycle = Cycle(moduleList, notesInput)
cycle.cycleCalc(cycle.composanteCalc ,cycle.meter, cycle.module, cycle.base, cycle.nb_notes, cycle.cycleSet)

