from PySide6.QtWidgets import QMainWindow, QApplication


class gestorTareas(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("Gestor de Tareas")
        self.setGeometry(300,300,300,150)


   # def agregarTarea(self):













if __name__ == "__main__":
    app = QApplication([])
    gestor = gestorTareas()
    gestor.show()
    app.exec()

