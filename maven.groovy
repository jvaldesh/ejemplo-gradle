def call() {
    stage('compile') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        sh "./mvnw clean compile -e"
    }

    stage('test') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        sh "./mvnw clean test -e"
    }

    stage('jar') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        sh "./mvnw clean package -e"
    }

    stage('SonarQube') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        withSonarQubeEnv(installationName: 'sonarqube') {
            sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
        }
    }

    stage('uploadNexus') {
        env.LAST_STAGE_NAME = env.STAGE_NAME
        nexusPublisher nexusInstanceId: 'nexus', nexusRepositoryId: 'test-repo', packages: [[$class: 'MavenPackage', mavenAssetList: [[classifier: '', extension: 'jar', filePath: '/home/julio/repos/ejemplo-maven/build/DevOpsUsach2020-0.0.1.jar']], mavenCoordinate: [artifactId: 'DevOpsUsach2020', groupId: 'com.devopsusach2020', packaging: 'jar', version: '0.0.1']]]
    }
}

return this;