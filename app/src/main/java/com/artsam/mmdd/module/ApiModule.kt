import androidx.annotation.Keep
import com.artsam.data.api.PaintingsApi
import com.artsam.data.utils.DateUtils.DATE_TIME_SHORT_TIME_ZONE_FORMAT
import com.google.gson.GsonBuilder
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Keep
internal val apis = module {

    single<PaintingsApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://github.com/SamoshkinR-Tem/mmdd.pictures/")
            .addConverterFactory(createGsonConverterFactory())
            .build()
        retrofit.create(PaintingsApi::class.java)
    }
}

private fun createGsonConverterFactory(): GsonConverterFactory {
    val gson = GsonBuilder()
        .setDateFormat(DATE_TIME_SHORT_TIME_ZONE_FORMAT)
        .setLenient()
        .create()
    return GsonConverterFactory.create(gson)
}