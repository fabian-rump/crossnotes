pipeline {
    agent any

    environment {
        JAVA_HOME = tool name: 'JDK 21', type: 'jdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"

        GRADLE_OPTS = '-Dorg.gradle.daemon=false -Dorg.gradle.parallel=true -Dorg.gradle.workers.max=4'

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

                script {
                    sh 'git log -1 --oneline'
                    sh 'git branch'
                }
            }
        }

        stage('Setup') {
            steps {
                script {
                    echo "Java Version:"
                    sh 'java -version'

                    echo "Gradle Version:"
                    sh './gradlew --version'

                    sh 'chmod +x gradlew'
                }
            }
        }

        stage('Clean') {
            steps {
                echo 'Cleaning previous builds...'
                sh './gradlew clean'
            }
        }

        stage('Build Android') {
            steps {
                echo 'Building Android Debug and Release...'
                sh './gradlew :composeApp:assembleDebug :composeApp:assembleRelease'
            }
        }

        stage('Test Android') {
            steps {
                echo 'Running Android Unit Tests...'
                sh './gradlew :composeApp:testDebugUnitTest'
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

        stage('Build iOS Framework') {
            when {
                expression {
                    return System.getProperty('os.name').toLowerCase().contains('mac')
                }
            }
            steps {
                echo 'Building iOS Framework...'
                sh './gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64'
                sh './gradlew :composeApp:linkReleaseFrameworkIosArm64'
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo 'Archiving build artifacts...'
                archiveArtifacts artifacts: '**/build/outputs/**/*.apk', fingerprint: true, allowEmptyArchive: true
                archiveArtifacts artifacts: '**/build/bin/**/*.framework/**', fingerprint: true, allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Build successful! ✅'
        }

        failure {
            echo 'Build failed! ❌'
        }

        always {
            cleanWs(
                deleteDirs: true,
                patterns: [
                    [pattern: '**/build', type: 'INCLUDE'],
                    [pattern: '**/.gradle', type: 'INCLUDE']
                ]
            )
        }
    }
}