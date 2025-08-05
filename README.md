# Patient Management Platform

A secure, scalable, and resilient microservices-based platform for managing patient records, built with Spring Boot and deployed on AWS.

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

This project demonstrates a production-ready application architecture, emphasizing best practices in microservices design, event-driven communication, cloud deployment, and observability.

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

## About The Project

The Patient Management Platform is a backend system designed to handle sensitive patient data with a focus on security, high availability, and performance. It breaks down functionalities into independent microservices that communicate asynchronously through a message broker, ensuring loose coupling and fault tolerance.

This project was built to apply and demonstrate skills in:
- Domain-Driven Design for service separation.
- Event-driven architecture for scalable and resilient systems.
- Cloud-native deployment and infrastructure as code.
- Production-grade monitoring and testing.

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
