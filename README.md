[![Build and deploy JAR app to Azure Web App - kinobackend](https://github.com/TheDanishMexican/kino_app_backend/actions/workflows/main_kinobackend.yml/badge.svg)](https://github.com/TheDanishMexican/kino_app_backend/actions/workflows/main_kinobackend.yml)

Hvis du ønsker at køre programmet skal du blot åbne linket "https://lemon-tree-0aebf7903.5.azurestaticapps.net" 

# Kino

This repository hosts the backend for our cinema platform. You can also explore the live version [here](https://kinobackend.azurewebsites.net).

## Running Locally

1. **Clone this repository:**

    - Clone this repository to your local machine and open the folder.

2. **Add your envoirement variables for the application properties:**

JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/INSERTDATABASENAMEHERE;JDBC_PASSWORD=INSERTDBPASSWORDHERE;JDBC_USERNAME=INSERTDBUSERNAMEHERE;SECRET_TOKEN=INSERTSECRETTOKENHERE

4. **Setup the Database:**

    - Open your database IDE, set up the connection, and create the database by building the program.


6. **Frontend Setup:**
    - Go to the frontend repository at: [https://github.com/TheDanishMexican/kino_app_frontend](https://github.com/TheDanishMexican/kino_app_frontend) and follow the steps in the README.md file to complete the setup.

Congratulations! Your backend is now up and running at [http://localhost:8080](http://localhost:8080). Feel free to explore as needed!

link to [swagger UI](http://localhost:8080/swagger-ui/index.html#/)

