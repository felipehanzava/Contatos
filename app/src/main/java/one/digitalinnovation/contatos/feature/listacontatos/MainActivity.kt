package one.digitalinnovation.contatos.feature.listacontatos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import one.digitalinnovation.contatos.application.ContatoApplication
import one.digitalinnovation.contatos.bases.BaseActivity
import one.digitalinnovation.contatos.feature.contato.ContatoActivity
import one.digitalinnovation.contatos.feature.listacontatos.adapter.ContatoAdapter
import one.digitalinnovation.contatos.feature.listacontatos.model.ContatosVO
import java.lang.Exception
import kotlinx.android.synthetic.main.activity_main.*
import one.digitalinnovation.contatos.R

class MainActivity : BaseActivity() {

    private var adapter: ContatoAdapter? = null

    override  fun onCreate(salvedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolBar(toolBar = "Lista de contatos", false)
        setupListView()
        setupOnClicks()
    }
    private fun setupOnClicks(){
        fab.setOnClickListener { onClickAdd() }
        ivBuscar.setOnClickListener { onClickBuscar() }
    }

    private fun setupListView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        onClickBuscar()
    }

    private fun onClickAdd(){
        val intent = Intent(this, ContatoActivity::class.java)
        startActivity(intent)
    }

    private fun onClickItemRecyclerView(index: Int){
        val intent = Intent(this,ContatoActivity::class.java)
        intent.putExtra("index", index)
        startActivity(intent)
    }

    private fun onClickBuscar(){
        val busca = etBuscar.text.toString()
        progress.visibility = View.VISIBLE
        Thread(Runnable {
            Thread.sleep(1500)
            var listaFiltrada: List<ContatosVO> = mutableListOf()
            try {
                listaFiltrada = ContatoApplication.instance.helperDB?.buscarContatos(busca) ?: mutableListOf()
            }catch (ex: Exception){
                ex.printStackTrace()
            }
            runOnUiThread {
                adapter = ContatoAdapter(this,listaFiltrada) {onClickItemRecyclerView(it)}
                recyclerView.adapter = adapter
                progress.visibility = View.GONE
                Toast.makeText(this,"Buscando por $busca", Toast.LENGTH_SHORT).show()
            }
        }).start()
    }

}

}
