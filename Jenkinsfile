pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Ejecutando build en Windows..."
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo "Ejecutando pruebas..."
                bat 'mvn test'
            }
        }
    }
}
