pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                checkout scm
            }
        }

        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {

        // SINGLE always block (MANDATORY)
        always {

            // Debug check
            bat 'dir target\\allure-results || echo "No allure-results found"'

            // ✅ Publish Allure (left sidebar)
            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results']]
            ])

            //Email after build completion
            emailext(
                subject: "Build ${currentBuild.currentResult}: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                mimeType: 'text/html',
                body: """
                    <h2>Jenkins Build Summary</h2>

                    <table border="1" cellpadding="6">
                        <tr><td><b>Job</b></td><td>${env.JOB_NAME}</td></tr>
                        <tr><td><b>Build</b></td><td>#${env.BUILD_NUMBER}</td></tr>
                        <tr><td><b>Status</b></td><td>${currentBuild.currentResult}</td></tr>
                    </table>

                    <br/>

                    <p>
                        <a href="${env.BUILD_URL}">Jenkins Build</a><br/>
                        <a href="${env.BUILD_URL}allure">Allure Report</a>
                    </p>

                    <p><i>Allure link works on Jenkins host.</i></p>
                """,
                to: "rajathpai90@gmail.com"
            )
        }
    }
}
