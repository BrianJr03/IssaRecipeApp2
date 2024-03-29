import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.defaultImageResultMemoryCache
import constants.APP_NAME
import okio.Path.Companion.toOkioPath
import java.io.File

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Issa Recipe App 2") {
        // App() - Add Support Later
    }
}
@Suppress("unused")
fun generateImageLoader(): ImageLoader {
    return ImageLoader {
        components {
            setupDefaultComponents()
        }
        interceptor {
            // cache 100 success image result, without bitmap
            defaultImageResultMemoryCache()
            memoryCacheConfig {
                maxSizeBytes(32 * 1024 * 1024) // 32MB
            }
            diskCacheConfig {
                directory(getCacheDir().toOkioPath().resolve("image_cache"))
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}

enum class OperatingSystem {
    Windows, Linux, MacOS, Unknown
}

private val currentOperatingSystem: OperatingSystem
    get() {
        val os = System.getProperty("os.name").lowercase()
        return if (os.contains("win")) {
            OperatingSystem.Windows
        } else if (os.contains("nix") || os.contains("nux") ||
            os.contains("aix")
        ) {
            OperatingSystem.Linux
        } else if (os.contains("mac")) {
            OperatingSystem.MacOS
        } else {
            OperatingSystem.Unknown
        }
    }

private fun getCacheDir() = when (currentOperatingSystem) {
    OperatingSystem.Windows -> File(System.getenv("AppData"), "$APP_NAME/cache")
    OperatingSystem.Linux -> File(System.getProperty("user.home"), ".cache/$APP_NAME")
    OperatingSystem.MacOS -> File(System.getProperty("user.home"), "Library/Caches/$APP_NAME")
    else -> throw IllegalStateException("Unsupported OS")
}