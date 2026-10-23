package com.habib.portfolio.data

data class Skill(val name: String, val level: Float, val category: String)
data class Project(
    val title: String,
    val description: String,
    val tech: List<String>,
    val url: String
)

object PortfolioData {
    const val NAME = "Syed Habibullah"
    const val EMAIL = "syedhabibullah704@gmail.com"
    const val PHONE = "+923710187540"
    const val LINKEDIN = "https://www.linkedin.com/in/habib-rizvi-9ab262331/"
    const val GITHUB = "https://github.com/hrizvi172"

    val roles = listOf("Android Developer", "Kotlin & Compose", "Flutter Developer")

    const val ABOUT =
        "BS Software Engineering student at GCU Faisalabad. I build responsive mobile apps " +
                "with Clean Architecture, MVVM and Firebase/Supabase backends, and fix crashes and performance " +
                "issues in live apps as a 5.0★ Upwork freelancer."

    val categories = listOf("All", "Android", "Flutter", "Backend", "Tools")

    // Adjust levels (0f..1f) to what you honestly feel
    val skills = listOf(
        Skill("Kotlin", 0.90f, "Android"),
        Skill("Jetpack Compose", 0.88f, "Android"),
        Skill("MVVM / Clean Architecture", 0.85f, "Android"),
        Skill("Retrofit & REST", 0.82f, "Android"),
        Skill("Room / SQLite", 0.80f, "Android"),
        Skill("StateFlow & ViewModel", 0.85f, "Android"),
        Skill("Flutter & Dart", 0.88f, "Flutter"),
        Skill("Bloc / Provider", 0.80f, "Flutter"),
        Skill("Firebase", 0.85f, "Backend"),
        Skill("Supabase", 0.75f, "Backend"),
        Skill("Git & GitHub", 0.85f, "Tools"),
        Skill("Android Studio", 0.92f, "Tools")
    )

    val projects = listOf(
        Project(
            "Boutyee Messenger",
            "Debugged and completed a social messenger: stories, groups, UI and real-time chat. Live on Google Play.",
            listOf("Flutter", "Real-time", "Firebase"),
            "https://play.google.com/store/apps/details?id=messaging.app.boutye"
        ),
        Project(
            "LocalLink",
            "Offline-first file sharing and device chat over WiFi/hotspot with an embedded local server and browser interface.",
            listOf("Flutter", "Local Server", "WiFi"),
            "https://github.com/hrizvi172/LocalLink"
        ),
        Project(
            "EasyShop",
            "E-commerce app with user and admin modules, role-based access, orders and product management.",
            listOf("Flutter", "Firestore", "Auth"),
            "https://github.com/hrizvi172/Easy_Shop"
        ),
        Project(
            "PennyWise",
            "Expense tracking app for tracking expenses and managing budget, keep track of expenses and see totals",
            listOf("Flutter", "Firestore", "Auth"),
            "https://github.com/hrizvi172/Penny_Wise"
        ),
    )
}
