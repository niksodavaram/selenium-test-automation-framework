FROM amazoncorretto:18-alpine3.15

#Workspace
WORKDIR /usr/share/udemy

#ADD .jar files under the target from host
ADD target/Planit_Technical_Assesment-1.0-SNAPSHOT.jar          Planit_Technical_Assesment-1.0-SNAPSHOT.jar
ADD target/Planit_Technical_Assesment-1.0-SNAPSHOT-tests.jar    Planit_Technical_Assesment-1.0-SNAPSHOT-tests.jar

#ADD excel files
ADD src/main/resource/testData/testData.xlsx          src/main/resource/testData/testData.xlsx

#ADD suite files 
ADD testng.xml          testng.xml

ENTRYPOINT [ "java","-jar","Planit_Technical_Assesment-1.0-SNAPSHOT.jar:Planit_Technical_Assesment-1.0-SNAPSHOT-tests.jar" ]

