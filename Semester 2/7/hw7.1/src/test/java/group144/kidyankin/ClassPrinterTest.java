package group144.kidyankin;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import static org.junit.Assert.*;

public class ClassPrinterTest {

    @Test
    public void printStack() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> clazz = classLoader.loadClass("group144.kidyankin.Stack");
        String EXPECTED =
                "public class Stack { \n" +
                "\n" +
                "// Fields: \n" +
                "private Node head;\n" +
                "private int size;\n" +
                "\n" +
                "// Constructors: \n" +
                "public Stack();\n" +
                "\n" +
                "// Methods: \n" +
                "public add(int arg0);\n" +
                "public isEmpty();\n" +
                "public pop();\n" +
                "public getSize();\n" +
                "private class Node { \n" +
                "\n" +
                "// Fields: \n" +
                "private int value;\n" +
                "private Node next;\n" +
                "final Stack this$0;\n" +
                "\n" +
                "// Constructors: \n" +
                " Node(group144.kidyankin.Stack, int, group144.kidyankin.Stack$Node);\n" +
                "\n" +
                "// Methods: \n" +
                "public getValue();\n" +
                "public getNext();\n" +
                "\n" +
                "} \n" +
                "\n" +
                "} \n";
        assertEquals(EXPECTED, ClassPrinter.print(clazz));
    }

    @Test
    public void printList() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> clazz = classLoader.loadClass("group144.kidyankin.List");
        String EXPECTED =
                "public class List<T> { \n" +
                "\n" +
                "// Fields: \n" +
                "private Node head;\n" +
                "private int length;\n" +
                "\n" +
                "// Constructors: \n" +
                "public List();\n" +
                "\n" +
                "// Methods: \n" +
                "public add(class java.lang.Object arg0, int arg1) throws java.lang.IndexOutOfBoundsException;\n" +
                "public add(class java.lang.Object arg0);\n" +
                "public getLength();\n" +
                "public isEmpty();\n" +
                "public pop(int arg0) throws group144.kidyankin.NoSuchElementException;\n" +
                "public pop() throws group144.kidyankin.NoSuchElementException;\n" +
                "public find(class java.lang.Object arg0);\n" +
                "private class Node<T> { \n" +
                "\n" +
                "// Fields: \n" +
                "private Object value;\n" +
                "private Node next;\n" +
                "final List this$0;\n" +
                "\n" +
                "// Constructors: \n" +
                " Node(group144.kidyankin.List, java.lang.Object, group144.kidyankin.List$Node);\n" +
                "\n" +
                "// Methods: \n" +
                "public getValue();\n" +
                "public setNext(class group144.kidyankin.List$Node arg0);\n" +
                "public getNext();\n" +
                "\n" +
                "} \n" +
                "\n" +
                "} \n";
        assertEquals(EXPECTED, ClassPrinter.print(clazz));
    }

    @Test
    public void printUniqueList() throws IOException, ClassNotFoundException {
        ClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://")});
        Class<?> clazz = classLoader.loadClass("group144.kidyankin.UniqueList");
        String EXPECTED =
                "public class UniqueList<T> extends List { \n" +
                "\n" +
                "// Constructors: \n" +
                "public UniqueList();\n" +
                "\n" +
                "// Methods: \n" +
                "public add(class java.lang.Object arg0) throws group144.kidyankin.ElementAlreadyAddedException;\n" +
                "public add(class java.lang.Object arg0, int arg1) throws java.lang.IndexOutOfBoundsException, group144.kidyankin.ElementAlreadyAddedException;\n" +
                "\n" +
                "} \n";
        assertEquals(EXPECTED, ClassPrinter.print(clazz));
    }
}