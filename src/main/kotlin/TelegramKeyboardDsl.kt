import org.telegram.telegrambots.meta.api.objects.LoginUrl
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo

@TelegramKeyboardDslMarker
open class TelegramKeyboardDsl {
    private val rows: MutableList<InlineKeyboardRow> = mutableListOf()

    fun build(): List<InlineKeyboardRow> {
        return rows
    }

    fun row(init: TelegramKeyboardRowDsl.() -> Unit) {
        rows.add(TelegramKeyboardRowDsl().apply(init).build())
    }

    fun singleColumnRows(init: TelegramKeyboardRowDsl.() -> Unit) {
        rows.addAll(TelegramKeyboardRowDsl().apply(init).buildColumn())
    }

    companion object {
        fun keyboard(init: TelegramKeyboardDsl.() -> Unit): List<InlineKeyboardRow> {
            return TelegramKeyboardDsl().apply(init).build()
        }
    }
}

@TelegramKeyboardDslMarker
class TelegramKeyboardRowDsl {
    private val buttons: MutableList<InlineKeyboardButton> = mutableListOf()

    fun callbackButton(text: String, callbackData: String) {
        buttons.add(InlineKeyboardButton(text).apply {
            this.callbackData = callbackData
        })
    }

    fun urlButton(text: String, url: String) {
        buttons.add(InlineKeyboardButton(text).apply {
            this.url = url
        })
    }

    fun webAppButton(text: String, webAppUrl: String) {
        buttons.add(InlineKeyboardButton(text).apply {
            this.webApp = WebAppInfo(webAppUrl)
        })
    }

    fun loginButton(text: String, loginUrl: String) {
        buttons.add(InlineKeyboardButton(text).apply {
            this.loginUrl = LoginUrl(loginUrl)
        })
    }

    fun build(): InlineKeyboardRow {
        return InlineKeyboardRow(buttons)
    }

    fun buildColumn(): List<InlineKeyboardRow> {
        return buttons.map { button ->
            InlineKeyboardRow(listOf(button))
        }

    }
}

fun main() {
    TelegramKeyboardDsl.keyboard {
        row {
            callbackButton("Button 1", "button1")
            urlButton("Button 2", "https://google.com")
        }
    }
}

@DslMarker
annotation class TelegramKeyboardDslMarker