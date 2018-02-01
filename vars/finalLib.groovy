#!usr/bin/groovy
def call(Map parameters = [:]) {
  // build status of null means successful
  echo 'myNewLib parameters: ' + parameters

  def environment = parameters.get('environment')
  def slack_channel = parameters.get('slack_channel')
  def email = parameters.get('email')
  def color = parameters.get('color')
  def message = parameters.get('message')
  
  if (slack_channel != null) {
  slackSend (channel: slack_channel, email: email, color: color, message: "testing slack ${env.BUILD_URL}", subject: "${env.service} Pipeline Notification" )
  }
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
