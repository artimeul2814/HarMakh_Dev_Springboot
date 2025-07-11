pipeline {
  agent any

  stages {
    stage('Clone') {
      steps {
        echo '🔍 Cloning project from GitHub...'
        checkout scm
      }
    }
    stage('Build with Maven') {
      steps {
        echo '🚀 Running Maven build...'
        sh './mvnw clean package -DskipTests'
      }
    }
    stage('Success') {
      steps {
        echo '✅ Build triggered successfully!'
      }
    }
  }
}
