# Oxy-Book-Exchange

To replicate this project you need to download or install:
1. Android Studio version 2020.3.1.26 (found here: https://developer.android.com/studio/#downloads)
2. MySQL Workbench version 8.0.27 (found here: https://dev.mysql.com/downloads/workbench/)
3. Node.js version 16.13.1 (npm version 8.1.2) (found here: https://nodejs.org/en/download/)

If you are just trying to download and use the app:
- The application may not allow you past the Google Sign In page because Google doesn't want apps that are not registered on the app store to be mass downloaded onto phones

If you want to build your own server and database:
- Make sure to replace all IP Address instances with your own computer's IP Address. These instances are located in:
    - MySQL database
    - oxybookexchangeserver.js 
    - app/src/main/java/com/example/oxybookexchange/MenuActivity.java
    - app/src/main/java/com/example/oxybookexchange/CreateActivity.java
    - app/src/main/java/com/example/oxybookexchange/EditActivity.java
