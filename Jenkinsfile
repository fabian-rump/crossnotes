pipeline {
    agent any

    environment {
        // Jenkins Credential ID: 'MY_API_KEY' → anpassen an deinen Namen
        API_KEY = credentials('MY_API_KEY')
        GRADLE_OPTS = '-Dorg.gradle.daemon=false -Dorg.gradle.parallel=true -Dorg.gradle.workers.max=4 -Dnet.bytebuddy.experimental=true'
    }

    options {
        timeout(time: 1, unit: 'HOURS')
        buildDiscarder(logRotator(numToKeepStr: '10'))
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                sh 'chmod +x gradlew'
                script {
                    sh '''
                        echo "sdk.dir=$ANDROID_SDK" > local.properties
                        java -version
                    '''
                    echo "Building branch: ${env.BRANCH_NAME}"
                }
            }
        }

        stage('Clean') {
            steps {
                echo 'Cleaning previous builds...'
                sh './gradlew clean'
            }
        }

        stage('Build Android Debug') {
            steps {
                echo 'Building Android Debug...'
                sh "./gradlew :composeApp:assembleDebug -PapiKey=${API_KEY}"
            }
        }

        stage('Test Android') {
            steps {
                echo 'Running Android Unit Tests...'
                sh "./gradlew :composeApp:testDebugUnitTest -PapiKey=${API_KEY}"
            }
            post {
                always {
                    junit '**/build/test-results/testDebugUnitTest/*.xml'
                    publishHTML(target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'composeApp/build/reports/tests/testDebugUnitTest',
                        reportFiles: 'index.html',
                        reportName: 'Android Test Report'
                    ])
                }
            }
        }

        stage('Build Android Release') {
            when {
                anyOf {
                    branch 'main'
                    branch 'master'
                    branch 'develop'
                }
            }
            steps {
                echo 'Building Android Release...'
                sh "./gradlew :composeApp:assembleRelease -PapiKey=${API_KEY}"
            }
        }

        stage('Archive APKs') {
            steps {
                echo 'Archiving build artifacts...'
                archiveArtifacts artifacts: '**/build/outputs/apk/**/*.apk',
                                 fingerprint: true,
                                 allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Build successful!'
        }

        failure {
            echo 'Build failed!'
        }
    }
}
