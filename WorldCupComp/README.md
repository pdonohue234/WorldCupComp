1. cmd within project workspace directory: mvn clean install => Builds ear
2. cmd with project workspace EAR directory: mvn appengine:devserver => runs server locally
3. http://localhost:8080/html/index.html

4. To upload to dev server: mvn appengine:update
5. Will be prompted for code, that will appear in a new web browser pop up


Locked update to app engine. Need to rollback with appcfg.cmd but not working. Create a new version of the app on appengine
1. Manually update version of app in web-inf\appengine-web.xml. Change <version> to whatever you want
2. Deploy using Maven as normal
3. Open appengine.google.com for project and go to version page
4. make new version the default
