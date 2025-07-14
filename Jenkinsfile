pipeline {
  agent any

  environment {
    IMAGE_NAME = "dev-springboot"
    IMAGE_TAG  = "devfresh"
  }
  stages{
    stage('Clean Workspace') {
      steps {
      deleteDir()
      }
    }
    stage('Checkout') {
      steps {
        git url: 'git@github.com:artimeul2814/HarMakh_Dev_Springboot.git' , credentialsId: 'git-ssh-key', branch: 'dev'
	checkout scm
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
	  ls -lh target/*.jar
	  if [ ! -f target/*.jar ]; then
    	    echo "❌ JAR not found — did Maven fail silently?"
    	    exit 1
  	  fi
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
