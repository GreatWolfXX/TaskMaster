package com.greatwolf.ui.preview.data

import com.greatwolf.models.Project

object ProjectMock {
    val previewProjects = listOf(
        Project(
            id = "0",
            priority = 2,
            title = "E-commerce Platform Redesign - NovaShop",
            description = "Overhauling the user interface design of NovaShop, our e-commerce platform, for a modern and",
            progress = 70,
            date = "January 30, 2024",
            color = 0xFF0041AA
        ),
        Project(
            id = "1",
            priority = 0,
            title = "UI Enhancement Redesign - SmartHome Control Panel",
            description = "Upgrading the user interface of the SmartHome control panel, ensuring a seamless and user",
            progress = 40,
            date = "March 4, 2024",
            color = 0xFF00AA81
        ),
        Project(
            id = "2",
            priority = 1,
            title = "Techno Market Website Redesign - StellarTech",
            description = "Transforming the user interface and experience of the StellarTech website to align with the latest",
            progress = 90,
            date = "February 24, 2024",
            color = 0xFF0081AA
        )
    )
}