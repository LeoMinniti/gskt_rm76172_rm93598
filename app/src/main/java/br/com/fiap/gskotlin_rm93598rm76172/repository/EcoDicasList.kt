package br.com.fiap.gskotlin_rm93598rm76172.repository

import br.com.fiap.gskotlin_rm93598rm76172.model.EcoDicas

fun getAllEcoDicas(): List<EcoDicas> {
    return listOf(
        EcoDicas(
            id = 1,
            title = "Dica Energia Solar",
            description = "Placas de energia solar não emitem poluição e não agridem o ambiente"
        ),
        EcoDicas(
            id = 2,
            title = "Dica Energia Eólica",
            description = "Usando helices conseguimos utilizar o vento e gerar energia"
        ),
        EcoDicas(
            id = 3,
            title = "Dica Energia Hidrelétrica",
            description = "Aproveitamos o movimento da água, pricipalmente quedas d'agua e geramos energia limpa"
        ),
    )
}

fun getEcoDicasListsByDescription(description: String): List<EcoDicas> {
    return getAllEcoDicas().filter {
        it.description.startsWith(prefix = description, ignoreCase = true)
    }
}

fun getEcoDicasListsByTitle(title: String): List<EcoDicas> {
    return getAllEcoDicas().filter {
        it.title.startsWith(prefix = title, ignoreCase = true)
    }
}