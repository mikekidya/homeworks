package group144.kidyankin;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/** Class printer using reflection mechanism */
public class ClassPrinter {

    /**
     * Returns the string containing reflected code of given class
     *
     * @param clazz class to be reflected
     * @return the string containing reflected code of given class
     */
    public static String print(Class<?> clazz) {
        return classDeclarationToString(clazz)
                + " { \n"
                + classFieldsToString(clazz)
                + classConstructorsToString(clazz)
                + classMethodsToString(clazz)
                + innerClassesToString(clazz)
                + "\n} \n";
    }

    private static String classDeclarationToString(Class<?> clazz) {
        StringBuilder result = new StringBuilder();
        if (clazz.getModifiers() != 0) {
            result.append(Modifier.toString(clazz.getModifiers()));
            result.append(" ");
        }
        result.append("class ").append(clazz.getSimpleName());
        if (clazz.getTypeParameters().length != 0) {
            result.append(Arrays.stream(clazz.getTypeParameters())
                    .map(Type::getTypeName)
                    .collect(Collectors.joining(", ", "<", ">")));
        }
        if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
            result.append(" extends ");
            result.append(clazz.getSuperclass().getSimpleName());
        }
        if (clazz.getInterfaces().length != 0) {
            result.append(Arrays.stream(clazz.getInterfaces())
                    .map(Class::getSimpleName)
                    .collect(Collectors.joining(", ", " implements ", "")));
        }
        return result.toString();
    }

    private static String classFieldsToString(Class<?> clazz) {
        if (clazz.getDeclaredFields().length == 0) {
            return "";
        }
        return Arrays.stream(clazz.getDeclaredFields())
                .map(ClassPrinter::fieldToString)
                .collect(Collectors.joining(";\n", "\n// Fields: \n", ";\n"));
    }

    private static String fieldToString(Field field) {
        return Modifier.toString(field.getModifiers()) + " "
                + field.getType().getSimpleName() + " "
                + field.getName();
    }

    private static String classConstructorsToString(Class<?> clazz) {
        if (clazz.getDeclaredConstructors().length == 0) {
            return "";
        }
        return Arrays.stream(clazz.getDeclaredConstructors())
                .map(constructor -> constructorToString(clazz, constructor))
                .collect(Collectors.joining(";\n", "\n// Constructors: \n", ";\n"));
    }

    private static String constructorToString(Class<?> clazz, Constructor constructor) {
        return Modifier.toString(constructor.getModifiers()) + " "
                + clazz.getSimpleName()
                + Arrays.stream(constructor.getParameterTypes())
                    .map(Type::getTypeName)
                    .collect(Collectors.joining(", ", "(", ")"));
    }

    private static String classMethodsToString(Class<?> clazz) {
        if (clazz.getDeclaredMethods().length == 0) {
            return "";
        }
        return Arrays.stream(clazz.getDeclaredMethods())
                .map(ClassPrinter::methodToString)
                .collect(Collectors.joining(";\n", "\n// Methods: \n", ";\n"));

    }

    private static String methodToString(Method method) {
        return Modifier.toString(method.getModifiers()) + " "
                + method.getName()
                + Arrays.stream(method.getParameters())
                    .map(parameter -> parameter.getType() + " " + parameter.getName())
                    .collect(Collectors.joining(", ", "(", ")"))
                + ((method.getExceptionTypes().length == 0) ? "" :
                        Arrays.stream(method.getExceptionTypes())
                            .map(Type::getTypeName)
                            .collect(Collectors.joining(", ", " throws ", "")));
    }

    private static String innerClassesToString(Class<?> clazz) {
        StringBuilder result = new StringBuilder();
        for (Class<?> innerClass : clazz.getDeclaredClasses()) {
            result.append(print(innerClass));
        }
        return result.toString();
    }

}
