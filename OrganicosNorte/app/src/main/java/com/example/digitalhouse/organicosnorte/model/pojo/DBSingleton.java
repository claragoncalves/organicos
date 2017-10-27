package com.example.digitalhouse.organicosnorte.model.pojo;

import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Created by digitalhouse on 10/27/17.
 */

public class DBSingleton {
    public static DBRoom instance = null;

    protected DBSingleton(){}

    public static DBRoom getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context,
                    DBRoom.class, "productos_db").allowMainThreadQueries().build();
        }
        return instance;
    }

}
