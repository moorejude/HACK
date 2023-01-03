package com.game.hack3.Items;

//Generic class that can create items and Get() that item.
public class Item<T>{
    private final T t;
    public String Name;

    public Item(T item, String name) {
        this.t = item;
        this.Name = String.valueOf(name);
    }

    public T Get() {
        return t;
    }

    public String GetName(){
        return Name;
    }

}
