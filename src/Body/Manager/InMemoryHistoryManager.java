package Body.Manager;

import Body.Interface.HistoryManager;
import Model.Type.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    HashMap<Integer, DoublyLinkedList.Node<Task>> historyMap = new HashMap<>();
    DoublyLinkedList<Task> history = new DoublyLinkedList<>();

    @Override
    public void add(Task task) {
        remove(task.id);

        historyMap.put(task.id, history.linkLast(task));
    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            history.removeNode(historyMap.get(id));
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
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
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

    public void removeNode(Node<T> node) {
        if (node == head) {
            if (node.next != null) {
                head = node.next;
                head.prev = null;
            } else {
                head = null;
                tail = null;
            }
        } else if (node == tail) {
            if (node.prev != null) {
                tail = node.prev;
                tail.next = null;
            } else {
                head = null;
                tail = null;
            }
        } else {
            if (node.prev != null) {
                node.prev.next = node.next;
            }
            if (node.next != null) {
                node.next.prev = node.prev;
            }
        }

    }
}
