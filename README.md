# Range Container Project

## Introduction/Design Considerations
The purpose of this project was to create a container that stores consecutive integers in ranges. This implementation allows for the input 
of individual integers for storage, lookup, and removal.

In fleshing this out, I immediately created a `Range` class in order to better conceptualize the storage of ranges. This also allowed
for easy lookup of individual numbers and eased up the logic for appending and splitting with direct "start" and "end" vars.

I then opted to use a self-balancing tree in order to store the ranges. This offers O(n) space where n is the number of ranges and 
an O(log(m)) lookup where m is the number of ranges in the container. In Java, the default implementation of this is `TreeSet`. Having 
prior class experience working with `TreeSet` I felt very comfortable building out my implementation around this data structure.

I then created the `Container` class to act as a vessel for the storage of integers which is heavily documented for reference.

## Files
- **Range.java** - A Java class responsible for defining ranges.
- **Container.java** - A Java class that acts as a container for integers via storage as ranges.
- **ContainerTest.java** - A Java class that tests the functionality of the `Container` class.
- **Demo.java** - Interactable demo for functionality of implmentation.

## Makefile Commands
The provided `Makefile` offers several commands to simplify the compilation, running, and cleanup processes:

### Compilation
To compile the Java source files into bytecode `.class` files, you can use the following command:

```bash
make
```

This will compile the `Range.java`, `Container.java`, `ContainerTest.java`, and `Demo.java` source files.

### Running the Demo
To run the demo application, you can use:

```bash
make run
```

This command will run the `Demo` class which drives a simple demo of the functionality of the container. Input options are continually requested and the subsequent contents of the container can be displayed.

### Testing
To run the tests for the `Container` class, use:

```bash
make test
```

This command will execute the `ContainerTest` class which tests the functionality of the container (mainly just for developmental use).

### Cleanup
If you wish to clean up (remove) the generated `.class` files without running or testing the program, you can simply use:

```bash
make clean
```
