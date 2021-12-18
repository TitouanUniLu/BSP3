"""
File containing the kaleidos class
"""
class Kaleidos:
    """
    a Kaleidos is composed of an accessory, a vertical structure and a composante
    it also requires a Cycle object to be created
    """
    accessory = []
    structVert = []
    composante = [] 

    def __init__(self, cycle, nb_notes):
        self.cycle = cycle
        self.nb_notes = nb_notes

        for i in range(0, cycle.meter):
            self.accessory.append(cycle.module[i])
        temp = 0
        tempList = [0]
        for i in range(0, cycle.meter):
            tempList.append((self.accessory[i] + temp) % nb_notes)
            temp += self.accessory[i]
        self.structVert.append(tempList)

    @staticmethod
    def composanteCalc(cycle, kaleidos):
        """
        function calculates the elemnts of the composantes by going through the elements of the vertical structure
        """
        kaleidos.composante.append(kaleidos.structVert[0])
        for i in range(1, kaleidos.nb_notes):
            tempList = []
            for j in range(0, cycle.meter+1):
                tempList.append((kaleidos.structVert[i-1][j]+1)%kaleidos.nb_notes)
            kaleidos.structVert.append(tempList)