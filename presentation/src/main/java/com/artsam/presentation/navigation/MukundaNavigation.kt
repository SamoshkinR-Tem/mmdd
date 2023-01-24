/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.artsam.presentation.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.artsam.presentation.navigation.MukundaDestinationsArgs.PICTURE_ID_ARG
import com.artsam.presentation.navigation.MukundaScreens.STATISTICS_SCREEN
import com.artsam.presentation.navigation.MukundaScreens.PICTURES_SCREEN
import com.artsam.presentation.navigation.MukundaScreens.PICTURE_DETAIL_SCREEN

/**
 * Screens used in [MukundaDestinations]
 */
private object MukundaScreens {
    const val PICTURES_SCREEN = "pictures"
    const val STATISTICS_SCREEN = "statistics"
    const val PICTURE_DETAIL_SCREEN = "picture"
}

/**
 * Arguments used in [MukundaDestinations] routes
 */
object MukundaDestinationsArgs {
    const val PICTURE_ID_ARG = "taskId"
    const val TITLE_ARG = "title"
}

/**
 * Destinations used in the [MainActivity]
 */
object MukundaDestinations {
    const val HOME_ROUTE = "$PICTURES_SCREEN?"
    const val STATISTICS_ROUTE = STATISTICS_SCREEN
    const val PICTURE_DETAILS_ROUTE = "$PICTURE_DETAIL_SCREEN/{$PICTURE_ID_ARG}"
}

/**
 * Models the navigation actions in the app.
 */
class MukundaNavigationActions(private val navController: NavHostController) {

    fun navigateToTasks(userMessage: Int = 0) {
        val navigatesFromDrawer = userMessage == 0
        navController.navigate(PICTURES_SCREEN) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = !navigatesFromDrawer
                saveState = navigatesFromDrawer
            }
            launchSingleTop = true
            restoreState = navigatesFromDrawer
        }
    }

    fun navigateToStatistics() {
        navController.navigate(MukundaDestinations.STATISTICS_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }

    fun navigateToPictureDetails(taskId: String) {
        navController.navigate("$PICTURE_DETAIL_SCREEN/$taskId")
    }
}
