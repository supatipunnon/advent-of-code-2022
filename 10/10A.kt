package `10A`
import java.io.File
import java.util.HashMap;

fun main()
{
    var regisX = 1;
    var cycle = 0;
    var nextsignal = 20;
    var sum = 0;

    fun sigsig(){
        if (cycle >= nextsignal && cycle < 221){
            println("${nextsignal} * ${regisX} = ${regisX*nextsignal} ")
            sum += regisX*nextsignal;
            nextsignal+=40;
        } 
    }
    
    val file = File("input");
    val lines = file.readLines();
    for (line in lines){
        // split line with space with index 0 is direction and index 1 is length
        val split = line.split(" ");
        when(split[0]){
            "addx" -> {
                cycle += 2;
                sigsig();
                regisX += split[1].toInt();
            }

            "noop" -> {
                cycle++;
                sigsig();
            }
        }
    }
    println(sum);
}