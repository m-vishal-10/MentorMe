package com.vishal.mentorme

import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun MentorDashboard(navController: NavController) {

    var selectedTitle by remember { mutableStateOf("Booked classes") }

    Scaffold(
        bottomBar = {
            MentorBottomBar(navController)
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
                    text = "Mentor",
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NavigationCard("Booked classes", count = 0, onCardClick = { selectedTitle = it })
                NavigationCard("Progress Check", count = 0, onCardClick = { selectedTitle = it })
            }

            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            when (selectedTitle) {
                "Booked classes" -> {
                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn {
                        items(listOf(
                            ClassInfo("AI Project", "Tuesday 03 Sep 2024, 18:00 - 19:00",  "Prince Albert"),
                            ClassInfo("Android Development Workshop", "Monday 01 Sep 2024, 14:00 - 15:00", "Jane Doe"),
                            // Add more class info here
                        )) { classInfo ->
                            ClassCard(
                                title = classInfo.title,
                                dateTime = classInfo.dateTime,
                                mentor = classInfo.mentor
                            )
                        }
                    }

                }
                "Progress Check" -> {
                    LinearProgressIndicator(
                        progress = { 0.1f },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(vertical = 16.dp),
                    )
                    Text("2 out of 5 milestone completed", fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Achieve a score of 70% or higher to earn your badge and certificate for completion of project", fontWeight = FontWeight.Bold)

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

            }

        }
    }
}
//region upcoming screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpcomingClasses(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Upcoming Classes") },
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
        ) {
            Text(
                text = "Click on time to join video session",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn {
                items(
                    listOf(
                        ClassInfo("AI Project", "Tuesday 03 Sep 2024, 18:00 - 19:00", "Prince Albert"),
                        ClassInfo("Android Development Workshop", "Monday 01 Sep 2024, 14:00 - 15:00", "Jane Doe")
                    )
                ) { classInfo ->
                    UpcomingClassCard(
                        title = classInfo.title,
                        dateTime = classInfo.dateTime,
                        mentor = classInfo.mentor
                    )
                }
            }
        }
    }
}

