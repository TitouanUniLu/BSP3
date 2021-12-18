"""
File containing the cycle class, needed for all other classes
"""
class Cycle:
    """
    a cycle is composed of a: meter, base, composante, phase and noteSet
    """
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
        
    
    @staticmethod
    def composanteCalc(x, meter, composante, base, notes, cycle):
        """
        function calculates the first composante when called for the first time, then calculates the other ones
        """
        if x == 0:
            composante0 = []
            temp = 0
            for i in range(0, meter):
                composante0.append((composante[i] + temp) % notes)
                temp += composante[i]
            cycle.append(composante0)
            return 

        else:
            comp_temp = []
            for i in range(0, meter):
                comp_temp.append((cycle[x-1][i]+base)%notes) 
            cycle.append(comp_temp)
            return

    @staticmethod
    def cycleCalc(f, meter, composante, base, notes, cycle):
        """
        function calculates all the elements of the composante until the last element of a composante is equal to 0
        """
        i = 0
        f(i, meter, composante, base, notes, cycle)

        while (cycle[i][meter-1] != 0):
            i+=1
            f(i, meter, composante, base, notes, cycle)

    @staticmethod
    def phaseCalc(cycle):
        """
        function calculates the amounts of phases (more practical than always using len(cycle.cycleSet))
        """
        cycle.phase = len(cycle.cycleSet)