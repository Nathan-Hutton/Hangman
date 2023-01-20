
public class ArraySet implements Set {
	public static final int DEFAULT_CAPACITY = 15;
	public static final int CAPACITY_MULTIPLIER = 2;

	private int capacity = 0;
	private int numberOfItems;
	private Object[] items;

	public ArraySet()
	{
		items = new Object[DEFAULT_CAPACITY];
		this.capacity = DEFAULT_CAPACITY;
	}
	public ArraySet(int capacity)
	{
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity must be >= 0");
		}
		this.capacity = capacity;
		items = new Object[capacity];
	}

	@Override
	public void add(Object element) {
		ensureCapacity();
		if(!(contains(element))&&(element != null))
		{
			items[numberOfItems] = element;
			numberOfItems++;
		}
	}

	@Override
	public void addAll(Object[] elements) {
		for(int i = 0; i < elements.length; i++)
		{
			this.add(elements[i]);
		}
	}

	@Override
	public boolean contains(Object element) {
		for(int i = 0; i < items.length; i++)
		{
			if(items[i] == null)
			{
				return false;
			}
			if(items[i].equals(element))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int getSize() {
		return numberOfItems;
		//return items.length returns the same results
	}

	@Override
	public void remove(Object element) {
		for(int i = 0; i < numberOfItems; i++)
		{
			if(items[i].equals(element))
			{
				items[i] = items[numberOfItems - 1];
				items[numberOfItems - 1] = null;
				numberOfItems--;
				return;
			}
		}
	}

	@Override
	public Set union(Set anotherSet) {
		Set newSet = anotherSet.difference(this);
		newSet.addAll(items);
		return newSet;
	}

	@Override
	public Set intersection(Set anotherSet) {
		Set newSet = new ArraySet();
		for(int i = 0; i < numberOfItems; i++)
		{
			if(anotherSet.contains(items[i]))
			{
				newSet.add(items[i]);
			}
		}
		return newSet;
	}

	@Override
	public Set difference(Set anotherSet) {
		Set newSet = new ArraySet();
		for(int i = 0; i < getSize(); i++)
		{
			if(!(anotherSet.contains(items[i])))
			{
				newSet.add(items[i]);
			}
		}
		return newSet;
	}
	private void ensureCapacity() {
		if (numberOfItems == capacity) {
			Object[] newArray = new Object[(numberOfItems+1) * CAPACITY_MULTIPLIER];
			System.arraycopy(items,0,newArray,0,numberOfItems);
			items = newArray;
		}
	}
}
