"""
File containing the kaleidocycle class
"""
class Kaleidocycle:
    """
    a Kaleidocycle only has a composante
    """
    composante = []

    def __init__(self, cycle, kaleidos, nb_notes):
        """
        in this function, the composante is calculated
        Each element i of the Kaleidocycle is made of the “Kaleidos-i” element of the cycle
        """
        self.cycle = cycle
        self.kaleidos = kaleidos
        self.nb_notes = nb_notes 

        self.composante.append(kaleidos.structVert[0])
        for i in range(0, len(cycle.cycleSet)):
            for j in range(0, len(cycle.module)):
                self.composante.append(kaleidos.structVert[cycle.cycleSet[i][j]])