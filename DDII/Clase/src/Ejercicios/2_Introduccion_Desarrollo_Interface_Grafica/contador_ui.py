from PySide6.QtWidgets import QApplication, QMainWindow
# Importa la clase generada por Qt Designer
from contador_ui import Ui_MainWindow # Asegúrate de usar el nombre correcto de tu archivo .uiexportado
class ContadorClics(QMainWindow):
 def __init__(self):
 super().__init__()
 self.ui = Ui_MainWindow()
 self.ui.setupUi(self)
 # Inicializar el contador
 self.contador = 0
 # Conectar el botón
 self.ui.boton_incrementar.clicked.connect(self.incrementar_contador)
 def incrementar_contador(self):
 self.contador += 1
 self.ui.etiqueta_contador.setText(f"Clics: {self.contador}")
if __name__ == "__main__":
 app = QApplication([])
 ventana = ContadorClics()
 ventana.show()
 app.exec()