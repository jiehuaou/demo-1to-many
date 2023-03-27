
## Bidirectional navigational access is not always good, 

especially for "**one-to-very-many" and "many-to-very-many**" relationships. Imagine a Group that 
contains thousands of Users:

* How would you access them? With so many Users, you usually need to apply some filtering and/or 
pagination, so that you need to execute a query anyway (unless you use collection filtering, 
which looks like a hack for me). Some developers may tend to apply filtering in memory in such 
cases, which is obviously not good for performance. Note that having such a relationship can 
encourage this kind of developers to use it without considering performance implications.

* How would you add new Users to the Group? Fortunately, Hibernate looks at the owning side 
of relationship when persisting it, so you can only set User.group. However, if you want to 
keep objects in memory consistent, you also need to add User to Group.users. But it would 
make Hibernate to fetch all elements of Group.users from the database!


## Unidirectional associations 

unidirectional associations with join-table are **not efficient** when it comes to removing child,
Hibernate will delete all children then re-insert the others one by one.

