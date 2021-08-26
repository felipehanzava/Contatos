package one.digitalinnovation.contatos.helpers

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper

class HelperDB (
    context: Context
): SQLiteOpenHelper(context, NOME_BANCO, null, VERSAO_ATUAL){

    companion object{
        private val NOME_BANCO = "contato.db"
        private val VERSAO_ATUAL = 2
    }
}