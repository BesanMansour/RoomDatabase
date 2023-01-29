package Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CarViewModel extends AndroidViewModel {
    CarRepository carRepository;

    public CarViewModel(@NonNull Application application) {
        super(application);
        carRepository = new CarRepository(application);
    }

    public void InsertCar(Car car) {
        carRepository.InsertCar(car);
    }

    public void deleteCar(int id) {
        carRepository.deleteCar(id);
    }

    public LiveData<List<Car>> allCar() {
        return carRepository.AllCar();
    }
    public void UpdateCar(Car car) {
        carRepository.UpdateCar(car);
    }

}

