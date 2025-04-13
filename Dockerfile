# Utilisez une image de base Java
FROM openjdk:17-jdk-alpine

# Copiez le fichier JAR de l'application dans le conteneur
COPY target/kaddem-0.0.1-SNAPSHOT.jar app.jar

# Exposez le port sur lequel l'application Spring Boot écoute
EXPOSE 8089

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]