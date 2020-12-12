pipeline {
    agent any

    stages {
        stage('Compile') {
            steps {
                sh "./mvnw clean compile -e"
            }
        }
        
        stage('Test') {
            steps {
                sh "./mvnw clean test -e"
            }
        }
        
        stage('Package') {
            steps {
                sh "./mvnw clean package -e"
            }
        }

        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv(installationName: 'sonarqube') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                }
            }
        }
        
        stage('Run background') {
            steps {
                sh "nohup bash mvnw spring-boot:run &"
                sh 'sleep 10'
            }
        }
        
        stage('Testing') {
            steps {
                sh "curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'"
            }
        }
    }
}
