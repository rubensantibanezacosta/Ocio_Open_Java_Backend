
[Go back](https://github.com/rubensantibanezacosta/Ocio_Open/blob/main/docs/English%20Readme.md)

# Usability Aspects

### Design
- Users consider that it is an elegant application in its design, and that this
helps the end user feel attracted, proof of this is:
The soft colors used in their interfaces.
The homogeneity of colors that are repeated in all the interfaces.

![Captura de pantalla de 2021-12-14 15-31-05](https://user-images.githubusercontent.com/44450566/146028571-8296ff8a-4802-4243-b7a9-b7e5fc087068.png)


- As can be seen in the previous image, it is a design in addition to
elegant, simple, in which overload is avoided. This helps your good
operation, and to focus the attention of the user who manages it on the aspects
that you will use.


### Menus

- It has menus that are visible at all times, which make it easier for the user to
find what you need without scrolling backwards or forwards through
of menus, just a mouse click away. Here is a sample of these menus.

![Captura de pantalla de 2021-12-14 15-23-39](https://user-images.githubusercontent.com/44450566/146027226-e8625b46-5289-4703-b020-6d6faff21664.png)


### Errors

- The user receives feedBack of errors that may arise in pop-up windows when they occur.

![Captura de pantalla de 2021-12-14 15-32-36](https://user-images.githubusercontent.com/44450566/146028889-a492f110-f543-46b3-8c06-92f0566f9370.png)


### Learning

The application has a very light learning curve, since it has several ways to access the information.

![Captura de pantalla de 2021-12-14 15-27-59](https://user-images.githubusercontent.com/44450566/146028027-8ae28946-7c5e-4783-8b91-79a828df5354.png)
![Captura de pantalla de 2021-12-14 15-26-02](https://user-images.githubusercontent.com/44450566/146028033-5a9e3a5c-2163-4be6-b218-c0c75db990e8.png)
![Captura de pantalla de 2021-12-14 15-25-39](https://user-images.githubusercontent.com/44450566/146028035-8ca596d7-4c2f-4579-a3a8-dd12988e378f.png)


### Icons

- The icons give you clues to the functions that can be performed by clicking on them.

![Captura de pantalla de 2021-12-14 15-28-49](https://user-images.githubusercontent.com/44450566/146028123-73302eae-e992-4820-9a0b-f33fbb7dacd7.png)


### Security

- Regarding security, the following flow is used:

    1. Authentication with Google Oauth 2.0
    2. Receipt of data from Google, and creation of its own JSON WEB TOKEN. It includes private signature, username, permissions, and token expiration.
    3. In each request the token is validated, and the permission necessary to carry out the request, both on the frontend and on the backend.

![Captura de pantalla de 2021-12-14 15-29-35](https://user-images.githubusercontent.com/44450566/146028287-b63d07cb-b750-48c2-b9ef-5a9bdfc386b5.png)
![Captura de pantalla de 2021-12-14 15-29-14](https://user-images.githubusercontent.com/44450566/146028292-8386945e-bbb3-4293-a693-ba6a1226f6aa.png)

    
 ### Multimedia elements

- Regarding multimedia elements, it has been chosen to associate an image to each event, and to use a calendar similar to Google calendars. In the calendar, the states of the event referring to the user can be differentiated with colors. It also makes it easy to search for the event by clicking on the selected day on the calendar. This is visible in previous images.

    1. I will attend (Green)
    2. I will not attend (Red)
    3. I have not answered (gray)

 
