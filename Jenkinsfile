@Library('cv-pipeline-library')_

import com.cinglevue.GlobalVars

pipeline {

  agent {
      label 'ctaf'
  }

  tools {
    maven 'MAVEN363'
    jdk 'JDK11'
  }

  stages {
    stage('intialize'){
      steps {
        script{
          sh '''
          echo "PATH= ${PATH}"
          echo "M3_HOME = ${M3_HOME}"
          git config user.email "developers@cinglevue.com"
          git config user.name "cicd-user"
          git config credential.helper store
            '''
          checkPollingValid()
        }
      }
    }

  stage('Build the artifacts'){
    when {
      expression {
        return (env.CI_SKIP == 'false');
      }
    }
    steps {
      script{
        if(env.BRANCH_NAME == 'master'){
          sh "mvn -U clean deploy -DskipIntegrationTests=true"
        } else {
          print 'The job will run only for test-jenkins-job branch....'
        }
      }
    }


  post {
    success {
      echo 'Pipeline completed successfully.'
    }
    unstable {
      echo 'Pipeline unstable.'
    }
    failure {
      echo 'Pipeline failure'
    }
    cleanup {
      //this always runs.
      deleteDir()
        }
      }
    }
  }
}
