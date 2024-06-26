import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    HashMap<Integer, DoublyLinkedList.Node<Task>> historyMap = new HashMap<>();
    DoublyLinkedList<Task> history = new DoublyLinkedList<>();

    @Override
    public void add(Task task) {
        remove(task.id);

        DoublyLinkedList.Node<Task> node = history.linkLast(task);
        historyMap.put(task.id, node);

    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            DoublyLinkedList.Node<Task> node = historyMap.get(id);
            history.removeNode(node);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }
}

class DoublyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;

    public static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public Node<T> linkLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        return newNode;
    }

    public ArrayList<T> getTasks() {
        ArrayList<T> tasks = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            tasks.add(current.data);
            current = current.next;
        }
        return tasks;
    }

    public Node<T> removeNode(Node<T> node) {
        if (head == null && tail == null) {
            return null;
        } else if (node == head) {
            if (node.next == null){
                return null;
            }else {
                head = node.next;
                head.prev = null;
                return head;
            }

        } else if (node == tail) {
            if (node.prev == null){
                return null;
            } else {
                tail = node.prev;
                tail.next = null;
                return head;
            }
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            return head;
        }
    }
}
