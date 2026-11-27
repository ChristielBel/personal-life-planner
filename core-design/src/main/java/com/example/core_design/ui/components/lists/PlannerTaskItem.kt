package com.example.core_design.ui.components.lists

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core_design.ui.components.inputs.PlannerCheckbox
import com.example.core_design.ui.icons.PlannerIcons
import com.example.localization.Strings

@Composable
fun PlannerTaskItem(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    completedLabel: String? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PlannerCheckbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )

        IconButton(onClick = onEdit) {
            Icon(
                imageVector = PlannerIcons.Edit,
                contentDescription = stringResource(Strings.Task.EDIT)
            )
        }

        IconButton(onClick = onDelete) {
            Icon(
                imageVector = PlannerIcons.Delete,
                contentDescription = stringResource(Strings.Common.DELETE)
            )
        }

        if (checked && completedLabel != null) {
            Text(
                text = completedLabel,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}
