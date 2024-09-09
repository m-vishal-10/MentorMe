package com.vishal.mentorme

object MentorRepository {

    private val mentors = mutableMapOf(
        // Existing Mentors
        "1" to Mentor(
            id = "1",
            name = "JP Gandhi",
            profession = "Career Guidance",
            rating = 4.5f,
            available_dates = mapOf(
                "2024-09-10" to listOf("10:00 AM", "11:00 AM", "2:00 PM"),
                "2024-09-11" to listOf("1:00 PM", "3:00 PM")
            )
        ),
        "2" to Mentor(
            id = "2",
            name = "Alice Johnson",
            profession = "Artificial Intelligence",
            rating = 4.8f,
            available_dates = mapOf(
                "2024-09-12" to listOf("9:00 AM", "11:00 AM"),
                "2024-09-14" to listOf("2:00 PM", "4:00 PM")
            )
        ),
        "3" to Mentor(
            id = "3",
            name = "John Smith",
            profession = "Machine Learning",
            rating = 4.2f,
            available_dates = mapOf(
                "2024-09-15" to listOf("10:00 AM", "1:00 PM"),
                "2024-09-16" to listOf("11:00 AM", "3:00 PM")
            )
        ),
        "4" to Mentor(
            id = "4",
            name = "Mark Brown",
            profession = "Android Development",
            rating = 3.9f,
            available_dates = mapOf(
                "2024-09-17" to listOf("9:00 AM", "12:00 PM"),
                "2024-09-19" to listOf("1:00 PM", "4:00 PM")
            )
        ),

        // New Mentors
        "5" to Mentor(
            id = "5",
            name = "Sophia Lee",
            profession = "Digital Marketing",
            rating = 4.7f,
            available_dates = mapOf(
                "2024-09-18" to listOf("10:00 AM", "3:00 PM"),
                "2024-09-20" to listOf("12:00 PM", "2:00 PM")
            )
        ),
        "6" to Mentor(
            id = "6",
            name = "David Kim",
            profession = "Cybersecurity",
            rating = 4.6f,
            available_dates = mapOf(
                "2024-09-21" to listOf("9:00 AM", "11:00 AM"),
                "2024-09-23" to listOf("1:00 PM", "4:00 PM")
            )
        ),
        "7" to Mentor(
            id = "7",
            name = "Emily Davis",
            profession = "Financial Analysis",
            rating = 4.9f,
            available_dates = mapOf(
                "2024-09-24" to listOf("10:00 AM", "2:00 PM"),
                "2024-09-26" to listOf("11:00 AM", "3:00 PM")
            )
        ),
        "8" to Mentor(
            id = "8",
            name = "Chris Evans",
            profession = "UI/UX Design",
            rating = 4.3f,
            available_dates = mapOf(
                "2024-09-27" to listOf("9:00 AM", "12:00 PM"),
                "2024-09-29" to listOf("2:00 PM", "4:00 PM")
            )
        ),
        "9" to Mentor(
            id = "9",
            name = "Olivia Martinez",
            profession = "Content Creation",
            rating = 4.4f,
            available_dates = mapOf(
                "2024-09-28" to listOf("10:00 AM", "1:00 PM"),
                "2024-09-30" to listOf("3:00 PM", "5:00 PM")
            )
        ),
        "10" to Mentor(
            id = "10",
            name = "James Wilson",
            profession = "Project Management",
            rating = 4.1f,
            available_dates = mapOf(
                "2024-10-01" to listOf("11:00 AM", "2:00 PM"),
                "2024-10-03" to listOf("1:00 PM", "4:00 PM")
            )
        ),
        "11" to Mentor(
            id = "11",
            name = "Ava Johnson",
            profession = "Data Science",
            rating = 4.8f,
            available_dates = mapOf(
                "2024-10-02" to listOf("10:00 AM", "3:00 PM"),
                "2024-10-04" to listOf("9:00 AM", "1:00 PM")
            )
        ),
        "12" to Mentor(
            id = "12",
            name = "Liam Brown",
            profession = "Software Engineering",
            rating = 4.5f,
            available_dates = mapOf(
                "2024-10-05" to listOf("12:00 PM", "2:00 PM"),
                "2024-10-07" to listOf("10:00 AM", "4:00 PM")
            )
        ),
        "13" to Mentor(
            id = "13",
            name = "Charlotte Smith",
            profession = "Blockchain Technology",
            rating = 4.7f,
            available_dates = mapOf(
                "2024-10-08" to listOf("11:00 AM", "1:00 PM"),
                "2024-10-10" to listOf("3:00 PM", "5:00 PM")
            )
        ),
        "14" to Mentor(
            id = "14",
            name = "Ethan Harris",
            profession = "Cloud Computing",
            rating = 4.6f,
            available_dates = mapOf(
                "2024-10-09" to listOf("10:00 AM", "2:00 PM"),
                "2024-10-11" to listOf("12:00 PM", "4:00 PM")
            )
        ),
        "15" to Mentor(
            id = "15",
            name = "Mia Wilson",
            profession = "Network Engineering",
            rating = 4.4f,
            available_dates = mapOf(
                "2024-10-12" to listOf("9:00 AM", "11:00 AM"),
                "2024-10-14" to listOf("1:00 PM", "3:00 PM")
            )
        )
    )

    fun getMentorById(id: String): Mentor? {
        return mentors[id]
    }

    fun getAllMentors(): List<Mentor> {
        return mentors.values.toList()
    }

    fun addMentor(mentor: Mentor) {
        mentors[mentor.id] = mentor
    }

    fun removeMentor(id: String) {
        mentors.remove(id)
    }
}
