import sys
from PySide6.QtCore import QTimer, Qt
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QLabel, QPushButton

class CronometroApp(QWidget):
    def __init__(self):
        super().__init__()

        # Configuración inicial de la ventana
        self.setWindowTitle("Cronómetro")
        self.resize(250, 150)

        # Variables de estado
        self.tiempo_decimas = 0  # Almacenamos el tiempo en décimas de segundo
        self.timer = QTimer(self)
        self.timer.timeout.connect(self.actualizar_tiempo)

        # Widgets de la interfaz
        self.label_tiempo = QLabel("00:00.0", self)  # Se inicializa con décimas
        self.label_tiempo.setStyleSheet("font-size: 24px; color: blue; text-align: center;")
        self.label_tiempo.setAlignment(Qt.AlignCenter)

        self.boton_iniciar = QPushButton("Iniciar", self)
        self.boton_iniciar.clicked.connect(self.iniciar)

        self.boton_pausar = QPushButton("Pausar", self)
        self.boton_pausar.clicked.connect(self.pausar)

        self.boton_reiniciar = QPushButton("Reiniciar", self)
        self.boton_reiniciar.clicked.connect(self.reiniciar)

        # Diseño de la interfaz
        layout = QVBoxLayout()
        layout.addWidget(self.label_tiempo)
        layout.addWidget(self.boton_iniciar)
        layout.addWidget(self.boton_pausar)
        layout.addWidget(self.boton_reiniciar)
        self.setLayout(layout)

    def actualizar_tiempo(self):
        """Actualiza el tiempo mostrado en la etiqueta con décimas de segundo."""
        self.tiempo_decimas += 1  # Incrementamos en décimas
        minutos, resto = divmod(self.tiempo_decimas, 600)  # 600 décimas = 60 segundos
        segundos, decimas = divmod(resto, 10)
        self.label_tiempo.setText(f"{minutos:02}:{segundos:02}.{decimas}")

    def iniciar(self):
        """Inicia el cronómetro."""
        if not self.timer.isActive():
            self.timer.start(100)  # Ahora se ejecuta cada 100 ms (0.1 seg)

    def pausar(self):
        """Pausa el cronómetro."""
        if self.timer.isActive():
            self.timer.stop()

    def reiniciar(self):
        """Reinicia el cronómetro a 00:00.0."""
        self.timer.stop()
        self.tiempo_decimas = 0
        self.label_tiempo.setText("00:00.0")

if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana = CronometroApp()
    ventana.show()
    sys.exit(app.exec())