@Composable
fun UpcomingClassCard(title: String, dateTime: String, mentor: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
            .clickable{"TODO:- join the video session "},
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
                    Icon(Icons.Filled.Face,
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

// end region upcoming class screen

//region manage screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageScreen(mentor: Mentor, navController: NavController) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    val selectedDate = remember { mutableStateOf<LocalDate?>(null) }
    val addedTimes = remember { mutableStateOf(mutableMapOf<String, MutableList<String>>()) }
    val openDialog = remember { mutableStateOf(false) }
    var currentDateForDialog by remember { mutableStateOf<LocalDate?>(null) }
    var time by remember { mutableStateOf("") }

    // Upcoming classes data
    val upcomingClasses = listOf(
        ClassInfo("AI Project", "Tuesday 03 Sep 2024, 18:00 - 19:00", "Prince Albert"),
        ClassInfo("Android Development Workshop", "Monday 01 Sep 2024, 14:00 - 15:00", "Jane Doe")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Manage Calendar") },
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
            // Upcoming Classes Section
            Text(
                text = "Upcoming Classes",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(upcomingClasses) { classInfo ->
                    UpcomingClassCard(
                        title = classInfo.title,
                        dateTime = classInfo.dateTime,
                        mentor = classInfo.mentor,
                        onAccept = { /* Handle accept logic */ },
                        onReject = { /* Handle reject logic */ }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

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

            LazyColumn(
                modifier = Modifier.weight(2f)
            ) {
                items(calendarItems.chunked(7)) { week ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        week.forEach { date ->
                            if (date != null) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(
                                            color = Color.Gray,
                                            shape = CircleShape
                                        )
                                        .clickable {
                                            selectedDate.value = date
                                            currentDateForDialog = date
                                            openDialog.value = true
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

            // Display the added times for the selected date below the calendar
            selectedDate.value?.let { selected ->
                val dateKey = selected.toString()
                val timesForDate = addedTimes.value[dateKey]

                if (!timesForDate.isNullOrEmpty()) {
                    Text(
                        text = "Added Times for $dateKey:",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    LazyColumn {
                        items(timesForDate) { time ->
                            Text(
                                text = time,
                                modifier = Modifier.padding(4.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }

    // Show time dialog if needed
    if (openDialog.value && currentDateForDialog != null) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Add Time for ${currentDateForDialog}") },
            text = {
                Column {
                    Text("Enter time:")
                    OutlinedTextField(
                        value = time,
                        onValueChange = { time = it },
                        placeholder = { Text("e.g., 10:00 AM") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (time.isNotEmpty() && currentDateForDialog != null) {
                        val dateKey = currentDateForDialog.toString()
                        if (!addedTimes.value.containsKey(dateKey)) {
                            addedTimes.value[dateKey] = mutableListOf()
                        }
                        addedTimes.value[dateKey]?.add(time)
                        openDialog.value = false
                    }
                }) {
                    Text("Add Time")
                }
            },
            dismissButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun UpcomingClassCard(
    title: String,
    dateTime: String,
    mentor: String,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = dateTime, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Mentor: $mentor", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = onAccept) {
                    Text("Accept")
                }
                Button(onClick = onReject) {
                    Text("Reject")
                }
            }
        }
    }
}


//end region manage screen


//region profile screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MentorProfile(navController: NavController) {
    // Track edit states
    var isEditingPersonalInfo by remember { mutableStateOf(false) }
    var isEditingProfessionalInfo by remember { mutableStateOf(false) }

    // Personal information (editable)
    var name by remember { mutableStateOf("Dr. Sarah Connor") }
    var email by remember { mutableStateOf("sarahconnor@gmail.com") }
    var phone by remember { mutableStateOf("+1 123 456 7890") }
    var gender by remember { mutableStateOf("Female") }
    var dob by remember { mutableStateOf("05/10/1980") }

    // Professional details (editable)
    var expertise by remember { mutableStateOf("Artificial Intelligence, Robotics") }
    var yearsOfExperience by remember { mutableStateOf("15 years") }
    var currentOrganization by remember { mutableStateOf("Cyberdyne Systems") }
    var designation by remember { mutableStateOf("Chief AI Scientist") }

    // Mentee details (editable)
    var menteeName by remember { mutableStateOf("John Doe") }
    var menteeEmail by remember { mutableStateOf("johndoe@gmail.com") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Mentor Profile") },
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
                contentDescription = "Mentor Profile Photo",
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

            // Professional Details Section
            SectionHeader(title = "Professional Details", isEditing = isEditingProfessionalInfo) {
                isEditingProfessionalInfo = !isEditingProfessionalInfo
            }
            if (isEditingProfessionalInfo) {
                EditableProfileDetailRow(label = "Expertise", value = expertise, onValueChange = { expertise = it })
                EditableProfileDetailRow(label = "Years of Experience", value = yearsOfExperience, onValueChange = { yearsOfExperience = it })
                EditableProfileDetailRow(label = "Current Organization", value = currentOrganization, onValueChange = { currentOrganization = it })
                EditableProfileDetailRow(label = "Designation", value = designation, onValueChange = { designation = it })
            } else {
                ProfileDetailRow(label = "Expertise", value = expertise)
                ProfileDetailRow(label = "Years of Experience", value = yearsOfExperience)
                ProfileDetailRow(label = "Current Organization", value = currentOrganization)
                ProfileDetailRow(label = "Designation", value = designation)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mentee Information Section
            Text(
                text = "Mentee Information",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            ProfileDetailRow(label = "Mentee Name", value = menteeName)
            ProfileDetailRow(label = "Mentee Email", value = menteeEmail)
        }
    }
}


//end region mentor profile screen
@Composable
fun MentorBottomBar(navController: NavController) {
    BottomAppBar(
        content = {
            Column(verticalArrangement = Arrangement.Center){
                IconButton(onClick = { navController.navigate("mentor_dashboard") }) {
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
                IconButton(onClick = { navController.navigate("manage_screen") }) {
                    Icon(Icons.Filled.Create, contentDescription = "Manage Classes")
                }
                Text("Manage")
            }
            Spacer(modifier = Modifier.weight(1f, true))
            Column(verticalArrangement = Arrangement.Center){
                IconButton(onClick = { navController.navigate("mentor_profile") }) {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                }
                Text("Profile")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun Sample2(){
    MentorDashboard(navController = rememberNavController())
}