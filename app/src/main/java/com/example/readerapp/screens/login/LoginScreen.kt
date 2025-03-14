package com.example.readerapp.screens.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.readerapp.navigation.HomeScreenRoute
import com.example.readerapp.navigation.SignUpScreenRoute
import org.checkerframework.checker.units.qual.Current

@Composable
fun LoginScreen(navController: NavHostController) {

    val viewModel : LoginViewModel = hiltViewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val uiState = viewModel.state.collectAsState()

    val context = LocalContext.current
    LaunchedEffect(key1 = uiState.value){
        when(uiState.value){
            SignInStates.Success ->{
                navController.navigate(HomeScreenRoute)
            }
            SignInStates.Error->{
                email = ""
                password = ""
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color(0xFFf5cb58)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "Log In",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(60.dp))
                Spacer(modifier = Modifier.weight(1f))
                Card(
                    modifier = Modifier
                        .fillMaxSize(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                {
                    Column(
                        modifier = Modifier.padding(horizontal = 40.dp, vertical = 30.dp),
                    ){
                        Text(
                            text = "Welcome",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Please log in to access your account and continue your reading journey.",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Text(
                            text = "Email",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text(text = "Enter your Email") },
                            modifier = Modifier
                                .width(322.dp)
                                .background(Color(0xFFf3e9b5))

                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = { Text(text = "Enter your Password") },
                            modifier = Modifier
                                .width(322.dp)
                                .background(Color(0xFFf3e9b5)),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        TextButton(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = {
                                navController.navigate(SignUpScreenRoute)
                            },
                        ) {
                            Text(text = "Don't have an account? Sign Up")
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        if(uiState.value == SignInStates.Loading){
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ){
                                CircularProgressIndicator()
                            }

                        }else if(uiState.value == SignInStates.Loading){
                            Text(
                                text = "Error",
                                color = Color(0xFFe95322)
                            )
                        }

                        Button(
                            onClick = {
                                viewModel.singIn(email, password)
                            },
                            modifier = Modifier
                                .height(45.dp)
                                .width(207.dp)
                                .align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(Color(0xFFe95322)),
                            enabled = email.isNotEmpty() && password.isNotEmpty() && uiState.value == SignInStates.Nothing || uiState.value == SignInStates.Error
                        ){
                            Text(text = "Log In")
                        }


                    }

                }

            }

        }
    }
}