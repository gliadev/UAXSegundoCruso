## se crea una intancia que se van usar todos los test
import pytest
import sys
import os

# Agregar la ruta base al sys.path para que Python encuentre Clase08
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))


from PySide6.QtWidgets import QApplication
from CodigoClase.Clase08 import TaskManager



@pytest.fixture(scope='session')
def app_instance():
    app = QApplication([])
    yield app
    app.quit()


@pytest.fixture()
def window(app_instance):
    window = TaskManager()  # ✅ Corrección de instancia
    window.show()
    return window


def test_add_empty_task(window):
    window.task_input.setText("")
    window.add_task()
    assert window.task_list.count() == 0


def test_delete_task(window):
    window.task_input.setText("Tarea de prueba")  # ✅ Agregar texto
    window.add_task()
    assert window.task_list.count() == 1  # ✅ Verificar que la tarea se agregó
    window.delete_task()
    assert window.task_list.count() == 0  # ✅ Verificar que la tarea se eliminó