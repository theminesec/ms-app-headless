package com.msa.headless.ui.view.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.msa.headless.R

/**
 * @author eric.song
 * @since 2024/7/31 11:45
 */
@Composable
fun DrawerContent() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val drawerWidth = screenWidth * 0.75f

    Box(
        modifier = Modifier
            .width(drawerWidth)
            .background(colorResource(id = R.color.brand_secondary))
            .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopDrawerContent()
            BottomDrawerContent()
        }
    }
}
@Composable
fun TopDrawerContent() {
    Column(modifier = Modifier.fillMaxWidth()) {
        // 菜单项
        NavigationDrawerItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ico_type_sale), contentDescription = null) },
            label = { Text(text = stringResource(id = R.string.drawer_sale)) },
            selected = false,
            onClick = {},
            modifier = Modifier
                .background(Color.Transparent) // 设置透明背景
                .clip(RoundedCornerShape(4.dp)), // 设置圆角
        )
        NavigationDrawerItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ico_biz_settlement), contentDescription = null) },
            label = { Text(text = stringResource(id = R.string.drawer_settlement)) },
            selected = false,
            onClick = {},
            modifier = Modifier
                .background(Color.Transparent) // 设置透明背景
                .clip(RoundedCornerShape(4.dp)) // 设置圆角
        )
        NavigationDrawerItem(
            icon = { Icon(painter = painterResource(id = R.drawable.ico_biz_history), contentDescription = null) },
            label = { Text(text = stringResource(id = R.string.drawer_history)) },
            selected = false,
            onClick = {},
            modifier = Modifier
                .background(Color.Transparent) // 设置透明背景
                .clip(RoundedCornerShape(4.dp)) // 设置圆角
        )
    }
}

@Composable
fun BottomDrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Divider(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.space_md))
                    .padding(vertical = dimensionResource(id = R.dimen.space_xl)),
                color = MaterialTheme.colorScheme.onBackground
            )

            NavigationDrawerItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ico_control_settings), contentDescription = null) },
                label = { Text(text = stringResource(id = R.string.drawer_settings)) },
                selected = false,
                onClick = {},
                modifier = Modifier.padding(bottom = 16.dp)
            )
            NavigationDrawerItem(
                icon = { Icon(painter = painterResource(id = R.drawable.ico_indicator_help), contentDescription = null) },
                label = { Text(text = stringResource(id = R.string.drawer_support)) },
                selected = false,
                onClick = {}
            )
        }

        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ico_biz_merchant),
                contentDescription = null,
                modifier = Modifier.padding(start = 24.dp)
            )
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.merchant_name),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = stringResource(id = R.string.merchant_id),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.72f),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}