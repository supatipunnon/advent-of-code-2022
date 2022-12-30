package `08B`
import java.io.File

fun main()
{
    val file = File("input");
    // read string from file and split it into lines
    val input = file.readText()
    val matrix = splitIntoMatrix(input);
    var maxLeft = Array(matrix.size) {0}
    var maxRight = Array(matrix.size) {0}
    var maxTop = Array(matrix[0].size) {0}
    var maxBottom = Array(matrix[0].size) {0}


    // check if tree see from top or left
    for (r in 0 until matrix.size)
    {
        for (c in 0 until matrix[0].size)
        {
            if (matrix[r][c] > matrix[r][maxLeft[r]]){
                maxLeft[r] = c;
            }
            if (matrix[r][c] > matrix[maxTop[c]][c]){
                maxTop[c] = r;
            }
            if (matrix[r][c] >= matrix[r][maxRight[r]]){
                maxRight[r] = c;
            }
            if (matrix[r][c] >= matrix[maxBottom[c]][c]){
                maxBottom[c] = r;
            }
        }
    }

    // function for calulate score
    fun score(r: Int, c: Int): Int{
        var left = 0
        var right = 0
        for (i in c - 1 downTo 0){
            if (matrix[r][i] < matrix[r][c]){
                left++;
            }else{
                left++;
                break;
            }
        }
        for (i in c + 1 until matrix[0].size){
            if (matrix[r][i] < matrix[r][c]){
                right++;
            }
            else{
                right++;
                break;
            }
        }
        var top = 0;
        var bottom = 0;
        for (i in r - 1 downTo 0){
            if (matrix[i][c] < matrix[r][c]){
                top++;
            }else{
                top++;
                break;
            }
        }
        for (i in r + 1 until matrix.size){
            if (matrix[i][c] < matrix[r][c]){
                bottom++;
            }else{
                bottom++;
                break;
            }
        }

        return left * right * top * bottom;
    }

    var maxScore = 0;
    maxLeft.forEachIndexed { r, c ->
        val score = score(r, c);
        if (score > maxScore){
            maxScore = score;
        }
    }
    maxRight.forEachIndexed { r, c ->
        val score = score(r, c);
        if (score > maxScore){
            maxScore = score;
        }
    }
    maxTop.forEachIndexed { c, r ->
        val score = score(r, c);
        if (score > maxScore){
            maxScore = score;
        }
    }
    maxBottom.forEachIndexed { c, r ->
        val score = score(r, c);
        if (score > maxScore){
            maxScore = score;
        }
    }
    println(maxScore);

}


// code form chat OpenAI
fun splitIntoMatrix(input: String): Array<Array<Int>> {
    // Split the input string into individual lines
    val lines = input.split("\n").map { it.trim() }.filter { it.isNotEmpty() }

    // Use the map function to parse each line as an array of integers
    val matrix = lines.map { line ->
        line.map { ch -> ch.toString().toInt() }.toTypedArray()
    }.toTypedArray()

    return matrix
} 