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
                        sh "nohup gradlew bootRun &"
                        sleep 10
                    }

                    stage('Rest') {
                        sh "curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'"
                    }

                    stage('Nexus') {
                        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
                    }
                }
            }
        }
    }
}