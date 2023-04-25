import androidx.annotation.Keep
import com.artsam.data.repo.PaintingsRepository
import org.koin.dsl.module

@Keep
internal val repo = module {
    single { PaintingsRepository(api = lazy { get() }) }
}