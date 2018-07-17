#!groovy

def buildTestAndPackageApp() {
  echo ">>> Building, testing and packaging App"
  try {
    sh "mvn clean verify"
  } finally {
    junit([healthScaleFactor: 0.0, testResults: '**/target/surefire-reports/*.xml,**/target/failsafe-reports/*.xml'])
  }
}

def buildDockerImage() {
  echo ">>> Building Docker image"
  sh "docker build -t app-image ./app-server"
}

def stopDockerContainerIfRunning() {
  echo ">>> Stopping Docker container if running"
  sh "docker rm -f \$(docker ps -q --filter 'name=app-container') || true > /dev/null"
}

def startDockerContainer() {
  echo ">>> Starting Docker container"
  sh "docker run --rm -d --name app-container -p 8090:8090 app-image"
  waitForAppToBeUp()
}

def waitForAppToBeUp() {
  echo ">>> Waiting for App to be UP"
  timeout(time: 30, unit: 'SECONDS') {
    waitUntil {
      script {
        def r = sh script: 'wget -q http://localhost:8090/health -O /dev/null', returnStatus: true
        return (r == 0);
      }
    }
  }
}

def runComponentTest() {
  echo ">>> Running Component test"
  try {
    sh "mvn -f=component-test/pom.xml verify"
  } finally {
    junit([healthScaleFactor: 0.0, testResults: '**/target/surefire-reports/*.xml'])
  }
}

def runTaurusTest() {
  try {
    sh "./performance-test/taurus.sh"
  } finally {
    perfReport 'performance-test/target/taurus-kpi-report.xml'
  }
}

node("local-agent") {
  deleteDir()

  stage('Checkout') {
    git 'http://root@localhost:30080/root/java-ci-example.git'
  }

  stage('Build & Test') {
    buildTestAndPackageApp()
  }

  stage('Concurrency Stress test') {

  }

  stage('Build Docker image') {
    buildDockerImage()
  }

  stage('Start Docker container') {
    stopDockerContainerIfRunning()
    startDockerContainer()
  }

  stage('Component Test') {
    runComponentTest()
  }

  stage('Performance Benchmark Test') {
    runTaurusTest()
  }

  stage('Tear down'){
    stopDockerContainerIfRunning()
  }
}