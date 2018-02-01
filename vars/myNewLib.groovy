#!usr/bin/groovy
def call(Map parameters = [:]) {
  echo 'myNewLib parameters: ' + parameters

  def environment = parameters.get('environment')
  def slack_channel = parameters.get('slack_channel')
  def email = parameters.get('email')
  def color = parameters.get('color')
  def message = parameters.get('message')
  def subject = parameters.get('subject')
  
  if(slack_channel != null) {
  slackSend (channel: slack_channel, email: email, color: color, message: message subject: subject )
  }
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
