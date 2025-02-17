package com.sduduzog.slimlauncher.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.lifecycle.LifecycleCoroutineScope
import com.jkuester.unlauncher.datastore.CorePreferences
import com.jkuester.unlauncher.datastore.QuickButtonPreferences
import com.jkuester.unlauncher.datastore.UnlauncherApps
import com.sduduzog.slimlauncher.datasource.apps.UnlauncherAppsMigrations
import com.sduduzog.slimlauncher.datasource.apps.UnlauncherAppsRepository
import com.sduduzog.slimlauncher.datasource.apps.UnlauncherAppsSerializer
import com.sduduzog.slimlauncher.datasource.coreprefs.CorePreferencesRepository
import com.sduduzog.slimlauncher.datasource.coreprefs.CorePreferencesSerializer
import com.sduduzog.slimlauncher.datasource.quickbuttonprefs.QuickButtonPreferencesMigrations
import com.sduduzog.slimlauncher.datasource.quickbuttonprefs.QuickButtonPreferencesRepository
import com.sduduzog.slimlauncher.datasource.quickbuttonprefs.QuickButtonPreferencesSerializer

private val Context.quickButtonPreferencesStore: DataStore<QuickButtonPreferences> by dataStore(
    fileName = "quick_button_preferences.proto",
    serializer = QuickButtonPreferencesSerializer,
    produceMigrations = { context -> QuickButtonPreferencesMigrations().get(context) }
)

private val Context.unlauncherAppsStore: DataStore<UnlauncherApps> by dataStore(
    fileName = "unlauncher_apps.proto",
    serializer = UnlauncherAppsSerializer,
    produceMigrations = { context -> UnlauncherAppsMigrations().get(context) }
)

private val Context.corePreferencesStore: DataStore<CorePreferences> by dataStore(
    fileName = "core_preferences.proto",
    serializer = CorePreferencesSerializer
)

class UnlauncherDataSource(context: Context, lifecycleScope: LifecycleCoroutineScope) {
    val quickButtonPreferencesRepo =
        QuickButtonPreferencesRepository(context.quickButtonPreferencesStore, lifecycleScope)
    val unlauncherAppsRepo = UnlauncherAppsRepository(context.unlauncherAppsStore, lifecycleScope)
    val corePreferencesRepo =
        CorePreferencesRepository(context.corePreferencesStore, lifecycleScope)
}
