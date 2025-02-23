# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'calculadoraDescuentos.ui'
##
## Created by: Qt User Interface Compiler version 6.8.2
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QMetaObject, QRect, QSize, Qt)
from PySide6.QtGui import (QFont)
from PySide6.QtWidgets import (QApplication, QLabel, QLineEdit, QMainWindow,
                               QMenuBar, QPushButton, QStatusBar, QWidget)


class Ui_MainWindow(object):  # Se cambió Ui_boton_calcular por Ui_MainWindow
    def setupUi(self, MainWindow):  # Se cambió boton_calcular por MainWindow
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(800, 600)

        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")

        self.entrada_precio = QLineEdit(self.centralwidget)
        self.entrada_precio.setObjectName(u"entrada_precio")
        self.entrada_precio.setGeometry(QRect(50, 60, 161, 51))

        self.entrada_descuento = QLineEdit(self.centralwidget)
        self.entrada_descuento.setObjectName(u"entrada_descuento")
        self.entrada_descuento.setGeometry(QRect(270, 60, 161, 51))

        self.boton_calcular = QPushButton(self.centralwidget)
        self.boton_calcular.setObjectName(u"boton_calcular")  # Se cambió pushButton por boton_calcular
        self.boton_calcular.setGeometry(QRect(190, 170, 91, 61))

        self.resultado = QLabel(self.centralwidget)
        self.resultado.setObjectName(u"resultado")
        self.resultado.setGeometry(QRect(170, 280, 200, 50))
        self.resultado.setFont(QFont("Arial", 12))
        self.resultado.setAlignment(Qt.AlignmentFlag.AlignCenter)

        MainWindow.setCentralWidget(self.centralwidget)

        self.menubar = QMenuBar(MainWindow)
        self.menubar.setObjectName(u"menubar")
        MainWindow.setMenuBar(self.menubar)

        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"Calculadora de Descuentos", None))
        self.boton_calcular.setText(QCoreApplication.translate("MainWindow", u"Calcular", None))
        self.resultado.setText(QCoreApplication.translate("MainWindow", ""))
