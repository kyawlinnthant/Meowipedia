package com.everest.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun NavigationInstructor(
    instructor: Channel<NavigationIntent>,
    controller: NavHostController
) {
    val activity = LocalContext.current as? Activity
    LaunchedEffect(
        key1 = activity,
        key2 = instructor,
        key3 = controller
    ) {
        instructor.receiveAsFlow().collect {
            if (activity?.isFinishing == true) return@collect
            when (it) {
                is NavigationIntent.Back ->
                    it.route?.let { route ->
                        controller.popBackStack(
                            route = route,
                            inclusive = it.inclusive
                        )
                    } ?: kotlin.run {
                        controller.popBackStack()
                    }

                is NavigationIntent.To ->
                    controller.navigate(it.route) {
                        launchSingleTop = it.isSingleTop
                        it.popupToRoute?.let { route ->
                            popUpTo(route) {
                                inclusive = it.inclusive
                            }
                        }
                    }
            }
        }
    }
}
