package `10B`
import java.io.File
import java.util.HashMap;

fun main()
{
    var regisX = 1;
    var cycle = 0;
    var ctr = 1;

    fun sigsig(){
        while(cycle > 0){
            if(ctr >= regisX && ctr <= regisX+2){
                print("#")
            }
            else{
                print(".")
            }
            ctr++;
            cycle--;
            if (ctr > 40){
                println();
                ctr = 1;
            }
        }
    }
    
    val file = File("input");
    val lines = file.readLines();
    for (line in lines){
        // split line with space with index 0 is direction and index 1 is length
        val split = line.split(" ");
        when(split[0]){
            "addx" -> {
                cycle = 2;
                sigsig();
                regisX += split[1].toInt();
            }

            "noop" -> {
                cycle=1;
                sigsig();
            }
        }
    }
}