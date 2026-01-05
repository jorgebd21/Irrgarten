# Irrgarten: Sistema de Simulación de Laberinto y Combate

Este proyecto consiste en el desarrollo de una aplicación interactiva que simula un juego de supervivencia táctica dentro de un entorno de laberinto. A través de un diseño basado estrictamente en la Programación Orientada a Objetos, la aplicación gestiona la interacción entre aventureros y criaturas en un tablero dinámico donde la estrategia y el azar determinan el resultado de cada partida.

---

## Concepto del Juego

El sistema sitúa a un jugador en una posición aleatoria de un tablero bidimensional. El objetivo principal es navegar a través de los pasillos del laberinto, evitando obstáculos y enfrentándose a monstruos, con el fin último de encontrar la salida o cumplir las condiciones de victoria establecidas por el motor de juego.

La experiencia se define por la gestión de recursos y la toma de decisiones espaciales. Cada movimiento puede derivar en un encuentro fortuito, obligando al jugador a evaluar su estado de salud y la calidad de su equipo antes de proceder.

---

## Mecánicas Principales

### Gestión del Laberinto y Movimiento

El laberinto no es solo un escenario estático, sino una entidad que controla la validez de cada acción. El sistema supervisa:

* **Navegación**: Los desplazamientos están limitados por muros, bordes del tablero y la presencia de otras entidades.
* **Casillas Dinámicas**: El tablero se actualiza en tiempo real para reflejar la posición actual de todos los elementos activos.
* **Búsqueda de Salida**: Existe una lógica interna que valida cuándo un jugador ha logrado alcanzar el punto de fuga.

### Sistema de Combate y Equipo

Los enfrentamientos se resuelven mediante un modelo matemático de ataque y defensa:

1. **Intensidad de Ataque**: Calculada en función de la fuerza base del personaje y el poder de las armas equipadas.
2. **Capacidad de Defensa**: Determinada por la resistencia propia y la eficacia de los escudos activos.
3. **Desgaste**: Las armas y escudos poseen un factor de durabilidad que disminuye con el uso, obligando a los jugadores a renovar su inventario constantemente tras ganar combates.

### El Rol del Azar (Dados)

Todas las decisiones críticas del motor de juego están mediadas por una clase especializada en la generación de eventos aleatorios. Esta gestiona:

* La potencia de los impactos en combate.
* La probabilidad de que un objeto se rompa.
* La generación de recompensas tras derrotar a un enemigo.
* El despliegue inicial de elementos en el mapa.

---

## Arquitectura del Sistema

El software ha sido diseñado bajo principios de ingeniería que garantizan la extensibilidad y la robustez del código:

| Concepto | Implementación |
| --- | --- |
| **Modularidad** | Separación clara entre la lógica de las entidades (Jugadores/Monstruos) y el motor de control. |
| **Polimorfismo** | Uso de comportamientos compartidos para diferentes tipos de combatientes. |
| **Encapsulamiento** | Protección estricta de los estados internos de las clases para evitar efectos secundarios. |
| **Gestión de Estados** | Un controlador centralizado que dicta el flujo de turnos y las transiciones del juego. |

---

## Dinámica de Personajes

### Jugadores

Los jugadores son las entidades controlables que poseen un inventario limitado. Su progresión depende de la victoria en combate, lo que les permite recolectar objetos más potentes. Si su salud llega a cero, el sistema procesa su derrota, a menos que existan mecánicas de resurrección o recuperación.

### Monstruos

Actúan como antagonistas estáticos o móviles dentro del laberinto. Poseen niveles de inteligencia y fuerza predefinidos que sirven como reto escalar. A diferencia de los jugadores, su función es puramente defensiva u obstructiva dentro del ecosistema del juego.

---

## Requisitos de Ejecución

Para iniciar la simulación, es necesario contar con un entorno de ejecución compatible con Java o Ruby. El sistema está optimizado para funcionar mediante una interfaz de consola donde se informa al usuario de cada evento ocurrido en el laberinto, permitiendo un seguimiento detallado de la evolución de las estadísticas de los personajes y el resultado de cada turno.

---

> **Nota de Diseño**: Este proyecto enfatiza la importancia de un diseño de clases coherente, donde la comunicación entre objetos sigue un protocolo estricto para mantener la integridad de la partida en todo momento.

**Desarrollado por:** Jorge Baeza Díaz

**Finalidad:** Proyecto de diseño de software orientado a objetos.
