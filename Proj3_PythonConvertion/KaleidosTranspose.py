class KaleidosTranspose:
    structVertT = []

    def __init__(self, kaleidos, k, nb_notes):
        self.kaleidos = kaleidos
        self.k = k
        self.nb_notes = nb_notes
        self.structVertT = kaleidos.structVert
        for i in range(0, len(self.structVertT)):
            for j in self.structVertT[i]:
                j = (j + k) % nb_notes

    