from types import MethodWrapperType


class Cycle:

    def __init__(self, module, nb_notes):
        self.module = module
        self.nb_notes = nb_notes
        meter = len(module)
        base = 0
        for i in range (0, meter):
            base += module[i]
        base = base % nb_notes
    
    def printModule(self):
        print(self.module)

