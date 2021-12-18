"""
File containing the kaleidos transpose class
"""
class KaleidosTranspose:
    """
    kaleidos transpose class only has a vertical structure and uses the kaleidos calculated previoulsy
    """
    structVertT = []

    def __init__(self, kaleidos, k, nb_notes):
        """
        by using the k value, add it to every value of the vertical structure to obtain the transposed version of the kaleidos
        """
        self.kaleidos = kaleidos
        self.k = k
        self.nb_notes = nb_notes
        
        for i in range(0, len(kaleidos.structVert)):
            temp = []
            for j in range(0, len(kaleidos.structVert[0])):
                temp.append((kaleidos.structVert[i][j]+k)%nb_notes)
            self.structVertT.append(temp)
                

    