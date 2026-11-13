README - Domi

Thomas Espinosa / Miguel Quintero / Manuel Ramirez / Cristian Bernal


Enlace del video https://youtu.be/eQ7WBUteC3w 

Cómo ejecutar el proyecto en IntelliJ 
clonar el repositorio del proyecto en el equipo.   
Abrir la carpeta del proyecto en IntelliJ IDEA. 
El sistema utiliza Java puro sin frameworks ni Maven.   
Verificar que el entorno de ejecución tenga configurado el JDK correcto.
Localizar el archivo Main.java dentro del paquete app (src/app/Main.java).   
Ejecutar la clase Main. 
Al iniciar, el programa cargará de forma automática los datos iniciales correspondientes a los barrios con cobertura, clientes de prueba y repartidores base.
Respuestas a las preguntas de arquitectura y colecciones, Clientes mediante HashMap cedula.
cliente Se utiliza un mapa en lugar de una lista porque permite buscar, editar o eliminar a un cliente de forma directa utilizando su número de cédula, logrando un acceso inmediato sin recorrer toda la colección. 
Barrios con cobertura mediante HashSet: Permite almacenar los nombres de los barrios sin duplicados y verificar de manera instantánea si un barrio se encuentra dentro de la zona de cobertura usando el método contains.   
Distancias de los barrios mediante HashMap barrio - km.
Se relacionan las distancias por barrio para evitar que el operador tenga que digitar los kilómetros manualmente.
Repartidores mediante ArrayList: Se emplea una lista porque el flujo principal requiere recorrer de manera secuencial a todos los repartidores disponibles para evaluar sus condiciones y comparar tarifas al momento de cotizar un pedido.   
Pedidos mediante ArrayList: Se usa una lista porque los pedidos se registran de forma consecutiva y cronológica, permitiendo listarlos y recorrerlos fácilmente para consultar su estado.   
Regla del modo lluvia el estado del modo lluvia se administra en el servicio. 
La regla se resolvió mediante polimorfismo: el servicio le indica el estado del clima a los repartidores, y cada subclase de Repartidor es la encargada de determinar si puede operar bajo la lluvia, las bicicletas y los drones retornan falso, y de aplicar de forma independiente el recargo porcentual sobre su propia tarifa. 
De esta manera se evita incluir condicionales específicos de tipos de vehículos dentro de la lógica del servicio.



