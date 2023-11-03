/*
 * Project and Training 2 - HS23, Computer Science, Berner Fachhochschule
 */

// You are not allowed to change this file.


package ch.bfh.queue;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

/**
 * This class tests encapsulation, i.e. the tests fail if methods
 * or variables are visible that are not declared in the interface or
 * inherited.
 */
//CHECKSTYLE:OFF MagicNumber
class EncapsulationTest {
    protected final Class<?> clazz = HeapBasedPriorityQueue.class;

    @Test
    void testAdditionalPublicMethods() {
        for (Method m : clazz.getDeclaredMethods()) {
            if (Modifier.isPublic(m.getModifiers()) && !methodOverridden(m)) {
                fail("Public method found that is neither declared in the interfaces "
                        + "nor inherited from the superclass: " + m);
            }
            if (!Modifier.isPublic(m.getModifiers()) && !Modifier.isPrivate(m.getModifiers())
                    && !Modifier.isProtected(m.getModifiers()) && !methodOverridden(m)) {
                fail("Method found that is package visible (must rather be private/protected) : " + m);
            }
        }
    }

    protected boolean methodOverridden(Method m) {
        Class<?> c = m.getDeclaringClass();
        if (c.equals(Object.class)) {
            return false;
        }
        try {
            c.getSuperclass().getMethod(m.getName(), m.getParameterTypes());
            return true;
        } catch (NoSuchMethodException e) {
            // method not found in superclass, continue
        }
        for (Class<?> i : c.getInterfaces()) {
            try {
                i.getMethod(m.getName(), m.getParameterTypes());
                return true;
            } catch (NoSuchMethodException e) {
                // method not found in this interface, continue
            }
        }
        return false;
    }

    @Test
    void testAdditionalPublicVariables() {
        for (Field f : clazz.getDeclaredFields()) {
            if (Modifier.isPublic(f.getModifiers())) {
                fail("Public variable found that is neither instantiated in the interfaces "
                        + "nor inherited from the superclass: " + f);
            }
            if (!Modifier.isPublic(f.getModifiers()) && !Modifier.isPrivate(f.getModifiers())
                    && !Modifier.isProtected(f.getModifiers())) {
                fail("Variable found that is package visible (must rather be private/protected) : " + f);
            }
        }
    }

    @Test
    void testAdditionalInnerClasses() {
        for (Class<?> c : clazz.getDeclaredClasses()) {
            if (Modifier.isPublic(c.getModifiers())) {
                fail("Public inner class found, should be protected/private: " + c);
            }
            if (!Modifier.isPublic(c.getModifiers()) && !Modifier.isPrivate(c.getModifiers())
                    && !Modifier.isProtected(c.getModifiers())) {
                fail("Class found that is package visible (must rather be private/protected) : " + c);
            }
        }
    }


}