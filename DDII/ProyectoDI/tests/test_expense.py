import pytest
from components.expense_database import ExpenseDatabase

@pytest.fixture
def db():
    db = ExpenseDatabase(":memory:")  # Base de datos en memoria para pruebas
    yield db
    db.close_connection()

def test_add_expense(db):
    db.add_expense(50.0, "Comida")
    expenses = db.get_expenses()
    assert len(expenses) == 1
    assert expenses[0][1] == 50.0
    assert expenses[0][2] == "Comida"

def test_delete_expense(db):
    db.add_expense(20.0, "Transporte")
    expense_id = db.get_expenses()[0][0]
    db.delete_expense(expense_id)
    expenses = db.get_expenses()
    assert len(expenses) == 0

def test_get_total_expense(db):
    db.add_expense(30.0, "Ocio")
    db.add_expense(70.0, "Comida")
    total = db.get_total_expense()
    assert total == 100.0
