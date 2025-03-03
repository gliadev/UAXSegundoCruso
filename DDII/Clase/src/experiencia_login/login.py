import sys
from PySide6.QtWidgets import QMainWindow, QApplication, QVBoxLayout, QLabel, QLineEdit, QPushButton, QWidget, QMessageBox
from database import verificar_usuario
from register import RegisterForm
from welcome import WelcomeForm

class LoginForm(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Inicio de Sesión")
        self.setGeometry(100, 100, 300, 250)

        layout = QVBoxLayout()
        title = QLabel("Inicio de Sesión")
        layout.addWidget(title)

        self.username_input = QLineEdit()
        self.username_input.setPlaceholderText("Nombre de usuario")
        layout.addWidget(self.username_input)

        self.password_input = QLineEdit()
        self.password_input.setPlaceholderText("Contraseña")
        self.password_input.setEchoMode(QLineEdit.Password)
        layout.addWidget(self.password_input)

        login_button = QPushButton("Iniciar sesión")
        login_button.clicked.connect(self.iniciar_sesion)
        layout.addWidget(login_button)

        register_button = QPushButton("Registrar")
        register_button.clicked.connect(self.abrir_registro)
        layout.addWidget(register_button)

        central_widget = QWidget()
        central_widget.setLayout(layout)
        self.setCentralWidget(central_widget)

    def abrir_registro(self):
        self.registro = RegisterForm()
        self.registro.show()
        self.close()

    def iniciar_sesion(self):
        usuario = self.username_input.text()
        contrasena = self.password_input.text()

        if not usuario or not contrasena:
            QMessageBox.warning(self, "Error", "Todos los campos son obligatorios")
            return

        valido, es_admin = verificar_usuario(usuario, contrasena)
        if valido:
            self.bienvenida = WelcomeForm(usuario, es_admin)
            self.bienvenida.show()
            self.close()
        else:
            QMessageBox.critical(self, "Error", "Usuario o contraseña incorrectos")
            self.username_input.clear()
            self.password_input.clear()
