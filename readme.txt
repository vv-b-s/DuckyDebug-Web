I. Needed software:
    a) Only to run the jar package
        -> JRE 1.8! - Spring boot works at best with this version. Errors may occur with upper or lower versions
        -> MySQL server - this app uses Hibernate ORM framework therefore MySQL server should be running with the following configurations:
            - PORT: 3306
            - Username: root
            - Password: (empty)
        -> Free port 8080: The app will run on http://localhost:8080 therefore port 8080 shouldn't be busy.
    b) For compiling/building packages or debugging code
        -> JDK 1.8! - this version is yet required because of the same reason as mentioned in case a)
        -> IDE supporting Java EE - This project has been made with IntelliJ IDEA so it will work at best with this IDE

II. How to run the jar package?
    Open your command prompt/terminal at the project folder and type: java -jar project.jar
        -> The file can be executed without terminal but it will be harder to stop the server when the work with the project is done
        -> If something went wrong please answer the following questions:
            - Is JRE 8 installed on your computer?
            - Are the path variables configured with the java executable? (Windows problem usually)
            - Is MySQL server running on port 3306?
            - Is TomCat server persistent? (usually should be provided with the jar)
            * If none of the above helped please rebuild the project, described in step III.

III. How to build/debug the project?
    Having said that Java EE supporting IDE is needed,
    1) Open the project folder in the IDE
    2) Import maven packages - usually IDE will ask whether you would like to import maven packages
    3) Use JDK 8 for building tools.
    4) Click run.
    * If everything is configured as described in I. the project should run without any problems.

IV. How to build an executable jar?
    For this procedure you would need to use the maven build tool which is provided by downloading the maven plugin to your computer
    After everything is downloaded, using terminal/cmd write the following command: maven package
    pom.xml has the instructions needed to build the jar. Maven will build the app and test it if it runs. After everything is complete a jar file will be written in target/ directory


If all of these steps fail, please contact me at: valentin.v1997@gmail.com
