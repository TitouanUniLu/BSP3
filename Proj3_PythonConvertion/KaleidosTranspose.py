class KaleidosTranspose:
    structVertT = []

    def __init__(self, kaleidos, k, nb_notes):
        self.kaleidos = kaleidos
        self.k = k
        self.nb_notes = nb_notes
        
        for i in range(0, len(kaleidos.structVert)):
            temp = []
            for j in range(0, len(kaleidos.structVert[0])):
                temp.append((kaleidos.structVert[i][j]+k)%nb_notes)
            self.structVertT.append(temp)
                

    