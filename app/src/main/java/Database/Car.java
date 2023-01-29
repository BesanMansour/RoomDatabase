package Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "car_table")
public class Car implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String car_name ;
    public String car_color;
    public int car_year;

    public Car() {
    }

    public Car(@NonNull String car_name, String car_color, int car_year) {
        this.car_name = car_name;
        this.car_color = car_color;
        this.car_year = car_year;
    }

    @NonNull
    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(@NonNull String car_name) {
        this.car_name = car_name;
    }

    public String getCar_color() {
        return car_color;
    }

    public void setCar_color(String car_color) {
        this.car_color = car_color;
    }

    public int getCar_year() {
        return car_year;
    }

    public void setCar_year(int car_year) {
        this.car_year = car_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
