package com.vishal.mentorme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

//region home page
@Composable
fun StudentDashboard(navController: NavController) {

    var selectedTitle by remember { mutableStateOf("Booked classes") }

    Scaffold(
        bottomBar = {
            StudentBottomBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE0E0E0)) // Pale gray
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Hello",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    // text = DataManager.currentUser?.name ?: "User"
                    text = "VishalBhalaje",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF004D40) // Dark teal
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle bell icon click */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bell),
                        contentDescription = "Notifications",
                        Modifier.size(24.dp)
                    )
                }
            }

            Text(
                text = "Ready for mentorship? Let's begin the journey.",
                fontSize = 15.sp,
                color = Color(0xFF004D40) // Dark teal
            )
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            // Navigation Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NavigationCard("Booked classes", count = 0, onCardClick = { selectedTitle = it },Modifier.weight(1f))
                NavigationCard("Progress Check", count = 0, onCardClick = { selectedTitle = it },Modifier.weight(1f))
                NavigationCard("Job Suggestions", count = 0, onCardClick = { selectedTitle = it },Modifier.weight(1f))
            }

            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            // Display selected section content
            when (selectedTitle) {
                "Booked classes" -> {
                    BookedClassesSection(navController)
                }
                "Progress Check" -> {
                    ProgressCheckSection()
                }
                "Job Suggestions" -> {
                    JobSuggestionsSection()
                }
            }
        }
    }
}

@Composable
fun BookedClassesSection(navController: NavController) {
    Text(
        "Booked Classes (0)",
        style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(8.dp))

    Text("You do not have any classes booked")

    Spacer(modifier = Modifier.height(8.dp))

    Button(
        onClick = { /* Book session */ },
        colors = ButtonDefaults.buttonColors()
    ) {
        Text("+ Book a Session")
    }
    Spacer(modifier = Modifier.height(8.dp))

    Text(
        "Your Past Classes (9)",
        style = MaterialTheme.typography.bodyLarge
    )
    Spacer(modifier = Modifier.height(8.dp))

    LazyColumn {
        items(listOf(
            ClassInfo("AI Project", "Tuesday 03 Sep 2024, 18:00 - 19:00",  "Prince Albert"),
            ClassInfo("Android Development Workshop", "Monday 01 Sep 2024, 14:00 - 15:00",  "Jane Doe")
        )) { classInfo ->
            ClassCard(
                title = classInfo.title,
                dateTime = classInfo.dateTime,
                mentor = classInfo.mentor
            )
        }
    }
}

@Composable
fun ProgressCheckSection() {
    LinearProgressIndicator(
        progress = 0.1f,
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(vertical = 16.dp),
    )
    Text("2 out of 5 milestones completed", fontSize = 14.sp)

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        "Achieve a score of 70% or higher to earn your badge and certificate for completion of the project",
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(16.dp))

    HorizontalDivider(thickness = 2.dp, color = Color(0xFF004D40))

    Spacer(modifier = Modifier.height(16.dp))

    Text("In progress (0)", fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(8.dp))

    Text("You have no progress check available yet")

    Spacer(modifier = Modifier.height(16.dp))

    Text("Completed (0)", fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(8.dp))

    Text("You have not completed any progress check, with a score of 70% or higher yet")
}

