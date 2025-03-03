from PySide6.QtWidgets import QApplication, QWidget, QVBoxLayout, QLineEdit, QPushButton, QListWidget, QMessageBox
import sys

class NoteApp(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Gestor de Notas")
        self.setGeometry(100, 100, 400, 300)

        self.layout = QVBoxLayout()

        self.note_input = QLineEdit()
        self.note_input.setPlaceholderText("Escribe una nota...")
        self.layout.addWidget(self.note_input)

        self.add_button = QPushButton("Agregar Nota")
        self.add_button.clicked.connect(self.add_note)
        self.layout.addWidget(self.add_button)

        self.note_list = QListWidget()
        self.layout.addWidget(self.note_list)

        self.delete_button = QPushButton("Eliminar Nota")
        self.delete_button.clicked.connect(self.delete_note)
        self.layout.addWidget(self.delete_button)

        self.setLayout(self.layout)

    def add_note(self):
        note = self.note_input.text().strip()
        if note:
            self.note_list.addItem(note)
            self.note_input.clear()
        else:
            QMessageBox.warning(self, "Error", "La nota no puede estar vacía")

    def delete_note(self):
        selected_item = self.note_list.currentRow()
        if selected_item >= 0:
            self.note_list.takeItem(selected_item)
        else:
            QMessageBox.warning(self, "Error", "Selecciona una nota para eliminar")

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = NoteApp()
    window.show()
    sys.exit(app.exec())
