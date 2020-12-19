pipeline {
    agent any

    stages {
        stage('Pipeline') {
            steps {
                script {
                    stage('Build & Test') {
                        sh "./gradlew clean build"
                    }

                    stage('Sonar') {
                        // corresponde a lo que se configuro en global tool config
                        def scannerHome = tool 'sonar-scanner';
                        // corresponde a lo que se configuro en system config
                        withSonarQubeEnv('sonarqube') { 
                            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
                        }
                    }

                    stage('Run') {

                    }

                    stage('Rest') {

                    }

                    stage('Nexus') {

                    }
                }
            }
        }
    }
}