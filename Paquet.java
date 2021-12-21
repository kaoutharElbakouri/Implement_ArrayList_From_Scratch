import java.util.*;

public class Paquet<T> implements Iterable<T>{
	private int size;
	private T[] tab;
	public Iterator<T> iterator(){
		return new Iterator<T>() {
			int currentIndex=0;
			@Override
			public boolean hasNext() {
				return currentIndex<size;
			}
			@Override
			public T next(){
				if(!hasNext()) throw new NoSuchElementException();
				return tab[currentIndex++];
			}
			@Override
			public void remove() {
				Paquet.this.remove(--currentIndex);
			}
		};
	}
	@SuppressWarnings("unchecked")
	public Paquet() {
		size=0;
		tab=(T[]) new Object[10];
	}
	
	public void add(T e) {
		if(size==tab.length) {
			tab=Arrays.copyOf(tab,size*2+1);
		}
		tab[size++]=e;
	}
	public void add(int index,T item) {
		if(size==tab.length) {
			tab=Arrays.copyOf(tab,size*2+1);
		}
	    for (int i = size; i > index; i--) {
	         tab[i] = tab[i - 1];
	        }
        tab[index] = item;
        size++;
		
		
	}
	public void remove(int index) {
		if(index<0 || index>=size) {
			System.out.println("\nwe can not remove element maybe because index "+index+" is out of Bounds.");
		}
		else {
		for(int i=index;i<size-1;i++) {
			tab[i]=tab[i+1];
		}
		size--;}}
	
	public int getSize() {
		return size;
	}
	public T get(int index) throws Exception {
		if(index<0 || index>=size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return tab[index];
	}
	public String toString() {
		String s="Les elements de notre Collection sont :";
		Iterator<T> it = this.iterator();
		while(it.hasNext()) {
			s=s+" "+it.next();
		}
		return s;
	}
	 public boolean isEmpty() {
	     return size == 0;
	    }
    public T set(int index, T newVal) {
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
        T old = tab[index];
        tab[index] = newVal;
        return old;
    }
	public static void main(String[] args) {
		Paquet<Integer> p =new Paquet<Integer>();
		try {
		p.add(10);
		p.add(20);
		p.add(30);
		p.add(40);
		p.add(50);
		System.out.println(p.get(0));
		Iterator<Integer> it2 = p.iterator();
		while(it2.hasNext()) {
			System.out.print(it2.next()+" ");
		}
		p.remove(4);
		System.out.println();
		Iterator<Integer> it = p.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		System.out.println();
		System.out.println(p.toString());
		}catch(Exception e) {
			e.getMessage();
		}
	}
	
}