@Composable
fun JobSuggestionsSection() {
    Text(
        text = "Job Suggestions Based on Your Profile",
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn {
        items(listOf(
            JobInfo("Junior AI Engineer", "TechCorp", "Remote", "Experience in AI/ML required"),
            JobInfo("Android Developer", "MobileSolutions", "On-site", "2+ years experience in Android development"),
            JobInfo("Data Scientist", "DataWiz", "Hybrid", "Expertise in Python, R, and SQL")
        )) { jobInfo ->
            JobCard(
                title = jobInfo.title,
                company = jobInfo.company,
                location = jobInfo.location,
                description = jobInfo.description
            )
        }
    }
}

// Reusable Job Card
@Composable
fun JobCard(title: String, company: String, location: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = company, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = location, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

data class JobInfo(val title: String, val company: String, val location: String, val description: String)


@Composable
fun NavigationCard(title: String, count: Int, onCardClick: (String) -> Unit,modifier: Modifier) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable {onCardClick(title)},
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, fontSize = 14.sp, textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$count",
            fontSize = 10.sp,
            modifier = Modifier
                .size(24.dp)
                .background(Color(0xFF80CBC4), shape = RoundedCornerShape(50))
                .padding(2.dp)
                .wrapContentSize(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
fun ClassCard(title: String, dateTime: String, mentor: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff1c3972)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Icon(painter = painterResource(id = R.drawable.ic_time),
                        contentDescription = "timing",
                        modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(dateTime, modifier = Modifier.padding(top = 2.dp))
                }
                Row {
                    Icon(Icons.Default.AccountCircle, "mentor",
                        modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(mentor, modifier = Modifier.padding(top = 2.dp))
                }
            }
        }
    }
}


//end region home page

//region search bar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(navController: NavController) {
    var searchValue by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    // Fetch all mentors and categories
    val mentors = MentorRepository.getAllMentors()
    val categories = mentors.map { it.profession }.distinct()

    // Filter mentors based on search value and selected category
    val filteredMentors = mentors.filter { mentor ->
        (searchValue.isEmpty() || mentor.name.contains(searchValue, ignoreCase = true)) &&
                (selectedCategory == null || mentor.profession == selectedCategory)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Search Mentors") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = searchValue,
                    onValueChange = { searchValue = it },
                    label = { Text("Search") },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(16.dp))
            }
                var expanded by remember { mutableStateOf(false) }
                Row(modifier = Modifier.padding(start = 295.dp,top = 8.dp)) {
                    TextButton(
                        onClick = { expanded = true }
                    ) {
                        Text(text = selectedCategory ?: "Category")
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "filter")
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.width(300.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text("All") },
                            onClick = {
                                selectedCategory = null
                                expanded = false
                            }
                        )
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category) },
                                onClick = {
                                    selectedCategory = category
                                    expanded = false
                                }
                            )
                        }
                    }
                }


            Spacer(modifier = Modifier.height(16.dp))

            Text("Suggested Mentors: ", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(filteredMentors) { mentor ->
                    PersonCard(navController = navController, mentor = mentor)
                }
            }
        }
    }
}




@Composable
fun PersonCard(navController: NavController, mentor: Mentor) {
    var rating by remember { mutableStateOf(mentor.rating) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
            .clickable { navController.navigate("booking_screen/${mentor.id}") },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xff1c3972), RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = mentor.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff1c3972),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mentor.profession,
                    fontSize = 14.sp,
                    color = Color(0xff1c3972)
                )
                Spacer(modifier = Modifier.height(8.dp))
                StarRatingBar(
                    maxStars = 5,
                    rating = rating,
                    onRatingChanged = {
                        rating = it
                    }
                )
            }
        }
    }
}


@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (0.5f * density).dp

    Row(
        modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected) Color(0xFFFFC700) else Color(0x20FFFFFF)
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier
                    .size(16.dp)
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                        }
                    )
                    .width(starSize).height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}
//end region search bar

