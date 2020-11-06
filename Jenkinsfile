node {
    def scmvars = checkout(scm)
    def commitHash = scmvars.GIT_COMMIT
    def mavenOptions = "-Drevision=${commitHash}"
    env.MAVEN_OPTS="${mavenOptions}"

    try {
        stage('Determine Jenkinsfile to build') {
            def changedFiles = sh(returnStdout: true, script: 'git diff --name-only HEAD^^')


        }
        currentBuild.result = 'SUCCESS'
    } catch (err) {
        println("ERR: ${err}")
        currentBuild.result = 'FAILED'
    }
}
