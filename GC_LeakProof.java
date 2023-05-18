import java.util.ArrayList;
import java.util.List;

// list 변수를 지역 변수로 선언하여 메모리 누수를 방지
/*
list 변수를 지역 변수로 선언하고, addToMyList 메서드에서 이를 인자로 전달합니다.
이렇게 하면, 해당 메서드가 호출될 때마다 새로운 list 객체가 생성되므로,
MyClass 인스턴스가 소멸될 때 해당 객체도 함께 메모리에서 해제됩니다.
 */

public class GC_LeakProof {
    
    public void addToMyList(List<String> list, String str) {
        list.add(str);
    }
    
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        GC_LeakProof gc = new GC_LeakProof();
        for (int i = 0; i < 1000000; i++) {
            gc.addToMyList(list, "string" + i);
        }
    }
}
