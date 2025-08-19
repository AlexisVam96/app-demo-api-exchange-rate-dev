pipeline {
    agent any

    tools {
        jdk 'jdk17'        // el nombre que configuraste en Global Tool Configuration
        maven 'Maven3'     // (opcional) si configuraste Maven en Jenkins
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn clean install'  // en Windows
                // sh 'mvn clean install' // en Linux
            }
        }
    }
}
