## How has it been created?
* Jakarta EE archetype, Web

## How to run locally?
* `mvn clean install package`
* set up your web server, adding the '*.war'
  * glassfish server
    * how to configure?
      * [Example video1](https://www.youtube.com/watch?v=AJxBg90HM4s)
      * [Example video2](https://www.youtube.com/watch?v=Z0fB4Mkmi3A)
    * run the glassfish server adding 'hello2.war'
* Open in your desired browser 'http://localhost:8080/tutorial-1.0-SNAPSHOT' and navigate to the other path

## Notes
* Add [OpenTelemetry hook](https://github.com/open-feature/java-sdk-contrib/tree/main/hooks/open-telemetry)
  * [how to use](https://github.com/open-feature/java-sdk-contrib/tree/main/hooks/open-telemetry)
* provider
  * configure on API level
  * types
    * sync
    * async
    * -- associated to a -- named client
* targeting
  * == dynamic criteria about the subject of flag evaluation
  * -- provided via -- evaluation context
* hooks
  * on
    * API
    * Client
    * flag invocation
* named client
  * -- can be associated with a -- provider
* event handler on
  * API
  * client
* shutdown ALL registered providers
  * uses
    * when you want to shut down the application