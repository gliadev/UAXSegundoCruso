from PySide6.QtWidgets import QMainWindow, QVBoxLayout, QLabel, QLineEdit, QPushButton, QWidget, QMessageBox, QCheckBox
from database import registrar_usuario
from login import LoginForm

class RegisterForm(QMainWindow):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Registro")
        self.setGeometry(100, 100, 400, 400)

        layout = QVBoxLayout()
        title = QLabel("Registro")
        layout.addWidget(title)

        self.username_input = QLineEdit()
        self.username_input.setPlaceholderText("Nombre de usuario")
        layout.addWidget(self.username_input)

        self.password_input = QLineEdit()
        self.password_input.setPlaceholderText("Contraseña")
        self.password_input.setEchoMode(QLineEdit.Password)
        layout.addWidget(self.password_input)

        self.admin_checkbox = QCheckBox("¿Es administrador?")
        layout.addWidget(self.admin_checkbox)

        register_button = QPushButton("Registrar")
        register_button.clicked.connect(self.registrar_usuario)
        layout.addWidget(register_button)

        back_button = QPushButton("Iniciar sesión")
        back_button.clicked.connect(self.volver_a_login)
        layout.addWidget(back_button)

        central_widget = QWidget()
        central_widget.setLayout(layout)
        self.setCentralWidget(central_widget)

    def volver_a_login(self):
        self.login = LoginForm()
        self.login.show()
        self.close()

    def registrar_usuario(self):
        usuario = self.username_input.text()
        contrasena = self.password_input.text()
        es_admin = 1 if self.admin_checkbox.isChecked() else 0

        if not usuario or not contrasena:
            QMessageBox.warning(self, "Error", "Todos los campos son obligatorios")
            return

        if registrar_usuario(usuario, contrasena, es_admin):
            QMessageBox.information(self, "Éxito", "Usuario registrado")
            self.volver_a_login()
        else:
            QMessageBox.critical(self, "Error", "El usuario ya existe")
