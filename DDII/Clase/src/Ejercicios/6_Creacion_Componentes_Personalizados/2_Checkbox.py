from PySide6.QtCore import Slot
from PySide6.QtWidgets import QApplication, QCheckBox, QLabel, QVBoxLayout, QWidget


# 1. Crear la clase MainWindow con un QCheckBox y un QLabel
class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Checkbox con Etiqueta')

        # Crear un QCheckBox
        self.checkbox = QCheckBox("Activar", self)

        # Crear un QLabel
        self.label = QLabel("Desactivado", self)

        # 2. Conectar la señal toggled del QCheckBox a un Slot
        self.checkbox.toggled.connect(self.cambiar_texto_label)

        # 3. Configurar el layout
        layout = QVBoxLayout(self)
        layout.addWidget(self.checkbox)
        layout.addWidget(self.label)
        self.setLayout(layout)  # Importante: establecer el layout en la ventana

    # 4. Slot que cambia el texto del QLabel dependiendo del estado del QCheckBox
    @Slot(bool)
    def cambiar_texto_label(self, estado):
        if estado:
            self.label.setText("Activado")
        else:
            self.label.setText("Desactivado")


# 5. Crear la aplicación y la ventana principal
if __name__ == "__main__":
    app = QApplication([])  # Crear la aplicación
    ventana = MainWindow()  # Crear la ventana principal
    ventana.show()  # Mostrar la ventana
    app.exec()  # Ejecutar la aplicación
