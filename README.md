# Getting Started

## Linux

### Compile Test Jar
* gradle clean build

### Run Jar
* Local:      gradle bootRun 
* Background: nohup gradle bootRun &

### Testing Application
* curl -X GET 'http://localhost:8081/rest/mscovid/test?msg=testing'
