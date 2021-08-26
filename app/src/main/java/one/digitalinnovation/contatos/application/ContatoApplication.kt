package one.digitalinnovation.contatos.application

import android.app.Application

class ContatoApplication: Application() {

    var helperDB: HelperDB? = null
        private set

    companion object{
        lateinit var instace: ContatoApplication
    }

    override fun OnCreate(){
        super.onCreate()
        instance = this
        helperDB = HelperDB( this)
    }


}