package com.greatwolf.ui.preview.data

import com.greatwolf.models.Project
import com.greatwolf.models.Task

object HomePreviewMock {
    data class HomeState(
        val listProjects: List<Project> = ProjectMock.previewProjects,
        val listTasks: List<Task> = TaskMock.previewTasks
    )
}