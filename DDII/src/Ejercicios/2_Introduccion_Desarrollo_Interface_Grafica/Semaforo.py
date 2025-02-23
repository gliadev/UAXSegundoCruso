import sys
from PySide6.QtWidgets import QMainWindow, QApplication
from EjercicioSemaforoInterfazGrafica import Ui_MainWindow

class Semaforo(QMainWindow):
    def __init__(self):
        super().__init__()
        self.ui = Ui_MainWindow()
        self.ui.setupUi(self)

        self.estado = 0

        self.ui.pushButton.clicked.connect(self.cambiar_semaforo)

        self.actualizar_colores()

    def cambiar_semaforo(self):

        self.estado = (self.estado + 1) % 3
        self.actualizar_colores()

    def actualizar_colores(self):

        if self.estado == 0:
            self.ui.lavelRojo.setStyleSheet("QLabel { background-color: red; border-radius: 30px; }")
            self.ui.labelAmarillo.setStyleSheet("QLabel { background-color: none; }")
            self.ui.labelVerde.setStyleSheet("QLabel { background-color: none; }")
        elif self.estado == 1:
            self.ui.lavelRojo.setStyleSheet("QLabel { background-color: none; }")
            self.ui.labelAmarillo.setStyleSheet("QLabel { background-color: yellow; border-radius: 30px; }")
            self.ui.labelVerde.setStyleSheet("QLabel { background-color: none; }")
        elif self.estado == 2:
            self.ui.lavelRojo.setStyleSheet("QLabel { background-color: none; }")
            self.ui.labelAmarillo.setStyleSheet("QLabel { background-color: none; }")
            self.ui.labelVerde.setStyleSheet("QLabel { background-color: green; border-radius: 30px; }")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = Semaforo()
    window.show()
    sys.exit(app.exec())
