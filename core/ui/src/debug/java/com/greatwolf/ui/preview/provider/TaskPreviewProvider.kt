package com.greatwolf.ui.preview.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.greatwolf.models.Task
import com.greatwolf.ui.preview.data.TaskMock

class TaskProvider : PreviewParameterProvider<Task> {
    override val values = sequenceOf(TaskMock.previewTasks[0], TaskMock.previewTasks[1])
}

class ListTasksProvider : PreviewParameterProvider<List<Task>> {
    override val values = sequenceOf(TaskMock.previewTasks)
}