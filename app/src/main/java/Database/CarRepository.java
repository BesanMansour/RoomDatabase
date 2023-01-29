package Database;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public class CarRepository {
    private CarDao carDao;
    private LiveData<List<Car>> AllCar;

    public CarRepository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        carDao =  db.carDao();
        AllCar = carDao.AllCar();
    }
    void InsertCar(Car car){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() { carDao.InsertCar(car); }
        });
    }

    void deleteCar(int id){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                carDao.deleteCar(id);
            }
        });
    }

    LiveData<List<Car>> AllCar(){
        return carDao.AllCar();
    }


    void UpdateCar(Car car){
        MyRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                carDao.UpdateCar(car);
            }
        });
    }

}


