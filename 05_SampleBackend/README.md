# Setup
 - Developer should have *git*, *apache tomcat 7*, *maven*, *java 7 jdk* installed.

 - These command should be invoked:
 - ```server$ mvn eclipse:eclipse clean install war:war```

Then app can be deployed in any servlet container.

# Run TestNG unit tests in Eclipse
 - Install TestNG plugin from Help > Eclipse Marketplace > TestNG
 - Add environment variables from the POM + javaagent:
```
<systemPropertyVariables>
	<jersey.test.host>localhost</jersey.test.host>
	<jersey.config.test.container.port>58080</jersey.config.test.container.port>
	<jersey.config.test.container.factory>org.glassfish.jersey.test.external.ExternalTestContainerFactory</jersey.config.test.container.factory>
</systemPropertyVariables>
```
  - add
```-Djersey.test.host=localhost -Djersey.config.test.container.port=58080 -Djersey.config.test.container.factory=org.glassfish.jersey.test.external.ExternalTestContainerFactory -javaagent:${env_var:HOME}/.m2/repository/org/apache/openjpa/openjpa/2.4.0/openjpa-2.4.0.jar```
to the run configuration (*VM Arguments*)

# Cobertura test coverage

 - Test coverage is available here: target/site/cobertura/index.html

# Working with GIT
 - **MERGE** should **not** be used! Only **REBASE** (```git pull --rebase```)
 - ```git add .```
 - ```git commit -m "#ISSUE_NUMBER ISSUE_DESCRIPTION - more information (if needed)"```
 - ```git pull --rebase```
 - ```mvn clean install```
 - ```git push```

 