package one.digitalinnovation.contatos.feature.contato

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import one.digitalinnovation.contatos.R
import one.digitalinnovation.contatos.application.ContatoApplication
import one.digitalinnovation.contatos.bases.BaseActivity
import one.digitalinnovation.contatos.feature.listacontatos.model.ContatosVO
import kotlinx.android.synthetic.main.activity_contato.*
import kotlinx.android.synthetic.main.activity_contato.toolBar

class ContatoActivity : BaseActivity(){

    private var idContato: Int = +1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contato)
        setupToolBar(toolBar,"Contato", true)
        setupContato()
        btnSalvarConato.setOnClickListener { onClickSalvarContato()}
    }

    private fun setupContato() {
        idContato = intent.getIntExtra("index", -1)
        if (idContato == -1){
            btnExcluirContato.visiblity = View.GONE
            return
        }
        progress.visibility = View.VISIBLE
        Thread (Runnable {
                Thread.sleep(1500)
                var lista = ContatoApplication.instace.helperDB?.buscarContatos("$idContato", true)  ?: return@Runnable
                var contato = lista.getOrNull (0) ?: return@Runnable
                runOnUiThread {
                    etNome.setText(contato.nome)
                    etTelefone.setText(contato.telefone)
                    progress.visibility = View.GONE
                }
        }).start()
    }

    private fun onClickSalvarContato(){
        val nome = etNome.text.toString()
        val telefone = etTelefone.text.toString()
        val contato = ContatosVO(
            idContato,
            nome,
            telefone
        )
        progress.visibility = View.VISIBLE
        Thread(Runnable {
            Thread.sleep(1500)
            if(idContato == -1){
                ContatoApplication.instance.helperDB?.salvarContato(contato)
            }else{
                ContatoApplication.instance.helperDB?.updateContato(contato)
            }
            runOnUiThread {
                progress.visibility = View.GONE
                finish()
            }
        }).start()
    }

    fun onClickExcluirContato(view: View) {
        if(idContato > -1){
            progress.visibility = View.VISIBLE
            Thread(Runnable {
                Thread.sleep(1500)
                ContatoApplication.instance.helperDB?.deletarCoontato(idContato)
                runOnUiThread {
                    progress.visibility = View.GONE
                    finish()
                }
            }).start()
        }
    }


}