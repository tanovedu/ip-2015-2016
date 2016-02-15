# Setup
 - Developer should have *git*, *apache tomcat 7*, *maven*, *java 7 jdk* installed.

 - These command should be invoked:
 - ```server$ mvn eclipse:eclipse clean install```

Then app can be deployed in any web server.

# Working with GIT
 - **MERGE** should **not** be used! Only **REBASE** (```git pull --rebase```)
 - ```git add .```
 - ```git commit -m "#ISSUE_NUMBER ISSUE_DESCRIPTION - more information (if needed)"```
 - ```git pull --rebase```
 - ```mvn clean install```
 - ```git push```
