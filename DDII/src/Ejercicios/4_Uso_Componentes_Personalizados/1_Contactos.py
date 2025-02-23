import sys
from PySide6.QtGui import QAction  # Importamos QAction desde QtGui
from PySide6.QtWidgets import QApplication, QMainWindow, QPushButton, QMenu, QVBoxLayout, QWidget, QLabel, QListWidget, QInputDialog, QMessageBox
from PySide6.QtCore import Qt

class ContactosApp(QMainWindow):
    def __init__(self):
        super().__init__()

        # Inicialización de la ventana principal
        self.setWindowTitle("Gestión de Contactos")
        self.setGeometry(300, 300, 400, 300)

        # Lista de contactos
        self.contactos = []

        # Crear el widget central y el layout
        central_widget = QWidget(self)
        self.setCentralWidget(central_widget)
        layout = QVBoxLayout(central_widget)

        # Crear un QLabel para mostrar los mensajes
        self.label = QLabel("Gestiona tus contactos", self)
        layout.addWidget(self.label)

        # Crear una lista para mostrar los contactos
        self.lista_contactos = QListWidget(self)
        layout.addWidget(self.lista_contactos)

        # Crear un botón para añadir un contacto ficticio
        self.boton_agregar = QPushButton("Agregar contacto ficticio", self)
        self.boton_agregar.clicked.connect(self.agregar_contacto_ficticio)
        layout.addWidget(self.boton_agregar)

        # Crear el menú y las acciones
        self.crear_menu()

    def crear_menu(self):
        """Crea la barra de menú con opciones de gestión de contactos"""
        menu_bar = self.menuBar()
        contactos_menu = menu_bar.addMenu("Contactos")

        # Acción "Añadir contacto"
        agregar_action = QAction("Añadir contacto", self)
        agregar_action.triggered.connect(self.agregar_contacto)
        contactos_menu.addAction(agregar_action)

        # Acción "Ver contactos"
        ver_action = QAction("Ver contactos", self)
        ver_action.triggered.connect(self.ver_contactos)
        contactos_menu.addAction(ver_action)

        # Acción "Eliminar contacto"
        eliminar_action = QAction("Eliminar contacto", self)
        eliminar_action.triggered.connect(self.eliminar_contacto)
        contactos_menu.addAction(eliminar_action)

    def agregar_contacto(self):
        """Solicita un nuevo contacto y lo añade a la lista"""
        nombre, ok = QInputDialog.getText(self, "Nuevo Contacto", "Introduce el nombre del contacto:")
        if ok and nombre:
            self.contactos.append(nombre)
            self.lista_contactos.addItem(nombre)
            self.label.setText(f"Contacto '{nombre}' añadido.")
        else:
            self.label.setText("Nombre no proporcionado.")

    def ver_contactos(self):
        """Muestra los contactos registrados"""
        if self.contactos:
            self.label.setText("Contactos: " + ", ".join(self.contactos))
        else:
            self.label.setText("No hay contactos disponibles.")

    def eliminar_contacto(self):
        """Elimina el contacto seleccionado"""
        item = self.lista_contactos.currentItem()
        if item:
            nombre_contacto = item.text()
            self.contactos.remove(nombre_contacto)
            self.lista_contactos.takeItem(self.lista_contactos.row(item))
            self.label.setText(f"Contacto '{nombre_contacto}' eliminado.")
        else:
            self.label.setText("Selecciona un contacto para eliminar.")

    def agregar_contacto_ficticio(self):
        """Añade un contacto ficticio a la lista"""
        contacto_ficticio = "Contacto Ficticio"
        self.contactos.append(contacto_ficticio)
        self.lista_contactos.addItem(contacto_ficticio)
        self.label.setText(f"Contacto ficticio '{contacto_ficticio}' añadido.")

# Ejecutar la aplicación
if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ContactosApp()
    window.show()
    sys.exit(app.exec())
