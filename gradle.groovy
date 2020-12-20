def call() {
    stage('Build & Test') {
        sh "./gradlew clean build"
    }

    stage('Sonar') {
        env.LAST_STAGE_NAME = env.STAGE_NAME

        // corresponde a lo que se configuro en global tool config
        def scannerHome = tool 'sonar-scanner';
        // corresponde a lo que se configuro en system config
        withSonarQubeEnv('sonarqube') { 
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build"
        }
    }

    stage('Run') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        sh "nohup ./gradlew bootRun &"
        sleep 10
    }

    stage('Rest') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        sh "curl -X GET 'http://localhost:8082/rest/mscovid/test?msg=testing'"
    }

    stage('Nexus') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: 'build/libs/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
    }
}

return this;