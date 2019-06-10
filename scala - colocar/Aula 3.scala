
import sys.process._

"wget -O ingredients.tsv https://drive.google.com/uc?export=download&id=1oTjCLXJZjciOmpweGsirlreu9zcrm6Py"!

var data = scala.io.Source.fromFile("ingredients.tsv").getLines
var lines = data.toArray

var dados = Array.ofDim[String](10000,16)
var dado = new Array[String](16)

for(i <- 1 until 10000){
    dado = lines(i).split("\t").toArray
    for(j <- 0 until dado.length-1){
       if(dado(j).isEmpty){ 
           dado(j) = null
       }
       dados(i)(j) = dado(j)
    }
}

var marcas = new Array[String](10000)
var marcas_originais = new Array[String](10000)

for(i<-0 until dados.length){
    marcas(i) = dados(i)(2)
    marcas_originais(i) = dados(i)(2)
}
marcas = marcas.distinct
marcas = marcas.filter(_!=null)

println("\n\n As marcas são: " + marcas.toList)
println("\n\n A quantidade de marcas é: " +  marcas.length)

var most = ""
var n_most = 0
var least = ""
var n_least = 0
var n_current = 0

marcas_originais = marcas_originais.filter(_!=null)

// for(i<-0 until marcas.length){
    
//     n_current = marcas_originais.count(_ == marcas_originais(i))
//     if(i == 0){
//         n_least = n_current
//         least = marcas_originais(0)
//     }
//     if(n_current > n_most){
//         n_most = n_current
//         most = marcas_originais(i)
//     }
//     if(n_current < n_least){
//         n_least = n_current
//         least = marcas_originais(i)
//     }
// }

val group = marcas_originais.groupBy(item => item)

print(group.map { item =>
    (item._1,item._2.size)
}.toList)

// val counts = data.map(word =>(word, 1))
// // from word -> num to num -> word
// // then sortBy num of occurrence in descending order 
// val mostCommon = counts.map(p => (p._2, p._1)).sortByKey(false, 1)
// mostCommon.take(5)
