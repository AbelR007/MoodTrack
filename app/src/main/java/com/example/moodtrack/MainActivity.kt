package com.example.moodtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodtrack.ui.theme.MoodTrackTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoodTrackTheme {
                MoodTrackApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodTrackApp() {
    MoodTrackScreen()
}

@Composable
fun MoodTrackScreen() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color(0xFFDCDDDA),
        drawerContent = { Text("Drawer content") },
        bottomBar = {
//            BottomBar()
            BottomAppBar(
                elevation = 10.dp,
                backgroundColor = Color(0xFFFFFFFFF)
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }
                ) {
                    Icon(Icons.Filled.Menu, contentDescription = "....")
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*do something*/ },
                backgroundColor = Color.DarkGray,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(6.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Localized description")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        content = { innerPadding ->
            Text("JO")
        }
    )
}

@Composable
fun BottomBar() {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(
        elevation = 10.dp,
        backgroundColor = Color(0xFFFFFFFFF),
        contentColor = Color.Black
    ) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Outlined.Menu,"")
        },
            label = { Text(text = "Calendar") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.ArrowDropDown,"")
        },
            label = { Text(text = "Mood") },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Person,"")
        },
            label = { Text(text = "Profile") },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    }
}

