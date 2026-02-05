package com.greatwolf.ui.preview.data

import com.greatwolf.models.Task

object TaskMock {
    val previewTasks = listOf(
        Task(
            id = "0",
            title = "Complete Website Redesign",
            description = "Redesign Project",
            completed = false,
            date = "Today",
            time = "2:00 PM"
        ),
        Task(
            id = "1",
            title = "Marketing Campaign Launch",
            description = "Campaign Launch",
            completed = true,
            date = "January 20, 2024",
            time = "10:30 AM"
        ),
        Task(
            id = "2",
            title = "Client Meeting Preparation",
            description = "Client Meeting",
            completed = false,
            date = "February 8, 2024",
            time = "11:00 AM"
        ),
        Task(
            id = "3",
            title = "Budget Proposal Submission",
            description = "Budget Proposal",
            completed = false,
            date = "March 1, 2024",
            time = "7:00 PM"
        ),
        Task(
            id = "4",
            title = "Content Creation for Social Media",
            description = "Marketing Campaign",
            completed = false,
            date = "April 1, 2024",
            time = "9:00 PM"
        ),
        Task(
            id = "5",
            title = "Code Review and Debugging",
            description = "Software Development",
            completed = false,
            date = "April 12, 2024",
            time = "8:00 AM"
        )
    )
}