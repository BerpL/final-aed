# Sistema de Reserva de Hoteles

Sistema de gestión de reservas hoteleras desarrollado en JavaFX.

## Importante: Nombre del Proyecto

**El nombre del proyecto debe ser `Reserva_de_hoteles`.**

Si la carpeta del proyecto tiene otro nombre (como `Reserva_de_vuelos`), renómbrala antes de continuar:

1. Cierra Eclipse si está abierto
2. Renombra la carpeta del proyecto a `Reserva_de_hoteles`
3. Vuelve a abrir Eclipse e importa el proyecto

Esto es importante porque el nombre del módulo Java debe coincidir con el nombre del proyecto.

## ¿Qué necesito para ejecutar este proyecto?

Necesitas instalar 3 cosas antes de poder ejecutar el proyecto:

1. **Java Development Kit (JDK) 24** - El lenguaje de programación
2. **JavaFX SDK 25** - La librería para crear la interfaz gráfica
3. **Ikonli** - Librería para los iconos

## Paso 1: Instalar Java (JDK 24)

### ¿Qué es esto?
Java es el lenguaje de programación que usa este proyecto. Necesitas la versión 24.

### ¿Cómo lo instalo?

1. Ve a esta página: [Descargar JDK 24](https://www.oracle.com/java/technologies/javase/jdk24-archive-downloads.html)

2. Descarga el archivo según tu computadora:
   - **Mac con Apple Silicon (M1, M2, M3)**: Busca "macOS Arm 64 DMG Installer" y descárgalo
   - **Mac con Intel**: Busca "macOS x64 DMG Installer" y descárgalo
   - **Windows**: Busca "Windows x64 Installer" y descárgalo
   - **Linux**: Busca "Linux x64 Compressed Archive" o "Linux x64 Debian Package" según tu distribución

3. Ejecuta el instalador y sigue las instrucciones (solo haz clic en "Siguiente" hasta terminar)

4. Verifica que se instaló correctamente:
   - Abre una terminal (en Mac: Terminal, en Windows: CMD o PowerShell)
   - Escribe: `java -version`
   - Debe aparecer algo como "java version 24.x.x"

## Paso 2: Instalar JavaFX SDK 25

### ¿Qué es esto?
JavaFX es la librería que permite crear ventanas y botones. Sin esto, el proyecto no puede ejecutarse.

### ¿Cómo lo instalo?

1. Ve a esta página: [Descargar JavaFX](https://gluonhq.com/products/javafx/)

2. Busca "JavaFX 25 SDK" y descarga la versión para tu sistema:
   - **Mac con Apple Silicon**: "macOS, aarch64, SDK"
   - **Mac con Intel**: "macOS, x64, SDK"
   - **Windows**: "Windows, x64, SDK"
   - **Linux**: "Linux, x64, SDK"

3. Extrae el archivo ZIP que descargaste

4. Renombra la carpeta extraída a `javafx-sdk`

5. Copia la carpeta `javafx-sdk` completa dentro de la carpeta del proyecto (donde está el archivo README.md)

**Resultado esperado:** Debes tener una carpeta llamada `javafx-sdk` dentro de la carpeta del proyecto.

## Paso 3: Instalar Ikonli (Iconos)

### ¿Qué es esto?
Ikonli es la librería que proporciona los iconos que ves en la aplicación (como el ícono de cama, calendario, etc.).

### ¿Cómo lo instalo?

1. Crea una carpeta llamada `lib` dentro de la carpeta del proyecto (si no existe)

2. Descarga estos 3 archivos y guárdalos en la carpeta `lib`:
   - [ikonli-core-12.3.1.jar](https://repo1.maven.org/maven2/org/kordamp/ikonli/ikonli-core/12.3.1/ikonli-core-12.3.1.jar)
   - [ikonli-javafx-12.3.1.jar](https://repo1.maven.org/maven2/org/kordamp/ikonli/ikonli-javafx/12.3.1/ikonli-javafx-12.3.1.jar)
   - [ikonli-fontawesome5-pack-12.3.1.jar](https://repo1.maven.org/maven2/org/kordamp/ikonli/ikonli-fontawesome5-pack/12.3.1/ikonli-fontawesome5-pack-12.3.1.jar)

   **Consejo:** Haz clic derecho en cada enlace y selecciona "Guardar enlace como..." o "Save link as..."

3. Verifica que tengas 3 archivos dentro de la carpeta `lib`:
   - ikonli-core-12.3.1.jar
   - ikonli-javafx-12.3.1.jar
   - ikonli-fontawesome5-pack-12.3.1.jar

## Paso 4: Abrir el proyecto en Eclipse

### ¿Qué es Eclipse?
Eclipse es un programa (IDE) que te permite escribir y ejecutar código Java de forma fácil.

### ¿Cómo abro el proyecto?

1. **Descarga Eclipse** (si no lo tienes):
   - Ve a [eclipse.org/downloads](https://www.eclipse.org/downloads/)
   - Descarga "Eclipse IDE for Enterprise Java and Web Developers"
   - Instálalo como cualquier programa normal

2. **Abre Eclipse**

3. **Importa el proyecto:**
   - En el menú superior, haz clic en `File` → `Import...`
   - En la ventana que aparece, busca y selecciona `General` → `Existing Projects into Workspace`
   - Haz clic en `Next`
   - Haz clic en `Browse...` y selecciona la carpeta del proyecto (donde está el README.md)
   - Asegúrate de que el proyecto esté marcado con una ✓
   - Haz clic en `Finish`

4. **Espera a que Eclipse termine de cargar el proyecto** (puede tardar unos segundos)

5. **Verifica el nombre del proyecto:**
   - El nombre del proyecto en Eclipse debe ser `Reserva_de_hoteles`
   - Si ves un nombre diferente (como `Reserva_de_vuelos`), haz clic derecho en el proyecto
   - Selecciona `Refactor` → `Rename...`
   - Cambia el nombre a `Reserva_de_hoteles`
   - Haz clic en `OK`

6. **Limpia el proyecto** (muy importante):
   - Haz clic derecho en el proyecto
   - Selecciona `Project` → `Clean...`
   - Asegúrate de que tu proyecto esté marcado
   - Haz clic en `Clean`
   - Espera a que termine (verás mensajes en la parte inferior de Eclipse)

## Paso 5: Ejecutar el proyecto

Una vez que el proyecto esté abierto en Eclipse:

1. **Verifica la configuración de ejecución:**
   - Haz clic derecho en el proyecto
   - Selecciona `Run As` → `Run Configurations...`
   - Si ya existe una configuración llamada "Main", selecciónala
   - Ve a la pestaña "Arguments"
   - En "VM arguments", verifica que todas las referencias digan `Reserva_de_hoteles` (no `Reserva_de_vuelos`)
   - Si ves el nombre antiguo, elimina esa configuración (botón "Delete") y crea una nueva (ver paso siguiente)

2. **Si necesitas crear una nueva configuración:**
   - En "Run Configurations...", haz clic en "New launch configuration" (icono de hoja con +)
   - Nombre: `Main`
   - Project: `Reserva_de_hoteles` (asegúrate de que sea este nombre)
   - Main class: `main.Main`
   - Ve a la pestaña "Arguments"
   - En "VM arguments", pega exactamente esto:
     ```
     --module-path "${workspace_loc:Reserva_de_hoteles/javafx-sdk/lib}" --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics -Djava.library.path="${workspace_loc:Reserva_de_hoteles/javafx-sdk/lib}" -XstartOnFirstThread
     ```
   - **Nota para Windows:** Elimina `-XstartOnFirstThread` del final
   - **Nota para Linux:** Elimina `-XstartOnFirstThread` del final
   - Haz clic en `Apply` y luego en `Run`

3. **O ejecuta directamente:**
   - En la parte izquierda (Package Explorer), busca la carpeta `src` → `main`
   - Haz clic derecho en el archivo `Main.java`
   - Selecciona `Run As` → `Java Application`

¡Listo! Debería abrirse la ventana de la aplicación.

## Estructura de carpetas esperada

Tu proyecto debe verse así:

```
Reserva_de_hoteles/
├── src/                    (código fuente)
├── javafx-sdk/             (JavaFX - lo descargaste en el Paso 2)
├── lib/                    (Ikonli - lo descargaste en el Paso 3)
│   ├── ikonli-core-12.3.1.jar
│   ├── ikonli-javafx-12.3.1.jar
│   └── ikonli-fontawesome5-pack-12.3.1.jar
└── README.md               (este archivo)
```

## Problemas comunes y soluciones

### El proyecto no se ejecuta y aparece un error sobre "JavaFX"

**Problema:** JavaFX no está en la ubicación correcta.

**Solución:**
- Verifica que la carpeta `javafx-sdk` esté dentro de la carpeta del proyecto
- Asegúrate de que dentro de `javafx-sdk` haya una carpeta llamada `lib` con archivos `.jar`

### Los iconos no se ven

**Problema:** Faltan los archivos de Ikonli.

**Solución:**
- Verifica que la carpeta `lib` exista dentro del proyecto
- Asegúrate de tener los 3 archivos `.jar` mencionados en el Paso 3

### Eclipse dice que no encuentra Java 24

**Problema:** Java 24 no está instalado o Eclipse no lo detecta.

**Solución:**
1. Verifica que Java 24 esté instalado (Paso 1)
2. En Eclipse, ve a `Window` → `Preferences` → `Java` → `Installed JREs`
3. Haz clic en `Add...` y busca la carpeta donde instalaste Java 24
4. Selecciona Java 24 y haz clic en `Apply and Close`

### Error: "Module Reserva_de_vuelos not found" o "Module not found"

**Problema:** El nombre del proyecto en Eclipse no coincide con el nombre del módulo, o hay archivos compilados antiguos.

**Solución paso a paso:**

1. **Verifica el nombre del proyecto:**
   - En Eclipse, mira el nombre del proyecto en la parte izquierda (Package Explorer)
   - Debe decir `Reserva_de_hoteles`
   - Si dice `Reserva_de_vuelos` o cualquier otro nombre:
     - Haz clic derecho en el proyecto
     - Selecciona `Refactor` → `Rename...`
     - Cambia el nombre a `Reserva_de_hoteles`
     - Haz clic en `OK`

2. **Limpia el proyecto:**
   - Haz clic derecho en el proyecto
   - Selecciona `Project` → `Clean...`
   - Asegúrate de que tu proyecto esté marcado
   - Haz clic en `Clean`
   - Espera a que termine de recompilar

3. **Verifica la configuración de ejecución:**
   - Haz clic derecho en el proyecto
   - Selecciona `Run As` → `Run Configurations...`
   - Si hay una configuración llamada "Main", selecciónala
   - Ve a la pestaña "Arguments"
   - En "VM arguments", verifica que diga `Reserva_de_hoteles` (no `Reserva_de_vuelos`)
   - Si dice el nombre antiguo, elimina esa configuración y crea una nueva:
     - Haz clic en "New launch configuration"
     - Nombre: `Main`
     - Project: `Reserva_de_hoteles`
     - Main class: `main.Main`
     - En VM arguments, pega esto:
       ```
       --module-path "${workspace_loc:Reserva_de_hoteles/javafx-sdk/lib}" --add-modules javafx.controls,javafx.fxml --enable-native-access=javafx.graphics -Djava.library.path="${workspace_loc:Reserva_de_hoteles/javafx-sdk/lib}" -XstartOnFirstThread
       ```
     - Haz clic en `Apply` y luego en `Run`

4. **Si el problema persiste:**
   - Cierra Eclipse completamente
   - Elimina la carpeta `.metadata` dentro de tu workspace de Eclipse (si existe)
   - Vuelve a abrir Eclipse
   - Importa el proyecto nuevamente

### El proyecto tiene errores (marcados con X rojos)

**Problema:** Faltan las librerías o no están configuradas correctamente.

**Solución:**
1. Haz clic derecho en el proyecto (en Package Explorer)
2. Selecciona `Properties`
3. Ve a `Java Build Path` → `Libraries`
4. Verifica que aparezcan las librerías de JavaFX e Ikonli
5. Si faltan, haz clic en `Add JARs...` y busca los archivos `.jar` en las carpetas `javafx-sdk/lib` y `lib`
6. Haz clic en `Apply and Close`
7. Refresca el proyecto: clic derecho → `Refresh` (o presiona F5)

## ¿Necesitas ayuda?

Si después de seguir estos pasos el proyecto no funciona:

1. Verifica que completaste todos los pasos
2. Revisa la sección "Problemas comunes" arriba
3. Asegúrate de que todas las carpetas estén en el lugar correcto (ver "Estructura de carpetas esperada")

## Recursos útiles

- [Documentación de JavaFX](https://openjfx.io/) - Para aprender más sobre JavaFX
- [Documentación de Eclipse](https://www.eclipse.org/documentation/) - Para aprender a usar Eclipse
