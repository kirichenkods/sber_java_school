abstract class Parent {
    private String Name;

    public Parent() {
        System.out.println("Parent:constructor");
    }

    public Parent(String name) {
        Name = name;
        System.out.println("Parent:name-constructor");
    }

    static {
        System.out.println("Parent:static 1");
    }

    static {
        System.out.println("Parent:static 2");
    }

    {
        System.out.println("Parent:instance 1");
    }

    {
        System.out.println("Parent:instance 2");
    }
}
