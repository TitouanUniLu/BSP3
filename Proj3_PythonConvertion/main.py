from PyQt5 import QtWidgets
from PyQt5.QtWidgets import QApplication, QMainWindow, QTabWidget
from Cycle import Cycle
from Kaleidocycle import Kaleidocycle
from Kaleidos import Kaleidos
import sys

notesInput = int(input("How many base notes do you want? "))  #for the moment: highest value is 12 but could be changed?

userInput = input("What notes are you using? ")
moduleList = []
for chr in userInput:
    if chr.isdigit(): moduleList.append(int(chr)%notesInput)  # "mod" just to make sure input is always correct

cycle = Cycle(moduleList, notesInput)
cycle.cycleCalc(cycle.composanteCalc ,cycle.meter, cycle.module, cycle.base, cycle.nb_notes, cycle.cycleSet)
cycle.phaseCalc(cycle)
kaleidos = Kaleidos(cycle, notesInput)
kaleidos.composanteCalc(cycle, kaleidos)
kaleidocycle = Kaleidocycle(cycle, kaleidos, notesInput)


def window(cycle, kaleidos, kaleidocycle):
    cycleset = 'Cycle set: ' + str(cycle.cycleSet)
    kdset = "Kaleidos set: " + str(kaleidos.structVert)
    kaleidocycleset = 'Kaleidocycle components: ' + str(kaleidocycle.composante)
    cycleInfo = "Module: " + str(cycle.module) + '\n\nMeter: ' + str(cycle.meter) + "\n\nPhases: " + str(len(cycle.cycleSet)) + "\n\nBase: " + str(cycle.base)

    #for i in range(0, len(cycle.cycleSet)):
    #    cycleset += "[ "
    #    for j in range(0, cycle.meter):
    #        cycleset += str(cycle.cycleSet[i][j]) + " "
    #    cycleset += "] "

    #for i in range(0, len(kaleidos.structVert)):
    #    kaleidosset += "[ "
    #    for j in range(0, cycle.meter+1):
    #        kaleidosset += str(kaleidos.structVert[i][j]) + " "
    #    kaleidosset += "] "

    #for i in range(0, len(kaleidocycle.composante)):
    #    kaleidocycleset += "[ "
    #    for j in range(0, cycle.meter+1):
    #        kaleidocycleset += str(kaleidocycle.composante[i][j]) + " "
    #    kaleidocycleset += "] "


    app = QApplication(sys.argv)
    win = QMainWindow()
    win.setGeometry(1000, 850, 1400, 850)
    win.setWindowTitle("Luigiverdi Python Converted")

    cycleLabel = QtWidgets.QLabel(win)
    cycleLabel.setText(cycleset)
    cycleLabel.adjustSize()

    kaleidosLabel = QtWidgets.QLabel(win)
    kaleidosLabel.setText(kdset)
    kaleidosLabel.adjustSize()
    kaleidosLabel.move(0,50)

    kaleidosLabel = QtWidgets.QLabel(win)
    kaleidosLabel.setText(kaleidocycleset)
    kaleidosLabel.adjustSize()
    kaleidosLabel.move(0,100)

    kaleidosLabel = QtWidgets.QLabel(win)
    kaleidosLabel.setText(cycleInfo)
    kaleidosLabel.adjustSize()
    kaleidosLabel.move(0,150)

    tab1 = QtWidgets.QLabel("info")
    tab2 = QtWidgets.QLabel("Kaleidos visuals")

    tabwidget = QTabWidget()
    tabwidget.addTab(tab1, "General Information")
    tabwidget.addTab(tab2, "Kaleidos Visualisation")


    win.show()
    sys.exit(app.exec_())

window(cycle, kaleidos, kaleidocycle)