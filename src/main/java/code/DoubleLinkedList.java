package code;

/**
 * @Author SunnyJ
 * @Date 2022/2/24 10:53 上午
 */
public class DoubleLinkedList<T> {
    private Link<T> prior;
    private Link<T> next;
    class Link<T>{
        public T val;
        public Link<T> pre;
        public Link<T> next;
        public Link(T val){
            this.val = val;
        }
        public void printVal(){
            System.out.println(val+" ");
        }
    }
    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();
        doubleLinkedList.addList(3);
        doubleLinkedList.addList(4);
        doubleLinkedList.addList(5);
        doubleLinkedList.delete();
        //doubleLinkedList.addList2();
        doubleLinkedList.display();
    }

    public DoubleLinkedList(){
        prior = null;
        next = null;
    }
    public boolean isEmpty(){
        return prior == null;
    }
    public void addList(T value){
        Link<T> newLink = new Link<>(value);
        if(isEmpty()){
            next = newLink;
        }else {
            prior.pre = newLink;
        }
        newLink.next = prior;
        prior = newLink;
    }
    public void delete(){
        if(prior.next == null){
            next = null;
        }else{
            prior.next.pre = null;
        }
        prior = prior.next;
    }
    public void display(){
        Link<T> cur = prior;
        while (cur!=null){
            cur.printVal();
            cur = cur.next;
        }
        System.out.println();
    }
}
