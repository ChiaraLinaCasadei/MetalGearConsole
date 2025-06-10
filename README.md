# MetalGearConsole

## Aclaraci�n
Este proyecto lo hice sola.
Este es el repo donde lo fui subiendo:
https://github.com/ChiaraLinaCasadei/MetalGearConsole

## Decisiones de l�gica

- Las misiones 1 y 2 comparten l�gica, var�a la cantidad de guardias y la ubicacion inicial de Snake y la puerta, los nombres de los items y la dimesion del mapa.
- Para los guardias estos son obst�culos y si los encuentran cambiar�n de direcci�n:
	* items
	* limites del mapa
	* otros guardias
	* puerta
- Los guardias tienen por iteraci�n 4 intentos de movimiento aleatorio.
- Si un guardia se encuentra con un obst�culo en todas las "futuras posiciones" que puede tomar aleatoriamente, se queda quieto ese turno.
- La mision 1 tiene 3 guardias, la mision 2 tiene 4 guardias.
- La posicion inicial de la puerta y snake siempre son fijas en las misiones 1 y 2. Lo aleatorio son los guardias y el item de cada mision.

## Decisiones de dise�o
- Se agreg� una opcion de salir al men�.
- El c�digo se lee desde archivo y se escribe en un archivo que si no existe se crea (est� bueno para bypassear misiones de forma r�pida y poder probarlas)
- La opcion de Iniciar Mision siempre te deja en la primer mision. La �nica forma de acceder a las otras es guardando y cargando el c�digo.
