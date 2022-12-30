package `09B`
import java.io.File
import java.util.HashMap;

fun main()
{
    // create array of pair (0,0) with size 9
    val tail = 10;
    var arr = Array(tail) { Pair(0, 0) };
    
    fun moveHead(direction: Char){
        when(direction){
            'L' -> arr[0] = Pair(arr[0].first, arr[0].second - 1);
            'R' -> arr[0] = Pair(arr[0].first, arr[0].second + 1);
            'U' -> arr[0] = Pair(arr[0].first - 1, arr[0].second);
            'D' -> arr[0] = Pair(arr[0].first + 1, arr[0].second);
        }
    }

    fun moveTail(){
        for(i in 1..tail-1){
            var y = arr[i-1].first - arr[i].first;
            var x = arr[i-1].second - arr[i].second;
            var dy = Math.abs(y);
            var dx = Math.abs(x);
            if(dy<=1 && dx<=1){
                break;
            }
            val first = if (y == 0) arr[i].first else if (y > 0) arr[i].first + 1 else arr[i].first - 1;
            val second = if (x == 0) arr[i].second else if (x > 0) arr[i].second + 1 else arr[i].second - 1;

            
            arr[i] = Pair(first, second);  
        }
    }
    
    val file = File("input");
    val lines = file.readLines();
    val visited = HashMap<Pair<Int, Int>, Boolean>();
    for (line in lines){
        // split line with space with index 0 is direction and index 1 is length
        val split = line.split(" ");
        val direction = split[0][0];
        val length = split[1].toInt();
        for (i in 0 until length){
            moveHead(direction);
            moveTail();
            visited[arr[tail-1]] = true;
        }
    }
    println(visited.size);
}