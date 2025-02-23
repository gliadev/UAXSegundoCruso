from PySide6.QtWidgets import QApplication, QMainWindow
from descuentos_ui import Ui_MainWindow  # Importamos la interfaz generada

class CalculadoraDescuentos(QMainWindow):
    def __init__(self):
        super().__init__()
        self.ui = Ui_MainWindow()  # Cargamos la interfaz
        self.ui.setupUi(self)

        # Conectar el botón a la función de cálculo
        self.ui.boton_calcular.clicked.connect(self.calcular_descuento)

    def calcular_descuento(self):
        try:
            # Obtener valores de los campos de entrada
            precio_original = float(self.ui.entrada_precio.text())
            descuento = float(self.ui.entrada_descuento.text())

            # Validación del descuento (debe estar entre 0 y 100)
            if descuento < 0 or descuento > 100:
                self.ui.resultado.setText("Descuento inválido (0-100%).")
                return

            # Calcular el precio final
            precio_final = precio_original * (1 - descuento / 100)

            # Mostrar el resultado
            self.ui.resultado.setText(f"Precio final: ${precio_final:.2f}")
        except ValueError:
            self.ui.resultado.setText("Introduce números válidos.")

if __name__ == "__main__":
    app = QApplication([])
    ventana = CalculadoraDescuentos()
    ventana.show()
    app.exec()
