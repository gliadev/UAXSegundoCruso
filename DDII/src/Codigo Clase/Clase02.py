import sys

from PySide6.QtWidgets import QMainWindow, QApplication
from src import Clase02InterfazGrafica



class Clase02(QMainWindow):
    def __init__(self):
        super().__init__()
        self.ui = Clase02InterfazGrafica.Ui_MainWindow()
        self.ui.setupUi(self)
        self.ui.btnIncrementar.clicked.connect(lambda: self.sumar_restar(1))
        self.ui.btnDecrementar.clicked.connect(lambda: self.sumar_restar(-1))
        self.ui.btnResetear.clicked.connect(lambda: self.sumar_restar(-int(self.ui.lblContador.text())))

    def sumar_restar(self, numero):
        try:
            contador = int(self.ui.lblContador.text())
            self.ui.lblContador.setText(str(contador + numero))
            print(f"{self.ui.plainTextEdit.toPlainText()}")
            if contador + numero > 0:
                self.ui.lblContador.setStyleSheet("QLabel {color: green;}")
        except ValueError:
            print("No se le a pasado un numero a la funcion")


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Clase02()
    window.show()
    sys.exit(app.exec_())