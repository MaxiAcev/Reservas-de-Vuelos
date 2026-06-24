Sistema de Reservas de Vuelos
Proyecto desarrollado en Java utilizando JPA/Hibernate y MySQL para gestionar pasajeros, vuelos
y reservas.
El objetivo fue practicar el desarrollo de aplicaciones backend aplicando conceptos de
programación orientada a objetos, persistencia de datos, arquitectura en capas y reglas de
negocio.

Funcionalidades:
• Registrar pasajeros.
• Registrar vuelos.
• Crear reservas asociando pasajeros y vuelos.
• Consultar información almacenada en la base de datos.
• Validar reglas de negocio antes de guardar información.

Arquitectura:

Entidades: Pasajero, Vuelo y Reserva.

Repositorios: Operaciones CRUD y acceso a datos mediante JPA/Hibernate.

Servicios: Lógica de negocio y validaciones.

Presentación: Aplicación de consola con menú interactivo.

Reglas de negocio implementadas:
• El monto debe ser mayor a 0.
• El estado de la reserva debe ser CONFIRMADA o PENDIENTE.
• No se pueden realizar reservas para vuelos cuya fecha ya haya pasado.
• El pasajero debe existir en la base de datos.
• El vuelo debe existir en la base de datos.

Tecnologías utilizadas:
• Java 17+
• Maven
• JPA
• Hibernate
• MySQL

Conceptos aplicados:
• Programación Orientada a Objetos (POO)
• Arquitectura en capas
• Patrón Repository
• Relaciones entre entidades con JPA
• Herencia y clases genéricas
• Manejo de excepciones
• Lazy Loading y Eager Loading
• Variables de entorno para credenciales

Configuración:
1. Clonar el repositorio.
2. Crear una base de datos MySQL llamada reserva_de_vuelo.
3. Configurar las variables de entorno DB_USER y DB_PASSWORD.
4. Ejecutar la clase Main.
Autor
Maximiliano Acevedo. Proyecto realizado con fines educativos para reforzar conocimientos de Java
Backend, persistencia de datos y buenas prácticas de desarrollo.