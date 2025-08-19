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
                echo "Ejecutando el build de la aplicación..."
                // ejemplo si usas Maven
                sh 'mvn clean install'
                // ejemplo si usas Gradle
                // sh './gradlew build'
            }
        }

        stage('Test') {
            steps {
                echo "Ejecutando pruebas..."
                // sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                echo "Desplegando aplicación..."
                // aquí tus pasos de despliegue
            }
        }
    }
}
