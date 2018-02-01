#!usr/bin/groovy
def call(Map parameters = [:]) {
  // build status of null means successful
  echo 'myNewLib parameters: ' + parameters

  def environment = parameters.get('environment')
  def slack_channel = parameters.get('slack_channel')
  def email = parameters.get('email')
  def color = parameters.get('color')
  def message = parameters.get('message')
  def subject = parameters.get('subject')
  def build_status = parameters.get('build_status')
  
  if (build_status != null) {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else {
    color = 'RED'
    colorCode = '#FF0000'
  }
  slackSend (channel: slack_channel, email: email, color: color, message: message subject: subject )
  if (email != null) {
      emailext (
        to: email,
        subject: subject,
        body: message,
        recipientProviders: [[$class: 'DevelopersRecipientProvider']],
        replyTo: 'no.reply.jenkins@corp.local'
      )
  }
}
