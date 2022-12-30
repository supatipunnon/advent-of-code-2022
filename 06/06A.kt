import java.io.File

fun main()
{
    val file = File("input")
    // read first line of file
    val line = file.readLines()[0]

    // convert to char array
    val chars = line.toCharArray()
    var left = 0;
    var right = 0;
  
    while (right - left != 3){
        right++;
        for (i in left..(right-1)){
            // println("i: $i, left: $left, right: $right")
            if (chars[i] == chars[right]){
                left = i + 1;
                break;
            }
        }
    }
    println(right+1);

}