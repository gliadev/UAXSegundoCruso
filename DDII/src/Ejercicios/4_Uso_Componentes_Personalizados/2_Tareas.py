import sys
from PySide6.QtGui import QAction  # Importamos QAction desde QtGui
from PySide6.QtWidgets import QApplication, QMainWindow, QPushButton, QMenu, QVBoxLayout, QWidget, QLabel, QListWidget, QInputDialog, QMessageBox
from PySide6.QtCore import Qt


class TareasApp(QMainWindow):
    def __init__(self):
        super().__init__()

        # Inicialización de la ventana principal
        self.setWindowTitle("Gestión de Tareas")
        self.setGeometry(300, 300, 400, 300)

        # Lista de tareas
        self.tareas = []

        # Crear el widget central y el layout
        central_widget = QWidget(self)
        self.setCentralWidget(central_widget)
        layout = QVBoxLayout(central_widget)

        # Crear un QLabel para mostrar los mensajes
        self.label = QLabel("Gestiona tus tareas", self)
        layout.addWidget(self.label)

        # Crear una lista para mostrar las tareas
        self.lista_tareas = QListWidget(self)
        layout.addWidget(self.lista_tareas)

        # Crear un botón para marcar una tarea como completada
        self.boton_completar = QPushButton("Marcar tarea como completada", self)
        self.boton_completar.clicked.connect(self.marcar_completada)
        layout.addWidget(self.boton_completar)

        # Crear el menú y las acciones
        self.crear_menu()

        # Crear menú contextual para asignar tarea a usuario
        self.lista_tareas.setContextMenuPolicy(Qt.CustomContextMenu)
        self.lista_tareas.customContextMenuRequested.connect(self.mostrar_menu_contextual)

    def crear_menu(self):
        """Crea la barra de menú con opciones de gestión de tareas"""
        menu_bar = self.menuBar()
        tareas_menu = menu_bar.addMenu("Tareas")

        # Acción "Nueva tarea"
        nueva_action = QAction("Nueva tarea", self)
        nueva_action.triggered.connect(self.nueva_tarea)
        tareas_menu.addAction(nueva_action)

        # Acción "Editar tarea"
        editar_action = QAction("Editar tarea", self)
        editar_action.triggered.connect(self.editar_tarea)
        tareas_menu.addAction(editar_action)

        # Acción "Eliminar tarea"
        eliminar_action = QAction("Eliminar tarea", self)
        eliminar_action.triggered.connect(self.eliminar_tarea)
        tareas_menu.addAction(eliminar_action)

    def nueva_tarea(self):
        """Solicita una nueva tarea y la añade a la lista"""
        descripcion, ok = QInputDialog.getText(self, "Nueva Tarea", "Introduce la descripción de la tarea:")
        if ok and descripcion:
            self.tareas.append(descripcion)
            self.lista_tareas.addItem(descripcion)
            self.label.setText(f"Tarea '{descripcion}' añadida.")
        else:
            self.label.setText("Descripción no proporcionada.")

    def editar_tarea(self):
        """Permite editar la tarea seleccionada"""
        item = self.lista_tareas.currentItem()
        if item:
            nueva_descripcion, ok = QInputDialog.getText(self, "Editar Tarea", "Edita la descripción de la tarea:")
            if ok and nueva_descripcion:
                item.setText(nueva_descripcion)
                self.label.setText(f"Tarea editada: {nueva_descripcion}")
        else:
            self.label.setText("Selecciona una tarea para editar.")

    def eliminar_tarea(self):
        """Elimina una tarea seleccionada"""
        item = self.lista_tareas.currentItem()
        if item:
            self.tareas.remove(item.text())
            self.lista_tareas.takeItem(self.lista_tareas.row(item))
            self.label.setText(f"Tarea '{item.text()}' eliminada.")
        else:
            self.label.setText("Selecciona una tarea para eliminar.")

    def marcar_completada(self):
        """Marca la tarea seleccionada como completada"""
        item = self.lista_tareas.currentItem()
        if item:
            item.setText(f"{item.text()} (Completada)")
            self.label.setText(f"Tarea '{item.text()}' marcada como completada.")
        else:
            self.label.setText("Selecciona una tarea para marcar como completada.")

    def mostrar_menu_contextual(self, pos):
        """Muestra el menú contextual para asignar tareas a usuarios"""
        menu_contextual = QMenu(self)
        asignar_action = menu_contextual.addAction("Asignar tarea a usuario")
        asignar_action.triggered.connect(self.asignar_usuario)
        menu_contextual.exec(self.lista_tareas.mapToGlobal(pos))

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = TareasApp()
    window.show()
    sys.exit(app.exec())
