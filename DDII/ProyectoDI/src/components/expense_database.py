import sqlite3

class ExpenseDatabase:
    def __init__(self, db_name="expenses.db"):
        self.connection = sqlite3.connect(db_name)
        self.cursor = self.connection.cursor()
        self.create_table()

    def create_table(self):
        self.cursor.execute("""
            CREATE TABLE IF NOT EXISTS expenses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                amount REAL NOT NULL,
                category TEXT NOT NULL
            )
        """
        )
        self.connection.commit()

    def add_expense(self, amount, category):
        self.cursor.execute("INSERT INTO expenses (amount, category) VALUES (?, ?)", (amount, category))
        self.connection.commit()

    def delete_expense(self, expense_id):
        self.cursor.execute("DELETE FROM expenses WHERE id = ?", (expense_id,))
        self.connection.commit()

    def get_expenses(self):
        self.cursor.execute("SELECT id, amount, category FROM expenses")
        return self.cursor.fetchall()

    def get_total_expense(self):
        self.cursor.execute("SELECT SUM(amount) FROM expenses")
        result = self.cursor.fetchone()[0]
        return result if result else 0

    def close_connection(self):
        self.connection.close()
