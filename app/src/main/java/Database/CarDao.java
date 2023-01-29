package Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CarDao {
    @Insert
    void InsertCar(Car car);

    @Update
    void UpdateCar(Car car);

    @Query("DELETE FROM car_table WHERE id = :id")
    void deleteCar(int id);

    @Query("SELECT * FROM car_table")
    LiveData<List<Car>> AllCar();
}




















