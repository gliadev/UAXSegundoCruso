# -*- coding: utf-8 -*-

################################################################################
## Form generated from reading UI file 'EjercicioSemaforoInterfazGrafica.ui'
##
## Created by: Qt User Interface Compiler version 6.8.1
##
## WARNING! All changes made in this file will be lost when recompiling UI file!
################################################################################

from PySide6.QtCore import (QCoreApplication, QDate, QDateTime, QLocale,
    QMetaObject, QObject, QPoint, QRect,
    QSize, QTime, QUrl, Qt)
from PySide6.QtGui import (QBrush, QColor, QConicalGradient, QCursor,
    QFont, QFontDatabase, QGradient, QIcon,
    QImage, QKeySequence, QLinearGradient, QPainter,
    QPalette, QPixmap, QRadialGradient, QTransform)
from PySide6.QtWidgets import (QApplication, QFrame, QLabel, QMainWindow,
    QMenuBar, QPushButton, QSizePolicy, QStatusBar,
    QWidget)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        if not MainWindow.objectName():
            MainWindow.setObjectName(u"MainWindow")
        MainWindow.resize(508, 545)
        self.centralwidget = QWidget(MainWindow)
        self.centralwidget.setObjectName(u"centralwidget")
        self.lavelRojo = QLabel(self.centralwidget)
        self.lavelRojo.setObjectName(u"lavelRojo")
        self.lavelRojo.setGeometry(QRect(190, 30, 71, 71))
        self.lavelRojo.setFrameShape(QFrame.Shape.Panel)
        self.labelAmarillo = QLabel(self.centralwidget)
        self.labelAmarillo.setObjectName(u"labelAmarillo")
        self.labelAmarillo.setGeometry(QRect(190, 130, 61, 71))
        self.labelVerde = QLabel(self.centralwidget)
        self.labelVerde.setObjectName(u"labelVerde")
        self.labelVerde.setGeometry(QRect(190, 230, 71, 71))
        self.pushButton = QPushButton(self.centralwidget)
        self.pushButton.setObjectName(u"pushButton")
        self.pushButton.setGeometry(QRect(180, 350, 81, 81))
        MainWindow.setCentralWidget(self.centralwidget)
        self.menubar = QMenuBar(MainWindow)
        self.menubar.setObjectName(u"menubar")
        self.menubar.setGeometry(QRect(0, 0, 508, 33))
        MainWindow.setMenuBar(self.menubar)
        self.statusbar = QStatusBar(MainWindow)
        self.statusbar.setObjectName(u"statusbar")
        MainWindow.setStatusBar(self.statusbar)

        self.retranslateUi(MainWindow)

        QMetaObject.connectSlotsByName(MainWindow)
    # setupUi

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(QCoreApplication.translate("MainWindow", u"MainWindow", None))
        self.lavelRojo.setText(QCoreApplication.translate("MainWindow", u"TextLabel", None))
        self.labelAmarillo.setText(QCoreApplication.translate("MainWindow", u"TextLabel", None))
        self.labelVerde.setText(QCoreApplication.translate("MainWindow", u"TextLabel", None))
        self.pushButton.setText(QCoreApplication.translate("MainWindow", u"PushButton", None))
    # retranslateUi

