from PySide6.QtWidgets import QWidget
from PySide6.QtCore import Qt, Signal
from PySide6.QtGui import QPainter, QPen, QColor


class CircularProgress(QWidget):
    progressChanged = Signal(int)

    def __init__(self, parent=None):
        super().__init__(parent)
        self._progress = 0
        self._max_value = 100
        self._thickness = 10
        self._circle_color = QColor(100, 150, 255)
        self._bg_color = QColor(200, 200, 200)
        self.setMinimumSize(400, 400)

        # Configurar la señal de progreso
        self.progressChanged.connect(self.update)

    def setProgress(self, value):
        """Actualiza el progreso y emite la señal"""
        if 0 <= value <= self._max_value:
            self._progress = value
            self.progressChanged.emit(value)

    def setCircleColor(self, color):
        """Configura el color del círculo de progreso"""
        self._circle_color = QColor(color)

    def setBgColor(self, color):
        """Configura el color de fondo del indicador"""
        self._bg_color = QColor(color)

    def paintEvent(self, event):
        """Dibuja el indicador de progreso"""
        painter = QPainter(self)
        painter.setRenderHint(QPainter.Antialiasing)

        # Obtener el tamaño y centro
        size = min(self.width(), self.height()) - self._thickness
        rect = self.rect().adjusted(self._thickness, self._thickness, -self._thickness, -self._thickness)

        # Dibujar el fondo del círculo
        pen = QPen(self._bg_color, self._thickness, Qt.SolidLine, Qt.RoundCap, Qt.RoundJoin)
        painter.setPen(pen)
        painter.drawArc(rect, 0, 360 * 16)

        # Dibujar el progreso
        pen.setColor(self._circle_color)
        painter.setPen(pen)
        angle = int(360 * 16 * (self._progress / self._max_value))
        painter.drawArc(rect, 90 * 16, -angle)

        painter.end()


# Prueba del componente
if __name__ == "__main__":
    from PySide6.QtWidgets import QApplication, QVBoxLayout, QPushButton, QSlider, QLabel

    app = QApplication([])

    main_widget = QWidget()
    layout = QVBoxLayout(main_widget)

    progress_circle = CircularProgress()
    slider = QSlider(Qt.Horizontal)
    slider.setMinimum(0)
    slider.setMaximum(100)

    def update_progress(value):
        progress_circle.setProgress(value)

    slider.valueChanged.connect(update_progress)
    layout.addWidget(progress_circle)
    layout.addWidget(QLabel("Ajusta el progreso"))
    layout.addWidget(slider)

    main_widget.show()
    app.exec()
