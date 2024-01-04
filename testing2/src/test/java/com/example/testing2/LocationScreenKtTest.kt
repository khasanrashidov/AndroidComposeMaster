//package com.example.testing2
//
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import androidx.test.rule.GrantPermissionRule
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Assert.*
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
//class LocationScreenTest {
//
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    @get:Rule
//    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
//
//    @get:Rule
//    val grantPermissionRule: GrantPermissionRule =
//        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)
//
//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
//    // ... The rest of the test class remains unchanged
//}
//
//
//@Test
//fun locationScreen_isDisplayed_andFetchLocation() {
//    val fakeLocationRepository = FakeLocationRepository()
//    val locationViewModel = LocationViewModel(fakeLocationRepository)
//
//    composeTestRule.setContent {
//        LocationScreen(locationViewModel)
//    }
//
//    // Check if initial state is displayed correctly
//    composeTestRule.onNodeWithText("Location:").assertExists()
//    composeTestRule.onNodeWithText("Get Location").assertExists()
//
//    // Click on "Get Location" button
//    testCoroutineRule.runBlockingTest {
//        composeTestRule.onNodeWithText("Get Location").performClick()
//        // Fetch location and update the UI
//        locationViewModel.fetchLocation()
//
//        // Check if the location is displayed correctly
//        composeTestRule.onNodeWithText("Location: 51.5074, -0.1278").assertExists()
//    }
//}
//
