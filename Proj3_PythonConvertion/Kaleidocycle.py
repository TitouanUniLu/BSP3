class Kaleidocycle:
    composante = []

    def __init__(self, cycle, kaleidos, nb_notes):
        self.cycle = cycle
        self.kaleidos = kaleidos
        self.nb_notes = nb_notes 

        self.composante.append(kaleidos.structVert[0])
        for i in range(0, len(cycle.cycleSet)):
            for j in range(0, len(cycle.module)):
                self.composante.append(kaleidos.structVert[cycle.cycleSet[i][j]])