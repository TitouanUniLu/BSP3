from Cycle import Cycle
from Kaleidocycle import Kaleidocycle
from Kaleidos import Kaleidos
from pprint import pprint

notesInput = int(input("How many base notes do you want? "))%12  #for the moment: highest value is 12 but could be changed?

userInput = input("What notes are you using? ")
moduleList = []
for chr in userInput:
    if chr.isdigit(): moduleList.append(int(chr)%notesInput)  # "mod" just to make sure input is always correct

cycle = Cycle(moduleList, notesInput)
cycle.cycleCalc(cycle.composanteCalc ,cycle.meter, cycle.module, cycle.base, cycle.nb_notes, cycle.cycleSet)
cycle.phaseCalc(cycle)
kaleidos = Kaleidos(cycle, notesInput)
kaleidos.composanteCalc(cycle, kaleidos)
kaleidocycle = Kaleidocycle(cycle, kaleidos, notesInput)