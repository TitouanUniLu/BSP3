from Cycle import Cycle

class Kaleidos:
    accessory = []
    structVert = []
    composante = [] #composante structure is a bit bad, need to modify it later (still works though)

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
        kaleidos.composante.append(kaleidos.structVert[0])
        for i in range(1, kaleidos.nb_notes):
            tempList = []
            for j in range(0, cycle.meter+1):
                tempList.append((kaleidos.structVert[i-1][j]+1)%kaleidos.nb_notes)
            kaleidos.structVert.append(tempList)