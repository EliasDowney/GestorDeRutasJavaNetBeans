@echo off
cd /d "%~dp0"
if not exist build\classes mkdir build\classes
dir /s /b src\*.java > "%TEMP%\fuentes_rutas.txt"
javac -encoding UTF-8 -d build\classes -cp "lib\mysql-connector-j-8.0.33.jar" @"%TEMP%\fuentes_rutas.txt"
if errorlevel 1 (
  pause
  exit /b 1
)
java -cp "build\classes;lib\mysql-connector-j-8.0.33.jar" Principal.Main
