#import pyqtgraph
from Cycle import Cycle
from Kaleidocycle import Kaleidocycle
from Kaleidos import Kaleidos
#import numpy
from pyqtgraph import PlotWidget
from PyQt5 import QtCore, QtGui, QtWidgets

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

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("LUIGIVERDI - BSP3 Kaleidocycle Python Conversion")
        MainWindow.resize(1800, 1200)
        self.centralwidget = QtWidgets.QWidget(MainWindow)
        self.centralwidget.setObjectName("centralwidget")

        self.tabWidget = QtWidgets.QTabWidget(self.centralwidget)
        self.tabWidget.setGeometry(QtCore.QRect(0, 0, 1700, 1100))
        self.tabWidget.setDocumentMode(False)
        self.tabWidget.setObjectName("tabWidget")

        #tab 1 - General Information
        self.tab = QtWidgets.QWidget()
        self.tab.setObjectName("tab")
        self.label = QtWidgets.QLabel(self.tab)
        self.label.setGeometry(QtCore.QRect(4, 5, 1800, 1200))
        font = QtGui.QFont()
        font.setPointSize(10)
        self.label.setFont(font)
        self.label.setObjectName("label")
        self.tabWidget.addTab(self.tab, "")

        #tab 2 - Kaleidos
        self.tab_2 = QtWidgets.QWidget()
        self.tab_2.setObjectName("tab_2")
        self.graphicsView = PlotWidget(self.tab_2)
        self.graphicsView.setGeometry(QtCore.QRect(0, 0, 900, 900))
        self.graphicsView.setXRange(-1, notesInput, padding=0)
        self.graphicsView.setYRange(-1, notesInput, padding=0)
        self.graphicsView.showGrid(x=True, y=True)
        self.graphicsView.setBackground('w')
        
        
        for i in range(0, len(kaleidos.structVert)):
            for j in kaleidos.structVert[i]:
                self.graphicsView.plot([i], [j],  symbol='x', symbolPen='r', symbolBrush = 0.1)

        self.tabWidget.addTab(self.tab_2, "")

        #tab 3 - Kaleidocycle
        self.tab_3 = QtWidgets.QWidget()
        self.tab_3.setObjectName("tab_3")
        self.graphicsView = PlotWidget(self.tab_3)
        self.graphicsView.setGeometry(QtCore.QRect(0, 0, 900, 900))

        xElem = [0]
        for i in range(0, len(cycle.cycleSet)):
            for j in cycle.cycleSet[i]:
                xElem.append(j)
        
        self.graphicsView.setXRange(-1, len(xElem), padding=0)
        self.graphicsView.setYRange(-1, notesInput, padding=0)
        self.graphicsView.showGrid(x=True, y=True)
        self.graphicsView.setBackground('w')


        for i in range(0, len(xElem)):
            for j in kaleidos.structVert[xElem[i]]:
                self.graphicsView.plot([i], [j],  symbol='x', symbolPen='r', symbolBrush = 0.1)

        self.graphicsView.plot()
        self.tabWidget.addTab(self.tab_3, "")

        #tab 4 - Kaleidos Transpose
        self.tab_4 = QtWidgets.QWidget()
        self.tab_4.setObjectName("tab_4")
        self.tabWidget.addTab(self.tab_4, "")

        #tab 5 - Kaleidocycle Transpose
        self.tab_5 = QtWidgets.QWidget()
        self.tab_5.setObjectName("tab_5")
        self.tabWidget.addTab(self.tab_5, "")

        MainWindow.setCentralWidget(self.centralwidget)
        self.statusbar = QtWidgets.QStatusBar(MainWindow)
        self.statusbar.setObjectName("statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)
        self.tabWidget.setCurrentIndex(0)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)


    def retranslateUi(self, MainWindow):
        info = "Module: " + str(cycle.module) + '\n\nMeter: ' + str(cycle.meter) + "\n\nPhases: " + str(len(cycle.cycleSet)) + "\n\nBase: " + str(cycle.base) + '\n\nCycle set: \n' + str(cycle.cycleSet) + "\n\nKaleidos set: \n" + str(kaleidos.structVert) + '\n\nKaleidocycle components: \n' + str(kaleidocycle.composante)

        title = "LUIGIVERDI - BSP3 Kaleidocycle Python Conversion"
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate(title, title))
        self.label.setText(_translate(title, info))
        self.label.adjustSize()
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab), _translate(title, "Information"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_2), _translate(title, "Kaleidos"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_4), _translate(title, "Kaleidos Transpose"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_3), _translate(title, "Kaleidocycle"))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_5), _translate(title, "Kaleidocycle transpose"))