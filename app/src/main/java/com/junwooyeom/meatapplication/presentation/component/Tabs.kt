package com.junwooyeom.meatapplication.presentation.component

import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.junwooyeom.meatapplication.presentation.view.HomeSections

@Composable
fun MeatTopTabs(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
) {
    val currentSection = tabs.first { it.route == currentRoute }

    Surface {
        TabRow(selectedTabIndex = currentSection.ordinal) {
            tabs.forEachIndexed { index, homeSections ->
                Tab(selected = currentSection.ordinal == index, onClick = {
                    navigateToRoute(homeSections.route)
                }, text = {
                    Text(text = stringResource(id = homeSections.title))
                }, icon = {
                    Icon(imageVector = homeSections.icon, contentDescription = null)
                })
            }
        }
    }
}
