from PySide6.QtWidgets import QWidget, QApplication, QVBoxLayout, QLineEdit, QPushButton, QListWidget, QMessageBox


class TaskManager(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Gestor de Tareas")
        self.setGeometry(100, 100, 400, 300)

        self.layout = QVBoxLayout()

        self.task_input = QLineEdit()
        self.task_input.setPlaceholderText("Escribe una tarea...")
        self.layout.addWidget(self.task_input)

        self.task_button = QPushButton("Agregar tarea")
        self.task_button.clicked.connect(self.add_task)  # Conexión corregida
        self.layout.addWidget(self.task_button)

        self.task_list = QListWidget()
        self.layout.addWidget(self.task_list)

        self.delete_button = QPushButton("Eliminar tarea")
        self.delete_button.clicked.connect(self.delete_task)  # Nueva conexión para eliminar
        self.layout.addWidget(self.delete_button)

        self.setLayout(self.layout)

    def add_task(self):
        task = self.task_input.text().strip()  # Corregido el acceso a texto

        if task:
            self.task_list.addItem(task)
            self.task_input.clear()
        else:
            QMessageBox.warning(self, "Error", "La tarea no puede estar vacía")

    def delete_task(self):
        selected_item = self.task_list.currentRow()
        if selected_item >=0:
            self.task_list.takeItem(selected_item)
        else:
            QMessageBox.warning(self, "Error", "Selecciona una tarea para eliminar")


if __name__ == '__main__':
    app = QApplication([])
    window = TaskManager()
    window.show()
    app.exec()
