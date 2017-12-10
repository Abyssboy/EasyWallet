package com.example.poomer555.easywallet.Model;

/**
 * Created by poome on 12/10/2017.
 */

public class Item {
    public final int Id;
    public final String Name;
    public final String Type;
    public final int Much;


    public Item(int id, String name, String type, int much) {
        Id = id;
        Name = name;
        Type = type;
        Much = much;
    }

    @Override
    public String toString() {
       return Name;
    }
}
