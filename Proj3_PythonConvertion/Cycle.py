from os import curdir
from types import MethodWrapperType
from itertools import accumulate, cycle
import operator

class Cycle:
    meter = 0
    base = 0
    cycleSet = []
    composante = []
    phase = 0

    def __init__(self, module, nb_notes):
        self.module = module
        self.nb_notes = nb_notes
        self.meter = len(module)
        for i in range (0, self.meter):
            self.base += module[i]
        self.base = self.base % nb_notes

        #self.cycleCalc(self.composanteCalc, self.meter, self.composante, self.base, self.nb_notes, self.cycleSet)
        
    
    @staticmethod
    def composanteCalc(x, meter, composante, base, notes, cycle):
        if x == 0:
            composante0 = []
            temp = 0
            for i in range(0, meter):
                composante0.append((composante[i] + temp) % notes)
                temp += composante[i]
            cycle.append(composante0)
            #composante0 = list(accumulate(composante, operator.add))            smarter method - try it later
            #for i in range(0, meter): composante0[i] = composante0[i] % notes
            #cycle.append(composante0)
            return 

        else:
            comp_temp = []
            for i in range(0, meter):
                comp_temp.append((cycle[x-1][i]+base)%notes) 
            cycle.append(comp_temp)
            return

    @staticmethod
    def cycleCalc(f, meter, composante, base, notes, cycle):
        i = 0
        f(i, meter, composante, base, notes, cycle)

        while (cycle[i][meter-1] != 0):
            i+=1
            f(i, meter, composante, base, notes, cycle)

    @staticmethod
    def phaseCalc(cycle):
        cycle.phase = len(cycle.cycleSet)


            

