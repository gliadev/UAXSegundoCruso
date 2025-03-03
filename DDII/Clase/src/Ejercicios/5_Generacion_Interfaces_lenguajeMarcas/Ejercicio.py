import sys
from PyQt5 import QtWidgets, uic


class MainWindow(QtWidgets.QMainWindow):
    def __init__(self):
        super().__init__()
        uic.loadUi("interfaz.ui", self)

        # Crear barra de herramientas y agregar botones
        self.toolbar = self.addToolBar("Barra de herramientas")

        self.btnLimpiar = QtWidgets.QAction("Limpiar", self)
        self.btnLimpiar.triggered.connect(self.limpiarTexto)
        self.toolbar.addAction(self.btnLimpiar)

        self.btnMensaje = QtWidgets.QAction("Mostrar mensaje", self)
        self.btnMensaje.triggered.connect(self.mostrarMensaje)
        self.toolbar.addAction(self.btnMensaje)

        # Conectar menú de ayuda a la ventana de diálogo
        self.menuAyuda.addAction("Acerca de", self.mostrarDialogo)

    def limpiarTexto(self):
        self.plainTextEdit.clear()

    def mostrarMensaje(self):
        QtWidgets.QMessageBox.information(self, "Mensaje", "Este es un mensaje personalizado.")

    def mostrarDialogo(self):
        dialogo = QtWidgets.QDialog(self)
        dialogo.setWindowTitle("Acerca de")
        layout = QtWidgets.QVBoxLayout()

        etiqueta = QtWidgets.QLabel("Esta aplicación permite cambiar el texto y mostrar mensajes.")
        layout.addWidget(etiqueta)

        botonCerrar = QtWidgets.QPushButton("Cerrar")
        botonCerrar.clicked.connect(dialogo.accept)
        layout.addWidget(botonCerrar)

        dialogo.setLayout(layout)
        dialogo.exec_()


if __name__ == "__main__":
    app = QtWidgets.QApplication(sys.argv)
    window = MainWindow()
    window.show()
    sys.exit(app.exec_())
