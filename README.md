

````markdown
# Patient Management Platform

A secure, scalable, and resilient microservices-based platform for managing patient records, built with Spring Boot and deployed on AWS.

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)
![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![AWS](https://img.shields.io/badge/AWS-ECS%20%26%20CDK-orange)

This project demonstrates a production-ready application architecture, emphasizing best practices in microservices design, event-driven communication, cloud deployment, and observability.

---

## Table of Contents

- [About The Project](#about-the-project)
- [Architecture Diagram](#architecture-diagram)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Running the Application](#running-the-application)
- [Testing](#testing)
- [API Documentation](#api-documentation)
- [Deployment](#deployment)
- [Observability](#observability)

---

## About The Project

The Patient Management Platform is a backend system designed to handle sensitive patient data with a focus on security, high availability, and performance. It breaks down functionalities into independent microservices that communicate asynchronously through a message broker, ensuring loose coupling and fault tolerance.

This project was built to apply and demonstrate skills in:
- Domain-Driven Design for service separation.
- Event-driven architecture for scalable and resilient systems.
- Cloud-native deployment and infrastructure as code.
- Production-grade monitoring and testing.

---

## Architecture Diagram

The system is composed of several microservices behind an API Gateway. Services communicate via REST and gRPC, and use Kafka for asynchronous, event-driven communication.

```mermaid
graph TD
    subgraph "Clients"
        User[External User/Client App]
    end

    subgraph "Observability Stack"
        Grafana(Grafana)
        Prometheus(Prometheus)
        Grafana --> Prometheus
    end

    subgraph "Cloud Infrastructure (AWS)"
        Gateway[Spring Cloud Gateway]
        PatientSvc[Patient Service - REST]
        ApptSvc[Appointment Service - REST]
        VitalsSvc[Vitals Service - gRPC]
        Kafka[Apache Kafka]
        Redis[Redis Cache]

        User -- HTTPS --> Gateway
        Gateway -- REST --> PatientSvc
        Gateway -- REST --> ApptSvc

        PatientSvc -- gRPC --> VitalsSvc
        PatientSvc -- Publishes/Subscribes --> Kafka
        ApptSvc -- Publishes/Subscribes --> Kafka

        PatientSvc <--> Redis
        ApptSvc <--> Redis

        Prometheus -- Scrapes Metrics --> Gateway
        Prometheus -- Scrapes Metrics --> PatientSvc
        Prometheus -- Scrapes Metrics --> ApptSvc
        Prometheus -- Scrapes Metrics --> VitalsSvc
    end
````

-----

## Key Features

  - **Secure API Gateway**: Single entry point with rate limiting and centralized authentication using Spring Cloud Gateway.
  - **Hybrid Communication**: Utilizes both REST for external APIs and high-performance gRPC for internal service-to-service communication.
  - **Event-Driven**: Services are decoupled using Apache Kafka, enabling asynchronous operations and greater resilience.
  - **Fault Tolerance**: Implemented circuit breakers with Resilience4j to prevent cascading failures.
  - **High Performance**: Integrated Redis for caching frequently accessed data, reducing database load and latency.
  - **Containerized**: All services are containerized with Docker for consistent environments and simplified deployment.
  - **Infrastructure as Code (IaC)**: The entire AWS infrastructure is defined and version-controlled using the AWS CDK.
  - **Comprehensive Monitoring**: Production-grade observability stack with Prometheus for metrics collection and Grafana for visualization.

-----

## Tech Stack

| Category              | Technology                                                     |
| --------------------- | -------------------------------------------------------------- |
| **Backend** | Java 17, Spring Boot 3                                         |
| **Communication** | REST, gRPC, Apache Kafka                                       |
| **Gateway & Resilience**| Spring Cloud Gateway, Resilience4j                             |
| **Database / Cache** | PostgreSQL (Primary), Redis (Caching)                          |
| **Containerization** | Docker, Docker Compose                                         |
| **Deployment** | AWS ECS Fargate, AWS CDK                                       |
| **Testing** | JUnit 5, Mockito, Rest Assured                                 |
| **Observability** | Prometheus, Grafana                                            |
| **API Docs** | Swagger / OpenAPI 3.0                                          |

-----

## Getting Started

Follow these instructions to get a local copy up and running for development and testing.

### Prerequisites

  - Java JDK 17 or later
  - Apache Maven or Gradle
  - Docker and Docker Compose
  - An IDE (e.g., IntelliJ IDEA, VSCode)
  - AWS CLI (configured, for deployment)

### Installation

1.  **Clone the repository:**
    ```sh
    git clone [https://github.com/your-username/patient-management-platform.git](https://github.com/your-username/patient-management-platform.git)
    ```
2.  **Navigate to the project directory:**
    ```sh
    cd patient-management-platform
    ```
3.  **Build the projects using Maven:**
    ```sh
    mvn clean install
    ```

-----

## Running the Application

The entire application stack, including services and infrastructure like Kafka and Redis, can be started locally using Docker Compose.

```sh
docker-compose up -d
```

This command will build the Docker images for each microservice and start them in detached mode.

### Key Endpoints (Local)

  - **API Gateway**: `http://localhost:8080`
  - **Kafka UI**: `http://localhost:9000`
  - **Prometheus**: `http://localhost:9090`
  - **Grafana**: `http://localhost:3000` (Default login: `admin`/`admin`)

-----

## Testing

The project includes a suite of unit, integration, and API tests. To run all tests:

```sh
mvn test
```

This will execute the Rest Assured API tests against the running services defined in the test configuration.

-----

## API Documentation

Each service exposes its API documentation via Swagger UI. Once the services are running, you can access them at their respective endpoints:

  - **Patient Service**: `http://localhost:8081/swagger-ui.html`
  - **Appointment Service**: `http://localhost:8082/swagger-ui.html`

-----

## Deployment

The application is designed to be deployed to **AWS ECS Fargate**. The infrastructure is defined using the **AWS CDK** in the `/cdk` directory.

To deploy the application to your AWS account:

1.  **Navigate to the CDK directory:**
    ```sh
    cd cdk
    ```
2.  **Bootstrap the CDK environment (if it's your first time):**
    ```sh
    cdk bootstrap
    ```
3.  **Deploy the stack:**
    ```sh
    cdk deploy
    ```

This will provision all the necessary AWS resources, build the Docker images, push them to ECR, and deploy the services to ECS Fargate.

-----

## Observability

Once the application is running (either locally or on AWS), you can monitor its performance:

1.  **Prometheus**: Access the Prometheus dashboard at `http://localhost:9090` to see the collected metrics from all microservices.
2.  **Grafana**: Access the Grafana dashboard at `http://localhost:3000`. Pre-configured dashboards are included to visualize key metrics like request latency, error rates, and JVM performance.

<!-- end list -->

```
```
