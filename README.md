# Programowanie Obiektowe - Java
## MiNI PW, Wiosna 2019/2020

### Laboratorium 1 - Środowisko i Hello World

```java
package mini.java.basic.objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

#### Przydatne do poruszania się po środowisku IntelliJ IDEA
- Sktóry klawiaturowe - https://resources.jetbrains.com/storage/products/intellij-idea/docs/IntelliJIDEA_ReferenceCard.pdf
- Szybki start dla IDEA - https://www.jetbrains.com/help/idea/getting-started.html
- Hello World w Java + IDEA - https://www.jetbrains.com/help/idea/creating-and-running-your-first-java-application.html

### Laboratorium 2 i 3 - Obiekty, klasy i tablice

```java
package mini.java.basic.objects;

public class Vehicle {
    protected String name;
    protected String[] manufacturerNames;
    protected int price;
}

public class Plane extends Vehicle {
    private int flightSpeed;
    private int altitude;
}

public class Car extends Vehicle {
    private int speed;
}

public class Bicycle extends Vehicle {
    private boolean twoPerson;
}
```

#### Przydatne linki
- Dziedziczenie - opis na Wikipedia (teoria) - https://pl.wikipedia.org/wiki/Dziedziczenie_%28programowanie%29
- Dziedziczenie - tutorial od Oracle - https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html

#### Poprzednie zadania punktowane
- [Rok 2018/2019](basic-arrays/src/mini/java/basic/arrays/test/README.md)


## Jak skutecznie zabrać się za rozwiązanie zadania punktowanego
[Instrukcja ze zrzutami ekranu](https://github.com/Rughalt/mini-objective-java/wiki/Zadania-Punktowane)


## Oprogramowanie
- Licencje Jetbrains dla studentów - https://www.jetbrains.com/student/
- Java 11/8 - Amazon Corretto - https://docs.aws.amazon.com/corretto/index.html

