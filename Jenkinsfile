pipeline {
  agent any

  environment {
    IMAGE_NAME = "dev-springboot"
    IMAGE_TAG  = "devfresh"
  }

  stages {
    stage('Checkout') {
      steps {
        git url: 'git@github.com:artimeul2814/HarMakh_Dev_Springboot.git' , credentialsId: 'your-ssh-creds-id', branch: 'dev'

      }
    }

    stage('Build Spring Boot JAR') {
      steps {
        sh './mvnw clean package -DskipTests'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh '''
          docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
        '''
      }
    }

    stage('Verify Docker Image') {
      steps {
        sh 'docker images | grep ${IMAGE_NAME}'
      }
    }
  }
}
