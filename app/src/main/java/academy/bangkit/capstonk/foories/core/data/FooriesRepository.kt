package academy.bangkit.capstonk.foories.core.data

import academy.bangkit.capstonk.foories.core.util.Mapper
import academy.bangkit.capstonk.foories.core.data.source.local.LocalDataSource
import academy.bangkit.capstonk.foories.core.data.source.remote.RemoteDataSource
import academy.bangkit.capstonk.foories.core.domain.model.Calories
import academy.bangkit.capstonk.foories.core.domain.model.Food
import academy.bangkit.capstonk.foories.core.domain.model.User
import academy.bangkit.capstonk.foories.core.domain.repository.IFooriesRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class FooriesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IFooriesRepository {
    override suspend fun getUserCalories(user: User): Calories {
        val result = remoteDataSource.getUserCaloriesNeed(user)
        return Mapper.calorieResponseToDomain(result)
    }

    override fun getUserTodayFoods(): LiveData<List<Food>> {
        return Transformations.map(localDataSource.getUserTodayFoods()) {
            Mapper.foodEntitiesToDomains(it)
        }
    }

    override fun getUserTodayTotalCalories(): LiveData<Double> {
        return localDataSource.getUserTodayTotalCalories()
    }

    override suspend fun insertFood(food: Food) {
        val foodEntity = Mapper.foodDomainToEntity(food)
        localDataSource.insertFood(foodEntity)
    }
}