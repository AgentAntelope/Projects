package ArrayList;
///
///  Contents: ArrayList Interface
///  Author:   John Aronis
///  Date:     December 2009
///

import java.util.Iterator ;

public interface MyArrayListInterface<T> {

  public void add(T element) ;

  public void add(int index, T element) ;

  public void set(int index, T element) ;

  public T get(int index) ;

  public void remove(int index) ;

  public boolean contains(T element) ;

  public int indexOf(T element) ;

  public int size() ;

  public Iterator<T> iterator() ;

}

/// End-of-File

