# 📦 Kaddem DevOps Project

Ce projet est une application Spring Boot intégrée avec une stack DevOps complète : CI/CD via Jenkins, gestion de dépendances avec Nexus, analyse de code avec SonarQube, conteneurisation avec Docker et monitoring via Prometheus + Grafana.

---

## 🚀 Fonctionnalités Principales

- 🔁 **CI/CD automatisé** via Jenkins Pipeline
- 🧪 Tests unitaires avec rapports `JUnit`
- ✅ Analyse de qualité avec **SonarQube**
- 📦 Déploiement des artefacts sur **Nexus**
- 🐳 Dockerisation et déploiement via `docker-compose`
- 📊 Monitoring de l'application avec **Prometheus** et **Grafana**
- 🕐 Cron Job pour l'évolution automatique des équipes (`@Scheduled`)
- 🔐 Accès sécurisé avec credentials Jenkins + Docker Hub

---

## 🛠️ Technologies Utilisées

| Outil         | Usage                                  |
|---------------|-----------------------------------------|
| Spring Boot   | Backend de l'application                |
| Jenkins       | CI/CD Pipeline                          |
| SonarQube     | Analyse de qualité de code              |
| Nexus         | Dépôt d’artefacts Maven                 |
| Docker        | Conteneurisation de l’application       |
| Prometheus    | Collecte des métriques système/app      |
| Grafana       | Visualisation des métriques             |
| H2            | Base de données embarquée               |

---

## 🔧 Lancement du Projet (Local / CI)

### ✅ Prérequis

- Java 17
- Maven
- Docker & Docker Compose
- Jenkins avec les plugins :
  - Pipeline
  - Docker Pipeline
  - Git
  - SonarQube Scanner
 
- 
### 🏅 **Get all teams**
**Endpoint**: `GET /etudiant/retrieve-all-equipes`

| Paramètre  | Type   | Description                                      |
|------------|--------|--------------------------------------------------|
| `none`     | N/A    | Cette méthode ne nécessite aucun paramètre. Elle retourne toutes les équipes. |

---

### 🏅 **Get student by id**
**Endpoint**: `GET /etudiant/retrieve-etudiant/{etudiant-id}`

| Paramètre    | Type    | Description                                   |
|--------------|---------|-----------------------------------------------|
| `etudiant-id`  | integer | Requis. L'ID de l'équipe à récupérer.         |

---

### ➕ **Add a new student**
**Endpoint**: `POST /etudiant/add-etudiant`

| Paramètre  | Type    | Description                                       |
|------------|---------|---------------------------------------------------|
| `etudiant`   | object  | Requis. Un objet `etudiant` contenant les informations de l'équipe à ajouter. |

---

### ❌ **Delete student by id**
**Endpoint**: `DELETE /etudiant/remove-etudiant/{etudiant-id}`

| Paramètre    | Type    | Description                                    |
|--------------|---------|-----------------------------------------------|
| `etudiant-id`  | integer | Requis. L'ID de l'équipe à supprimer.          |

---

### ✏️ **Update student**
**Endpoint**: `PUT /etudiant/update-etudiant`

| Paramètre  | Type    | Description                                     |
|------------|---------|-------------------------------------------------|
| `etudiant`   | object  | Requis. Un objet `etudiant` contenant les informations à mettre à jour. |

---

### 🔄 **Evolve teams based on contracts**
**Endpoint**: `PUT /etudiant/faireEvoluerEquipes`

| Paramètre  | Type   | Description                                           |
|------------|--------|-------------------------------------------------------|
| `none`     | N/A    | Cette méthode fait évoluer les équipes en fonction des contrats actifs des étudiants. Elle est exécutée selon un cron configuré à 13h chaque jour. |
#ProjetDevopsPipeline
[![Build Status](http://192.168.65.129:8080/buildStatus/icon?job=projetDevops)](http://192.168.65.129:8080/job/projetDevops/)

