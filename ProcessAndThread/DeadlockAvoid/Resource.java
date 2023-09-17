package DeadlockAvoid;

public class Resource {
    private int id;

    public Resource(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public synchronized void doSomething() {
        System.out.println("Resource " + id + " is doing something.");
    }

    public synchronized void doAnotherThing(Resource other) {
        System.out.println("Resource " + id + " is doing another thing.");
        other.doSomething();
    }
}

