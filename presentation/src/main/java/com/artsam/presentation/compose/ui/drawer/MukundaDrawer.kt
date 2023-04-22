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
package com.artsam.presentation.compose.ui.drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artsam.presentation.R
import com.artsam.presentation.compose.ui.theme.primaryDarkColor
import com.artsam.presentation.navigation.MukundaDestinations
import com.artsam.presentation.navigation.MukundaNavigationActions
import com.google.accompanist.appcompattheme.AppCompatTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppModalDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navigationActions: MukundaNavigationActions,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    content: @Composable () -> Unit
) {
    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = currentRoute,
                navigateToTasks = { navigationActions.navigateToTasks() },
                navigateToStatistics = { navigationActions.navigateToStatistics() },
                closeDrawer = { coroutineScope.launch { drawerState.close() } }
            )
        }
    ) {
        content()
    }
}

@Composable
private fun AppDrawer(
    currentRoute: String,
    navigateToTasks: () -> Unit,
    navigateToStatistics: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        DrawerHeader()
        DrawerButton(
            painter = painterResource(id = R.drawable.ic_list),
            label = stringResource(R.string.ui_pictures_title),
            isSelected = currentRoute == MukundaDestinations.HOME_ROUTE,
            action = {
                navigateToTasks()
                closeDrawer()
            }
        )
        DrawerButton(
            painter = painterResource(id = R.drawable.ic_statistics),
            label = stringResource(id = R.string.ui_statistics_title),
            isSelected = currentRoute == MukundaDestinations.STATISTICS_ROUTE,
            action = {
                navigateToStatistics()
                closeDrawer()
            }
        )
    }
}

@Composable
private fun DrawerHeader(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(primaryDarkColor)
            .height(dimensionResource(id = R.dimen.ui_header_height))
            .padding(dimensionResource(id = R.dimen.ui_header_padding))
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_mukunda_mohini),
            contentDescription = stringResource(R.string.ui_pictures_header_image_content_desc),
            modifier = Modifier.width(dimensionResource(id = R.dimen.ui_header_image_width))
        )
        Text(
            text = stringResource(id = R.string.ui_pictures),
            color = MaterialTheme.colors.surface
        )
    }
}

@Composable
private fun DrawerButton(
    painter: Painter,
    label: String,
    isSelected: Boolean,
    action: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tintColor = if (isSelected) {
        MaterialTheme.colors.secondary
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.6f)
    }

    TextButton(
        onClick = action,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.ui_def_margin))
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painter,
                contentDescription = null, // decorative
                tint = tintColor
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = tintColor
            )
        }
    }
}

/**
 * Use [AppCompatTheme] if you need to use legacy or .xml
 * but it doesn't work with the Compose @Preview("Class contents")
 * To make the preview work - use  [MaterialTheme]
 *
 * @see <a href="https://developer.android.com/jetpack/compose/designsystems/views-to-compose">developer.android.com</a>
 *
 * @see <a href="https://habr.com/ru/companies/surfstudio/articles/570994/">habr.com</a>
 *
 */
@Preview("Drawer contents")
@Composable
fun PreviewAppDrawer() {
    MaterialTheme {
        Surface {
            AppDrawer(
                currentRoute = MukundaDestinations.HOME_ROUTE,
                navigateToTasks = {},
                navigateToStatistics = {},
                closeDrawer = {}
            )
        }
    }
}
