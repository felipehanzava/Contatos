package one.digitalinnovation.contatos.singleton

import one.digitalinnovation.contatos.feature.listacontatos.model.ContatosVO

object ContatoSingleton {
    var lista: MutableList<ContatosVO> = mutableListOf()
}