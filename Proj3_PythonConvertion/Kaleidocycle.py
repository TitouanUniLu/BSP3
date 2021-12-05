class Kaleidocycle:
    composante = []
    size = 0

    def __init__(self, cycle, kaleidos, nb_notes):
        self.cycle = cycle
        self.kaleidos = kaleidos
        self.nb_notes = nb_notes
        self.size = cycle.meter * cycle.phase + 1