//region booking screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Booking(mentor: Mentor, navController: NavController) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Book a Session") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Month and Year header with navigation buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { currentMonth = currentMonth.minusMonths(1) }) {
                    Text("<")
                }

                Text(
                    text = "${currentMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentMonth.year}",
                    style = MaterialTheme.typography.headlineMedium
                )

                TextButton(onClick = { currentMonth = currentMonth.plusMonths(1) }) {
                    Text(">")
                }
            }

            // Weekdays header (starting from Sunday)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val daysOfWeek = listOf(
                    DayOfWeek.SUNDAY,
                    DayOfWeek.MONDAY,
                    DayOfWeek.TUESDAY,
                    DayOfWeek.WEDNESDAY,
                    DayOfWeek.THURSDAY,
                    DayOfWeek.FRIDAY,
                    DayOfWeek.SATURDAY
                )

                daysOfWeek.forEach { dayOfWeek ->
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            // Days of the month
            val firstDayOfMonth = currentMonth.atDay(1)
            val firstDayOfWeekIndex = (firstDayOfMonth.dayOfWeek.value % 7) // Adjusting for week start
            val daysInMonth = currentMonth.lengthOfMonth()

            // Create a list of days with placeholders at the start and end
            val calendarItems = buildList {
                // Add empty slots for days before the 1st of the month
                addAll(List(firstDayOfWeekIndex) { null })
                // Add days of the current month
                addAll((1..daysInMonth).map { day -> currentMonth.atDay(day) })
                // Add empty slots for days after the last day of the month to fill the last row
                val trailingEmptySlots = 7 - (size % 7)
                if (trailingEmptySlots < 7) {
                    addAll(List(trailingEmptySlots) { null })
                }
            }

            LazyColumn {
                items(calendarItems.chunked(7)) { week ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        week.forEach { date ->
                            if (date != null) {
                                val isAvailable = mentor.available_dates.containsKey(date.toString())
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            color = if (isAvailable) Color.Blue else Color.Gray,
                                            shape = CircleShape
                                        )
                                        .clickable(enabled = isAvailable) {
                                            selectedDate.value = date
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = date.dayOfMonth.toString(),
                                        color = Color.White
                                    )
                                }
                            } else {
                                // Empty box for placeholder days
                                Box(modifier = Modifier.size(40.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display available time slots for the selected date
            selectedDate.value?.let { selected ->
                val availableTimes = mentor.available_dates[selected.toString()]
                if (availableTimes != null) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Available Times on $selected",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    availableTimes.forEach { time ->
                        Button(
                            onClick = { /* Handle booking logic */ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text(text = time)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Chat with mentor button
            Row {
                IconButton(onClick = { /*TODO: Chat with the mentor*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_chat),
                        contentDescription = "chat",
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "To Chat with the mentor",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}



data class Mentor(
    val id: String,
    val name: String,
    val profession: String,
    val rating: Float,
    var available_dates: Map<String, List<String>> // Key: Date string (yyyy-MM-dd), Value: List of available times
)


data class ClassInfo(
    val title: String,
    val dateTime: String,
    val mentor: String
)
//end region booking screen

//region profile screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentProfile(navController: NavController) {
    // Track edit states
    var isEditingPersonalInfo by remember { mutableStateOf(false) }
    var isEditingAcademicInfo by remember { mutableStateOf(false) }

    // Personal information (editable)
    var name by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("johndoe@gmail.com") }
    var phone by remember { mutableStateOf("+1 234 567 890") }
    var gender by remember { mutableStateOf("Male") }
    var dob by remember { mutableStateOf("01/01/1995") }

    // Academic details (editable)
    var tenthGrade by remember { mutableStateOf("77.2%") }
    var twelfthGrade by remember { mutableStateOf("91.8%") }
    var diploma by remember { mutableStateOf("Not Applicable") }
    var undergraduate by remember { mutableStateOf("8.6 CGPA") }
    var postgraduate by remember { mutableStateOf("Not Applicable") }
    var backlogHistory by remember { mutableStateOf("No") }
    var currentBacklogs by remember { mutableStateOf("0") }
    var workExperience by remember { mutableStateOf("Not Applicable") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Student Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Profile Photo
            Image(
                painter = painterResource(id = R.drawable.ic_profile), // replace with actual photo resource
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Personal Information Section
            SectionHeader(title = "Personal Information", isEditing = isEditingPersonalInfo) {
                isEditingPersonalInfo = !isEditingPersonalInfo
            }
            if (isEditingPersonalInfo) {
                EditableProfileDetailRow(label = "Name", value = name, onValueChange = { name = it })
                EditableProfileDetailRow(label = "Email", value = email, onValueChange = { email = it })
                EditableProfileDetailRow(label = "Phone", value = phone, onValueChange = { phone = it })
                EditableProfileDetailRow(label = "Gender", value = gender, onValueChange = { gender = it })
                EditableProfileDetailRow(label = "DOB", value = dob, onValueChange = { dob = it })
            } else {
                ProfileDetailRow(label = "Name", value = name)
                ProfileDetailRow(label = "Email", value = email)
                ProfileDetailRow(label = "Phone", value = phone)
                ProfileDetailRow(label = "Gender", value = gender)
                ProfileDetailRow(label = "DOB", value = dob)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Academic Details Section
            SectionHeader(title = "Academic Details", isEditing = isEditingAcademicInfo) {
                isEditingAcademicInfo = !isEditingAcademicInfo
            }
            if (isEditingAcademicInfo) {
                EditableProfileDetailRow(label = "10th", value = tenthGrade, onValueChange = { tenthGrade = it })
                EditableProfileDetailRow(label = "12th", value = twelfthGrade, onValueChange = { twelfthGrade = it })
                EditableProfileDetailRow(label = "Diploma", value = diploma, onValueChange = { diploma = it })
                EditableProfileDetailRow(label = "Under Graduate", value = undergraduate, onValueChange = { undergraduate = it })
                EditableProfileDetailRow(label = "Post Graduate", value = postgraduate, onValueChange = { postgraduate = it })
                EditableProfileDetailRow(label = "Backlogs History", value = backlogHistory, onValueChange = { backlogHistory = it })
                EditableProfileDetailRow(label = "Current Backlogs", value = currentBacklogs, onValueChange = { currentBacklogs = it })
                EditableProfileDetailRow(label = "Work Experience", value = workExperience, onValueChange = { workExperience = it })
            } else {
                ProfileDetailRow(label = "10th", value = tenthGrade)
                ProfileDetailRow(label = "12th", value = twelfthGrade)
                ProfileDetailRow(label = "Diploma", value = diploma)
                ProfileDetailRow(label = "Under Graduate", value = undergraduate)
                ProfileDetailRow(label = "Post Graduate", value = postgraduate)
                ProfileDetailRow(label = "Backlogs History", value = backlogHistory)
                ProfileDetailRow(label = "Current Backlogs", value = currentBacklogs)
                ProfileDetailRow(label = "Work Experience", value = workExperience)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mentor Information Section
            Text(
                text = "Mentor Information",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            ProfileDetailRow(label = "Mentor Name", value = "XYZ")
            ProfileDetailRow(label = "Mentor Email", value = "xyz@gmail.com")
        }
    }
}

// Composable for Section Header with Edit button
@Composable
fun SectionHeader(title: String, isEditing: Boolean, onEditClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { onEditClick() }) {
            Icon(
                imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                contentDescription = if (isEditing) "Save" else "Edit"
            )
        }
    }
}

// Profile detail row for editable fields
@Composable
fun EditableProfileDetailRow(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f)
        )
    }
}

// Profile detail row for non-editable fields
@Composable
fun ProfileDetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = "$label: ",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
    }
}




//end region profile screen

@Composable
fun StudentBottomBar(navController: NavController) {
    BottomAppBar(
        content = {
            Column(verticalArrangement = Arrangement.Center){
            IconButton(onClick = { navController.navigate("student_dashboard") }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
            Text("Home")
            }
            Spacer(modifier = Modifier.weight(1f, true))
            Column(verticalArrangement = Arrangement.Center) {
                IconButton(onClick = { navController.navigate("upcoming_screen") }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Upcoming Classes")
                }
                Text("Upcoming")
            }
            Spacer(modifier = Modifier.weight(1f, true))
            Column (verticalArrangement = Arrangement.Center){
            IconButton(onClick = { navController.navigate("search_screen") }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
            Text("Search")
            }
            Spacer(modifier = Modifier.weight(1f, true))
            Column(verticalArrangement = Arrangement.Center){
            IconButton(onClick = { navController.navigate("student_profile") }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
            }
            Text("Profile")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Sample(){
    StudentDashboard(navController = rememberNavController())
}