#!usr/bin/groovy
def call(Map parameters = [:]) {
  // build status of null means successful

  def environment = parameters.get('environment')
  def slack_channel = parameters.get('slack_channel')
  def email = parameters.get('email')
  def message = parameters.get('message')

  if (slack_channel != null) {
      slackSend (channel: slack_channel, subject: "${env.service} Pipeline Notification" )
  }
}
