"""
File containing the kaleidocycle transpose class
"""
class KaleidocycleTranspose:
    """
    a kaleidocycle transpose only has a composante transpose and uses the previously calculated kaleidocycle
    """
    composanteT = []

    def __init__(self, kaleidocycle, k, nb_notes):
        """
        by using the k value, add it to every value of the composante to obtain the transposed version of the kaleidocycle
        """
        self.kaleidocycle = kaleidocycle
        self.k = k
        self.nb_notes = nb_notes
        
        for i in range(0, len(kaleidocycle.composante)):
            temp = []
            for j in range(0, len(kaleidocycle.composante[0])):
                temp.append((kaleidocycle.composante[i][j]+k)%nb_notes)
            self.composanteT.append(temp)
                