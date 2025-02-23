import sys
from PySide6.QtWidgets import QApplication
from login import LoginForm
from database import crear_base_datos

if __name__ == "__main__":
    crear_base_datos()
    app = QApplication(sys.argv)
    ventana = LoginForm()
    ventana.show()
    sys.exit(app.exec())
