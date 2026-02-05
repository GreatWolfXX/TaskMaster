package com.greatwolf.ui.preview.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greatwolf.models.Project
import com.greatwolf.ui.preview.data.ProjectMock

class ProjectProvider : PreviewParameterProvider<Project> {
    override val values = ProjectMock.previewProjects.asSequence()
}

class ListProjectsProvider : PreviewParameterProvider<List<Project>> {
    override val values = sequenceOf(ProjectMock.previewProjects)
}