@echo off
chcp 65001 >nul
cls
echo.
echo ==========================================
echo    🎮 EJECUTOR DE APLICACIÓN LABERINTO 🎮
echo ==========================================
echo.

REM Verificar si existe el archivo SQL
if not exist "Laberinto.sql" (
    echo ❌ Error: No se encuentra el archivo Laberinto.sql
    echo.
    pause
    exit /b 1
)

REM Verificar si existe la librería MySQL
if not exist "lib\mysql-connector-j-8.2.0.jar" (
    echo ❌ Error: No se encuentra la librería MySQL en lib\mysql-connector-j-8.2.0.jar
    echo.
    pause
    exit /b 1
)

echo 📊 Verificación de Base de Datos
echo.
:inicio
echo ¿Has ejecutado el script de la base de datos Laberinto.sql?
echo.
echo 1. Sí - Continuar con la aplicación
echo 2. No - Mostrar instrucciones y salir
echo.
set /p opcion="Selecciona una opción (1 o 2): "

if "%opcion%"=="1" goto ejecutar_app
if "%opcion%"=="2" goto mostrar_instrucciones

echo ❌ Opción no válida. Inténtalo de nuevo.
echo.
pause
goto inicio

:mostrar_instrucciones
cls
echo.
echo ==========================================
echo    📋 INSTRUCCIONES DE BASE DE DATOS
echo ==========================================
echo.
echo Para ejecutar el script de la base de datos necesitas:
echo.
echo 1. 🔧 Tener MySQL instalado y ejecutándose
echo.
echo 2. 📱 Abrir MySQL Workbench o cliente MySQL de línea de comandos
echo.
echo 3. 💾 Ejecutar el archivo Laberinto.sql con el siguiente comando:
echo    mysql -u root -p ^< Laberinto.sql
echo.
echo    O desde MySQL Workbench:
echo    - Abrir el archivo Laberinto.sql
echo    - Ejecutar todo el script (Ctrl+Shift+Enter)
echo.
echo 4. ✅ Verificar que la base de datos 'Laberinto' se creó correctamente
echo.
echo 5. 👤 Confirmar que el usuario 'JavaLaberinto' fue creado
echo.
echo ==========================================
echo El script de la base de datos incluye:
echo ==========================================
echo • Creación de la base de datos 'Laberinto'
echo • Creación de todas las tablas necesarias
echo • Usuario 'JavaLaberinto' con permisos
echo • Datos de prueba (preguntas y laberinto mini)
echo.
echo 📁 Archivo a ejecutar: Laberinto.sql
echo 📍 Ubicación: %CD%\Laberinto.sql
echo.
echo Después de ejecutar el script, vuelve a ejecutar este programa.
echo.
pause
exit /b 0

:ejecutar_app
cls
echo.
echo ==========================================
echo      🚀 INICIANDO APLICACIÓN LABERINTO
echo ==========================================
echo.

REM Verificar si las clases están compiladas
if not exist "controller\Launcher.class" (
    echo 📝 Compilando código Java...
    javac -cp "lib\mysql-connector-j-8.2.0.jar" -d . src\controller\*.java src\model\*.java src\view\*.java
    
    if errorlevel 1 (
        echo ❌ Error al compilar el código Java
        echo.
        pause
        exit /b 1
    )
    
    echo ✅ Compilación exitosa
    echo.
)

echo 🔗 Conectando a la base de datos...
echo.

REM Ejecutar la aplicación con el classpath correcto
java -cp "lib\mysql-connector-j-8.2.0.jar;." controller.Launcher

if errorlevel 1 (
    echo.
    echo ❌ Error al ejecutar la aplicación
    echo.
    echo Posibles problemas:
    echo • MySQL no está ejecutándose
    echo • La base de datos 'Laberinto' no existe
    echo • El usuario 'JavaLaberinto' no tiene permisos
    echo.
    echo 💡 Sugerencia: Ejecuta el script Laberinto.sql
    echo.
    pause
    exit /b 1
)

echo.
echo ✅ Aplicación finalizada correctamente
pause
exit /b 0