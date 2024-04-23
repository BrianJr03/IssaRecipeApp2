package jr.brian.issarecipeapp2

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import blocs.RootComponent
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.defaultImageResultMemoryCache
import com.seiko.imageloader.option.androidContext
import jr.brian.shared.database.AppDatabase
import models.local.DatabaseDriver
import okio.Path.Companion.toOkioPath

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = retainedComponent {
            RootComponent(it)
        }

        setContent {
            val driver = DatabaseDriver(applicationContext).createDriver()
            val appDatabase = AppDatabase(driver)

            CompositionLocalProvider(
                LocalImageLoader provides remember { generateImageLoader() }
            ) {
                App(
                    root = root,
                    appDatabase = appDatabase
                )
            }
        }
    }

    private fun generateImageLoader(): ImageLoader {
        return ImageLoader {
            options {
                androidContext(applicationContext)
            }
            components {
                setupDefaultComponents()
            }
            interceptor {
                // cache 100 success image result, without bitmap
                defaultImageResultMemoryCache()
                memoryCacheConfig {
                    // Set the max size to 25% of the app's available memory.
                    maxSizePercent(applicationContext, 0.25)
                }
                diskCacheConfig {
                    directory(
                        applicationContext.cacheDir.resolve(
                            relative = "image_cache"
                        ).toOkioPath()
                    )
                    maxSizeBytes(512L * 1024 * 1024) // 512MB
                }
            }
        }
    }
}