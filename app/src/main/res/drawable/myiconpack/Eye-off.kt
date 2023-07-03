package myiconpack

import MyIconPack
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MyIconPack.`Eye-off`: ImageVector
    get() {
        if (`_eye-off` != null) {
            return `_eye-off`!!
        }
        `_eye-off` = Builder(name = "Eye-off", defaultWidth = 32.0.dp, defaultHeight = 32.0.dp,
                viewportWidth = 32.0f, viewportHeight = 32.0f).apply {
            path(fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(31.299f, 15.25f)
                arcToRelative(17.66f, 17.66f, 0.0f, false, false, -6.16f, -6.268f)
                lineToRelative(1.922f, -1.921f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, false, -2.122f, -2.122f)
                lineToRelative(-2.65f, 2.65f)
                arcTo(17.583f, 17.583f, 0.0f, false, false, 16.0f, 6.414f)
                arcTo(17.718f, 17.718f, 0.0f, false, false, 0.701f, 15.25f)
                arcToRelative(1.497f, 1.497f, 0.0f, false, false, 0.0f, 1.5f)
                arcToRelative(17.66f, 17.66f, 0.0f, false, false, 6.16f, 6.268f)
                lineTo(4.939f, 24.94f)
                arcToRelative(1.5f, 1.5f, 0.0f, true, false, 2.122f, 2.122f)
                lineToRelative(2.65f, -2.65f)
                arcTo(17.583f, 17.583f, 0.0f, false, false, 16.0f, 25.586f)
                curveToRelative(6.29f, 0.0f, 12.151f, -3.386f, 15.299f, -8.836f)
                arcToRelative(1.497f, 1.497f, 0.0f, false, false, 0.0f, -1.5f)
                close()
                moveTo(3.763f, 16.0f)
                arcTo(14.712f, 14.712f, 0.0f, false, true, 16.0f, 9.414f)
                curveToRelative(1.342f, 0.0f, 2.658f, 0.192f, 3.92f, 0.544f)
                lineToRelative(-0.917f, 0.918f)
                arcTo(5.9f, 5.9f, 0.0f, false, false, 16.0f, 10.054f)
                arcTo(5.954f, 5.954f, 0.0f, false, false, 10.054f, 16.0f)
                arcToRelative(5.9f, 5.9f, 0.0f, false, false, 0.822f, 3.003f)
                lineToRelative(-1.824f, 1.824f)
                arcTo(14.697f, 14.697f, 0.0f, false, true, 3.762f, 16.0f)
                close()
                moveTo(18.946f, 16.0f)
                arcTo(2.95f, 2.95f, 0.0f, false, true, 16.0f, 18.946f)
                curveToRelative(-0.251f, 0.0f, -0.491f, -0.041f, -0.724f, -0.1f)
                lineToRelative(3.57f, -3.57f)
                curveToRelative(0.059f, 0.233f, 0.1f, 0.473f, 0.1f, 0.724f)
                close()
                moveTo(13.054f, 16.0f)
                arcTo(2.95f, 2.95f, 0.0f, false, true, 16.0f, 13.054f)
                curveToRelative(0.251f, 0.0f, 0.491f, 0.041f, 0.724f, 0.1f)
                lineToRelative(-3.57f, 3.57f)
                curveToRelative(-0.059f, -0.233f, -0.1f, -0.473f, -0.1f, -0.724f)
                close()
                moveTo(16.0f, 22.586f)
                curveToRelative(-1.342f, 0.0f, -2.658f, -0.192f, -3.92f, -0.544f)
                lineToRelative(0.917f, -0.918f)
                arcToRelative(5.9f, 5.9f, 0.0f, false, false, 3.003f, 0.822f)
                arcTo(5.954f, 5.954f, 0.0f, false, false, 21.946f, 16.0f)
                arcToRelative(5.9f, 5.9f, 0.0f, false, false, -0.822f, -3.003f)
                lineToRelative(1.824f, -1.824f)
                arcTo(14.697f, 14.697f, 0.0f, false, true, 28.238f, 16.0f)
                arcTo(14.712f, 14.712f, 0.0f, false, true, 16.0f, 22.586f)
                close()
            }
        }
        .build()
        return `_eye-off`!!
    }

private var `_eye-off`: ImageVector? = null
