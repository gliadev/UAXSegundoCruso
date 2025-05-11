import sys
from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QLineEdit, QPushButton, QTextEdit

class TaskApp(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Lista de Tareas")
        self.setGeometry(300, 300, 400, 300)

        # Layout
        layout = QVBoxLayout()

        # Cuadro de texto para ingresar tareas
        self.task_input = QLineEdit()
        self.task_input.setPlaceholderText("Escribe una tarea")

        # Área de texto para mostrar las tareas
        self.task_list = QTextEdit()
        self.task_list.setReadOnly(True)  # Solo lectura

        # Botón para agregar tarea
        self.add_button = QPushButton("Agregar tarea")
        self.add_button.clicked.connect(self.add_task)

        # Añadir widgets al layout
        layout.addWidget(self.task_input)
        layout.addWidget(self.add_button)
        layout.addWidget(self.task_list)

        self.setLayout(layout)

    def add_task(self):
        task = self.task_input.text()
        if task:
            # Añadir la tarea a la lista
            current_tasks = self.task_list.toPlainText()
            new_tasks = current_tasks + f"- {task}\n"
            self.task_list.setPlainText(new_tasks)
            self.task_input.clear()  # Limpiar el cuadro de texto

if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = TaskApp()
    window.show()
    sys.exit(app.exec())
