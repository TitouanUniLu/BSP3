class KaleidocycleTranspose:
    composanteT = []

    def __init__(self, kaleidocycle, k, nb_notes):
        self.kaleidocycle = kaleidocycle
        self.k = k
        self.nb_notes = nb_notes
        
        for i in range(0, len(kaleidocycle.composante)):
            temp = []
            for j in range(0, len(kaleidocycle.composante[0])):
                temp.append((kaleidocycle.composante[i][j]+k)%nb_notes)
            self.composanteT.append(temp)
                