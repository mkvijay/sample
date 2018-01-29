#!usr/bin/groovy
def call(Map parameters = [:]) {
  echo 'approval parameters: ' + parameters
  
  def slack_channel = parameters.get('slack_channel')
  def slack_color = 'GREEN'
  def color_code = '#00FF00'
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def summary = "${subject} (${env.BUILD_URL})"
 
  buildStatus = currentBuild.result
   // Override default values based on build status
  if (buildStatus == 'STARTED') {
    color = 'YELLOW'
    colorCode = '#FFFF00'
  } else if (buildStatus == 'SUCCESSFUL') {
    color = 'GREEN'
    colorCode = '#00FF00'
  } else (buildStatus == 'FAILED') {
    color = 'RED'
    colorCode = '#FF0000'
  }
  // Send notifications
  slackSend (channel: '#work-test', color: colorCode, message: summary)
}
  
