# 🏛️ Kaddem - Gestion des Départements

Ce module fait partie du projet **Kaddem** et gère l'entité `Departement` ainsi que ses relations avec les étudiants. Il expose une API REST complète pour créer, lire, mettre à jour et supprimer les départements universitaires.


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
| Spring Boot   | Framework principal                     |
| Spring MVC    | Gestion des endpoints REST              |
| JPA / Hibernate | Mapping objet-relationnel (ORM)       |
| Jackson       | Sérialisation JSON                      |
| Log4j         | Gestion des logs                        |
| Maven         | Gestionnaire de dépendances             |

---

## 🧬 Entité Principale : `Departement`

```java
public class Departement {
    private String nomDepart;

    @OneToMany(mappedBy="departement")
    @JsonIgnore
    private Set<Etudiant> etudiants;
}


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
### 🏅 **Get all departement**
**Endpoint**: `GET /departement/retrieve-all-departements`

| Paramètre  | Type   | Description                                      |
|------------|--------|--------------------------------------------------|
| `none`     | N/A    | Cette méthode ne nécessite aucun paramètre. Elle retourne toutes les departements. |

---

### 🏅 **Get departement by id**
**Endpoint**: `GET /equipe/retrieve-departement/{departement-id}`

| Paramètre    | Type    | Description                                   |
|--------------|---------|-----------------------------------------------|
| `equipe-id`  | integer | Requis. L'ID du departement à récupérer.         |

---

### ➕ **Add a new departement**
**Endpoint**: `POST /departement/add-departement`

| Paramètre  | Type    | Description                                       |
|------------|---------|---------------------------------------------------|
| `departement`   | object  | Requis. Un objet `Equipe` contenant les informations du departement à ajouter. |

---

### ❌ **Delete departement by id**
**Endpoint**: `DELETE /departement/remove-departement/{departement-id}`

| Paramètre    | Type    | Description                                    |
|--------------|---------|-----------------------------------------------|
| `equipe-id`  | integer | Requis. L'ID du departement à supprimer.          |

---

### ✏️ **Update departement**
**Endpoint**: `PUT /departement/update-departement`

| Paramètre  | Type    | Description                                     |
|------------|---------|-------------------------------------------------|
| `departement`   | object  | Requis. Un objet `Departement` contenant les informations à mettre à jour. |

---
