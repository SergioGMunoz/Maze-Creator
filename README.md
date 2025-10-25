# 🧩 Laberinto

**Laberinto** es un juego desarrollado en Java utilizando Swing para la interfaz gráfica.  
El jugador se enfrenta a un laberinto con obstáculos (cocodrilos 🐊 y botiquines 🩹), y solo puede avanzar si responde correctamente a preguntas.  
El administrador puede crear laberintos, colocar bloques y añadir diferentes preguntas.

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.java.com/)  
[![Swing](https://img.shields.io/badge/Swing-GUI-blue?style=for-the-badge)]()  
[![JDBC](https://img.shields.io/badge/JDBC-Database-lightgrey?style=for-the-badge)]()  
[![Build](https://img.shields.io/badge/Status-En%20proceso-yellow?style=for-the-badge)]()

---

## 🎮 Jugador

- El jugador inicia en la esquina superior izquierda y debe llegar a la esquina inferior derecha **sin ver el diseño del laberinto**.
- Cada movimiento requiere **responder correctamente una pregunta tipo test**.
- Si responde mal o se acaba el tiempo, pierde un porcentaje de vida.
- Obstáculos en el laberinto:
  - 🐊 Cocodrilos (quitan vida)
  - 🩹 Botiquines (recuperan vida)
- El jugador pierde si su vida llega a 0 o si se queda sin preguntas disponibles.
- Al finalizar la partida, se registra el resultado en un ranking.

---

## 🧠 Administrador

- Crear nuevos laberintos con distintos tamaños y configuraciones.
- Generar disposiciones aleatorias con botiquines y cocodrilos.
- Añadir preguntas para el quiz.
- Controlar parámetros como:
  - 🕐 Tiempo máximo por pregunta
  - ❓ Daño por respuesta incorrecta o fuera de tiempo
  - 🩹 Porcentaje de recuperación/daño por objetos

---
## 🕹️ Cómo jugar

### ✅ Requisitos

- [MySQL](https://www.mysql.com/) (servidor en local)
- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

### 🧩 Pasos para ejecutar el juego
1. Clona el programa en tu equipo: `git clone https://github.com/SergioGMunoz/Laberinto`
2. Ejecuta el script de la Base de Datos **Laberinto.sql** en MySQL
3. Ejecuta el programa usando el script: `.\ejecutar_app.bat` (Windows)
   - O manualmente: `java -cp "lib\mysql-connector-j-8.2.0.jar;." controller.Launcher`

### 🔑 Credenciales de prueba
- **Administrador**: Contraseña `1234`
  - Permite crear laberintos, añadir preguntas y configurar el juego
- **Jugador**: Introduce cualquier alias para jugar

---
