package com.msa.headless.ui.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.msa.headless.R
import com.msa.headless.ui.view.ui.theme.MsappheadlessTheme
import com.msa.headless.viewmodel.ActivationViewModel

class ActivationActivity : ComponentActivity() {

    private val viewModel: ActivationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MsappheadlessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ActivationScreen(viewModel) {
                        startActivity(Intent(this, PaymentActivity::class.java))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivationScreen(viewModel: ActivationViewModel,onActivationSuccess: () -> Unit) {
    val isShowLoading by viewModel.isShowLoading.collectAsState()
    var isLoginEnabled by remember { mutableStateOf(false) }
    val errorMessage by viewModel.errorMessage.collectAsState()
    var isError by remember { mutableStateOf(false) }
    val activationSuccess by viewModel.activationSuccess.collectAsState()
    val context = LocalContext.current

    val activationCode = remember {
        mutableStateOf(TextFieldValue(
            text = "",
        ))
    }

    LaunchedEffect(activationSuccess) {
        if (activationSuccess) {
            onActivationSuccess()
        }
    }
    
    LaunchedEffect(errorMessage) {
        isError = errorMessage != null
    }

    if (isShowLoading) {
        LoadingIndicator()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), // replace @dimen/space_md
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Spacer(modifier = Modifier.height(32.dp)) // replace @dimen/space_lg

            Image(
                painter = painterResource(id = R.drawable.logo_full),
                contentDescription = null,
                modifier = Modifier.wrapContentHeight()
            )
            Spacer(modifier = Modifier.height(16.dp)) // replace @dimen/space_md

            Text(
                text = stringResource(id = R.string.activation_title),
                style = MaterialTheme.typography.headlineSmall // replace ?activationScreenTitle
            )

            Spacer(modifier = Modifier.height(48.dp)) // replace @dimen/space_3xl
            Text(
                text = stringResource(id = R.string.activation_code_prompt),
                style = MaterialTheme.typography.bodyMedium, // replace ?textAppearanceLabelMedium
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp)) // replace @dimen/space_2xs

            TextField(
                value = activationCode.value, // replace with your value
                onValueChange = {newCode ->
                    val formattedText = formatActivationCode(newCode.text)
                    activationCode.value = TextFieldValue(
                        text = formattedText,
                        selection = TextRange(formattedText.length)
                    )
                    // Calculate new cursor position
                    isLoginEnabled = formattedText.length == 14
                    isError = false
                }, // replace with your onValueChange
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.active_hint),
                        color = colorResource(id = R.color.basic_muted_foreground)
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                isError = isError,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_entry_qr_merchant_present),
                        contentDescription = "Menu",
                        tint = colorResource(id = R.color.brand_primary)
                    )
                }
            )
            if (isError && errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp)) // replace @dimen/space_md

            Text(
                text = stringResource(id = R.string.activation_code_guidance),
                style = MaterialTheme.typography.bodyMedium, // replace ?textAppearanceBodyMedium
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp)) // replace @dimen/space_sm

            Text(
                text = stringResource(id = R.string.label_no_activation_code),
                style = MaterialTheme.typography.bodyMedium, // replace ?textAppearanceBodyMedium
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp)) // replace @dimen/space_sm

            Text(
                text = stringResource(id = R.string.link_text_here),
                color = colorResource(id = R.color.brand_primary),
                modifier = Modifier
                    .clickable {
                        // Handle contact support click
                        val url = "https://minesecsoftpos.com/form/activation-code"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        context.startActivity(intent)
                    }
                    .padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp)) // add some space before the loading indicator

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally) {
                ActivationButton(
                    onClick = { viewModel.activation(activationCode.value.text.replace("-","")) },
                    enabled = isLoginEnabled,
                    containerColor = if (isLoginEnabled) colorResource(id = R.color.brand_primary) else colorResource(
                        id = R.color.basic_muted_foreground
                    ),
                    contentColor = Color.White
                )
                Text(
                    text = stringResource(id = R.string.powered_by_),
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.basic_muted_foreground)
                )
            }
        }
    }
}


@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier
            .size(72.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(colorResource(id = R.color.primary_quarter), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(56.dp),
                strokeWidth = 4.dp,
                color = Color.Green
            )
        }
    }
}

@Composable
fun ActivationButton(
    onClick: () -> Unit,
    enabled: Boolean,
    containerColor: Color,
    contentColor: Color
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.activation_check_in),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
    }
}


private fun formatActivationCode(input: String): String {
    val digitsOnly = input.replace("-", "")
    val formatted = buildString {
        var count = 0
        for (i in digitsOnly.indices) {
            if (count == 4 && i < digitsOnly.length) {
                append("-")
                count = 0
            }
            append(digitsOnly[i])
            count++
        }
    }
    return formatted.take(14) // Ensure maximum length of 14 characters
}
fun calculateNewCursorPosition(oldText: String, newText: String, oldSelection: Int): Int {
    var newSelection = oldSelection
    var offset = 0

    for (i in oldText.indices) {
        if (i >= newText.length) break
        if (oldText[i] != newText[i]) {
            if (newText[i] == '-') {
                offset++
            } else {
                offset--
            }
        }
        newSelection += offset
        offset = 0
    }

    return newSelection.coerceAtMost(newText.length)
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MsappheadlessTheme {
//        ActivationScreen()
    }
}