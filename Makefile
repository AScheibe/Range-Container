SOURCES = Range.java Container.java ContainerTest.java Demo.java

CLASSFILES = Range.class Container.class ContainerTest.class Demo.class

$(CLASSFILES): $(SOURCES)
	javac $(SOURCES)

test: $(CLASSFILES)
	java -ea ContainerTest
	make clean

run: $(CLASSFILES)
	java Demo
	make clean

clean:
	rm *.class