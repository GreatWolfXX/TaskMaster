package com.greatwolf.ui.component

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.greatwolf.ui.R
import com.greatwolf.ui.theme.Error100
import com.greatwolf.ui.theme.Neutral100
import com.greatwolf.ui.theme.Neutral300
import com.greatwolf.ui.theme.Neutral400
import com.greatwolf.ui.theme.Neutral500
import com.greatwolf.ui.theme.Neutral600
import com.greatwolf.ui.theme.Neutral700
import com.greatwolf.ui.theme.Primary200
import com.greatwolf.ui.theme.Primary50
import com.greatwolf.ui.theme.Typography

enum class CustomTextFieldType {
    STANDARD,
    PASSWORD,
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    type: CustomTextFieldType = CustomTextFieldType.STANDARD,
    leadingIcon: ImageVector? = null,
    label: String = "",
    placeholder: String = "",
    hint: String = "",
    value: String,
    onValueChanged: (String) -> Unit,
    isError: Boolean = false,
    showHint: Boolean = false,
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    val typeKeyboardOptions = when (type) {
        CustomTextFieldType.PASSWORD -> KeyboardOptions(keyboardType = KeyboardType.Password)
        else -> KeyboardOptions.Default
    }
    val keyboardOptions = typeKeyboardOptions.copy(imeAction = imeAction)

    var passwordVisibility by remember { mutableStateOf(false) }
    val visualTransformTypeCase = when (type) {
        CustomTextFieldType.PASSWORD -> if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        else -> VisualTransformation.None
    }

    val shape = RoundedCornerShape(10.dp)

    val borderColor = if (isFocused) {
        Primary50
    } else {
        if (isSystemInDarkTheme()) Neutral600 else Neutral100
    }

    val borderShadowColorAlpha by animateFloatAsState(if (isFocused) 0.4f else 0f)

    Column(
        modifier = Modifier.animateContentSize()
    ) {
        if(label.isNotBlank()) {
            Text(
                text = label,
                style = Typography.labelMedium,
                color = if (isSystemInDarkTheme()) Neutral100 else Neutral700
            )
            Spacer(modifier = Modifier.size(6.dp))
        }
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Primary200.copy(borderShadowColorAlpha)
                )
                .padding(1.dp)
        ) {
            BasicTextField(
                modifier = modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = borderColor,
                        shape = shape
                    ),
                value = value,
                onValueChange = onValueChanged,
                enabled = enabled,
                textStyle = Typography.bodySmall.copy(
                    color = if (isSystemInDarkTheme()) Neutral300 else Neutral700
                ),
                keyboardOptions = keyboardOptions,
                singleLine = true,
                visualTransformation = visualTransformTypeCase,
                cursorBrush = SolidColor(
                    value = if (isSystemInDarkTheme()) Neutral300 else Neutral500
                ),
                interactionSource = interactionSource
            ) { innerTextField ->
                CustomTextFieldDecoration(
                    type = type,
                    leadingIcon = leadingIcon,
                    placeholder = placeholder,
                    showPlaceholder = !isFocused && value.isEmpty(),
                    passwordVisibility = passwordVisibility,
                    onPasswordVisibilityClick = {
                        passwordVisibility = !passwordVisibility
                    },
                    innerTextField = innerTextField
                )
            }
        }
        Hint(
            hint = hint,
            isError = isError,
            showHint = showHint
        )
    }
}

@Composable
private fun CustomTextFieldDecoration(
    type: CustomTextFieldType,
    leadingIcon: ImageVector? = null,
    placeholder: String,
    showPlaceholder: Boolean,
    passwordVisibility: Boolean,
    onPasswordVisibilityClick: () -> Unit,
    innerTextField: @Composable () -> Unit
) {
    val passwordVisibilityIcon = if (passwordVisibility) {
        ImageVector.vectorResource(R.drawable.ic_eye_slash)
    } else {
        ImageVector.vectorResource(R.drawable.ic_eye)
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = leadingIcon,
                    tint = if (isSystemInDarkTheme()) Neutral300 else Neutral400,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
            Box {
                if (showPlaceholder) {
                    Text(
                        text = placeholder,
                        style = Typography.bodySmall,
                        color = if (isSystemInDarkTheme()) Neutral300 else Neutral400
                    )
                }
                innerTextField()
            }
        }
        if (type == CustomTextFieldType.PASSWORD) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onPasswordVisibilityClick),
                imageVector = passwordVisibilityIcon,
                tint = if (isSystemInDarkTheme()) Neutral300 else Neutral400,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun Hint(
    hint: String,
    isError: Boolean,
    showHint: Boolean
) {
    val colorHint = if (isError) {
        Error100
    } else {
        if (isSystemInDarkTheme()) Neutral300 else Neutral400
    }

    if (showHint || isError) {
        Spacer(modifier = Modifier.size(6.dp))
        Row {
            if (isError) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_info_circle),
                    tint = Error100,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(4.dp))
            }
            Text(
                text = hint,
                style = Typography.bodySmall,
                color = colorHint
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun CustomTextFieldPreview() {
    CustomTextField(
        type = CustomTextFieldType.PASSWORD,
        label = "Label",
        placeholder = "Placeholder",
        hint = "This is a hint text to help user",
        leadingIcon = ImageVector.vectorResource(R.drawable.ic_eye),
        value = "",
        onValueChanged = { },
        enabled = true,
        showHint = false,
        isError = true
    )
}