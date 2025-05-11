from PySide6.QtWidgets import QMainWindow, QApplication, QPushButton


class BotonPersonalizado(QMainWindow):
    def __init__(self):
        super().__init__()

        # Configuración inicial de la ventana
        self.setWindowTitle("Botón personalizado")
        self.setGeometry(300, 300, 300, 150)

        # Crear el botón
        self.boton = QPushButton("Dale al boton", self)
        self.boton.setGeometry(100, 50, 100, 40)  # Posición y tamaño del botón

        # Conectar el clic del botón a la función
        self.boton.clicked.connect(self.cambiar_texto)

    def cambiar_texto(self):
        # Cambiar el texto del botón al ser presionado
        self.boton.setText("Botón apretado")


if __name__ == "__main__":
    app = QApplication([])
    ventana = BotonPersonalizado()
    ventana.show()
    app.exec()
