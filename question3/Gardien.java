package question3;
import java.util.Stack;

public class Gardien{
    private Stack<Memento> mementoStk;

    public Gardien(){
        this.mementoStk = new Stack<Memento>();
    }

    public Memento getMemento() {
        return this.mementoStk.pop();
    }

    public void setMemento(Memento memento){
        this.mementoStk.push(memento);
    }
}