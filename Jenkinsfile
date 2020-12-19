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
}