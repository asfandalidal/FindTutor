package com.example.findtutor.presentation.main_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Biotech
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.ripple
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.findtutor.ui.theme.PrimaryColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.findtutor.R
import com.example.findtutor.ui.theme.poppinsRegular

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BrowseTutorsUI(navController: NavController) {
    val tabs = listOf("Subjects", "BISE Grades")
    var selectedTab by remember { mutableStateOf(0) }

    val pagerState = rememberPagerState()
    LaunchedEffect(pagerState.currentPage) {
        selectedTab = pagerState.currentPage
    }

    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 0.dp)
    ) {
        Text(
            text = "Browse Tutors",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontFamily = poppinsRegular,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEachIndexed { index, tab ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            selectedTab = index
                            // Scroll to the selected page
                            CoroutineScope(Dispatchers.Main).launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                ) {
                    Text(
                        text = tab,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (selectedTab == index) Color.Black else Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    if (selectedTab == index) {
                        Box(
                            modifier = Modifier
                                .width(40.dp)
                                .height(2.dp)
                                .background(PrimaryColor)
                        )
                    }
                }
            }
        }

        HorizontalPager(
            count = tabs.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> SubjectsTab(
                    onMathsSelected = {
                        // Navigate to the Maths teacher list screen.
                        navController.navigate("teacher_list/Mirpurkhas") // Use your defined route here.
                    }
                )
                1 -> BISEGradesTab()
            }
        }
    }
}



@Composable
fun SubjectsTab(onMathsSelected: () -> Unit = {}) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // List of subjects (update as needed)
        val subjects = listOf(
            "Maths" to Icons.Default.Calculate,
            "Holy Quran" to Icons.Default.MenuBook,
            "English" to Icons.Default.Language,
            "Physics" to Icons.Default.Numbers,
            "Biology" to Icons.Default.Biotech,
            "Chemistry" to Icons.Default.Science,
        )

        items(subjects) { (subject, icon) ->
            // For the Maths button, trigger the onMathsSelected lambda.
            SubjectButton(
                subject = subject,
                icon = icon,
                onClick = {
                    if (subject.equals("Maths", ignoreCase = true)) {
                        onMathsSelected()
                    } else {
                        // Handle other subjects if needed.
                    }
                }
            )
        }
    }
}

@Composable
fun BISEGradesTab() {
    // Grid layout for BISE Grades buttons
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val grades = listOf(
            "Play Group" to R.drawable.play ,
            "Primary" to R.drawable.p,
            "Middle" to R.drawable.m,
            "High" to R.drawable.high,
        )

        items(grades) { (grade, icon) ->
            GradeButton(grade, icon)
        }
    }
}

@Composable
fun GradeButton(grade: String, icon: Int, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .clickable(
                onClick = { /* Handle click */ },
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            )
            .padding(12.dp)
            .width(IntrinsicSize.Max)

    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = grade,
            modifier = Modifier.size(40.dp),
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(PrimaryColor)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = grade,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}

@Composable
fun SubjectButton(subject: String, icon: ImageVector, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple()
            )
            .padding(12.dp)
            .width(IntrinsicSize.Max)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = subject,
            tint = PrimaryColor,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = subject,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
