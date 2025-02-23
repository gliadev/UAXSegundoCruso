import sqlite3
import bcrypt

DB_NAME = "usuarios.db"

def crear_base_datos():
    """Crea la base de datos si no existe."""
    conexion = sqlite3.connect(DB_NAME)
    cursor = conexion.cursor()
    cursor.execute('''
    CREATE TABLE IF NOT EXISTS usuarios(
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        usuario TEXT NOT NULL UNIQUE,
        contrasena BLOB NOT NULL,
        es_admin INTEGER NOT NULL DEFAULT 0
    )
    ''')
    conexion.commit()
    conexion.close()

def registrar_usuario(usuario, contrasena, es_admin):
    """Registra un usuario en la base de datos con contraseña encriptada."""
    contrasena_encriptada = bcrypt.hashpw(contrasena.encode(), bcrypt.gensalt())

    try:
        conexion = sqlite3.connect(DB_NAME)
        cursor = conexion.cursor()
        cursor.execute("INSERT INTO usuarios(usuario, contrasena, es_admin) VALUES (?, ?, ?)",
                       (usuario, contrasena_encriptada, es_admin))
        conexion.commit()
        conexion.close()
        return True
    except sqlite3.IntegrityError:
        return False

def verificar_usuario(usuario, contrasena):
    """Verifica si el usuario y la contraseña son correctos."""
    conexion = sqlite3.connect(DB_NAME)
    cursor = conexion.cursor()
    cursor.execute("SELECT contrasena, es_admin FROM usuarios WHERE usuario = ?", (usuario,))
    resultado = cursor.fetchone()
    conexion.close()

    if resultado and bcrypt.checkpw(contrasena.encode(), resultado[0]):
        return True, resultado[1]
    return False, None

def obtener_usuarios():
    """Obtiene la lista de usuarios registrados."""
    conexion = sqlite3.connect(DB_NAME)
    cursor = conexion.cursor()
    cursor.execute("SELECT usuario, contrasena, es_admin FROM usuarios")
    usuarios = cursor.fetchall()
    conexion.close()
    return usuarios

def eliminar_usuario(usuario):
    """Elimina un usuario de la base de datos."""
    conexion = sqlite3.connect(DB_NAME)
    cursor = conexion.cursor()
    cursor.execute("DELETE FROM usuarios WHERE usuario = ?", (usuario,))
    conexion.commit()
    conexion.close()
