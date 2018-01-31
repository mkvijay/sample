#!usr/bin/groovy
def call(Map parameters = [:]) {
  // build status of null means successful
  echo 'myNewLib parameters: ' + parameters

  def environment = parameters.get('environment')
  def slack_channel = parameters.get('slack_channel')
  def email = parameters.get('email')
  def message = parameters.get('message')
  def build_status = parameters.get('build_status')
  
  if (build_status != null) {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }
  slackSend (channel: slack_channel, message: "testing slack ${env.BUILD_URL}", subject: "${env.service} Pipeline Notification" )
  if (email != null) {
      emailext (
        to: email,
        subject: "${env.service} Pipeline Notification!",
        body: "${env.service} pipeline deploy status, please take a look:  ${env.BUILD_URL}",
        recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        replyTo: 'no.reply.jenkins@corp.local'
      )
  }
}
