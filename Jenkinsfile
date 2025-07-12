pipeline {
  agent any

  environment {
    DOCKER_REPO = 'dev-springboot' // You can change this to match your Docker Hub or private registry
  }

  stages {

    stage('Clone') {
      steps {
        echo '🔍 Cloning Git repository...'
        checkout scm
      }
    }

    stage('Build with Maven') {
      steps {
        echo '🚀 Building Spring Boot app with Maven...'
        sh './mvnw clean package -DskipTests'

        echo '📦 Locating and renaming generated JAR...'
        script {
          def originalJar = sh(script: "ls target/*.jar | grep -v original", returnStdout: true).trim()
          sh "mv ${originalJar} target/app.jar"
        }
      }
    }

    stage('Docker Build & Tag') {
      steps {
        echo '🐳 Building Docker image from app.jar...'
        script {
          def shortCommit = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
          def buildTag = "${DOCKER_REPO}:${shortCommit}"
          echo "🏷️ Tagging image as ${buildTag}"
          sh "docker build -t ${buildTag} ."
        // Deploy using docker-compose with env override
	  writeFile file: 'deploy.env', text: "SPRINGBOOT_IMAGE_TAG=${buildTag}"
	  sh "docker-compose --env-file deploy.env -f docker-compose.yml up -d --force-recreate"
	}
      }
    }

    stage('Success') {
      steps {
        echo '✅ Jenkins pipeline completed successfully!'
      }
    }
  }
}
