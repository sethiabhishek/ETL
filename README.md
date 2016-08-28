# ETL


The project deals with basic ETL processing.

a) currently file extraction is possible which can be extended further and configured so as to extend the functionality

b) UpperCase and wordcount transformation is currently supported

c) File loading/writing is curently configured which again can be extended in future

d) All the configurations need to be given in application.properties based on which transformation will be decided at run time

e) dependencies are managed via dependency.xml which can be changed in future to have cross cutting concerns viz logging / auditing introduced in the application, without even changing the code
