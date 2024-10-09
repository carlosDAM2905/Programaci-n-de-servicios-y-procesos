
# Descripción del programa `EjemploPing`

Este programa tiene como objetivo ejecutar el comando `ping` para comprobar la conectividad de red hacia `google.com`, ajustándose al sistema operativo en el que se está ejecutando (Windows o Linux), y mostrar tanto la salida estándar como los errores en la consola.

## Funcionamiento:

### Detección del sistema operativo:
El programa verifica el sistema operativo mediante `System.getProperty("os.name")` y ajusta el comando de `ping` en consecuencia:

- **Windows**: Ejecuta `ping google.com`.
- **Linux**: Ejecuta `ping -c 4 google.com`, donde `-c 4` indica que solo se enviarán 4 paquetes de ping.

### Creación del proceso:
Utiliza `ProcessBuilder` para crear el proceso que ejecuta el comando correspondiente.

### Captura de la salida y errores:

- **Salida estándar**: El programa captura la salida del comando utilizando un `BufferedReader` que lee el `InputStream` del proceso.
- **Errores**: También captura los errores potenciales de ejecución a través del `ErrorStream`.

### Manejo de errores y salida:

- Primero, el programa comprueba si hay errores leyendo del flujo de error (`ErrorStream`). Si los hay, los imprime en la consola.
- Si no hay errores, procede a imprimir la salida estándar del comando `ping` línea por línea.

### Manejo de excepciones:
Si algo sale mal (como problemas al ejecutar el comando), el bloque `catch` imprimirá "Problemita".

## Resumen del flujo:
- Si estás en **Windows**, hará un ping a `google.com`.
- Si estás en **Linux**, hará un ping de 4 paquetes a `google.com`.
- Mostrará tanto los resultados del ping como los errores, si los hubiera.

Este tipo de programas es útil para hacer comprobaciones de red desde un entorno Java, ejecutando comandos nativos del sistema operativo.
