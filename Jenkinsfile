pipeline {
    agent any

    tools {
        jdk 'jdk17'        // el nombre que configuraste en Global Tool Configuration
        maven 'Maven'     // (opcional) si configuraste Maven en Jenkins
    }
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub')
        IMAGE_NAME = "alexisdev96/app-demo-api-exchange-rate-dev"
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'  // en Windows
                // sh 'mvn clean install' // en Linux
            }
        }
        stage('Build Docker Image') {
            steps {
                bat "docker build -t %IMAGE_NAME%:latest ."
            }
        }

        stage('Push to DockerHub') {
            steps {
                bat "echo %DOCKERHUB_CREDENTIALS_PSW% | docker login -u %DOCKERHUB_CREDENTIALS_USR% --password-stdin"
                bat "docker push %IMAGE_NAME%:latest"
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                bat "docker compose down || exit 0"
                bat "docker compose up -d --build"
            }
        }
    }
}
