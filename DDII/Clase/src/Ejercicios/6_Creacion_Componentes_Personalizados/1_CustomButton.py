from PySide6.QtCore import Property, Signal, Slot
from PySide6.QtWidgets import QApplication, QPushButton, QWidget, QVBoxLayout


# 1. Crear la clase CustomButton que hereda de QPushButton
class CustomButton(QPushButton):
    # 2. Definir una señal 'textoCambiado' que será emitida cuando el texto cambie
    textoCambiado = Signal(str)

    def __init__(self, text='', parent=None):
        super().__init__(text, parent)
        self._text = text  # Almacenar el texto internamente
        self.setText(self._text)  # Establecer el texto inicial del botón

    # 3. Implementar la propiedad 'text' utilizando el decorador @Property
    @Property(str)
    def text(self):
        return self._text

    @text.setter
    def text(self, value):
        if self._text != value:  # Si el texto cambia, emitimos la señal
            self._text = value
            self.setText(self._text)  # Actualizamos el texto del botón
            self.textoCambiado.emit(self._text)  # Emitir la señal

    # 4. Slot que ajusta el tamaño del botón basado en el texto
    @Slot(str)
    def ajustar_tamano(self, nuevo_texto):
        largo_texto = len(nuevo_texto)
        self.setFixedWidth(100 + largo_texto * 10)  # Ajustar el tamaño basado en la longitud del texto
        print(f"Nuevo tamaño ajustado para el texto: {nuevo_texto}")


# 5. Crear la ventana principal
class MainWindow(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle('Botón Personalizado')

        # Crear el CustomButton
        self.boton = CustomButton("Texto Inicial", self)

        # Conectar la señal 'textoCambiado' a un slot para cambiar el tamaño del botón
        self.boton.textoCambiado.connect(self.boton.ajustar_tamano)

        # Configurar el diseño de la ventana principal
        layout = QVBoxLayout(self)
        layout.addWidget(self.boton)


# 6. Crear y ejecutar la aplicación
if __name__ == "__main__":
    app = QApplication([])  # Crear la aplicación
    ventana = MainWindow()  # Crear la ventana principal
    ventana.show()  # Mostrar la ventana
    app.exec()  # Ejecutar la aplicación
