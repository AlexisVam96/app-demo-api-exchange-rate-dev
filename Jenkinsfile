pipeline {
    agent any

    tools {
        jdk 'jdk17'       // el nombre configurado en Global Tool Configuration
        maven 'Maven'     // idem para Maven
    }

    environment {
        IMAGE_NAME = "alexisdev96/app-demo-api-exchange-rate-dev"
    }

    stages {
        stage('Build Maven') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t %IMAGE_NAME%:latest ."
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        docker push %IMAGE_NAME%:latest
                    """
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                // en Windows el compose ahora es "docker compose" (sin guion)
                bat """
                    docker compose down || exit 0
                    docker compose up -d --build
                """
            }
        }
    }
}
