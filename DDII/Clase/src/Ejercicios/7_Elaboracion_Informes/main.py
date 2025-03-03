# --- Librerías necesarias ---
import pandas as pd  # Para manejar el archivo CSV
import altair as alt  # Para crear gráficos interactivos
import datapane as dp  # Para generar informes HTML interactivos
import os  # Para manejar archivos y rutas
from PySide6.QtWebEngineWidgets import QWebEngineView  # Para mostrar el informe en la app
from PySide6.QtWidgets import QMainWindow, QComboBox, QApplication, QVBoxLayout, QWidget
from PySide6.QtCore import QUrl
import sys  # Para manejar la salida del programa

# --- 1. CARGA DE DATOS ---
# Leer el archivo CSV con los datos del inventario y convertir la fecha a formato datetime
fichero_csv = "inventario.csv"
try:
    df = pd.read_csv(fichero_csv)
    df['Fecha de Entrada'] = pd.to_datetime(df['Fecha de Entrada'])  # Convertir la columna de fecha
except Exception as e:
    print(f"Error al cargar el archivo CSV: {e}")
    sys.exit(1)  # Si hay error, salir del programa


# --- 2. GENERACIÓN DE GRÁFICOS ---
def generar_informe(categoria):
    """
    Genera un informe HTML con:
    - Un gráfico de barras (total de unidades disponibles por categoría)
    - Un gráfico de líneas (evolución del inventario a lo largo del tiempo)
    - Una tabla con los productos de la categoría seleccionada
    """

    # Filtrar los datos según la categoría seleccionada
    datos_categoria = df[df['Categoría'] == categoria]

    # --- Gráfico de Barras: Total de Unidades por Categoría ---
    grafico_barras = alt.Chart(datos_categoria).mark_bar().encode(
        x='Categoría:N',  # Categoría en el eje X
        y='Unidades Disponibles:Q',  # Unidades disponibles en el eje Y
        color='Categoría:N'  # Color para diferenciar las categorías
    ).properties(title=f"Unidades disponibles por Categoría - {categoria}")

    # --- Gráfico de Líneas: Evolución del Inventario ---
    grafico_lineas = alt.Chart(datos_categoria).mark_line().encode(
        x=alt.X('Fecha de Entrada:T', title="Fecha"),
        y='Unidades Disponibles:Q',
        color='Categoría:N'
    ).properties(title=f"Evolución de inventario - {categoria}")

    # --- Creación del Informe HTML ---
    titulo = dp.HTML(f'<h1>Informe de Inventario: {categoria}</h1>')  # Título del informe
    tabla_productos = dp.DataTable(datos_categoria[['Producto', 'Unidades Disponibles', 'Precio Unitario (€)',
                                                    'Fecha de Entrada']])  # Tabla con productos

    reporte = dp.Report(
        titulo,
        dp.Plot(grafico_barras),
        dp.Plot(grafico_lineas),
        tabla_productos
    )

    # Guardar el informe HTML en un archivo
    ruta_reporte = os.path.abspath(f"informe_inventario_{categoria}.html")
    reporte.save(ruta_reporte)
    return ruta_reporte  # Devolver la ruta del archivo generado


# --- 3. INTERFAZ GRÁFICA ---
class VentanaPrincipal(QMainWindow):
    """
    Clase que define la ventana principal de la aplicación con:
    - Un combo box para seleccionar la categoría
    - Un visor web para mostrar el informe HTML generado
    """

    def __init__(self):
        super().__init__()
        self.setWindowTitle("Informe de Inventario")  # Título de la ventana

        # --- Selector de Categoría (Combo Box) ---
        self.selector_categoria = QComboBox()
        self.selector_categoria.addItems(df['Categoría'].unique())  # Agregar categorías disponibles
        self.selector_categoria.currentTextChanged.connect(self.actualizar_reporte)  # Evento cuando cambia la selección

        # --- Vista Web para el Informe ---
        self.vista_web = QWebEngineView()  # Visor HTML dentro de la aplicación

        # --- Diseño de la Ventana ---
        layout = QVBoxLayout()
        layout.addWidget(self.selector_categoria)  # Añadir selector de categoría
        layout.addWidget(self.vista_web)  # Añadir visor web

        contenedor = QWidget()
        contenedor.setLayout(layout)
        self.setCentralWidget(contenedor)

        # Cargar el primer informe al iniciar la app
        self.actualizar_reporte()

    def actualizar_reporte(self):
        """
        Función que se ejecuta al seleccionar una categoría y actualiza la vista web con el informe correspondiente.
        """
        categoria_seleccionada = self.selector_categoria.currentText()  # Obtener categoría seleccionada
        ruta_reporte = generar_informe(categoria_seleccionada)  # Generar informe HTML

        # Verificar si el archivo HTML existe y mostrarlo en la vista web
        if os.path.exists(ruta_reporte):
            self.vista_web.load(QUrl.fromLocalFile(ruta_reporte))


# --- 4. EJECUCIÓN DE LA APLICACIÓN ---
if __name__ == "__main__":
    app = QApplication(sys.argv)  # Crear la aplicación Qt
    ventana = VentanaPrincipal()  # Instanciar la ventana principal
    ventana.show()  # Mostrar la ventana
    sys.exit(app.exec())  # Ejecutar la aplicación
