[Ver en castellano](https://github.com/rubensantibanezacosta/Ocio_Open)

# Ocio Open  *(Training project)*

Repository of the App and the Ocio Open server.

The project is a basic social network that allows, empowers and rewards the organization of social events for the employees of a company.


## Client
![image](https://user-images.githubusercontent.com/44450566/142890754-a330b388-f293-4a12-a665-47dc6995a3ca.png)

[Open Canarias SL](https://www.opencanarias.com/)

## Project Documentation

- [Application requirements report](https://github.com/rubensantibanezacosta/Ocio_Open/blob/main/docs/requisites.md)
- [Use case diagram](https://github.com/rubensantibanezacosta/Ocio_Open/blob/main/docs/Casos%20de%20Uso.png)
- [Data model](https://github.com/rubensantibanezacosta/Ocio_Open/blob/main/docs/Captura%20de%20pantalla%20de%202021-12-03%2011-33-16.png)
- [API Rest Calls Report in Postman](https://documenter.getpostman.com/view/17032586/UVC8E77j)
- [FIGMA designed prototype](https://www.figma.com/proto/avUqIHB3yfnUUCIBHcHBDu/Open-Ocio?node-id=182%3A98&starting-point-node-id=182%3A98)
- [Usability Ascpects](https://github.com/rubensantibanezacosta/Ocio_Open/blob/main/docs/Usability.md)

## Starting

Download Links:

From Github: https://github.com/rubensantibanezacosta/Ocio_Open

## Prerequisites

You need a development environment with:
* [Git](https://git-scm.com) -  https://git-scm.com/downloads.
* [MySQL](https://www.mysql.com) -  https://www.mysql.com/downloads/.
* [Node.js](https://nodejs.org) -  https://nodejs.org/es/download/. 

## Installation instructions

Clone the repository:

```
git clone https://github.com/rubensantibanezacosta/Ocio_Open
```

The project consists of 3 different parts:
* Frontend
* Backend
* Database

You need to have node.js installed in your development environment. LTS version recommended : https://nodejs.org/es/

Once cloned, you must update the dependencies.

```
cd frontend/
npm install
```
```
cd backend/
npm install
```


* For the frontend, the Google login function is used, you need to create a client ID by creating a new project on the Google developers website: https://console.cloud.google.com/apis

On this page you must configure a new project by clicking on create crendential and Obtain your Google ClientId.

![Captura de pantalla de 2021-11-22 15-04-08](https://user-images.githubusercontent.com/44450566/142885020-f59c7e6d-2fb1-467b-9fa4-b66f516ff12d.png)

In the Credentials section you must fill in the "URI*" field with your frontend host address. In our case:
 
![Captura de pantalla de 2021-12-06 11-54-29](https://user-images.githubusercontent.com/44450566/144841712-ed985048-a395-4059-b449-84026d664d14.png)



You will need to create the frontend/src/app/config/config.ts file and fill it with your credentials using the schema from the frontend/src/app/config.ts.example file

```
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class VariablesService {

  variables = {
    googleClientId: google Client Id,
    googleClientSecret: Google Client Secret,
    host: Backend host (En nuestro caso 'http://localhost:4000')

  }


  getVariables() {
    return this.variables;
  }
}


```




* For your backend:


1. You need a MySQL server running.

2. Create the database, in your case it should be called ``` ocio_open ```

3. Import the file to your database:  [SQL file](https://github.com/rubensantibanezacosta/Ocio_Open/blob/main/docs/ocioopenBBDD.sql)

4. You should create the backend/.env file and fill it with your credentials using the backend/.env.example file schema

MySQL
```
MYSQL_DATABASE=ocio_open
MYSQL_USER=username
MYSQL_PASSWORD="MySQL access key
MYSQL_ROOT_PASSWORD=MySQL access key
DB_HOST=MySQL host
``` 
Mode
```
NODE_ENV=development
```
 JWT
```
JWT_SECRET=The secret used for the Jwt protocol
TOKEN_EXPIRE_TIME=Token expiration time in minutes
TOKEN_EXPIRE_TIME_REMEMBER_ME=Token expiration time in minutes with the remember me box checked
```
Api key tokens

These keys are automatically generated in the database by executing the script:
```
npm run roleKeysCreate 
``` 
to create them, or 
```
npm run roleKeysUpdate 
```
to update them.

Once created, copy and paste them here:

```
PUBLIC_API_KEY_TOKEN=
ADMIN_API_KEY_TOKEN=
```
Domains

```
ACCEPTED_DOMAINS=domains accepted for registration separated by commas. Example: gmail.com,hotmail.com
```
Mail credentials
```
EMAIL_API_NAME=email address to send notifications
EMAIL_PASSWORD=Api password for email
```
Frontend End point
```
FRONTEND_ENDPOINT=http://{address where the frontend is hosted}/eventsbydate/
```
### Mailing

For the mailing, a private Google account has been used. In your configuration panel of your google account, access the *SECURITY* section and enable two-step verification.
![Captura de pantalla de 2021-12-09 18-20-44](https://user-images.githubusercontent.com/44450566/145453642-36cc4206-d6ae-4d00-a8af-144b5f55227b.png)

The next step is to access the *APPLICATION PASSWORDS* section, and generate a new password.

![Captura de pantalla de 2021-12-09 18-22-45](https://user-images.githubusercontent.com/44450566/145453889-9eb9953b-cd20-4aaa-8683-3c4ffea87214.png)


Copy and paste your email address and your generated key into the .env file



Finally enjoy the project:

Developer mode:
```
backend/
npm run dev
```
```
frontend/
ng serve -o
```

Production Mode:
```
backend/
npm run start
```
```
frontend/
ng serve -o
```

If you follow the instructions, you should have three servers started:
* Frontend (http://localhost:4200)
* Backend (http://localhost:4000)
* Mysql (Address selected by yourself in `DB_HOST`)

Enjoy!!!


