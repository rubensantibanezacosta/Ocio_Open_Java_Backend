
[Volver al Inicio](https://github.com/rubensantibanezacosta/Ocio_Open)

# Aspectos de Usabilidad

### Diseño
- Los usuarios consideran que es una aplicación elegante en su diseño, y que esto
ayuda a que el usuario final se sienta atraido, prueba de ello es:
Los colores suaves que se utilizan en sus interfaces.
La homogeneidad de colores que se repiten en todas las intefaces.

![Captura de pantalla de 2021-12-14 15-31-05](https://user-images.githubusercontent.com/44450566/146028571-8296ff8a-4802-4243-b7a9-b7e5fc087068.png)


- Como se puede observar en la imagen anterior, se trata de un diseño además de
elgante, simple, en la que se evita la sobrecarga. Esto ayuda a su buen
funcionamiento, y a centrar la atención del usuario que la maneja en los aspectos
necesarios que usará.


### Menús

- Dispone de menús visibles en todo momento, que facilitan que el usuario
encuentre lo que necesita sin desplazamientos hacia atrás o hacia delante através
de menús, a solo un clic de ratón. A continuación una muestra de estos menús.

![Captura de pantalla de 2021-12-14 15-23-39](https://user-images.githubusercontent.com/44450566/146027226-e8625b46-5289-4703-b020-6d6faff21664.png)


### Errores

- El usuario recibe feedBack de los errores que puedan surgir en ventanas emergentes cuando se producen
- 
![Captura de pantalla de 2021-12-14 15-32-36](https://user-images.githubusercontent.com/44450566/146028889-a492f110-f543-46b3-8c06-92f0566f9370.png)


### Aprendizaje

La aplicación tiene una curva de aprendizaje muy liviana, ya que dispone de varios caminos para acceder a la información.

![Captura de pantalla de 2021-12-14 15-27-59](https://user-images.githubusercontent.com/44450566/146028027-8ae28946-7c5e-4783-8b91-79a828df5354.png)
![Captura de pantalla de 2021-12-14 15-26-02](https://user-images.githubusercontent.com/44450566/146028033-5a9e3a5c-2163-4be6-b218-c0c75db990e8.png)
![Captura de pantalla de 2021-12-14 15-25-39](https://user-images.githubusercontent.com/44450566/146028035-8ca596d7-4c2f-4579-a3a8-dd12988e378f.png)


### Iconos

- Los iconos te dan pistas te las funciones que se pueden realizar haciendo click en ellos.

![Captura de pantalla de 2021-12-14 15-28-49](https://user-images.githubusercontent.com/44450566/146028123-73302eae-e992-4820-9a0b-f33fbb7dacd7.png)


### Seguridad

- En cuanto a la seguridad, se utiliza el siguiente flujo:

    1. Autencicación con Google Oauth 2.0
    2. Recepción de datos de Google, y creación de JSON WEB TOKEN propio. En el se incluyen firma privada, username, permisos, y caducidad del token.
    3. En cada petición se valida el token, y el permiso necesario para realizar la petición, tanto en el frontend como en el backend.

![Captura de pantalla de 2021-12-14 15-29-35](https://user-images.githubusercontent.com/44450566/146028287-b63d07cb-b750-48c2-b9ef-5a9bdfc386b5.png)
![Captura de pantalla de 2021-12-14 15-29-14](https://user-images.githubusercontent.com/44450566/146028292-8386945e-bbb3-4293-a693-ba6a1226f6aa.png)

    
 ### Elementos multimedia

- En cuanto a elementos multimedia, se ha optado por asociar una imagen a cada evento, y utilizar un calendario similar a los calendarios de Google. En el calendario, se pueden diferenciar con colores los estados del evento referentes al usuario. También facilita la busqueda del evento haciendo click en el día seleccionado del calendario. Este es visible en imagenes anteriores.

    1. Asistiré (Verde)
    2. No asistiré (Rojo)
    3. No he contestado (gris)

 

