package com.vishal.mentorme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vishal.mentorme.ui.theme.MentorMeTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MentorMeTheme {
                NavigationGraph()
            }
        }
    }
}
@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    val userType = remember { mutableStateOf("") }

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController)
        }
        composable("user_selection_screen") {
            UserSelectionScreen(navController, userType)
        }
        composable("login_screen"){
            LoginForm(navController, userType.value)
        }
        composable("sign_up_screen"){
            SignUpScreen(navController)
        }
        composable("student_dashboard"){
            StudentDashboard(navController)
        }
        composable("mentor_dashboard"){
            MentorDashboard(navController)
        }
        composable("search_screen"){
            SearchPage(navController)
        }
        composable(
            route = "booking_screen/{mentorId}",
            arguments = listOf(navArgument("mentorId") { type = NavType.StringType })
        ) { backStackEntry ->
            val mentorId = backStackEntry.arguments?.getString("mentorId")
            // Fetch the mentor data based on mentorId here or use a placeholder for demo
            val mentor = fetchMentorById(mentorId) // Implement this function to retrieve mentor
            Booking(mentor = mentor,navController)
        }
        composable("student_profile") {
            StudentProfile(navController)
        }
        composable("upcoming_screen"){
            UpcomingClasses(navController)
        }
        composable("mentor_profile"){
            MentorProfile(navController)
        }
        var sample = Mentor(
            id = "11",
            name = "Ava Johnson",
            profession = "Data Science",
            rating = 4.8f,
            available_dates = mapOf(
                "2024-10-02" to listOf("10:00 AM", "3:00 PM"),
                "2024-10-04" to listOf("9:00 AM", "1:00 PM")
            )
        )

        composable("manage_screen"){
            ManageScreen(sample,navController)
        }
    }
}
fun fetchMentorById(mentorId: String?): Mentor {
    return mentorId?.let { MentorRepository.getMentorById(it) } ?: Mentor(
        id = "1",
        name = "Default Mentor",
        profession = "Default Profession",
        rating = 0f,
        available_dates = emptyMap()
    )
}


@Composable
fun SplashScreen(navController: NavHostController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo"
        )
    }
    LaunchedEffect(Unit) {
        delay(1500)
        navController.navigate("user_selection_screen") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }
}

@Composable
fun UserSelectionScreen(navController: NavController, userType: MutableState<String>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Student Button
            Button(
                onClick = {
                    userType.value = "student"
                    navController.navigate("login_screen") {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(
                    text = "Student",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            // Mentor Button
            Button(
                onClick = {
                    userType.value = "mentor"
                    navController.navigate("login_screen") {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                elevation = ButtonDefaults.buttonElevation(8.dp)
            ) {
                Text(
                    text = "Mentor",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}


@Composable
fun LoginForm(navController: NavController, userType: String) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState() // Scroll state for vertical scrolling

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)  // Enable vertical scroll
            .padding(16.dp)   // Padding around the content
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center, // Start from top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login/Registration",
            fontStyle = FontStyle.Normal,
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onSurface,  // Text color adapting to theme
            modifier = Modifier.padding(16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            label = { Text("Email", color = MaterialTheme.colorScheme.onSurface) }, // Adapting color
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface // Text color
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            label = { Text("Enter Your Password", color = MaterialTheme.colorScheme.onSurface) }, // Adapting color
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface // Text color
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                when (userType) {
                    "student" -> navController.navigate("student_dashboard")
                    "mentor" -> navController.navigate("mentor_dashboard")
                }
            },
            enabled = true,
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Login", color = MaterialTheme.colorScheme.onPrimary)
        }

        TextButton(onClick = {
            navController.navigate("sign_up_screen"){
                popUpTo("login_screen"){inclusive = true}
            }
        }) {
            Text(
                "Don't have an account? Sign Up",
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }

    var selectedCountry by remember { mutableStateOf("") }
    var expandedCountry by remember { mutableStateOf(false) }
    val countries = listOf("United States", "Canada", "United Kingdom", "India", "Australia")

    var selectedCity by remember { mutableStateOf("") }
    var expandedCity by remember { mutableStateOf(false) }
    val cities = listOf("New York", "Toronto", "London", "Mumbai", "Sydney")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "mentorme",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Get Started!",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Name, Email, and Contact Fields remain unchanged
        OutlinedTextField(
            value = name,
            onValueChange = {name = it},
            label = { Text("Enter Name", color = MaterialTheme.colorScheme.onSurface) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text("Enter Email", color = MaterialTheme.colorScheme.onSurface) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )

        OutlinedTextField(
            value = contact,
            onValueChange = {contact = it},
            label = { Text("Enter Contact Number", color = MaterialTheme.colorScheme.onSurface) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )

        // Dropdown for Countries
        ExposedDropdownMenuBox(
            expanded = expandedCountry,
            onExpandedChange = { expandedCountry = !expandedCountry }
        ) {
            OutlinedTextField(
                value = selectedCountry,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select Country", color = MaterialTheme.colorScheme.onSurface) },
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    focusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )

            DropdownMenu(
                expanded = expandedCountry,
                onDismissRequest = { expandedCountry = false }
            ) {
                countries.forEach { country ->
                    DropdownMenuItem(
                        text = { Text(text = country) },
                        onClick = {
                            selectedCountry = country
                            expandedCountry = false
                        }
                    )
                }
            }
        }

        // Dropdown for Cities
        ExposedDropdownMenuBox(
            expanded = expandedCity,
            onExpandedChange = { expandedCity = !expandedCity }
        ) {
            OutlinedTextField(
                value = selectedCity,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select City", color = MaterialTheme.colorScheme.onSurface) },
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    focusedTextColor = MaterialTheme.colorScheme.onSurface
                )
            )

            DropdownMenu(
                expanded = expandedCity,
                onDismissRequest = { expandedCity = false }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(text = city) },
                        onClick = {
                            selectedCity = city
                            expandedCity = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Enter Password", color = MaterialTheme.colorScheme.onSurface) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 8.dp),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                focusedTextColor = MaterialTheme.colorScheme.onSurface
            )
        )

        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Sign Up", color = MaterialTheme.colorScheme.onPrimary)
        }

        TextButton(onClick = { navController.navigate("login_screen") }) {
            Text(
                "Already have an account? Log in",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}