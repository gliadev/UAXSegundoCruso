import sys
import os
import pytest
from PySide6.QtWidgets import QApplication

# Agregar la ruta del directorio raíz al sys.path
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), '..')))

# Importar correctamente la clase desde notas.py
try:
    from notas import NoteApp
except ModuleNotFoundError:
    print("⚠️ ERROR: No se pudo encontrar el módulo 'notas'. Verifica que 'notas.py' esté en la carpeta correcta.")
    sys.exit(1)

app = QApplication([])  # Se necesita una instancia de QApplication para PySide6

@pytest.fixture
def note_app():
    return NoteApp()

def test_add_note(note_app):
    note_app.note_input.setText("Nota de prueba")
    note_app.add_note()
    assert note_app.note_list.count() == 1
    assert note_app.note_list.item(0).text() == "Nota de prueba"

def test_delete_note(note_app):
    note_app.note_input.setText("Nota de prueba")
    note_app.add_note()
    note_app.note_list.setCurrentRow(0)
    note_app.delete_note()
    assert note_app.note_list.count() == 0

def test_empty_note(note_app):
    note_app.note_input.setText("")
    note_app.add_note()
    assert note_app.note_list.count() == 0  # No se debe agregar una nota vacía
