package com.greatwolf.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.greatwolf.models.Project
import com.greatwolf.models.Task
import com.greatwolf.ui.component.CustomIconButton
import com.greatwolf.ui.component.ProjectCard
import com.greatwolf.ui.component.SliderIndicator
import com.greatwolf.ui.component.SwitchMenu
import com.greatwolf.ui.component.TaskCard
import com.greatwolf.ui.preview.data.HomePreviewMock
import com.greatwolf.ui.preview.provider.HomePreviewProvider
import com.greatwolf.ui.theme.BodyXSmallTextStyleNormal
import com.greatwolf.ui.theme.Neutral200
import com.greatwolf.ui.theme.Neutral50
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Primary200
import com.greatwolf.ui.theme.Primary300
import com.greatwolf.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel = koinViewModel(),
) {
    val state by vm.state.collectAsStateWithLifecycle()
    val event by vm.event.collectAsStateWithLifecycle(HomeEvent.Idle)

    HomeContent(
        state = state,
        onIntent = { intent ->
            vm.onIntent(intent)
        }
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onIntent: (HomeIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WelcomeBlock(
            name = "Artem", //TEST
            onClickNotification = {}
        )
        Spacer(modifier = Modifier.size(20.dp))
        // Searchbar
        Spacer(modifier = Modifier.size(20.dp))
        ProjectBlock(listProjects = state.listProjects)
        Spacer(modifier = Modifier.size(20.dp))
        ToDoBlock(listTasks = state.listTasks)
    }
}

@Composable
private fun WelcomeBlock(
    name: String,
    onClickNotification: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.wrapContentSize()
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.welcome_back, name),
                style = BodyXSmallTextStyleNormal,
                color = if (isSystemInDarkTheme()) Neutral200 else Neutral500
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                modifier = Modifier,
                text = stringResource(R.string.ready_conquer_day),
                style = Typography.labelMedium,
                color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
            )
        }
        CustomIconButton(
            icon = ImageVector.vectorResource(com.greatwolf.ui.R.drawable.ic_notification),
            onClick = onClickNotification
        )
    }
}

@Composable
private fun TitleBlock(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier,
            text = title,
            style = Typography.bodyMedium,
            color = if (isSystemInDarkTheme()) Neutral50 else Neutral700
        )
        Text(
            modifier = Modifier.clickable(
                onClick = onClick
            ),
            text = stringResource(R.string.see_all),
            style = BodyXSmallTextStyleNormal,
            color = if (isSystemInDarkTheme()) Primary200 else Primary300
        )
    }
}

@Composable
private fun ProjectBlock(
    listProjects: List<Project>
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleBlock(
            title = stringResource(R.string.your_project)
        ) { }
        Spacer(modifier = Modifier.size(12.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            pageSpacing = 16.dp
        ) { page ->
            ProjectCard(
                data = listProjects[page]
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        SliderIndicator(pagerState = pagerState)
    }
}

@Composable
private fun ToDoBlock(
    listTasks: List<Task>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TitleBlock(
            title = stringResource(R.string.to_do_list)
        ) { }
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SwitchMenu(
                text = stringResource(R.string.all_task),
                selected = true // Test
            ) { }
            SwitchMenu(
                text = stringResource(R.string.today),
                selected = false // Test
            ) { }
            SwitchMenu(
                text = stringResource(R.string.ongoing),
                selected = false // Test
            ) { }
            SwitchMenu(
                text = stringResource(R.string.completed),
                selected = false // Test
            ) { }
        }
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listTasks) { task ->
                TaskCard(task) { }
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomePreviewProvider::class) homeState: HomePreviewMock.HomeState
) {
    val state = HomeUiState(
        listProjects = homeState.listProjects,
        listTasks = homeState.listTasks
    )
    HomeContent(
        state = state,
        onIntent = { },
    )
}