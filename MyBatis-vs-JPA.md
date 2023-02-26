# Key Difference Between MyBatis vs JPA/Hibernate

## object-centric view VS database-centric view
Hibernate basically considers **object-centric view** and maps the object model with the 
database model with the minimum efforts from the developers end, 

whereas MyBatis considers **database-centric view** and maps the ResultSet of objects without 
worrying about the source whether table, stored procedures,

## Hibernate is complex VS MyBatis is easy
MyBatis is comparatively quite simple and easy to understand for the new developers 
as it is a small tool also which mainly uses the SQL of which the programmer is familiar 
whereas on the other hand, Hibernate is complex to understand and is quite a large tool.

## Portability and Migration, 
MyBatis is very poor in terms of migration and Portability as **all SQL is written** in database 
whereas in Hibernate, all the database specific specifications are written in XML, 
so **HQL** does not need to worry about which database is used and its migration.

## SQL Automatic
Hibernate generates its own SQL **automatically** whereas in MyBatis developer has to **hand-write**  
its own SQL queries.

## Mapping
Hibernate maps the **Java classes to the database tables** 
whereas MyBatis tool maps the **SQL to the Java methods**.

## When in complex queries
In case of fetching complex queries, MyBatis works excellently whereas in Hibernate, 
the entire object graph is loaded first, and then it starts tuning queries with the 
lazy tricks which could be quite complex and lengthy.

## labour cost
In terms of labour cost, **working on MyBatis would be cheap** as compared to Hibernate 
as it is simpler to understand and makes the use of SQL mainly, so low skilled 
developers can easily work on it whereas working on **Hibernate requires the 
high skilled guru** to work on it.


