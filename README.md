# Stack Mathematics Interpreter

My Mathematics Interpreter Implementation In Java

## Using

```java
public class HelloWorld {
    public static void main(String []args){
        System.out.println("Hello, World!");

        Interpreter interpreter = new StackMathematicsInterpreter("1+2*3");
        System.out.println(interpreter.getResult());

        interpreter = new Interpreter("1+2*3+4");
        System.out.println(interpreter.getResult());

        interpreter = new Interpreter("1+2*3+4*5");
        System.out.println(interpreter.getResult());
    }
}
```

© Copyright 2022, Max Base
