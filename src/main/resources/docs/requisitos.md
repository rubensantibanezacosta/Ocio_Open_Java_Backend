[Volver al Readme](https://github.com/rubensantibanezacosta/Ocio_Open/)

## REQUISITOS

### UBICACIONES

- Que la gente sepa rápido si el evento es en Tenerife, Gran Canaria o virtual, además de a lo mejor sacar en algún sitio la estadística, a modo ranking, como se  ha hecho para los usuarios con más eventos puntuados. (puede servir para motivar a los de una isla u otra si ven que unos hacen más cosas que los otros) 

-  Campos en el card del evento:

	- Un campo Provincia/isla o "Zona", que sólo pueda tener 3 opciones. TFE, GC y Virtual, para 	saber si el evento se propone en Tenerife, Gran Canaria o de forma virtual. Esta info estaría    bien que apareciera en la pantalla principal del evento.

	- Un segundo campo que si sea libre e indique la ubicación (como el que está en el prototipo), y si es virtual pueda indicar el enlace a la videoconferencia creada.

### IMAGENES

- Tener una galería de imágenes precargada para ser usada en el campo Imagen, y que no carguen una imagen por evento sino que puedan usar imágenes ya cargadas por otros compañeros en eventos previos. Si no te gusta ninguna imagen de la librería o no hay una imagen adecuada, entonces podrá cargar una imagen (y esta pase a formar parte de la librería de imágenes).

### FORMULARIOS

- Se ha de tener en cuenta la opción de desapuntarse de los eventos, aportando un motivo.


### USUARIOS

- Tener una tabla de usuarios. Cada vez que un empleado acceda a la aplicación, con la integración por Google, registrarle como usuario en la tabla, si no está previamente (es la primera vez que entra en la aplicación) o bien, si ya está registrado, actualizar una campo de la tabla que sea último acceso.  En ppo me gustaría tener en esta tabla:

	- id del usuario, me vale el mail, es único.
	- fecha y hora del primer acceso.
	- fecha y hora del ultimo acceso

### NOTIFICACIONES

- Poder hacer notificaciones por mail:

	- Cada vez que se cree un evento, a todos los mails que tengamos en la tabla "usuarios"
	- Cada vez que se edite o cancele un evento en el que te has apuntado.
	- Cada vez que se apunte/desapunte un compañero a un evento que has propuesto o te has apuntado.


### ADMINISTRACIÓN

- Tener un apartado de administración (para roles administradores) donde, como mínimo se pueda:
 	- EVENTOS  Poder hacer consultas de eventos y edición de estos (aunque no seamos el organizador).
 	- USUARIOS Poder consultar la tabla de usuarios, con su fecha de registro, y su ultima hora de conexión
