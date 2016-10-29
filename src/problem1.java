import java.util.Collection;


public interface problem1 {

	public void add(Object o);
	public void add(int index, Object o);
	public void addAll(Collection c);
	public void clear();
	public boolean contains(Object o);
	public Object get(int Index);
	public int indexOf(Object o);
	public boolean isEmpty();
	public int lastIndexOf(Object o) ;
	public Object remove(int Index);
	public boolean remove(Object o);
	public void removeAll(Collection c);
	public void removeRange(int fromindex, int toindex);
	public void retainAll(Collection c) ;
	public void set(int index, Object o);
	public int size();
	public Collection subList(int fromindex, int toindex);
	
	
}
