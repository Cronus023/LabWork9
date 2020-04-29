package entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;

public class ListOfIdentifiables<T extends Identifiable & Serializable> implements Serializable {
	private static final long serialVersionUID = -4621472982618921772L;

	protected HashSet<T> items = new HashSet<T>();
    private transient Integer nextId;

    public ListOfIdentifiables() {
    	// start value of the next free id - 1 
        nextId = 1;
    }
    
    

    //read from stream object, auto  method
    @SuppressWarnings("unchecked")
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        items = (HashSet<T>)in.readObject();
        nextId = 0;
        //find max id
        for(T item : items) {
            final Integer itemId = item.getId();
            if(itemId > nextId)
                nextId = itemId;
        }
        //free id 
        nextId++;
    }
    //write to stream object, auto method
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.writeObject(items);
    }
    
    protected int getNextId() {
        return nextId++;
    }

}
