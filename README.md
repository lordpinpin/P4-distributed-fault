# Enrollment System

## Overview

The **Enrollment System** is a distributed application with two main services:

- **Auth Service**: Handles user authentication (login/logout).
- **Course Service**: Manages course data and enrollments.

It uses **Javalin** for APIs, **PostgreSQL** for data storage, and **Docker** for containerization.

## Features

- User authentication (login/logout)
- Course management (create, enroll)
- REST APIs
- Docker-based deployment

## Getting Started

### Prerequisites

- **Docker** and **Docker Compose** installed.
- **Java 11+** for building the services.

### Setup

1. Clone the repository:
git clone <repo-url>

2. Then, navigate to the project directory.
   
3. Build and run services with Docker Compose:
docker-compose up --build

The services will be available on:
Auth Service: http://localhost:7001
Course Service: http://localhost:7002

Stopping Services
To stop the services, run:

docker-compose down

## Demo

[...Demo Link](https://drive.google.com/file/d/13452kodBG4iVMNhc3JTW3EwJ8UVe_Xlo/view?usp=sharing)

## Slides

For the information slides, check [STDISCM - P4 Info Slides.pdf](https://github.com/lordpinpin/P4-distributed-fault/blob/main/STDISCM%20-%20P4%20Info%20Slides.pdf)
