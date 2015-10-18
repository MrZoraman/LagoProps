# Lago-Props
A simple wrapper for java's properties api at the moment.
I'm pretty tired of writing boilerplate code, so I wrote this
library to make my life easier.

It tries to use exceptions as little as possible, instead using
several boolean getters to check the state of things, so you can
be as careful (or not) about your data as you want, without exceptions
being shoved down your throat. If you're feeling confident, you can
read data with a single line of code. If you want to be super careful,
you can check several things before using the data read from the file.

# Javadoc
I have [javadocs](http://jd.lagopusempire.com/LagoProps/1.0/)!
If it's not documented, then you shouldn't need to use it.

# Code Example
Assuming that this is in a class called Main, and there is a
test.properties in the jar file. It is important to note that this
library will only work if there is a default properties file in the 
jar, or else it won't work. It automatically exports that file to the
working directory and uses it for default values.
```java
//this does throw an exception. I coudln't avoid them completely without
//hindering the functionality of the library. Exceptions do exist for a reason.
LagoProps props = new PropertiesLProps("test.properties", Main.class);
//get some value, assuming the test.properties contains "foo=bar"
Prop<String> prop = props.getString("foo");
System.out.println(prop.get());//prints 'bar'
prop.set("baz");
//also throws an exception :(
props.save();
//now test.properties (on the disk) contains "foo=baz"
```
I encourage you to go through the Prop file api and look at
all the methods you have at your disposal for checking the integrity
of the data.

# maven
Add this to your repositories:
```xml
<repository>
    <id>Lagopus Empire Repo</id>
    <name>Lagopus Empire Repo-releases</name>
    <url>http://repo.lagopusempire.com:3000</url>
</repository>
```
Add this to your dependencies
```xml
<dependency>
    <groupId>com.lagopusempire</groupId>
    <artifactId>LagoProps</artifactId>
    <version>1.0</version>
</dependency>
```