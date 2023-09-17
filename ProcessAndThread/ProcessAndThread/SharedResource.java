package ProcessAndThread;

// 공유 자원 클래스
public class SharedResource {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

