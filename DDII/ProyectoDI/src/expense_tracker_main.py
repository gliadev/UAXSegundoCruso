from PySide6.QtWidgets import QApplication, QMainWindow
from components.expense_manager import ExpenseManager


class MainApp(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Calculadora de Gastos Diarios")
        self.setGeometry(100, 100, 600, 500)

        # Widget central
        self.central_widget = ExpenseManager()
        self.setCentralWidget(self.central_widget)


if __name__ == "__main__":
    app = QApplication([])
    window = MainApp()
    window.show()
    app.exec()
