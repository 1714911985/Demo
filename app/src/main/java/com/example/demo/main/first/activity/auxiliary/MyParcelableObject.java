package com.example.demo.main.first.activity.auxiliary;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelableObject implements Parcelable {
    private int id;
    private String name;

    public MyParcelableObject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MyParcelableObject() {
    }

    @Override
    public String toString() {
        return "MyParcelableObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected MyParcelableObject(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MyParcelableObject> CREATOR = new Creator<MyParcelableObject>() {
        @Override
        public MyParcelableObject createFromParcel(Parcel in) {
            return new MyParcelableObject(in);
        }

        @Override
        public MyParcelableObject[] newArray(int size) {
            return new MyParcelableObject[size];
        }
    };
}
