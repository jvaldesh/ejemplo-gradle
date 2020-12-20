pipeline {
    agent any

    stages {
        stage('Pipeline') {
            environment {
                LAST_STAGE_NAME = ''
            }
            
            steps {
                script {
                    println "buildtool: " + params.buildtool
                    def ejecucion = ''
                    if (params.buildtool == 'gradle') {
                        ejecucion = load 'gradle.groovy'
                    } else {
                        ejecucion = load 'maven.groovy'
                    }
                    
                    ejecucion.call()
                }
            }
        }
    }

    post {
        success {
            slackSend color: 'good', message: "[Julio Valdés][${env.JOB_NAME}][${params.buildtool}] Ejecución exitosa"
        }

        failure {
            slackSend color: 'danger', message: "[Julio Valdés][${env.JOB_NAME}][${params.buildtool}] Ejecución fallida en stage ${env.LAST_STAGE_NAME}"
        }
    }
}