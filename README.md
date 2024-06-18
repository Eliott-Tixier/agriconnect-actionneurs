# agriconnect-actionneurs
Projet AGRICONNECT, micro-service Actionneurs

Script de création de la base de données :
docker run --name ac_actionneurs -p 3307:3306  -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=agriconnect-actionneurs -d mysql:latest
