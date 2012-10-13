java -jar java-cup-11a.jar -interface mini_mips.cup
jflex mips.flex
javac -classpath /home/sean/programming/java/cs1622/stm52_project2/java-cup-11a.jar:/home/sean/programming/java/cs1622/stm52_project2/ main.java 
java -classpath /home/sean/programming/java/cs1622/stm52_project2/java-cup-11a.jar:/home/sean/programming/java/cs1622/stm52_project2/ main rawr.mips
