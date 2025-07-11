pipeline {
  agent any

  stages {
    stage('Clone') {
      steps {
        echo '🔍 Cloning project from GitHub...'
        checkout scm
      }
    }

    stage('Success') {
      steps {
        echo '✅ Build triggered successfully!'
      }
    }
  }
}
