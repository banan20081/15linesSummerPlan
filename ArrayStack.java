import java.util.List;
import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * Simple Stack using ArrayList
 * 
 * @author Banan Badran
 * @since 2/26/2025 
 */
 
public class ArrayStack<E> implements Stack<E> {
	private List<E> theStack;
	
	public ArrayStack(){
		theStack = new ArrayList<E>();
	}
	
	/** @return 		true if stack is empty ; false otherwise */
	public boolean isEmpty(){return theStack.isEmpty();}
	
	/** @return thr top element on the stack */
	public E peek(){
		if(theStack.isEmpty())
			throw new EmptyStackException();
		return theStack.get(theStack.size() -1);
	}
	
	/** @param obj	 the object to push onto the top of the stack */
	public void push(E obj){ theStack.add(obj); }
	
	/** @return 		the obj removed frm the top of the stack */
	public E pop(){
		if(theStack.isEmpty())
			throw new EmptyStackException();
		return theStack.remove(theStack.size()-1);
	}
}
