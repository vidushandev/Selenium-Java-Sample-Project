pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building Project..'
                bat 'mvn clean'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                bat 'mvn test'
            }
        }
        stage('Compile') {
            steps {
                echo 'Deploying....'
                bat 'mvn compile'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}