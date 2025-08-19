pipeline {
    agent any

    tools {
        jdk 'jdk17'        // el nombre que configuraste en Global Tool Configuration
        maven 'Maven'     // (opcional) si configuraste Maven en Jenkins
    }
    environment {
        DOCKER_IMAGE = "alexisdev96/app-demo-api-exchange-rate-dev"
        DOCKER_TAG   = "latest"
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
                script {
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                        docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").push()
                    }
                }
            }
        }
    }
}
