fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")


    val xs = (0..3).flatMap { value -> (0..3).map { value } }.toList()
    val ys = (0..3).flatMap { (0..3).map { it } }.toList()

    val elements = xs.zip(ys).shuffled()

    println(xs)
    println(ys)
    println(elements)
}