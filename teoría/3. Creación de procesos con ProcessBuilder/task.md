Se lanzan procesos llamando a su constructor. Hay varias posibilidades:
- Pasando únicamente como parámetro el programa a ejecutar.
```java
Process proceso = new Processbuilder("notepad.exe").start();
````
- Añadiendo además parámetros que serán entregados al proceso que se crea:
````java
Process proceso = new Processbuilder("notepad.exe", "datos.txt").start();
````