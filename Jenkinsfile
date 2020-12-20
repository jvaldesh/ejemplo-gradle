pipeline {
    agent any

    stages {
        stage('Pipeline') {
            steps {
                script {
                    println "buildtool: " + params.buildtool

                    if( params.buildtool == 'gradle') {
                        def ejecucion = load 'gradle.groovy'
                        ejecucion.call()
                    } else {
                        def ejecucion = load 'maven.groovy'
                        ejecucion.call()
                    }

                    
                }
            }
        }
    }

    post {
        success {
            slackSend color: 'good', message: '[Julio Valdés][${env.JOB_NAME}][${params.buildtool}] Ejecución exitosa'
        }

        failure {
            slackSend color: 'danger', message: '[Julio Valdés][${env.JOB_NAME}][${params.buildtool}] Ejecución fallida en stage ${env.STAGE_NAME}'
        }
    }
}