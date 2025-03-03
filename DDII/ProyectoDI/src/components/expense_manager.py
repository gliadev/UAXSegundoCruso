from PySide6.QtWidgets import QWidget, QVBoxLayout, QLineEdit, QPushButton, QListWidget, QComboBox, QMessageBox, QLabel
from components.expense_database import ExpenseDatabase


class ExpenseManager(QWidget):
    def __init__(self):
        super().__init__()

        self.db = ExpenseDatabase()
        self.layout = QVBoxLayout()

        # Campo de entrada para el monto
        self.amount_input = QLineEdit()
        self.amount_input.setPlaceholderText("Ingrese el monto del gasto...")
        self.layout.addWidget(self.amount_input)

        # Selección de categoría
        self.category_selector = QComboBox()
        self.category_selector.addItems(["Comida", "Transporte", "Ocio", "Otros"])
        self.layout.addWidget(self.category_selector)

        # Botón para agregar gasto
        self.add_button = QPushButton("Agregar Gasto")
        self.add_button.clicked.connect(self.add_expense)
        self.layout.addWidget(self.add_button)

        # Lista de gastos
        self.expense_list = QListWidget()
        self.layout.addWidget(self.expense_list)

        # Etiqueta para mostrar total
        self.total_label = QLabel("Total: 0€")
        self.layout.addWidget(self.total_label)

        self.setLayout(self.layout)
        self.total_expense = 0
        self.load_expenses()

    def add_expense(self):
        amount = self.amount_input.text().strip()
        category = self.category_selector.currentText()

        if amount and amount.replace('.', '', 1).isdigit():
            amount = float(amount)
            self.db.add_expense(amount, category)
            self.total_expense += amount
            self.expense_list.addItem(f"{category}: {amount}€")
            self.total_label.setText(f"Total: {self.total_expense:.2f}€")
            self.amount_input.clear()
        else:
            QMessageBox.warning(self, "Error", "Ingrese un monto válido")

    def load_expenses(self):
        expenses = self.db.get_expenses()
        self.total_expense = self.db.get_total_expense()
        for expense in expenses:
            self.expense_list.addItem(f"{expense[2]}: {expense[1]}€")
        self.total_label.setText(f"Total: {self.total_expense:.2f}€")
