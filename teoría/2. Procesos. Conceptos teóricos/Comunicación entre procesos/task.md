Los procesos de un sistema son elementos separados. Cada uno tiene su espacio de memoria, su tiempo de CPU asignado por el planificador y su estado de los registros.

Sin embargo, a menudo hay dependencias entre ellos (por ejemplo, para que se ejecute un proceso necesita recibir un dato que resulta de la ejecución de otro proceso), y por tanto deben poder comunicarse entre sí.

La comunicación entre procesos se denomina **IPC** (Inter-Process Communication) y se puede hacer de varias formas:

- Mediante **flujos (streams)** **de entrada y salida**: de esta forma los procesos pueden leer y escribir información unos en otros. Los procesos aquí deben estar relacionados (uno arranca al otro, obteniendo una referencia al mismo. *Ver actividad resuelta 1.1.*
- Mediante **sockets**: son mecanismos de comunicación de bajo nivel, estableciendo canales de bytes bidireccionales entre procesos alojados en distintas máquinas. Estos procesos pueden estar programados en distintos lenguajes, por lo que gracias a los sockets los procesos pueden intercambiar cualquier tipo de información.
- Mediante **RPC (Remote Process Call):** llamada a procedimiento remoto. Consiste en realizar llamadas a métodos de otros procesos que pueden estar en otras máquinas. En Java se realiza mediante la metodología llamada **RMI** (Remote Method Invocation), equivalente a RPC pero orientado a objetos.
- Mediante **persistencia**: es decir, los procesos realizan operaciones de lectura y escritura en ficheros o bases de datos.
- Mediante **servicios a través de Internet**: a través de FTP (servicio de transferencia de ficheros), servicios web, tecnología cloud, etc.Aunque los procesos son elementos independientes, deben poder comunicarse entre sí