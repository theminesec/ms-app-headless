package com.msa.headless.ui.view.ui


import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.msa.headless.R
import com.msa.headless.configs.ApplicationConfigStore
import com.msa.headless.model.StartingNavigator
import com.msa.headless.ui.TAG
import com.msa.headless.ui.theme.MsappheadlessTheme
import com.msa.headless.viewmodel.StartingViewModel
import com.theminesec.sdk.headless.model.WrappedResult

class MainActivity : ComponentActivity() {

    private val viewModel: StartingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MsappheadlessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoadingScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen(viewModel: StartingViewModel) {
    val context = LocalContext.current
    var showProgress by remember { mutableStateOf(true) }
    val startingNavigator by viewModel.startingNavigatorEvent.observeAsState()
    //val sdkInitRespLiveData = (context.applicationContext as APP).sdkInitRespLiveData.observeAsState()
    val sdkInitRespLiveData = viewModel.sdkMpocInitRespLiveData.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.initHeadless(context.applicationContext as Application)
    }

    sdkInitRespLiveData.value?.let { result ->
        when (result) {
            is WrappedResult.Success -> {
                Log.d(TAG, "initSoftPos:$result")
                LaunchedEffect(Unit) {
                    val applicationConfigStore = ApplicationConfigStore()
                    applicationConfigStore.setSdkId(result.value.sdkId)
                    viewModel.initSettings(context.applicationContext)
                }
            }

            is WrappedResult.Failure -> {
                Log.e(TAG, "initSoftPos:$result")
                // Handle error case
                ErrorDialog(errorMessage = "Init SoftPOS Failure:${result.code}-${result.message}") {
                    if (context is Activity) {
                        context.finish()
                    }
                }
            }
        }
    }

    startingNavigator?.let { event ->
        showProgress = false
        when (event) {
            is StartingNavigator.ToActivation -> {
                Log.d(TAG, "ToActivation->")
                InitSettingsCompletionAnimation {
                    context.startActivity(Intent(context, ActivationActivity::class.java))
                    if (context is Activity) {
                        context.finish()
                    }
                }
            }

            is StartingNavigator.ToPayment -> {
                Log.d(TAG, "ToPayment->")
                InitSettingsCompletionAnimation {
                    context.startActivity(Intent(context, PaymentActivity::class.java))
                    if (context is Activity) {
                        context.finish()
                    }
                }
            }

            is StartingNavigator.ToError -> {
                Log.d(TAG, "ToError->")
                ErrorDialog(errorMessage = event.message) {
                    if (context is Activity) {
                        context.finish()
                    }
                }
            }
        }

    }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        StartLoading(showProgress)
        // Footer copyright
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            FooterCopyright()
        }
    }
}

@Composable
fun InitSettingsCompletionAnimation(onCompletion: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        contentAlignment = Alignment.Center) {
        Box(modifier = Modifier.size(128.dp)) {
            AnimatedCircle(colorResource(id = R.color.brand_primary)) {
                Log.d(TAG, "Animation Completed GOTO Activity Now...")
                onCompletion()
            }
        }
    }
}


@Composable
fun StartLoading(showProgress: Boolean) {
    Box(
        modifier = Modifier
            .size(160.dp)
            .background(colorResource(id = R.color.primary_quarter), CircleShape),
        contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .size(128.dp)
                .background(colorResource(id = R.color.primary_quarter), CircleShape),
            contentAlignment = Alignment.Center) {

            // logoSquare
            Image(
                painter = painterResource(id = R.drawable.logo_square),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )

            if (showProgress) {
                // progressIndicator
                CircularProgressIndicator(
                    modifier = Modifier.size(128.dp),
                    strokeWidth = 6.dp,
                    color = colorResource(id = R.color.brand_primary)
                )
            }
        }
    }
}

@Composable
fun FooterCopyright() {
    Column {
        Text(
            text = stringResource(id = R.string.powered_by_),
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(id = R.color.basic_muted_foreground)
        )
    }
}

@Composable
fun ErrorDialog(errorMessage: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(text = "Error")
        },
        text = {
            Text(text = errorMessage)
        },
        confirmButton = {
            Button(
                onClick = {
                    onDismiss()
                    // Optionally handle confirm action here
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Composable
fun AnimatedCircle(color: Color, onAnimationComplete: () -> Unit) {
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1200)
        )
        onAnimationComplete()
    }

    Canvas(modifier = Modifier.size(128.dp)) {
        val strokeWidth = 8.dp.toPx()
        drawArc(
            color = color,
            startAngle = 0f,
            sweepAngle = animatedProgress.value * 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
    }
}











