# Bigdata_Garbage-Collection
가비지 컬렉션(Garbage Collection)에 대한 설명과 예제를 통한 학습.

<h4>가비지 컬렉션(Garbage Collection)</h4>
프로그램에서 동적으로 할당된 메모리 중에서 더 이상 사용하지 않는 메모리를 탐지하고 해제하는 자동화된 메모리 관리 기술입니다.<br>

<h4> 사용 이유</h4>
프로그램에서 메모리를 사용하다보면, 메모리를 해제하는 코드를 작성하지 않으면 사용되지 않는 메모리가 계속 쌓여서 메모리 누수(Memory leak)가 발생할 수 있습니다. <br>
이러한 문제를 해결하기 위해서 가비지 컬렉션은 필수적입니다.<br>
<br>
가비지 컬렉션은 프로그램에서 사용 중인 메모리 영역에서 더 이상 사용하지 않는 객체(인스턴스)를 탐지하고, 자동으로 해당 객체가 차지하고 있는 메모리를 해제합니다. 이 과정에서 프로그램이 일시 중단되는 시간(Stop the World)이 발생할 수 있으며, 이 시간이 길어지면 성능 문제가 발생할 수 있습니다.<br>
<br>
<h4> 동작 매커니즘</h4>
가비지 컬렉션의 매커니즘은 크게 두 가지 방법으로 구현됩니다.<br>
<b>참조 카운팅(Reference counting) 기법</b><br>
- 객체가 참조될 때마다 카운트를 증가시키고, 해당 객체를 가리키는 포인터가 없어지면 카운트를 감소시키는 방법입니다. 카운트가 0이 되면 해당 객체를 해제하는 방식입니다.<br>
<b>마크 앤 스위프(Mark and Sweep) 기법</b><br>
- 사용 가능한 메모리를 추적하고, 참조되는 객체를 표시(Mark)합니다. 그리고 마크되지 않은 객체들은 사용 가능한 메모리로 판단하고, 해당 객체가 차지하는 메모리를 해제합니다.<br>

<h4> 주의 사항</h4>
- 객체를 사용한 후, 더 이상 해당 객체를 사용하지 않을 때는 해당 객체의 참조를 null로 초기화해야 합니다.<br>
- 순환 참조(Reference cycle)를 피해야 합니다. 예를 들어, 두 개의 객체가 서로를 참조하는 경우에는, 하나의 객체만이 다른 객체를 참조하도록 하여, 한 객체가 다른 객체를 참조하지 않도록 합니다.<br>


<h4> Example </h4>
<b>- 가비지 컬렉션으로도 메모리 leak이 발생될 때.</b><br>
<code>
    public class MyClass {
        private List<String> list = new ArrayList<>();
        public void addToMyList(String str) {
            list.add(str);
        }
        public static void main(String[] args) {
            MyClass myClass = new MyClass();
            for (int i = 0; i < 1000000; i++) {
                myClass.addToMyList("string" + i);
            }
        }
    }
</code>
<b>- list 변수를 지역 변수로 선언하여 메모리 누수를 방지.</b><br>
<code>
    public class MyClass {
        public void addToMyList(List<String> list, String str) {
            list.add(str);
        }
        public static void main(String[] args) {
            List<String> list = new ArrayList<>();
            MyClass myClass = new MyClass();
            for (int i = 0; i < 1000000; i++) {
                myClass.addToMyList(list, "string" + i);
            }
        }
    }
</code>
  
 -출처
  Chat GPT
  Github Lib0823
