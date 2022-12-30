package `09A`
import java.io.File
import java.util.HashMap;

fun main()
{
    var head = Pair(0, 0);
    var tail = Pair(0, 0);
    
    fun moveHead(direction: Char){
        val oldHead = head;
        when(direction){
            'L' -> head = Pair(head.first, head.second - 1);
            'R' -> head = Pair(head.first, head.second + 1);
            'U' -> head = Pair(head.first - 1, head.second);
            'D' -> head = Pair(head.first + 1, head.second);
        }

        if (Math.abs(head.first - tail.first) > 1 || Math.abs(head.second - tail.second) > 1){
            tail = Pair(oldHead.first, oldHead.second);
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
            visited[tail] = true;
        }
    }
    println(visited.size);
}