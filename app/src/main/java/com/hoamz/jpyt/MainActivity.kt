package com.hoamz.jpyt

import android.media.Image
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.hoamz.jpyt.ui.theme.JPYTTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val tabItems = listOf(
            TabItem(
                title = "Home",
                selectedItem = Icons.Filled.Home,
                unselectedItem = Icons.Outlined.Home
            ),
            TabItem(
                title = "Profile",
                selectedItem = Icons.Filled.Person,
                unselectedItem = Icons.Outlined.Person
            ),
            TabItem(
                title = "Favorite",
                selectedItem = Icons.Filled.Favorite,
                unselectedItem = Icons.Outlined.Favorite
            ),
        )
        setContent {
            JPYTTheme {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .statusBarsPadding()
                ) {
                    var selectTabIndex by remember {
                        mutableStateOf(0)
                    }

                    val pagerState = rememberPagerState {
                        tabItems.size
                    }

                    //nhan vao item -> qua pager
                    LaunchedEffect(key1 = selectTabIndex) {
                        pagerState.scrollToPage(selectTabIndex)
                    }

                    //vuot qua page -> thay doi tab
                    LaunchedEffect(key1 = pagerState.currentPage) {
                        selectTabIndex = pagerState.currentPage
                    }

                    TabRow(selectedTabIndex = selectTabIndex,
                        indicator = {tabPosition ->
                            TabRowDefaults.Indicator(modifier = Modifier.tabIndicatorOffset(currentTabPosition = tabPosition[selectTabIndex]),
                                color = Color.Magenta,
                                height = 1.dp)
                        }) {
                        tabItems.forEachIndexed { index, item ->
                            Tab(
                                selected = (selectTabIndex == index),
                                onClick = {
                                    selectTabIndex = index
                                },
                                text = {
                                    Text(text = item.title)
                                },
                                icon = {
                                    Icon(imageVector = if(selectTabIndex == index) item.selectedItem else item.unselectedItem,
                                        contentDescription = null)
                                }
                            )
                        }
                    }
                    HorizontalPager(state = pagerState,
                        modifier = Modifier.fillMaxWidth().weight(1f)) {index ->
                        Box (
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Text(text = tabItems[index].title)
                        }
                    }
                }
            }
        }
    }
}

data class TabItem(
    val title : String,
    val selectedItem : ImageVector,
    val unselectedItem : ImageVector
)


