package co.tiagoaguiar.evernotekt.model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter

/**
 *
 * Setembro, 24 2019
 * @author suporte@moonjava.com.br (Tiago Aguiar).
 */
data class Note(
    var id: Int = 0,
    var title: String? = null,
    var desc: String? = null,
    var date: String? = null, // 12/12/2120 -> Fev 2020
    var body: String? = null
){
    val createdDate: String
        get() {
            val locale = Locale("pt", "BR")

            return try{
                val date: Date? = SimpleDateFormat("dd/MM/yyyy", locale ).parse(date ?: "")
                return SimpleDateFormat("MMM yyyy", locale).format(date).capitalize();
            }catch (e: ParseException){
                ""
            }

        }
}